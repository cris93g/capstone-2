package gameData;

import org.newdawn.slick.opengl.Texture;

import static gameHelpers.LibraryHelpers.*;
import static gameHelpers.TimeHelper.*;

import java.util.ArrayList;

public class Enemy implements ObjInterf{
	private int width,height,currentCheckpoint;
	private float speed,x,y,health,startHealth;
	private Tile startTile;
	private Texture texture,healthBackground,healthForeground,healthBorder;
	private MapGrid grid;
	private boolean first=true,alive=true;
	private ArrayList<Checkpoint> checkpoints;
	private int[] directions;
	
	public Enemy(Texture texture,Tile startTile,MapGrid grid,int width,int height,float speed,float health) {
		this.texture=texture;
		this.healthBackground=uploadImage("healthbackground");
		this.healthForeground=uploadImage("healthforeground");
		this.healthBorder=uploadImage("healthborder");
		this.startTile=startTile;
		this.x=startTile.getX();
		this.y=startTile.getY();
		this.width=width;
		this.height=height;
		this.speed=speed;
		this.health=health;
		this.startHealth=health;
		this.grid=grid;
		this.checkpoints=new ArrayList<Checkpoint>();
		this.directions=new int[2];	
		//x
		this.directions[0]=0;
		//y
		this.directions[1]=0;
		directions=findNextDirection(startTile);
		
		this.currentCheckpoint=0;
		populateCheckpointList();
		
	}

//checks if the enemy reached the checkpoints size or end if not keep going 
	public void update() {
		if(first) {
			first=false;
			}
		else {
			if(checkPointReached()) {
				if(currentCheckpoint +1 == checkpoints.size()) {
					endReach();
				
				}else {
					currentCheckpoint++;
				}
			}else {
				x+= Delta() * checkpoints.get(currentCheckpoint).getxDirection()*speed;
				y+=Delta() * checkpoints.get(currentCheckpoint).getyDirection()*speed;
			}
		}
	}
	//if enemy reaches the end it dies and player looses a live
	private void endReach() {
		Player.modifyLives(-1);
		Die();
	}
	//checks if the enemy is 3 before or 3 after to get his position
	private boolean checkPointReached() {
		boolean reached=false;
		Tile t = checkpoints.get(currentCheckpoint).getTile();
		//check if posotopm reached tile variance of 3
		if(x>t.getX()-3 
				&& x< t.getX()+3
				&& y> t.getY()-3 
				&& y< t.getY()+3 ) {
			reached=true;
			x=t.getX();
			y=t.getY();
		}
		return reached;
	}
	//adds new checkpoints
	private void populateCheckpointList() {
		checkpoints.add(findNextCheckpoint(startTile,directions=findNextDirection(startTile)));
		int counter=0;
		boolean cont=true;
		while(cont) {
			int[] currentD=findNextDirection(checkpoints.get(counter).getTile());
			//check if next direction/checkpint exist end after 20 checkpoints
			if(currentD[0]== 2 || counter==20) {
				cont=false;
			}else {
				checkpoints.add(findNextCheckpoint(checkpoints.get(counter).getTile(),
						directions=findNextDirection(checkpoints.get(counter).getTile())));
			}
			counter++;
		}
	}
	//finds next tile
	//loops throught the tiles depending on the directions and puts the new checkpoint
	private Checkpoint findNextCheckpoint(Tile s,int[] dir) {
		Tile next=null;
		Checkpoint c =null;
		//boolean to decide if next checkpoint is found
		boolean found=false;
		int counter=1;
		while(!found) {
			if(s.getXplace()+dir[0] * counter == grid.getTilesWide() || 
					s.getYplace()+dir[1] * counter == grid.getTilesHigh()||
 					s.getType()!= 
					grid.GetTile(s.getXplace()+dir[0] * counter,
					s.getYplace()+dir[1] * counter).getType()) {
				found=true;
				counter-=1;
				next=grid.GetTile(s.getXplace()+dir[0] * counter,
						s.getYplace()+dir[1] * counter);
						
			}
			counter++;
		}
		c=new Checkpoint(next,dir[0],dir[1]);
		return c;
		
	}
	//find the next direction up down left right also in the end checks if tile is out of bounds
	//checks if next tile is the same type if it is than u move to that one
	private int[] findNextDirection(Tile s) {
		int[] dir = new int[2];
		Tile u=grid.GetTile(s.getXplace(),s.getYplace()-1);
		Tile r=grid.GetTile(s.getXplace()+1,s.getYplace());
		Tile d=grid.GetTile(s.getXplace(),s.getYplace()+1);
		Tile l=grid.GetTile(s.getXplace()-1,s.getYplace());
		if(s.getType()==u.getType()&& directions[1] !=1) {
			dir[0]=0;
			dir[1]=-1;
		}else if(s.getType()==r.getType()&& directions[0] !=-1) {
			dir[0]=1;
			dir[1]=0; 
		}else if(s.getType()==d.getType()&& directions[1] !=-1) {
			dir[0]=0;
			dir[1]=1; 
		}else if(s.getType()==l.getType()&& directions[0] !=1) {
			dir[0]=-1;
			dir[1]=0; 
		}else {
			dir[0]=2;
			dir[1]=2;
		}
		
		return dir;
	}
	//damage method
	public void damage(int ammount) {
		health-=ammount;
		if(health<=0) {
			Die();
			Player.modifyGold(5);
		}
	}
	//die method to the enemy
	private void Die() {
		alive=false;
	}
	//draws health bar
	public void draw() {
		float healthPercentage=health/startHealth;
		drawRotatingImage(texture,x,y,width,height,0);
		drawFourSidedImage(healthBackground,x,y-16,width,8);
		drawFourSidedImage(healthForeground,x,y-16,64*healthPercentage,8);
		drawFourSidedImage(healthBorder,x,y-16,width,8);
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public float getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public Tile getStartTile() {
		return startTile;
	}
	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public boolean isFirst() {
		return first;
	}
	public void setFirst(boolean first) {
		this.first = first;
	}
	
	public MapGrid getTileGrid() {
		return grid;
	}
	public boolean isAlive() {
		return alive;
	}
	
}
