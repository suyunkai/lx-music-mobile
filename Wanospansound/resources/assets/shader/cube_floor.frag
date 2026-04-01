#version 300 es
precision mediump float;
out vec4 fragColor;

in vec4 vColor;
in vec2 TexCoord;
uniform sampler2D ourTexture;

void main() {
    fragColor = texture(ourTexture, TexCoord)*vColor;
}