package sigseg.game.snm.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * NullScreen does exactly nothing -- it's there to avoid nullchecks everywhere
 */
public class NullScreen extends Screen{
	@Override public void create() {}
	@Override public void update() {}
	@Override public void render(SpriteBatch sb) {}
	@Override public void resize(int width, int height) {}
	@Override public void dispose() {}
	@Override public void pause() {}
	@Override public void resume() {}
}
