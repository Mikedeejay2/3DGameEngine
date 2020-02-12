package com.base.engine.rendering;

import com.base.engine.components.BaseLight;
import com.base.engine.components.DirectionalLight;
import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;

public class ForwardDirectional extends Shader
{

    private static final ForwardDirectional instance = new ForwardDirectional();

    public static ForwardDirectional getInstance()
    {
        return instance;
    }

    public ForwardDirectional()
    {
        super();

        addVertexShaderFromFile("forward-directional.vs.glsl");
        addFragmentShaderFromFile("forward-directional.fs.glsl");

        setAttribLocation("position", 0);
        setAttribLocation("texCoord", 1);
        setAttribLocation("normal", 2);

        compileShader();

        addUniform("model");
        addUniform("MVP");

        addUniform("specularIntensity");
        addUniform("specularPower");
        addUniform("eyePos");

        addUniform("directionalLight.base.color");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
    }

    public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine)
    {
        Matrix4f worldMatrix = transform.getTransformation();
        Matrix4f projectedMatrix = renderingEngine.getMainCamera().getViewProjection().mul(worldMatrix);
        material.getTexture("diffuse").bind();

        setUniform("model", worldMatrix);
        setUniform("MVP", projectedMatrix);

        setUniformf("specularIntensity", material.getFloat("specularIntensity"));//getSpecularIntensity());
        setUniformf("specularPower", material.getFloat("specularPower"));

        setUniform("eyePos", renderingEngine.getMainCamera().getTransform().getTransformedPos());
        setUniformDirectionalLight("directionalLight", (DirectionalLight) renderingEngine.getActiveLight());
    }

    public void setUniformBaseLight(String uniformName, BaseLight baseLight)
    {
        setUniform(uniformName + ".color", baseLight.getColor());
        setUniformf(uniformName + ".intensity", baseLight.getIntensity());
    }

    public void setUniformDirectionalLight(String uniformName, DirectionalLight directionalLight)
    {
        setUniformBaseLight(uniformName + ".base", (BaseLight) directionalLight);
        setUniform(uniformName + ".direction", directionalLight.getDirection());
    }
}
