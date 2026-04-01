#version 300 es
layout(location = 0) in vec3 vPosition;
layout(location = 1) in vec2 aTexCoord;

uniform mat4 m;
uniform mat4 pv;
uniform vec4 color;

out vec4 vColor;
out vec2 TexCoord;

void main() {
    gl_Position = pv*m*vec4(vPosition,1.0f);
    TexCoord = aTexCoord;
    vColor = color;
}