package gameData;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import gameFrontend.Drawing;
import gameHelpers.TimeHelper;

import static gameHelpers.LibraryHelpers.*;

import java.util.ArrayList;
public class Player {
	private boolean leftMouseButtonDown,rightMouseButtonDown,holdingTower;
	private MapGrid grid;
	private TileValues[] types;
	private int index;
	public static int gold,lives;
	private TowerAbs tempTower;
	private WaveManager waveManager;
	private ArrayList<TowerAbs> towerList;
	public Player(MapGrid grid,WaveManager waveManager) {
		this.grid=grid;
		this.types=new TileValues[3];
		this.types[0]= TileValues.GrassR;
		this.types[1]= TileValues.GrassR;
		this.types[2]= TileValues.Block;
		this.index=0;
		this.waveManager=waveManager;
		this.towerList=new ArrayList<TowerAbs>();
		this.leftMouseButtonDown=false;
		this.rightMouseButtonDown=false;
		this.holdingTower=false;
		this.tempTower=null;
		gold=0;
		lives=0;
		
		
		
	}
	//if we have a tower we can add one to our array also reduces ur gold
	public void placeTower() {
		if(holdingTower) {
			if(modifyGold(-20)) {
				towerList.add(tempTower);	
			}else {
				System.out.println("not enough money");
			}
		}
		holdingTower=false;
		tempTower=null;
	}
	
	//initial gold and lives
	public void initialValues() {
		gold=50;
		lives=5;
	}
	//adds gold
	public static boolean modifyGold(int ammount) {
		if(gold+ammount>=0) {
			gold+=ammount;
			return true;
		}
		return false;
	}
	//method to reduce lives
	public static void modifyLives(int ammount) {
		lives+=ammount;
	}
	//probably wont use but u can modify tiles
	public void setTile() {
		grid.setTile((int)Math.floor(Mouse.getX()/32),
				(int)Math.floor((HEIGHT- Mouse.getY()-5)/32), types[index]);
	}
	

	//lambda
	public void update() {
		if(holdingTower) {
			tempTower.setX(getTileHovered().getX());
			tempTower.setY(getTileHovered().getY());
			tempTower.draw();
		}
		
		towerList.stream().forEach((value)->{
			value.update();
			value.draw();
			value.updateEnemyList(waveManager.getCurrentWave().getEnemiesList());
		});
		
		//handle mouse
		if(Mouse.isButtonDown(0) && !leftMouseButtonDown) {
			placeTower();
		}			
		
		leftMouseButtonDown=Mouse.isButtonDown(0);
		rightMouseButtonDown=Mouse.isButtonDown(1);

	}
	//gets the current tower u clicked on
	public void pickTower(TowerAbs t) {
		tempTower=t;
		holdingTower=true;
	}
	//gets the tile the mouse is on
	private Tile getTileHovered() {
		return grid.GetTile(Mouse.getX()/64,(HEIGHT-Mouse.getY()-1)/64);
	}
	
	
}
