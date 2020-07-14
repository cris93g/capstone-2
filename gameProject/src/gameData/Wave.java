package gameData;

import static gameHelpers.TimeHelper.*;

import java.util.ArrayList;

public class Wave {
	private float timeSinceLastSpawn, spawnTime;
	private Enemy enemyType;
	private ArrayList<Enemy> enemyList;
	private int  enemiesPerWave;
	private boolean waveCompleted;
	public Wave( Enemy enemyType,float spawnTime,int enemiesPerWave) {
		this.enemyType = enemyType;
		this.spawnTime = spawnTime;
		this.enemiesPerWave=enemiesPerWave;
		this.timeSinceLastSpawn = 0;
		this.enemyList = new ArrayList<Enemy>();
		this.waveCompleted=false;
		Spawn();
	}
	//respawns the enemy
	public void update() {
		boolean allEnemiesDead=true;
		if(enemyList.size()<enemiesPerWave) {
			timeSinceLastSpawn+=Delta();
			if(timeSinceLastSpawn>spawnTime) {
				Spawn();
				timeSinceLastSpawn=0;
		}
		}
	
		for(Enemy e : enemyList) {
			if(e.isAlive()) {
			allEnemiesDead=false;
			e.update();
			e.draw();
			} 
			
		}
		if(allEnemiesDead) {
			waveCompleted=true;
		}
	}
	//adds new enemy well draws them
	private void Spawn() {
		enemyList.add(new Enemy(enemyType.getTexture(), enemyType.getStartTile(), enemyType.getTileGrid(), 64, 64,
				enemyType.getSpeed(),enemyType.getHealth()));
	}
	
	public boolean isCompleted() {
		return waveCompleted;
	}
	
	public ArrayList<Enemy> getEnemiesList(){
		return enemyList;
	}

}
