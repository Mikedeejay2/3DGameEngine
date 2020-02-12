package com.base.engine.rendering.meshLoading;

import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;

import java.util.ArrayList;

public class OBJModel
{
    private ArrayList<Vector3f> positions;
    private ArrayList<Vector2f> texCoords;
    private ArrayList<Vector3f> normals;
    private ArrayList<OBJIndex> indices;

    public OBJModel()
    {
        positions = new ArrayList<Vector3f>();
        texCoords = new ArrayList<Vector2f>();
        normals   = new ArrayList<Vector3f>();
        indices   = new ArrayList<OBJIndex>();
    }

    public ArrayList<Vector3f> getPositions() { return positions; }
    public ArrayList<Vector2f> getTexCoords() { return texCoords; }
    public ArrayList<Vector3f> getNormals()   { return normals;   }
    public ArrayList<OBJIndex> getIndices()    { return indices;   }
}
