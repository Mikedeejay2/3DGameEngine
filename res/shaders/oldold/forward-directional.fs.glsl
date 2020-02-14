#version 120
#include "lighting.glh.glsl"
#include "lighting.fsh.glsl"

varying vec2 texCoord0;
varying vec3 normal0;
varying vec3 worldPos0;

uniform sampler2D diffuse;
uniform DirectionalLight R_directionalLight;

vec4 CalcLightingEffect(vec3 normal, vec3 worldPos)
{
    return CalcDirectionalLight(R_directionalLight, normal, worldPos);
}

#include "lightingMain.fsh.glsl"