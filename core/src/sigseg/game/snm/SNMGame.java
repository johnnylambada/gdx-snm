package sigseg.game.snm;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import sigseg.game.snm.screen.GameScreen;
import sigseg.game.snm.screen.ScreenManager;

public class SNMGame implements ApplicationListener {
	
	public static int WIDTH = 480, HEIGHT = 800;
	public TextureAtlas atlas;
	public SpriteBatch batch;
	public static SNMGame inst;
	
	@Override
	public void create() {
		inst = this;
		batch = new SpriteBatch();
		atlas = new TextureAtlas(Gdx.files.internal("cards.atlas"));
		ScreenManager.set(new GameScreen());
	}

	@Override
	public void dispose() {
		ScreenManager.get().dispose();
		batch.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(3.0f/256.0f, 131.0f/256.0f, 48.0f/256.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		ScreenManager.get().update();
		ScreenManager.get().render(batch);
	}

	@Override
	public void resize(int width, int height) {
		ScreenManager.get().resize(width, height);
	}

	@Override
	public void pause() {
		ScreenManager.get().pause();
	}

	@Override
	public void resume() {
		ScreenManager.get().resume();
	}
}
