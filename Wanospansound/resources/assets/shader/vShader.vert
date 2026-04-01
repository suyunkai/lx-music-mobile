#version 300 es
layout(location = 0) in vec4 vPosition;
layout(location = 1) in vec3 vColor;
layout (location = 2) in vec2 aTexCoord;

uniform mat4 transform;
out vec3 color;
out vec2 TexCoord;

void main() {
    gl_Position = transform * vPosition;
    color = vColor;
    TexCoord = vec2(-aTexCoord.x, aTexCoord.y);
}