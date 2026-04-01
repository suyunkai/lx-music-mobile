#version 300 es
precision lowp float;
out vec4 fragColor;
in vec2 TexCoord;
uniform sampler2D sampler;

void main() {
    fragColor = texture(sampler, TexCoord);
}
