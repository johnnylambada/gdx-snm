package sigseg.game.snm;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sigseg.game.snm.screen.GameScreen;
import sigseg.game.snm.screen.ScreenManager;

public class JohnGame implements ApplicationListener {
	
	public static int WIDTH = 480, HEIGHT = 800;
	private SpriteBatch batch;
	
	@Override
	public void create() {		
		batch = new SpriteBatch();
		ScreenManager.set(new GameScreen());
	}

	@Override
	public void dispose() {
		ScreenManager.get().dispose();
		batch.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0, 0, 1);
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
