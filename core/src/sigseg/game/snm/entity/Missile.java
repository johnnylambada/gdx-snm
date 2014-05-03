package sigseg.game.snm.entity;

import com.badlogic.gdx.math.Vector2;
import sigseg.game.snm.JohnGame;
import sigseg.game.snm.SpriteManager;

public class Missile extends Entity {

	public Missile(Vector2 pos) {
		super(SpriteManager.MISSILE, pos, new Vector2(0, 5));
	}

	@Override
	public void update() {
		pos.add(direction);
	}
	
	public boolean checkEnd() {
		return pos.y >= JohnGame.HEIGHT;
	}
	
}
