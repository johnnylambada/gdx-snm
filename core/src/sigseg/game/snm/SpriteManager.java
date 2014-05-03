package sigseg.game.snm;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteManager {

	public static Sprite PLAYER = SNMGame.inst.atlas.createSprite("s01S");
	public static Sprite MISSILE = SNMGame.inst.atlas.createSprite("missile");
	public static Sprite ENEMY = SNMGame.inst.atlas.createSprite("enemy");
	public static Sprite GAME_OVER = SNMGame.inst.atlas.createSprite("gameover");
	public static Sprite GAME_WON = SNMGame.inst.atlas.createSprite("gamewon");
}
