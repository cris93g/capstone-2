package gameData;

public class WaveManager {
	private float timeSinceLastWave,timeBetweenEnemies;
	private int waveNumber,enemiesPerWave;
	private Wave currentWave;
	private Enemy enemyType;
	public WaveManager(Enemy enemyType,float timeBetweenEnemies,int enemiesPerWave) {
		this.enemyType=enemyType;
		this.timeBetweenEnemies=timeBetweenEnemies;
		this.enemiesPerWave=enemiesPerWave;
		this.timeSinceLastWave=0;
		this.waveNumber=0;
		this.currentWave=null;
		newWave();
	}
	//if u kill them all u win
	public void update() {
		if(!currentWave.isCompleted()) {
			currentWave.update();
		}else {
			System.out.println("you have won");
		}
	}
	//if u want to add new wave
	private void newWave() {
		currentWave= new Wave(enemyType,timeBetweenEnemies,enemiesPerWave);
		waveNumber++;
	}
	public Wave getCurrentWave() {
		return currentWave;
	}
}
