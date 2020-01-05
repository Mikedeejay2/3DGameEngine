package com.base.engine.rendering;

import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;

public class BasicShader extends Shader
{
    private static final BasicShader instance = new BasicShader();

    public static BasicShader getInstance()
    {
        return instance;
    }

    public BasicShader()
    {
        super();

        addVertexShaderFromFile("basicVertex.vs.glsl");
        addFragmentShaderFromFile("basicFragment.fs.glsl");
        compileShader();

        addUniform("transform");
        addUniform("color");
    }

    public void updateUniforms(Transform transform, Material material)
    {
        Matrix4f worldMatrix = transform.getTransformation();
        Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().mul(worldMatrix);

        material.getTexture().bind();

        setUniform("transform",projectedMatrix);
        setUniform("color", material.getColor());
    }
}
