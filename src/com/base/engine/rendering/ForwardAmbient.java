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
        super.updateUniforms(transform, material, renderingEngine);
    }
}
