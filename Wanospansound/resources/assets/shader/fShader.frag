#version 300 es
precision mediump float;
out vec4 fragColor;
in vec3 color;
in vec2 TexCoord;
uniform sampler2D sampler;
void main() {
    fragColor = texture(sampler, TexCoord);
}