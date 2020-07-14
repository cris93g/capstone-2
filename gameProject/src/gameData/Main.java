package gameData;


import static gameHelpers.LibraryHelpers.*;

import org.lwjgl.opengl.Display;

import gameHelpers.GameHelper;
import gameHelpers.TimeHelper;



public class Main {	
	public Main() {
		Start();
		//while we do not close the window were showing our game
		while(!Display.isCloseRequested()) {
			TimeHelper.update();
			GameHelper.update();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	//main run method
	public static void main(String[] args) {
		new Main();
	}

}
