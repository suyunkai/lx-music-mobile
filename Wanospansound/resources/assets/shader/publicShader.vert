#version 300 es
layout(location = 0) in vec3 vPosition;

out vec3 color;

uniform mat4 transform;

void main() {
    gl_Position = transform * vec4(vPosition, 1.0f);
}