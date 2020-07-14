package gameData;

public enum TileValues {
	Grass("darkgrass",true),Block("block",false),TallGrass("tallgrass",true),GrassR("grass",true),Brush("brush",false),NULL("block",false);
	String textureName;
	boolean buildable;
	
	TileValues(String textureName,boolean buildable){
		this.textureName=textureName;
		this.buildable=buildable;
	}
}
