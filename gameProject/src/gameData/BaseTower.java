package gameData;

import java.util.ArrayList;

public class BaseTower extends TowerAbs{
	//values get passedDown from tower abstract
	public BaseTower(TowerValues type,Tile startTile,ArrayList<Enemy> enemies) {
		super(type,startTile,enemies);
	}
}
