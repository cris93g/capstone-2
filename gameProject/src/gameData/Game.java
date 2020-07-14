package gameData;

import org.lwjgl.input.Mouse;

import gameFrontend.Drawing;

import static gameHelpers.LibraryHelpers.uploadImage;
public class Game {
	private MapGrid grid;
	private Player player;
	private WaveManager waveManager;
	public static final int TILE_SIZE=64;
	private Drawing towerPickerUi;
	private Drawing gam;

	public Game(int[][]map) {
		grid= new MapGrid(map);
		waveManager= new WaveManager(new Enemy(uploadImage("enemy"), grid.GetTile(2, 0),grid,64,64,40,25),2,20);
		player= new Player(grid,waveManager); 
		player.initialValues();
		setupUi();
		
	}
	private void setupUi() {
		gam=new Drawing();
		towerPickerUi=new Drawing();
		towerPickerUi.addButton("baseTower", "idk", 0,0);
		towerPickerUi.addButton("bluebase", "blueTop", 64, 0);
		towerPickerUi.addButton("guy", "guy", 1, 64);
		towerPickerUi.addButton("redGuy", "redGuy", 64, 64);
		
	}
	private void updateUI() {
		towerPickerUi.draw();
		gam.normaldrawString(1200, 0,  Integer.toString(Player.lives));
		gam.normaldrawString(1240, 0, Integer.toString(Player.gold));
		if(Player.lives==0) {
		gam.drawString(600,150, "YOU LOOSE!!!!");
		}
		
		if(waveManager.getCurrentWave().isCompleted()) {
			gam.drawString(600,150, "YOU WINNNN!!!!");
		}
		if(Mouse.next()) {
			boolean mouseClicked=Mouse.isButtonDown(0);
				if(mouseClicked) {
				if(towerPickerUi.isButtonClicked("baseTower")) {
					player.pickTower(new BaseTower(TowerValues.NormalTower,grid.GetTile(0, 0),waveManager.getCurrentWave().getEnemiesList()));
				}
				if(towerPickerUi.isButtonClicked("bluebase")) {
					player.pickTower(new BaseTower(TowerValues.BetterTower,grid.GetTile(0, 0),waveManager.getCurrentWave().getEnemiesList()));
				}
				if(towerPickerUi.isButtonClicked("guy")) {
					player.pickTower(new BaseTower(TowerValues.Guy,grid.GetTile(0, 0),waveManager.getCurrentWave().getEnemiesList()));
				}
				if(towerPickerUi.isButtonClicked("redGuy")) {
					player.pickTower(new BaseTower(TowerValues.RedGuy,grid.GetTile(0, 0),waveManager.getCurrentWave().getEnemiesList()));
				}
			}
		}
	}

	public void update() {
		grid.Draw();
		waveManager.update();
		player.update();	
		updateUI();
	}
}
