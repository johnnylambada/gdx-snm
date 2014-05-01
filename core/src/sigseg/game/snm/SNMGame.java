package sigseg.game.snm;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class SNMGame extends ApplicationAdapter {
	SpriteBatch batch;
	TextureAtlas atlas;
	Sprite sprite;


	@Override
	public void create () {
		batch = new SpriteBatch();
		atlas = new TextureAtlas(Gdx.files.internal("cards.atlas"));
		sprite = atlas.createSprite("s01S");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(3.0f/256.0f, 131.0f/256.0f, 48.0f/256.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(sprite, 0, 0);
		batch.end();
	}
}
