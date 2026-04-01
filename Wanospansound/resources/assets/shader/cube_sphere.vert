#version 300 es
layout(location = 0) in vec3 vPosition;
layout(location = 1) in vec3 vNormal;

uniform vec4 aColor;
uniform mat4 m;
uniform mat4 pv;
uniform vec3 vlightPos;
uniform vec3 vViewPos;

out vec3 color;
out vec3 normal;
out vec3 lightPos;
out vec3 viewPos;

out vec3 fragPos;

void main() {
    fragPos = (m*vec4(vPosition, 1.0f)).xyz;
    color = aColor.xyz;
    normal = mat3(transpose(inverse(m)))*vNormal;
    lightPos = vlightPos;
    viewPos = vViewPos;
    gl_Position = pv*m*vec4(vPosition, 1.0f);
}