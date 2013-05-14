package com.badlogicgames.superjumper;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "superjumper";
		cfg.width = 320;
		cfg.height = 480;
		//cfg.width = 1920;
		//cfg.height = 1080;
		cfg.useGL20 = true;

		new LwjglApplication(new SuperJumper(), cfg);
	}
}
