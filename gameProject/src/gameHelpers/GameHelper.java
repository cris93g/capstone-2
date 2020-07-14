package gameHelpers;


import gameData.Game;
import gameData.MenuDisplay;

public class GameHelper {
	public static enum GameState{
		MAINMENU,GAME,EDITOR
	}
	public static GameState gameState=GameState.MAINMENU;
	public static MenuDisplay mainMenu;
	public static Game game;
	
	
	static int[][]map= {
			{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3},
			{0,0,1,0,0,0,3,3,0,0,1,1,1,1,1,1,1,0,3,3},
			{0,0,1,0,0,0,1,1,1,1,1,0,0,0,0,0,1,0,3,3},
			{0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,3,0},
			{0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,0,0},
			{0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,3,3},
			{0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,3,3},
			{0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,3,3,3,3},
			{0,0,1,1,1,1,1,0,0,0,3,3,0,0,1,0,3,3,3,3},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3},
			{0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	};
	
	public static void update() {
		switch(gameState) {
		case MAINMENU:
			if(mainMenu==null) {
				mainMenu= new MenuDisplay();
			}
			mainMenu.update();
			break;
		case GAME:
			if(game==null) {
				game=new Game(map);
				
			}
			game.update();
			break;
		default:
			break;
		
		}
	}
	public static void setState(GameState newState) {
		gameState=newState;
	}
}
