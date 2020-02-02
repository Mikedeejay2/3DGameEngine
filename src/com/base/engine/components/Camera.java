package com.base.engine.components;

import com.base.engine.core.*;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Window;

public class Camera extends GameComponent
{
    public static final Vector3f yAxis = new Vector3f(0, 1, 0);
//
//    private Vector3f pos;
//    private Vector3f forward;
//    private Vector3f up;
    private Matrix4f projection;

    public Camera(float fov, float aspect, float zNear, float zFar)
    {
//        this.pos = new Vector3f(0, 0, 0);
//        this.forward = new Vector3f(0, 0, 1).normalized();
//        this.up = new Vector3f(0, 1, 0).normalized();
        this.projection = new Matrix4f().initPerspective(fov, aspect, zNear, zFar);
    }

    boolean mouseLocked = false;
    Vector2f centerPosition = new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2);

    public Matrix4f getViewProjection()
    {
        Matrix4f cameraRotation = getTransform().getRot().toRotationMatrix();
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(-getTransform().getPos().getX(), -getTransform().getPos().getY(), -getTransform().getPos().getZ());

        return projection.mul(cameraRotation.mul(cameraTranslation));
    }

    @Override
    public void addToRenderingEngine(RenderingEngine renderingEngine)
    {
        renderingEngine.addCamera(this);
    }

    @Override
    public void input(float delta)
    {
        float sensitivity = -0.5f;
        float movAmt = (float)(10 * delta);
        float rotAmt = (float)(100 * delta);

        if(Input.getKey(Input.KEY_ESCAPE))
        {
            Input.setCursor(true);
            mouseLocked = false;
        }
        if(Input.getMouse(0))
        if(Input.getMouse(0))
        {
            Input.setMousePosition(centerPosition);
            Input.setCursor(false);
            mouseLocked = true;
        }

        if(Input.getKey(Input.KEY_W))
            move(getTransform().getRot().getForward(), movAmt);
        if(Input.getKey(Input.KEY_S))
            move(getTransform().getRot().getForward(), -movAmt);
        if(Input.getKey(Input.KEY_A))
            move(getTransform().getRot().getLeft(), movAmt);
        if(Input.getKey(Input.KEY_D))
            move(getTransform().getRot().getRight(), movAmt);

        if(mouseLocked)
        {
            Vector2f deltaPos = Input.getMousePosition().sub(centerPosition);

            boolean rotY = deltaPos.getX() != 0;
            boolean rotX = deltaPos.getY() != 0;

            if(rotY)
                getTransform().setRot(getTransform().getRot().mul(new Quaternion(yAxis, (float)Math.toRadians(deltaPos.getX() * sensitivity))).normalized());
            if(rotX)
                getTransform().setRot(getTransform().getRot().mul(new Quaternion(getTransform().getRot().getRight(), (float)Math.toRadians(-deltaPos.getY() * sensitivity))).normalized());

            if(rotY || rotX)
                Input.setMousePosition(new Vector2f(Window.getWidth()/2, Window.getHeight()/2));
        }

        /*
        if(Input.getKey(Input.KEY_UP))
            rotateX(-rotAmt);
        if(Input.getKey(Input.KEY_DOWN))
            rotateX(rotAmt);
        if(Input.getKey(Input.KEY_LEFT))
            rotateY(-rotAmt);
        if(Input.getKey(Input.KEY_RIGHT))
            rotateY(rotAmt);
        */
    }

    public void move(Vector3f dir, float amt)
    {
        getTransform().setPos(getTransform().getPos().add(dir.mul(amt)));;
    }
}
