#version 300 es
layout(location = 0) in vec3 vPosition;

out vec4 color;

uniform vec4 aColor;
uniform mat4 m;
uniform mat4 pv;

void main() {
    gl_Position = pv*m*vec4(vPosition, 1.0f);
    color = aColor;
}