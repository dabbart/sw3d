package com.sw3d.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class EscMenu {
    //test menu
    public final static Stage stage;
    static {
		stage = new Stage(new ScreenViewport());
	}
    public final static Texture square;
    static {
		square = new Texture(Gdx.files.absolute("InscribedCircle.png"));
	}

    public EscMenu()
    {
		Image image1 = new Image(square);
		image1.setPosition(Gdx.graphics.getWidth()/3-image1.getWidth()/2,
				Gdx.graphics.getHeight()*2/3-image1.getHeight()/2);
		stage.addActor(image1);

		Image image2 = new Image(square);
		image2.setPosition(Gdx.graphics.getWidth()*2/3-image2.getWidth()/2,
				Gdx.graphics.getHeight()*2/3-image2.getHeight()/2);
		image2.setOrigin(image2.getWidth()/2,image2.getHeight()/2);
		image2.rotateBy(45);
		stage.addActor(image2);

		Image image3 = new Image(square);
		image3.setSize(square.getWidth()/2,square.getHeight()/2);
		image3.setPosition(Gdx.graphics.getWidth()/3-image3.getWidth()/2,
				Gdx.graphics.getHeight()/3-image3.getHeight());
		stage.addActor(image3);

		square.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
		TextureRegion textureRegion = new TextureRegion(square);
		textureRegion.setRegion(0,0,square.getWidth()*8,square.getHeight()*4);
		Image image4 = new Image(textureRegion);
		image4.setSize(200,100);
		image4.setPosition(Gdx.graphics.getWidth()*2/3-image4.getWidth()/2,
				Gdx.graphics.getHeight()/3-image4.getHeight());
		stage.addActor(image4);
    }

    protected void finalize()
	{
		stage.dispose();
		square.dispose();
	}
}
