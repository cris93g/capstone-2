package gameData;

import static gameHelpers.LibraryHelpers.*;

import org.newdawn.slick.opengl.Texture;
public enum TowerValues {
	
	NormalTower(new Texture[]{uploadImage("towerbase"),uploadImage("idk")},10,1000,3),
	BetterTower(new Texture[]{uploadImage("towerbase"),uploadImage("blueTop")},50,1000,3),
	Guy(new Texture[]{uploadImage("bottomguy"),uploadImage("guy")},100,1000,3),
	RedGuy(new Texture[]{uploadImage("bottomguy"),uploadImage("redGuy")},100,1000,2);
	Texture[] textures;
	int damage;
	int range;
	float firingSpeed;
	
	TowerValues(Texture[] textures,int damage,int range,float firingSpeed){
		this.textures=textures;
		this.damage=damage;
		this.range=range;
		this.firingSpeed=firingSpeed;
	}
}
