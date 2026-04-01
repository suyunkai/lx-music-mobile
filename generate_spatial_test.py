"""
Generate a 7.1 surround sound test WAV file.
Each speaker position plays a tone sequentially with a voice-like identifier.

Channel order (WAV standard for 7.1):
  0: Front Left (FL)
  1: Front Right (FR)
  2: Front Center (FC)
  3: LFE (Subwoofer)
  4: Back Left (BL)
  5: Back Right (BR)
  6: Side Left (SL)
  7: Side Right (SR)
"""

import struct
import math
import os

SAMPLE_RATE = 48000
BITS_PER_SAMPLE = 16
NUM_CHANNELS = 8
TONE_DURATION = 2.0      # seconds per channel
SILENCE_GAP = 0.5         # silence between tones
FADE_DURATION = 0.05      # fade in/out to avoid clicks

# Channel info: (name, base_frequency, harmonics)
# Using different frequencies so you can tell channels apart by ear
CHANNELS = [
    ("Front Left",    440,   [1.0, 0.5, 0.3]),        # A4
    ("Front Right",   494,   [1.0, 0.5, 0.3]),        # B4
    ("Front Center",  523,   [1.0, 0.6, 0.2]),        # C5
    ("LFE (Sub)",     80,    [1.0, 0.8]),              # Low bass
    ("Back Left",     587,   [1.0, 0.4, 0.25]),       # D5
    ("Back Right",    659,   [1.0, 0.4, 0.25]),       # E5
    ("Side Left",     698,   [1.0, 0.3, 0.2, 0.1]),   # F5
    ("Side Right",    784,   [1.0, 0.3, 0.2, 0.1]),   # G5
]

# Also add a "sweep" section at the end where sound rotates around all speakers
SWEEP_DURATION = 4.0  # seconds for full rotation
SWEEP_FREQ = 550      # Hz


def generate_tone(freq, duration, harmonics, sample_rate=SAMPLE_RATE):
    """Generate a tone with harmonics and fade in/out."""
    num_samples = int(duration * sample_rate)
    fade_samples = int(FADE_DURATION * sample_rate)
    samples = []
    for i in range(num_samples):
        t = i / sample_rate
        val = 0.0
        for h_idx, h_amp in enumerate(harmonics):
            val += h_amp * math.sin(2 * math.pi * freq * (h_idx + 1) * t)
        # Normalize
        max_amp = sum(harmonics)
        val /= max_amp
        # Fade in/out
        if i < fade_samples:
            val *= i / fade_samples
        elif i > num_samples - fade_samples:
            val *= (num_samples - i) / fade_samples
        # Amplitude
        val *= 0.7
        samples.append(val)
    return samples


def generate_click_pattern(channel_idx, sample_rate=SAMPLE_RATE):
    """Generate a distinct click pattern (number of beeps = channel index + 1) before the tone."""
    beep_duration = 0.08
    beep_gap = 0.08
    beep_freq = 1000
    num_beeps = channel_idx + 1
    total_duration = num_beeps * beep_duration + (num_beeps - 1) * beep_gap + 0.15
    num_samples = int(total_duration * sample_rate)
    samples = [0.0] * num_samples

    for b in range(num_beeps):
        start = int((b * (beep_duration + beep_gap)) * sample_rate)
        end = start + int(beep_duration * sample_rate)
        fade = int(0.005 * sample_rate)
        for i in range(start, min(end, num_samples)):
            t = (i - start) / sample_rate
            val = 0.6 * math.sin(2 * math.pi * beep_freq * t)
            # Fade
            pos = i - start
            length = end - start
            if pos < fade:
                val *= pos / fade
            elif pos > length - fade:
                val *= (length - pos) / fade
            samples[i] = val
    return samples


def float_to_int16(val):
    """Convert float [-1, 1] to int16."""
    val = max(-1.0, min(1.0, val))
    return int(val * 32767)


def write_wav(filename, all_frames, num_channels, sample_rate, bits_per_sample):
    """Write a multichannel WAV file with WAVE_FORMAT_EXTENSIBLE."""
    num_samples = len(all_frames) // num_channels
    bytes_per_sample = bits_per_sample // 8
    block_align = num_channels * bytes_per_sample
    byte_rate = sample_rate * block_align
    data_size = num_samples * block_align

    # Channel mask for 7.1: FL|FR|FC|LFE|BL|BR|SL|SR
    # FL=0x1, FR=0x2, FC=0x4, LFE=0x8, BL=0x10, BR=0x20, SL=0x200, SR=0x400
    channel_mask = 0x1 | 0x2 | 0x4 | 0x8 | 0x10 | 0x20 | 0x200 | 0x400  # = 0x63F

    # WAVE_FORMAT_EXTENSIBLE
    fmt_code = 0xFFFE
    cb_size = 22
    valid_bits = bits_per_sample
    # Sub-format GUID for PCM: 00000001-0000-0010-8000-00aa00389b71
    sub_format = (
        b'\x01\x00\x00\x00'
        b'\x00\x00'
        b'\x10\x00'
        b'\x80\x00'
        b'\x00\xaa\x00\x38\x9b\x71'
    )

    fmt_chunk_size = 18 + cb_size  # 40 bytes

    with open(filename, 'wb') as f:
        # RIFF header
        riff_size = 4 + (8 + fmt_chunk_size) + (8 + data_size)
        f.write(b'RIFF')
        f.write(struct.pack('<I', riff_size))
        f.write(b'WAVE')

        # fmt chunk
        f.write(b'fmt ')
        f.write(struct.pack('<I', fmt_chunk_size))
        f.write(struct.pack('<H', fmt_code))          # wFormatTag
        f.write(struct.pack('<H', num_channels))       # nChannels
        f.write(struct.pack('<I', sample_rate))        # nSamplesPerSec
        f.write(struct.pack('<I', byte_rate))          # nAvgBytesPerSec
        f.write(struct.pack('<H', block_align))        # nBlockAlign
        f.write(struct.pack('<H', bits_per_sample))    # wBitsPerSample
        f.write(struct.pack('<H', cb_size))            # cbSize
        f.write(struct.pack('<H', valid_bits))         # wValidBitsPerSample
        f.write(struct.pack('<I', channel_mask))       # dwChannelMask
        f.write(sub_format)                            # SubFormat GUID

        # data chunk
        f.write(b'data')
        f.write(struct.pack('<I', data_size))

        # Write interleaved samples
        for i in range(0, len(all_frames), num_channels):
            for ch in range(num_channels):
                val = float_to_int16(all_frames[i + ch])
                f.write(struct.pack('<h', val))


