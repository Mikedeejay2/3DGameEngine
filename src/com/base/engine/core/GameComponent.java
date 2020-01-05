package com.base.engine.core;

public interface GameComponent
{
    public void init(Transform transform);
    public void input(Transform transform);
    public void update(Transform transform);

    public void render(Transform transform);
}
