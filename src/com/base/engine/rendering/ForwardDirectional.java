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
        super("forward-directional");
    }

    public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine)
    {
        super.updateUniforms(transform, material, renderingEngine);
    }
}
