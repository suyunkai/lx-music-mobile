#version 300 es
layout (location = 0) in vec4 vPosition;
out vec2 TexCoords;
out vec4 textColor;

uniform mat4 transform;
uniform vec4 aColor;

void main()
{
   gl_Position = transform * vec4(vPosition.xy, 0.0, 1.0);
   TexCoords = vPosition.zw;
   textColor = aColor;
}