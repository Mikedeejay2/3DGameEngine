package com.base.engine.rendering;

import com.base.engine.core.Matrix4f;

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

    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material)
    {
        material.getTexture().bind();

        setUniform("transform",projectedMatrix);
        setUniform("color", material.getColor());
    }
}
