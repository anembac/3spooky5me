package com.tda367.infinityrun.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tda367.infinityrun.InfinityRun;

class DesktopLauncher { //Make sure you've set working dir to InfinityRun/core/assets
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "InfinityRun_ALPHA";
		config.width = 1600;
		config.height = 900;
		config.resizable = false;
		config.forceExit=true;
		new LwjglApplication(new InfinityRun(), config);
	}
}

