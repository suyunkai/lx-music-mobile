#version 300 es
precision mediump float;


out vec4 FragColor;

in vec3 color;
in vec3 normal;
in vec3 lightPos;
in vec3 fragPos;
in vec3 viewPos;

void main() {
    vec3 lightColor = vec3(1.0f, 1.0f, 1.0f);
    vec3 ambient = 0.5 * lightColor;

    vec3 norm = normalize(normal);
    vec3 lightDir = normalize(lightPos -fragPos);
    float diff = max(dot(norm, lightDir), 0.0);
    vec3 diffuse = diff * lightColor;

    float specularStrength = 0.2;
    vec3 viewDir = normalize(viewPos - fragPos);
    vec3 reflectDir = reflect(-lightDir, norm);
    float max_value = max(dot(viewDir, reflectDir), 0.0);
    float spec = pow(max_value, 8.0f);
    vec3 specular = specularStrength * spec * lightColor; 

    vec3 result = (ambient + diffuse + specular) * color;
    FragColor = vec4(result,1.0);
}
