package sigseg.game.snm.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sigseg.game.snm.JohnGame;
import sigseg.game.snm.TextureManager;
import sigseg.game.snm.camera.OrthoCamera;

public class GameOverScreen extends Screen {

	private OrthoCamera camera;
	private Sprite texture;
	
	public GameOverScreen(boolean won) {
		if (won)
			texture = TextureManager.GAME_WON;
		else
			texture = TextureManager.GAME_OVER;
	}
	
	@Override
	public void create() {
		camera = new OrthoCamera();
		camera.resize();
	}

	@Override
	public void update() {
		camera.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		sb.draw(texture, JohnGame.WIDTH / 2 - texture.getWidth() / 2, JohnGame.HEIGHT / 2 - texture.getHeight() / 2);
		sb.end();
	}

	@Override
	public void resize(int width, int height) {
		camera.resize();
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

}
