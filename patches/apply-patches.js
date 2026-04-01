/**
 * 自动应用补丁脚本
 * 在 npm install 后运行，将多声道 AudioProcessor 注入到 react-native-track-player 的 ExoPlayer 中
 *
 * 关键：react-native-track-player 是 library module，不能直接 import app module 的类。
 * 因此使用反射来获取 MultichannelAudioProcessor 实例。
 */
const fs = require('fs')
const path = require('path')

const TRACK_PLAYER_MUSIC_MANAGER = path.join(
  __dirname, '..', 'node_modules', 'react-native-track-player',
  'android', 'src', 'main', 'java', 'com', 'guichaguri', 'trackplayer', 'service', 'MusicManager.java'
)

function applyMultichannelPatch() {
  if (!fs.existsSync(TRACK_PLAYER_MUSIC_MANAGER)) {
    console.log('[patches] react-native-track-player not found, skipping multichannel patch')
    return
  }

  let content = fs.readFileSync(TRACK_PLAYER_MUSIC_MANAGER, 'utf-8')

  // Check if already patched
  if (content.includes('MultichannelAudioProcessor')) {
    console.log('[patches] Multichannel patch already applied')
    return
  }

  // 1. Add imports (only media3 classes, no app module imports)
  const importMarker = 'import com.guichaguri.trackplayer.service.player.LocalPlayback;'
  const newImports = `${importMarker}

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.DefaultAudioSink;`

  content = content.replace(importMarker, newImports)

  // 2. Replace DefaultRenderersFactory creation
  // Use reflection to get AudioProcessor from app module to avoid compile-time dependency
  const oldFactory = '        DefaultRenderersFactory renderersFactory = new DefaultRenderersFactory(service);'
  const newFactory = `        // Inject MultichannelAudioProcessor via reflection (library can't import app classes)
        AudioProcessor[] audioProcessors = getMultichannelProcessor();
        DefaultAudioSink audioSink = new DefaultAudioSink.Builder(service)
                .setAudioProcessors(audioProcessors)
                .build();

        DefaultRenderersFactory renderersFactory = new DefaultRenderersFactory(service) {
            @Override
            protected AudioSink buildAudioSink(
                    Context context,
                    boolean enableFloatOutput,
                    boolean enableAudioTrackPlaybackParams) {
                return audioSink;
            }
        };`

  content = content.replace(oldFactory, newFactory)

  // 3. Add the reflection helper method before the last closing brace of the class
  const helperMethod = `
    /**
     * 通过反射获取 MultichannelAudioProcessor 实例
     * 避免 library module 对 app module 的编译依赖
     */
    private static AudioProcessor[] getMultichannelProcessor() {
        try {
            Class<?> clazz = Class.forName("cn.toside.music.mobile.spatialAudio.SpatialAudioModule");
            java.lang.reflect.Method method = clazz.getMethod("getAudioProcessor");
            AudioProcessor processor = (AudioProcessor) method.invoke(null);
            if (processor != null) {
                Log.d(Utils.LOG, "MultichannelAudioProcessor loaded via reflection");
                return new AudioProcessor[]{processor};
            }
        } catch (Exception e) {
            Log.w(Utils.LOG, "MultichannelAudioProcessor not available: " + e.getMessage());
        }
        return new AudioProcessor[0];
    }
`

  // Insert before the last closing brace
  const lastBrace = content.lastIndexOf('}')
  content = content.substring(0, lastBrace) + helperMethod + '\n}'

  fs.writeFileSync(TRACK_PLAYER_MUSIC_MANAGER, content, 'utf-8')
  console.log('[patches] Multichannel AudioProcessor patch applied successfully (reflection mode)')
}

try {
  applyMultichannelPatch()
} catch (e) {
  console.error('[patches] Failed to apply multichannel patch:', e.message)
}
