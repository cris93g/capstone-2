package gameData;

import static gameHelpers.LibraryHelpers.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import gameFrontend.Drawing;
import gameHelpers.GameHelper;
import gameHelpers.GameHelper.GameState;
public class MenuDisplay {
	private Texture background;
	private Drawing menuUi;
	public MenuDisplay() {
		background= uploadImage("mainmenu");
		menuUi= new Drawing();
		menuUi.addButton("play","playbutton",WIDTH/2-120,(int) (HEIGHT * 0.45f));
		
	}
	//adds play button to the menu
	private void updateButtons() {
		if(Mouse.isButtonDown(0)) {
			if(menuUi.isButtonClicked("play")){
				GameHelper.setState(GameState.GAME);
			}
		}
	}
	public void update() {
		drawFourSidedImage(background,0,0,2048,1024);
		menuUi.draw();
		updateButtons();
	}

}
