package com.base.engine.rendering;

import com.base.engine.components.BaseLight;
import com.base.engine.components.PointLight;
import com.base.engine.components.SpotLight;
import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;

public class ForwardSpot extends Shader
{

    private static final ForwardSpot instance = new ForwardSpot();

    public static ForwardSpot getInstance()
    {
        return instance;
    }

    public ForwardSpot()
    {
        super("forward-spot");
    }

    public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine)
    {
        super.updateUniforms(transform, material, renderingEngine);
    }
}
