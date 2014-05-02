package sigseg.game.snm.screen;

public class ScreenManager {

	private static NullScreen nullScreen = new NullScreen();
	private static Screen screen = nullScreen;
	
	public static void set(Screen newScreen) {
		if (screen != nullScreen)
			screen.dispose();
		screen = newScreen;
		screen.create();
	}
	
	public static Screen get() {
		return screen;
	}

}
