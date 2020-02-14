package com.base.engine.rendering;

import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;

public class ForwardAmbient extends Shader
{
    private static final ForwardAmbient instance = new ForwardAmbient();

    public static ForwardAmbient getInstance()
    {
        return instance;
    }

    public ForwardAmbient()
    {
        super("forward-ambient");
    }

    public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine)
    {
//        Matrix4f worldMatrix = transform.getTransformation();
//        Matrix4f projectedMatrix = renderingEngine.getMainCamera().getViewProjection().mul(worldMatrix);
        super.updateUniforms(transform, material, renderingEngine);
//        material.getTexture("diffuse").bind();

//        setUniform("T_MVP", projectedMatrix);
//        setUniform("R_ambient", renderingEngine.getAmbientLight());
    }
}
