package gameData;

import org.newdawn.slick.opengl.Texture;

import static gameHelpers.LibraryHelpers.*;
import static gameHelpers.TimeHelper.Delta;

import java.util.ArrayList;
import java.util.Arrays;
public abstract class TowerAbs implements ObjInterf{

	
	private float x,y,timeSinceLastShot,firingSpeed,angle;
	private int width,height,damage,range;
	private Enemy target;
	private Texture[] textures;
	private ArrayList<Enemy> enemies;
	private boolean targeted;
	private ArrayList<Projectile> projectiles;
	
	public TowerAbs(TowerValues type,Tile startTile,ArrayList<Enemy> enemies) {
		this.textures=type.textures;
		this.damage=type.damage;
		this.x=startTile.getX();
		this.y=startTile.getY();
		this.width=startTile.getWidth();
		this.height=startTile.getHeight();
		this.enemies=enemies;
		this.targeted=false;
		this.range=type.range;
		this.timeSinceLastShot=0f;
		this.projectiles=new ArrayList<Projectile>();
		this.firingSpeed=type.firingSpeed;
		this.angle=0;
	}
	//finds the closest person checks all enemys and finds closets
	private Enemy acquireTarget() {
		Enemy closest =null;
		float closestDistance = 10000;
		for(Enemy e: enemies) {
			if(isInRange(e) && findDistance(e)<closestDistance) {
				closestDistance=findDistance(e);
				closest=e;
			}
		}
		if(closest!=null) {
			targeted=true;
		}
		return closest;
	}
	
	//checks if its in range
	private boolean isInRange(Enemy e) {
		float xDistance=Math.abs(e.getX()-x);
		float yDistance=Math.abs(e.getY()-y);
		if(xDistance<range && yDistance<range) {
			return true;
		}
		return false;
		
	}
	//adds x y to find the distance
	private float findDistance(Enemy e) {
		float xDistance=Math.abs(e.getX()-x);
		float yDistance=Math.abs(e.getY()-y);
		return xDistance + yDistance;
	}
	//calculates the angle between tower and enemy
	private float calculateAngle() {
		double angleTemp=Math.atan2(target.getY()-y, target.getX()-x);
		return (float) Math.toDegrees(angleTemp)-90;
	}
	//checks time last shot than adds new projectile shoots at tiles 
	private void shoot() {
		timeSinceLastShot=0;
		projectiles.add(new Projectile(uploadImage("bluebullet"),target,x+Game.TILE_SIZE/2-Game.TILE_SIZE/4,y+Game.TILE_SIZE/2-Game.TILE_SIZE/4,32,32,300,10));
	}
	//checks alive enemy wise
	public void updateEnemyList(ArrayList<Enemy> newList) {
		enemies=newList;
	}
	@Override
	public void update() {
		if(!targeted) {
			target=acquireTarget();
		}
		
		if(target==null || target.isAlive()==false) {
		targeted=false;
		}
		timeSinceLastShot+=Delta();
		if(timeSinceLastShot>firingSpeed) {
			shoot();
		}	
		//Stream lambda
		projectiles.stream().forEach(value->{
			value.update();
		});
				
		angle=calculateAngle();
		draw();
	}

	@Override
	public void draw() {
		drawFourSidedImage(textures[0],x,y,width,height);
		if(textures.length>1) {
			for(int i=1;i<textures.length;i++) {
				drawRotatingImage(textures[i],x,y,width,height,angle);
			}
		}
	}
	
	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public void setX(float x) {
		// TODO Auto-generated method stub
		this.x=x;
		
	}

	@Override
	public void setY(float y) {
		// TODO Auto-generated method stub
		this.y=y;
		
	}

	@Override
	public void setWidth(int width) {
		// TODO Auto-generated method stub
		this.width=width;
		
	}

	@Override
	public void setHeight(int height) {
		// TODO Auto-generated method stub
		this.height=height;
		
	}



}
