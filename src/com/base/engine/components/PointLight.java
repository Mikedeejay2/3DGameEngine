package com.base.engine.components;

import com.base.engine.core.RenderingEngine;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Attenuation;
import com.base.engine.rendering.ForwardPoint;

public class PointLight extends BaseLight
{
    private BaseLight baseLight;
    private Attenuation atten;
    private Vector3f position;
    private float range;

    public PointLight(Vector3f color, float intensity, Attenuation atten, Vector3f position, float range)
    {
        super(color, intensity);
        this.atten = atten;
        this.position = position;
        this.range = range;

        setShader(ForwardPoint.getInstance());
    }

    public BaseLight getBaseLight()
    {
        return baseLight;
    }

    public void setBaseLight(BaseLight baseLight)
    {
        this.baseLight = baseLight;
    }

    public Attenuation getAtten()
    {
        return atten;
    }

    public void setAtten(Attenuation atten)
    {
        this.atten = atten;
    }

    public Vector3f getPosition()
    {
        return position;
    }

    public void setPosition(Vector3f position)
    {
        this.position = position;
    }

    public float getRange()
    {
        return range;
    }

    public void setRange(float range)
    {
        this.range = range;
    }
}
