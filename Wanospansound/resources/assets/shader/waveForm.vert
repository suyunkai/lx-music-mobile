#version 300 es
layout(location = 0) in vec2 vPosition;

out vec4 color;

uniform vec4 aColor;
uniform mat4 transform;

void main() {
    gl_Position = transform * vec4(vPosition,0.0f, 1.0f);
    color = aColor;
}