def main():
    print("Generating 7.1 spatial audio test file...")
    print(f"Sample rate: {SAMPLE_RATE} Hz, {BITS_PER_SAMPLE}-bit, {NUM_CHANNELS} channels")

    # Build audio: each section has one channel active
    # Frame = one sample across all channels
    all_frames = []

    # --- Part 1: Sequential channel test ---
    for ch_idx, (name, freq, harmonics) in enumerate(CHANNELS):
        print(f"  Channel {ch_idx}: {name} ({freq} Hz)")

        # Click pattern (identifies which speaker)
        clicks = generate_click_pattern(ch_idx)
        # Main tone
        tone = generate_tone(freq, TONE_DURATION, harmonics)
        # Combine: clicks + tone
        channel_audio = clicks + tone

        # Silence gap
        gap_samples = int(SILENCE_GAP * SAMPLE_RATE)

        # Write frames: only this channel is active
        for s in channel_audio:
            for c in range(NUM_CHANNELS):
                all_frames.append(s if c == ch_idx else 0.0)

        # Silence gap between channels
        for _ in range(gap_samples):
            for c in range(NUM_CHANNELS):
                all_frames.append(0.0)

    # --- Part 2: Circular sweep ---
    print("  Generating circular sweep...")
    sweep_samples = int(SWEEP_DURATION * SAMPLE_RATE)
    fade = int(FADE_DURATION * SAMPLE_RATE)

    # Sweep order: FL -> FC -> FR -> SR -> BR -> BL -> SL -> FL (clockwise)
    sweep_order = [0, 2, 1, 7, 5, 4, 6, 0]  # channel indices, clockwise

    for i in range(sweep_samples):
        t = i / SAMPLE_RATE
        # Position in the sweep (0.0 to len(sweep_order)-1)
        pos = (t / SWEEP_DURATION) * (len(sweep_order) - 1)
        seg = int(pos)
        frac = pos - seg
        if seg >= len(sweep_order) - 1:
            seg = len(sweep_order) - 2
            frac = 1.0

        ch_from = sweep_order[seg]
        ch_to = sweep_order[seg + 1]

        # Generate sine tone
        val = 0.6 * math.sin(2 * math.pi * SWEEP_FREQ * t)

        # Fade overall
        if i < fade:
            val *= i / fade
        elif i > sweep_samples - fade:
            val *= (sweep_samples - i) / fade

        # Crossfade between channels
        for c in range(NUM_CHANNELS):
            if c == ch_from and c == ch_to:
                all_frames.append(val)
            elif c == ch_from:
                all_frames.append(val * (1.0 - frac))
            elif c == ch_to:
                all_frames.append(val * frac)
            else:
                all_frames.append(0.0)

    # --- Part 3: All channels together (chord) ---
    print("  Generating all-channel chord...")
    chord_duration = 3.0
    chord_samples = int(chord_duration * SAMPLE_RATE)

    # Gap before chord
    for _ in range(int(0.5 * SAMPLE_RATE)):
        for c in range(NUM_CHANNELS):
            all_frames.append(0.0)

    for i in range(chord_samples):
        t = i / SAMPLE_RATE
        for ch_idx, (name, freq, harmonics) in enumerate(CHANNELS):
            val = 0.0
            for h_idx, h_amp in enumerate(harmonics):
                val += h_amp * math.sin(2 * math.pi * freq * (h_idx + 1) * t)
            max_amp = sum(harmonics)
            val /= max_amp
            # Fade
            if i < fade:
                val *= i / fade
            elif i > chord_samples - fade:
                val *= (chord_samples - i) / fade
            val *= 0.5  # Lower volume for all-channel
            all_frames.append(val)

    # Write WAV
    output_path = os.path.join(os.path.dirname(__file__), "spatial_test_7_1.wav")
    write_wav(output_path, all_frames, NUM_CHANNELS, SAMPLE_RATE, BITS_PER_SAMPLE)

    total_duration = len(all_frames) / NUM_CHANNELS / SAMPLE_RATE
    file_size = os.path.getsize(output_path)

    print(f"\nGenerated: {output_path}")
    print(f"Duration: {total_duration:.1f} seconds")
    print(f"File size: {file_size / 1024 / 1024:.1f} MB")
    print(f"\nTest structure:")
    print(f"  Part 1: Sequential channel test (each speaker plays in order)")
    for ch_idx, (name, freq, _) in enumerate(CHANNELS):
        start = ch_idx * (TONE_DURATION + SILENCE_GAP + 0.5)
        print(f"    ~{start:.1f}s - {name}: {ch_idx+1} beep(s) + {freq}Hz tone")
    print(f"  Part 2: Clockwise sweep (sound rotates around all speakers)")
    print(f"  Part 3: All channels play together (full surround chord)")


if __name__ == "__main__":
    main()
