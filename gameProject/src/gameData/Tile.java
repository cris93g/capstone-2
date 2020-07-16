package gameData;

import static gameHelpers.LibraryHelpers.*;

import org.newdawn.slick.opengl.Texture;
public class Tile implements ObjInterf{
	private float x,y;
	private int width,height;
	private Texture texture;
	private TileValues type;
	public Tile(float x,float y, int width,int height,TileValues type) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.type=type;
		this.texture=uploadImage(type.textureName);
		
	}
	public int getXplace() {
		return(int)x /64;
	}
	public int getYplace() {
		return(int)y /64;
	}
	public void draw() {
		drawFourSidedImage(texture,x,y,width,height);
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
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public TileValues getType() {
		return type;
	}
	public void setType(TileValues type) {
		this.type = type;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
