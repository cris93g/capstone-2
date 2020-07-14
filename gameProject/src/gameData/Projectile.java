package gameData;

import static gameHelpers.LibraryHelpers.*;
import static gameHelpers.TimeHelper.*;

import org.newdawn.slick.opengl.Texture;
public class Projectile implements ObjInterf{
	private Texture texture;
	private float x,y,speed,xVelocity,yVelocity;
	private int damage,width,height;
	private boolean alive;
	private Enemy target;
	public Projectile(Texture texture,Enemy target, float x, float y,int width,int height, float speed,int damage) {
		this.texture=texture;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.speed=speed;
		this.damage=damage;
		this.target=target;
		this.alive=true;
		this.xVelocity=0;
		this.yVelocity=0;
		calculateDirection();
		
	}
	//checks the distance between enemy and tower
	private void calculateDirection() {
		float totalAllowedMovement=1.0f;
		//gets distance and turns it positive
		float xDistanceFromTarget=Math.abs(target.getX()-x-Game.TILE_SIZE/4 +Game.TILE_SIZE/2);
		float yDistanceFromTarget=Math.abs(target.getY()-y-Game.TILE_SIZE/4+Game.TILE_SIZE/2);
		float totalDistanceFromTarget= xDistanceFromTarget+yDistanceFromTarget;
		float xPercentOfMovement=xDistanceFromTarget/totalDistanceFromTarget;
		xVelocity=xPercentOfMovement;
		yVelocity=totalAllowedMovement-xPercentOfMovement;
		if(target.getX()<x) {
			xVelocity*=-1;
		}
		if(target.getY()<y) {
			yVelocity*=1;
		}
	}
	//if alive 
	public void update () {
		if(alive) {
			x+=xVelocity * speed* Delta() ;
			y+= yVelocity * speed*Delta();
			if(checkCollision(x,y,width,height,target.getX(),target.getY(),target.getWidth(),target.getHeight())) {
				target.damage(damage);
				alive=false;
			}
			
			draw();
		}
	}
	//draws 4 sided shaped
	public void draw() {
		drawFourSidedImage(texture,x,y,32,32);
	}
	@Override
	public float getX() {
		// TODO Auto-generated method stub
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
