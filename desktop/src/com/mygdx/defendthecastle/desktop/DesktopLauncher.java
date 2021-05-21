package com.mygdx.defendthecastle.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.defendthecastle.defendTheCastle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Defend The Castle!");
		config.setWindowedMode(1152,648);
		config.setForegroundFPS(60);
		new Lwjgl3Application(new defendTheCastle(), config);
	}
}
