package sigseg.game.snm;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteManager {

	public static Sprite PLAYER = JohnGame.inst.atlas.createSprite("player");
	public static Sprite MISSILE = JohnGame.inst.atlas.createSprite("missile");
	public static Sprite ENEMY = JohnGame.inst.atlas.createSprite("enemy");
	public static Sprite GAME_OVER = JohnGame.inst.atlas.createSprite("gameover");
	public static Sprite GAME_WON = JohnGame.inst.atlas.createSprite("gamewon");
}
