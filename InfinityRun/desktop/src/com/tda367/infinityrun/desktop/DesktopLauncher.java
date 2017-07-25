package com.tda367.infinityrun.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tda367.infinityrun.InfinityRun;
import com.tda367.infinityrun.Utils.Constants;

class DesktopLauncher { //Make sure you've set working dir to InfinityRun/core/assets
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "InfinityRun_BETA";
		config.width = Constants.windowWidth;
		config.height = Constants.windowHeight;
		config.resizable = false;
		config.forceExit=true;
		new LwjglApplication(new InfinityRun(), config);
	}
}

