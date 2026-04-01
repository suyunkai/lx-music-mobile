#version 300 es
layout(location = 0) in vec2 vPosition;

out vec4 vColor;

//out vec2 vSize;      // 矩形大小
//out float vRadius;   // 圆角半径
//out vec4 vPos; //起始绘制位置

uniform vec4 aColor;
uniform mat4 transform;
uniform float heightening;
uniform float startVal;
uniform float endVal;

void main() {
    float kVal = (endVal - startVal)*0.5f;
    float bVal = (startVal + endVal)*0.5f;

    gl_Position = transform * vec4(vPosition.x*kVal+bVal, vPosition.y*heightening, 0.0f, 1.0f);

//    vRadius = 0.05;
//    vSize = vec2(endVal - startVal - vRadius, heightening - vRadius);
//    vPos = vec2(vPosition.x + (endVal - startVal - vRadius)*0.5, vPosition.y - (heightening - vRadius)*0.5);
//    vSize = vec2(endVal - startVal, heightening); //*6.0
//    vPos = vec2(vPosition.x, vPosition.y);
//    vPos = vec2(-1.0f, 0.0);

    vColor = aColor;
}