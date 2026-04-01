#version 300 es
layout(location = 0) in vec4 vPosition;
layout (location = 2) in vec2 aTexCoord;

uniform mat4 transform;
uniform mat4 projection;

out vec2 TexCoord;

void main() {
    gl_Position = projection * transform * vPosition;
    TexCoord = vec2(-aTexCoord.x, aTexCoord.y);
}