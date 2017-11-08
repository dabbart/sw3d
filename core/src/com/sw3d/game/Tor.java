package com.sw3d.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.badlogic.gdx.physics.bullet.linearmath.btMotionState;
import sun.security.ssl.Debug;

public class Tor {
    public Model model;
    public ModelInstance instance;
    public btRigidBody rigidBody;
    public btRigidBody.btRigidBodyConstructionInfo rigidBodyConstructionInfo;
    public btCollisionShape shape;
    public btCollisionObject object;
    public MyMotionState motionState;
    public com.badlogic.gdx.math.collision.BoundingBox boundingBox;
    public float mass;



    public Vector3 tmpV3;

    public Tor(AssetManager assetManager)
    {
        model = assetManager.get("tor_scena.g3db", Model.class);


        for(int i = 0; i < model.nodes.size; i++)
        {
            String id = model.nodes.get(i).id;
            Debug.println("LOADING SCENE",id);
            ModelInstance torPartInstance = new ModelInstance(model, id);
            Node node = torPartInstance.getNode(id);

            torPartInstance.transform.set(node.globalTransform);
            node.translation.set(0,0,0);
            node.scale.set(1, 1, 1);
            node.rotation.idt();
            torPartInstance.calculateTransforms();

        }



        instance = new ModelInstance(model);
        instance.transform.setToRotation(Vector3.Y,0);
        instance.transform.setTranslation(0, 0, 0);
        for (Material mat : instance.materials ) {
            mat.set(new BlendingAttribute(GL30.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
        }
        motionState = new MyMotionState();
        motionState.transform = instance.transform;

        boundingBox = new com.badlogic.gdx.math.collision.BoundingBox();
        model.calculateBoundingBox(boundingBox);

        tmpV3 = new Vector3(boundingBox.getWidth(), boundingBox.getHeight(), boundingBox.getDepth());
        btBoxShape boxShape = new btBoxShape(tmpV3);
        shape = boxShape;

        mass = 10000000f;

        if(shape != null && mass >= 0)
        {
            Vector3 localInertia;
            // Calculate local inertia, bodies with no masses are static
            if(mass == 0)
                localInertia = Vector3.Zero;
            else {
                shape.calculateLocalInertia(mass, tmpV3);
                localInertia = tmpV3;
            }

            rigidBodyConstructionInfo = new btRigidBody.btRigidBodyConstructionInfo(mass, motionState, shape, localInertia);
        }

        object = new btCollisionObject();
        object.setCollisionShape(shape);
        object.setWorldTransform(instance.transform);

        rigidBody = new btRigidBody(rigidBodyConstructionInfo);
        rigidBody.setMotionState(motionState);
    }

    static class MyMotionState extends btMotionState {
        Matrix4 transform;
        @Override
        public void getWorldTransform (Matrix4 worldTrans) {
            worldTrans.set(transform);
        }
        @Override
        public void setWorldTransform (Matrix4 worldTrans) {
            transform.set(worldTrans);
        }
    }

    protected void finalize()
    {
        object.dispose();
        shape.dispose();
        model.dispose();
    }
}
