package com.sw3d.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sun.security.ssl.Debug;

public class MyGdxGame extends Game implements ApplicationListener {
	public PerspectiveCamera perspectiveCamera;
	public OrthographicCamera orthographicCamera;
	public CameraInputController camController;

	public ModelBatch modelBatch;

	public Motur[] motury;
	public Tor tor;

	public EscMenu escMenu;

	public Array<ModelInstance> instances = new Array<ModelInstance>();
	public Array<ModelInstance> moturInstances = new Array<ModelInstance>();
	public Array<ModelInstance> bandaInstances = new Array<ModelInstance>();
	public Array<ModelInstance> bandaDmuchInstances = new Array<ModelInstance>();
	public Array<ModelInstance> reklamaInstances = new Array<ModelInstance>();
	public ModelInstance tasmaInstance;

	public Environment environment;

	public AssetManager assets;
	public AssetManager getAssets() {
		return assets;
	}

	public boolean loading;

	//proba organizera zawodow i dzialania jsona
	public OrganizerZawodow organizerZawodow;

	//tmp
	boolean collision;
	public btCollisionConfiguration collisionConfig;
    public btDispatcher dispatcher;

	@Override
	public void create () {
		Bullet.init();

		modelBatch = new ModelBatch();

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.5f, 0.5f, 0.5f, 1f));
		environment.add(new DirectionalLight().set(0.9f, 0.9f, 0.9f, -0.5f, -0.8f, -0.2f));

		perspectiveCamera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		perspectiveCamera.position.set(0f, 7f, 10f);
		perspectiveCamera.lookAt(0, 0, 0);
		perspectiveCamera.near = 1f;
		perspectiveCamera.far = 300f;
		perspectiveCamera.update();

		orthographicCamera = new OrthographicCamera(230, 230 * (Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));
		orthographicCamera.position.set(32, 8, 32);
		orthographicCamera.direction.set(0,-1,-0.1f);
		orthographicCamera.zoom = 1f;
		orthographicCamera.update();

		camController = new CameraInputController(perspectiveCamera);
		Gdx.input.setInputProcessor(camController);

		/*ModelBuilder modelBuilder = new ModelBuilder();
		model = modelBuilder.createBox(5f, 5f, 5f,
				new Material(ColorAttribute.createDiffuse(Color.GREEN)),
				VertexAttributes.Usage.Position| VertexAttributes.Usage.Normal);
		instance = new ModelInstance(model);*/

		assets = new AssetManager();
		//Debug.println("CREATE","before assets.load");
		assets.load("banda.g3db", Model.class);
		assets.load("banda_dmuchana_10st.g3db", Model.class);
		assets.load("maszyna_startowa.g3db", Model.class);
		assets.load("reklama.g3db", Model.class);
		assets.load("motur_libgdx.g3db", Model.class);
		assets.load("tor_scena.g3db", Model.class);
		//Debug.println("CREATE","after assets.load");
		loading = true;

		organizerZawodow = new OrganizerZawodow();

	}

	private void doneLoading()
	{
		collisionConfig = new btDefaultCollisionConfiguration();
		dispatcher = new btCollisionDispatcher(collisionConfig);

		motury = new Motur[1];
		motury[0] = new Motur(assets);
		instances.add(motury[0].moturInstance);
		moturInstances.add(motury[0].moturInstance);

		tor = new Tor(assets);
		for(int i = 0; i < tor.model.nodes.size; i++)
		{
			String id = tor.model.nodes.get(i).id;
			Debug.println("LOADING SCENE",id);
			ModelInstance torPartInstance = new ModelInstance(tor.model, id);
			Node node = torPartInstance.getNode(id);

			torPartInstance.transform.set(node.globalTransform);
			node.translation.set(0,0,0);
			node.scale.set(1, 1, 1);
			node.rotation.idt();
			torPartInstance.calculateTransforms();

			instances.add(torPartInstance);
		}

		escMenu = new EscMenu();

		/*
		tasmaInstance = new ModelInstance(assets.get("maszyna_startowa.g3db", Model.class));
		tasmaInstance.transform.setToRotation(Vector3.Y, 180).trn(0,0,4.3f);
		instances.add(tasmaInstance);

		Model moturModel = assets.get("motur_libgdx.g3db", Model.class);
		for (float i = 0; i < 4; i++)
		{
			ModelInstance moturInstance = new ModelInstance(moturModel);
			moturInstance.transform.setToRotation(Vector3.Y, 0);
			moturInstance.transform.setTranslation(-1.5f, 0, 3.5f * i + 22f);
			for (Material mat : moturInstance.materials ) {
				mat.set(new BlendingAttribute(GL30.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
			}
			instances.add(moturInstance);
			moturInstances.add(moturInstance);
		}

		Model bandaModel = assets.get("banda.g3db", Model.class);
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

		Model bandaDmuchanaModel = assets.get("banda_dmuchana_10st.g3db", Model.class);
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

		Model reklamaModel = assets.get("reklama.g3db", Model.class);
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
		}*/


		loading = false;
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
		final float delta = Math.min(1f/30f, Gdx.graphics.getDeltaTime());

		if(loading && assets.update()) {
			doneLoading();
		}

		if(!loading && !collision)
		{
			motury[0].moturInstance.transform.translate(delta, -delta, 0f);
			motury[0].moturInstance.transform.rotate(Vector3.Y,20);
			motury[0].object.setWorldTransform(motury[0].moturInstance.transform);
			collision = checkCollision(tor.object, motury[0].object);
		}

		if(!loading)
			collision = checkCollision(tor.object, motury[0].object);

		camController.update();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(perspectiveCamera);
		if(instances != null)
			modelBatch.render(instances, environment);
		modelBatch.end();
/*
		modelBatch.begin(orthographicCamera);
		if(instances != null)
			modelBatch.render(instances, environment);
		modelBatch.end();*/

		escMenu.stage.act();
		escMenu.stage.draw();
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
		dispatcher.dispose();
		collisionConfig.dispose();
		modelBatch.dispose();

		tor.finalize();
		motury[0].finalize();
		escMenu.finalize();
	}

	boolean checkCollision(btCollisionObject obj0, btCollisionObject obj1){
		CollisionObjectWrapper co0 = new CollisionObjectWrapper(obj0);
		CollisionObjectWrapper co1 = new CollisionObjectWrapper(obj1);

		//btCollisionAlgorithmConstructionInfo ci = new btCollisionAlgorithmConstructionInfo();
		//ci.setDispatcher1(dispatcher);
		btCollisionAlgorithm algorithm = dispatcher.findAlgorithm(co0.wrapper, co1.wrapper);

		btDispatcherInfo info = new btDispatcherInfo();
		btManifoldResult result = new btManifoldResult(co0.wrapper, co1.wrapper);

		algorithm.processCollision(co0.wrapper, co1.wrapper, info, result);

		boolean r = result.getPersistentManifold().getNumContacts() > 0;

//		Debug.println("checkCollision()",result.toString());

		dispatcher.freeCollisionAlgorithm(algorithm.getCPointer());
		result.dispose();
		info.dispose();
		co1.dispose();
		co0.dispose();

		return r;
	}
}
