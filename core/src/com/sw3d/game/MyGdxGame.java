package com.sw3d.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.sun.deploy.util.Waiter;
import sun.security.ssl.Debug;

import javax.jws.WebParam;

public class MyGdxGame implements ApplicationListener {
	public PerspectiveCamera cam;
	public CameraInputController camController;

	public ModelBatch modelBatch;

	public Array<ModelInstance> instances = new Array<ModelInstance>();
	public Array<ModelInstance> moturInstances = new Array<ModelInstance>();
	public Array<ModelInstance> bandaInstances = new Array<ModelInstance>();
	public Array<ModelInstance> bandaDmuchInstances = new Array<ModelInstance>();
	public Array<ModelInstance> reklamaInstances = new Array<ModelInstance>();
	public ModelInstance tasmaInstance;

	public Environment environment;

	public AssetManager assets;
	public boolean loading;

	//proba organizera zawodow i dzialania jsona
	public OrganizerZawodow organizerZawodow;

	@Override
	public void create () {
		modelBatch = new ModelBatch();

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.5f, 0.5f, 0.5f, 1f));
		environment.add(new DirectionalLight().set(0.9f, 0.9f, 0.9f, -1f, -0.8f, -0.2f));

		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(0f, 7f, 10f);
		cam.lookAt(0, 0, 0);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();

		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);

		/*ModelBuilder modelBuilder = new ModelBuilder();
		model = modelBuilder.createBox(5f, 5f, 5f,
				new Material(ColorAttribute.createDiffuse(Color.GREEN)),
				VertexAttributes.Usage.Position| VertexAttributes.Usage.Normal);
		instance = new ModelInstance(model);*/

		assets = new AssetManager();
		//Debug.println("CREATE","before assets.load");
		assets.load("core/assets/banda.g3db", Model.class);
		assets.load("core/assets/banda_dmuchana_10st.g3db", Model.class);
		assets.load("core/assets/maszyna_startowa.g3db", Model.class);
		assets.load("core/assets/reklama.g3db", Model.class);
		assets.load("core/assets/motur2.g3db", Model.class);
		assets.load("core/assets/tor_scena.g3db", Model.class);
		//Debug.println("CREATE","after assets.load");
		loading = true;

		organizerZawodow = new OrganizerZawodow();
	}

	private void doneLoading()
	{
		//Debug.println("DONELOADING","1");
		tasmaInstance = new ModelInstance(assets.get("core/assets/maszyna_startowa.g3db", Model.class));
		tasmaInstance.transform.setToRotation(Vector3.Y, 180).trn(0,0,4.3f);
		instances.add(tasmaInstance);
		//Debug.println("DONELOADING","2");

		Model moturModel = assets.get("core/assets/motur2.g3db", Model.class);
		for (float i = 0; i < 4; i++)
		{
			ModelInstance moturInstance = new ModelInstance(moturModel);
			moturInstance.transform.setToRotation(Vector3.Y, MathUtils.random(-8f, 8f));
			moturInstance.transform.setTranslation(-1.5f, 0, 2.8f * i + 25f);
			for (Material mat : moturInstance.materials ) {
				mat.set(new BlendingAttribute(GL30.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
			}
			instances.add(moturInstance);
			moturInstances.add(moturInstance);
		}

		//Debug.println("DONELOADING","3");
		Model bandaModel = assets.get("core/assets/banda.g3db", Model.class);
		for (float x = -50f; x <= 50f; x += 1.815f)
		{
			ModelInstance bandaInstance = new ModelInstance(bandaModel);
			bandaInstance.transform.setToRotation(Vector3.Y, 270).trn(0,0, MathUtils.random(-5f, 5f));
			bandaInstance.transform.setTranslation(x, 0, 10f);
			for (Material mat : bandaInstance.materials ) {
				mat.set(new BlendingAttribute(GL30.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
			}
			instances.add(bandaInstance);
			bandaInstances.add(bandaInstance);

			ModelInstance bandaPInstance = new ModelInstance(bandaModel);
			bandaPInstance.transform.setToRotation(Vector3.Y, 90).trn(0,0, MathUtils.random(-5f, 5f));
			bandaPInstance.transform.setTranslation(x, 0, -60f);
			for (Material mat : bandaPInstance.materials ) {
				mat.set(new BlendingAttribute(GL30.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
			}
			instances.add(bandaPInstance);
			bandaInstances.add(bandaPInstance);
		}
		//Debug.println("DONELOADING","4");

		Model bandaDmuchanaModel = assets.get("core/assets/banda_dmuchana_10st.g3db", Model.class);
		for (float i = 0; i < 4; i++)
		{
			for( float num = 0; num < 9; num++)
			{
				ModelInstance bandaDmuchanaInstance = new ModelInstance(bandaDmuchanaModel);
				bandaDmuchanaInstance.transform.setToRotation(Vector3.Y, 90*i+10*num).trn(0,0, MathUtils.random(-2f, 2f));
				bandaDmuchanaInstance.transform.setTranslation(10 + num*3, 0, -15- i*3);
				for (Material mat : bandaDmuchanaInstance.materials ) {
					mat.set(new BlendingAttribute(GL30.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
				}
				instances.add(bandaDmuchanaInstance);
				bandaDmuchInstances.add(bandaDmuchanaInstance);

			}
		}
		//Debug.println("DONELOADING","4");

		Model reklamaModel = assets.get("core/assets/reklama.g3db", Model.class);
		for (float x = -20f; x <= 20f; x += 1.5f)
		{
			ModelInstance reklamaInstance = new ModelInstance(reklamaModel);
			reklamaInstance.transform.setToRotation(Vector3.Y, 90).trn(0,0, MathUtils.random(-5f, 5f));
			reklamaInstance.transform.setTranslation(x, 0, -10f);
			for (Material mat : reklamaInstance.materials ) {
				mat.set(new BlendingAttribute(GL30.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
			}
			instances.add(reklamaInstance);
			reklamaInstances.add(reklamaInstance);
		}

		Model torModel = assets.get("core/assets/tor_scena.g3db");
		for(int i = 0; i < torModel.nodes.size; i++)
		{
			String id = torModel.nodes.get(i).id;
			Debug.println("LOADING SCENE",id);
			ModelInstance torPartInstance = new ModelInstance(torModel, id);
			Node node = torPartInstance.getNode(id);

			torPartInstance.transform.set(node.globalTransform);
			node.translation.set(0,0,0);
			node.scale.set(1, 1, 1);
			node.rotation.idt();
			torPartInstance.calculateTransforms();

			instances.add(torPartInstance);
		}
		loading = false;
		//Debug.println("DONELOADING","5");
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
		if(loading && assets.update())
			doneLoading();
		//Debug.println("RENDER","1");
		camController.update();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);

		//Debug.println("RENDER","2");
		modelBatch.begin(cam);
		//Debug.println("RENDER","3");
		if(instances != null)
			modelBatch.render(instances, environment);
		//Debug.println("RENDER","4");
		modelBatch.end();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose () {
		instances.clear();
		modelBatch.dispose();
	}
}
