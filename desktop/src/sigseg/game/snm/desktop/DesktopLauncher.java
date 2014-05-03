package sigseg.game.snm.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import sigseg.game.snm.SNMGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SNMGame.WIDTH;
		config.height= SNMGame.HEIGHT;
		new LwjglApplication(new SNMGame(), config);
	}
}
