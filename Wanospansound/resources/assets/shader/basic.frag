#version 300 es
precision lowp float;
out vec4 FragColor;

in vec4 color;
in vec2 TexCoord;
uniform sampler2D sampler;

void main() {
    FragColor = texture(sampler, TexCoord) * color;
}

