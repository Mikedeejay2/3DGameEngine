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
        if(material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtil.unbindTextures();

        setUniform("transform",projectedMatrix);
        setUniform("color", material.getColor());
    }
}