package sigseg.game.snm;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class TextureManager {

	public static Sprite PLAYER = JohnGame.inst.atlas.createSprite("player");
	public static Sprite MISSILE = JohnGame.inst.atlas.createSprite("missile");
	public static Sprite ENEMY = JohnGame.inst.atlas.createSprite("enemy");
	public static Sprite GAME_OVER = JohnGame.inst.atlas.createSprite("gameover");
	public static Sprite GAME_WON = JohnGame.inst.atlas.createSprite("gamewon");
//	public static Texture PLAYER = new Texture(Gdx.files.internal("player.png"));
//	public static Texture MISSILE = new Texture(Gdx.files.internal("missile.png"));
//	public static Texture ENEMY = new Texture(Gdx.files.internal("enemy.png"));
//	public static Texture GAME_OVER = new Texture(Gdx.files.internal("gameover.png"));
//	public static Texture GAME_WON = new Texture(Gdx.files.internal("gamewon.png"));
	
}
