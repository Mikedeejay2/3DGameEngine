package com.base.engine.core;

import com.base.engine.rendering.Window;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class Input
{
    public static final int NUM_KEYCODES = 256;
    public static final int NUM_MOUSEBUTTONS = 5;

    private static boolean[] lastKeys = new boolean[NUM_KEYCODES];
    private static boolean[] lastMouse = new boolean[NUM_MOUSEBUTTONS];

    public static void update()
    {
        for(int i = 0; i < NUM_KEYCODES; i++)
            lastKeys[i] = getKey(i);

        for(int i = 0; i < NUM_MOUSEBUTTONS; i++)
            lastMouse[i] = getMouse(i);
    }


    public static boolean getKey(int keyCode)
    {
        if(keyCode > 31)
        if(glfwGetKey(Window.getWindow(), keyCode) == 1)
            return true;
        return false;
    }

    public static boolean getKeyDown(int keyCode)
    {
        return getKey(keyCode) && !lastKeys[keyCode];
    }

    public static boolean getKeyUp(int keyCode)
    {
        return !getKey(keyCode) && lastKeys[keyCode];
    }

    public static boolean getMouse(int mouseButton)
    {
        if(glfwGetMouseButton(Window.getWindow(), mouseButton) == 1)
            return true;
        return false;
    }

    public static boolean getMouseDown(int mouseButton)
    {
        return getMouse(mouseButton) && !lastMouse[mouseButton];
    }

    public static boolean getMouseUp(int mouseButton)
    {
        return !getMouse(mouseButton) && lastMouse[mouseButton];
    }

    public static Vector2f getMousePosition()
    {
        DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(Window.getWindow(), posX, posY);
        return new Vector2f((float)posX.get(), (float)posY.get());
    }

    public static void setMousePosition(Vector2f pos)
    {
        glfwSetCursorPos(Window.getWindow(), pos.getX(), pos.getY());
    }

    public static void setCursor(boolean enabled)
    {
        if(enabled)
        {
            glfwSetInputMode(Window.getWindow(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
        }
        else
            glfwSetInputMode(Window.getWindow(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    }

}
