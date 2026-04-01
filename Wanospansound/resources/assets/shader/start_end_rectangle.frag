#version 300 es
precision mediump float;

out vec4 FragColor;
//in vec2 vSize;
//in vec2 vPos;
//in float vRadius;
in vec4 vColor;

//vec2 iResolution = vec2(1458.0, 694.0);
//
//vec2 uv = vec2(0);  // centered pixel position -1 .. 1
//float roundedRectangle (vec2 pos, vec2 size, float radius, float thickness)
//{
//    float d = length(max(abs(uv - pos),size) - size) - radius;
//    return smoothstep(0.66, 0.33, d / thickness * 5.0);
//}
//
//float roundedFrame (vec2 pos, vec2 size, float radius, float thickness)
//{
//    float d = length(max(abs(uv - pos),size) - size) - radius;
//    return smoothstep(0.55, 0.45, abs(d / thickness) * 5.0);
//}

void main() {
//    vec2 pos, size;
//    vec2 npos = gl_FragCoord.xy / iResolution.xy;   // 0.0 .. 1.0
//
////     get uv position with origin at window center
//    float aspect = iResolution.x / iResolution.y;   // aspect ratio x/y
//    vec2 ratio = vec2(aspect, 1.0);                 // aspect ratio (x/y,1)
//    uv = (2.0 * npos - 1.0) * ratio;             // -1.0 .. 1.0
//
//    vec3 col;
//    float intensity = 0.0;
//
//    //--- rounded rectangle ---
//    const vec3 rectColor = vec3(1.0, 0.0, 0.0);
//    intensity = 0.6 * roundedRectangle (vPos, vSize, vRadius, 0.05);//0.05
//    col = mix(col, rectColor, intensity);

    //--- rounded frame ---
//    const vec3 frameColor = vec3(0.5, 0.3, 0.6);
//    intensity = roundedFrame (vPos, vSize, 0.02, 0.1);
//    col = mix(col, frameColor, intensity);

//    FragColor = vec4 (col, 1.0);

    FragColor = vColor;
}