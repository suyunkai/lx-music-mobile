#version 300 es
layout(location = 0) in vec3 vPosition;
layout (location = 2) in vec2 aTexCoord;

out vec4 color;
out vec2 TexCoord;

uniform vec4 aColor;
uniform mat4 transform;
uniform mat4 projection;

void main() {
    gl_Position = projection * transform * vec4(vPosition, 1.0f);
    TexCoord = vec2(-aTexCoord.x, aTexCoord.y);
    color = aColor;
}