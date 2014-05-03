package sigseg.game.snm.screen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sigseg.game.snm.SNMGame;
import sigseg.game.snm.SpriteManager;
import sigseg.game.snm.camera.OrthoCamera;

public class GameOverScreen extends Screen {

	private OrthoCamera camera;
	private Sprite sprite;
	
	public GameOverScreen(boolean won) {
		if (won)
			sprite = SpriteManager.GAME_WON;
		else
			sprite = SpriteManager.GAME_OVER;
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
		sb.draw(sprite, SNMGame.WIDTH / 2 - sprite.getWidth() / 2, SNMGame.HEIGHT / 2 - sprite.getHeight() / 2);
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
