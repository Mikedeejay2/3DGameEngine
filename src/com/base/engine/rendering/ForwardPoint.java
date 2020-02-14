package com.base.engine.rendering;

import com.base.engine.components.BaseLight;
import com.base.engine.components.PointLight;
import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;

public class ForwardPoint extends Shader
{

    private static final ForwardPoint instance = new ForwardPoint();

    public static ForwardPoint getInstance()
    {
        return instance;
    }

    public ForwardPoint()
    {
        super("forward-point");
    }

    public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine)
    {
        super.updateUniforms(transform, material, renderingEngine);
    }
}
