package com.base.engine.rendering;

import com.base.engine.core.Vector2f;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.GL_DRAW_FRAMEBUFFER;
import static org.lwjgl.opengl.GL30.glBindFramebuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class Window
{
    private static long window;
    private static int width, height;
    private static String title;

    public static void createWindow(int width2, int height2, String title2)
    {
        width = width2;
        height = height2;
        title = title2;
        try
        {
            // Setup an error callback. The default implementation
            // will print the error message in System.err.
            GLFWErrorCallback.createPrint(System.err).set();

            // Initialize GLFW. Most GLFW functions will not work before doing this.
            if ( !glfwInit() )
                throw new IllegalStateException("Unable to initialize GLFW");

            // Configure GLFW
            glfwDefaultWindowHints(); // optional, the current window hints are already the default
            glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
            glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

            // Create the window
            window = glfwCreateWindow(width, height, title, NULL, NULL);
            if ( window == NULL )
                throw new RuntimeException("Failed to create the GLFW window");

//            // Setup a key callback. It will be called every time a key is pressed, repeated or released.
//            glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
//                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
//                    glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
//            });

            // Get the thread stack and push a new frame
            try ( MemoryStack stack = stackPush() ) {
                IntBuffer pWidth = stack.mallocInt(1); // int*
                IntBuffer pHeight = stack.mallocInt(1); // int*

                // Get the window size passed to glfwCreateWindow
                glfwGetWindowSize(window, pWidth, pHeight);

                // Get the resolution of the primary monitor
                GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

                // Center the window
                glfwSetWindowPos(
                        window,
                        (vidmode.width() - pWidth.get(0)) / 2,
                        (vidmode.height() - pHeight.get(0)) / 2
                );
            } // the stack frame is popped automatically

            // Make the OpenGL context current
            glfwMakeContextCurrent(window);
            GL.createCapabilities();
            // Enable v-sync
            glfwSwapInterval(0);

            // Make the window visible
            glfwShowWindow(window);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void render()
    {
        glfwPollEvents();
        glfwSwapBuffers(window);
    }

    public static void dispose()
    {
        glfwDestroyWindow(window);
        glfwSetWindowShouldClose(window, true);
    }

    public static void bindAsRenderTarget()
    {
        glBindFramebuffer(GL_DRAW_FRAMEBUFFER, 0);
        glViewport(0, 0, getWidth(), getHeight());
    }

    public static boolean isCloseRequested()
    {
        return glfwWindowShouldClose(window);
    }

    public static int getWidth()
    {
        return width;
    }

    public static int getHeight()
    {
        return height;
    }

    public static String getTitle()
    {
        return title;
    }

    public Vector2f getCenter()
    {
        return new Vector2f(getWidth() / 2, getHeight() / 2);
    }

    public static long getWindow()
    {
        return window;
    }
}
