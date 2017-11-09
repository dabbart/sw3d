package com.sw3d.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody.btRigidBodyConstructionInfo;
import com.badlogic.gdx.physics.bullet.linearmath.btMotionState;
import com.badlogic.gdx.math.collision.BoundingBox;

public class Motur{
    public Model moturModel;
    public ModelInstance moturInstance;
    public btRigidBody rigidBody;
    public btRigidBodyConstructionInfo rigidBodyConstructionInfo;
    public btCollisionShape shape;
    public btCollisionObject object;
    public MyMotionState motionState;
    public BoundingBox boundingBox;
    public float mass;

    public Vector3 tmpV3;

    public Motur(AssetManager assetManager)
    {
        moturModel = assetManager.get("motur_libgdx.g3db", Model.class);
        moturInstance = new ModelInstance(moturModel);
        moturInstance.transform.setToRotation(Vector3.Y,0);
        moturInstance.transform.setTranslation(0, 2, 0);
        for (Material mat : moturInstance.materials ) {
            mat.set(new BlendingAttribute(GL30.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
        }
        motionState = new MyMotionState();
        motionState.transform = moturInstance.transform;

        boundingBox = new BoundingBox(Vector3.Zero, new Vector3(2f, 2f, 2f));
        moturModel.calculateBoundingBox(boundingBox);

        tmpV3 = new Vector3(boundingBox.getWidth(), boundingBox.getHeight(), boundingBox.getDepth());
        btBoxShape boxShape = new btBoxShape(tmpV3);
        shape = boxShape;

        mass = 150f;

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

            rigidBodyConstructionInfo = new btRigidBodyConstructionInfo(mass, motionState, shape, localInertia);
        }

        object = new btCollisionObject();
        object.setCollisionShape(shape);
        object.setWorldTransform(moturInstance.transform);

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
        moturModel.dispose();
    }
}


