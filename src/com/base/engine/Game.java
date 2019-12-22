package com.base.engine;

import org.lwjgl.input.Keyboard;

public class Game
{
    private Mesh mesh;
    private Shader shader;
    private Material material;
    private Transform transform;
    private Camera camera;

    public Game()
    {
        mesh = new Mesh(); //ResourceLoader.loadMesh("box.obj");//new Mesh();
        material = new Material(ResourceLoader.loadTexture("test.png"), new Vector3f(0, 1, 1));
        shader = new PhongShader();
        camera = new Camera();
        transform = new Transform();

//		Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1,-1,0), new Vector2f(0,0)),
//									      new Vertex(new Vector3f(0,1,0), new Vector2f(0.5f,0)),
//									      new Vertex(new Vector3f(1,-1,0), new Vector2f(1.0f,0)),
//									      new Vertex(new Vector3f(0,-1,1), new Vector2f(0.5f,1.0f))};
//
//		int[] indices = new int[] {3,1,0,
//								   2,1,3,
//								   0,1,2,
//								   0,2,3};

        Vertex[] vertices = new Vertex[] { new Vertex( new Vector3f(-1.0f, -1.0f, 0.5773f),	    new Vector2f(0.0f, 0.0f)),
                                           new Vertex( new Vector3f(0.0f, -1.0f, -1.15475f),	new Vector2f(0.5f, 0.0f)),
                                           new Vertex( new Vector3f(1.0f, -1.0f, 0.5773f),		new Vector2f(1.0f, 0.0f)),
                                           new Vertex( new Vector3f(0.0f, 1.0f, 0.0f),          new Vector2f(0.5f, 1.0f)) };

        int indices[] = { 0, 3, 1,
                          1, 3, 2,
                          2, 3, 0,
                          1, 2, 0 };

        mesh.addVertices(vertices, indices, true);

        Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
        Transform.setCamera(camera);

        PhongShader.setAmbientLight(new Vector3f(0.1f, 0.1f, 0.1f));
        PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1, 1, 1), 0.8f), new Vector3f(1, 1, 1)));

    }

    public void input()
    {
        camera.input();
/*
        if(Input.getKeyDown(Keyboard.KEY_UP))
            System.out.println("We've just pressed down!");

        if(Input.getKeyUp(Keyboard.KEY_UP))
            System.out.println("We've just pressed up!");

        if(Input.getMouseDown(1))
            System.out.println("We've just right clicked at " + Input.getMousePosition().toString());

        if(Input.getMouseUp(1))
            System.out.println("We've just released right mouse button!");
 */
    }

    float temp = 0.0f;

    public void update()
    {
        temp += Time.getDelta();

        float sinTemp = (float)Math.sin(temp);

        transform.setTranslation(0, 0, 5);
        transform.setRotation(0, sinTemp * 180, 0);
        //transform.setScale(0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp);
    }

    public void render()
    {
        RenderUtil.setClearColor(Transform.getCamera().getPos().div(2048f).abs());
        shader.bind();
        shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
        mesh.draw();
    }

}
