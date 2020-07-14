package gameData;
public class MapGrid {
	public Tile[][]map;
	private int tilesWide,tilesHigh;
	public MapGrid() {
		map= new Tile[tilesWide][tilesHigh];
		for (int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				map[i][j]= new Tile(i *64,j*64,64,64,TileValues.Grass);
			}
		}
	}
	//we populate the new map array depending on the case like 0=block BY PUSING A specific tile
	public MapGrid(int[][] newMap) {
		this.tilesHigh=newMap.length;
		this.tilesWide=newMap[0].length;
		map= new Tile[20][15];
		for (int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				switch(newMap[j][i]) {
					case 0:
						map[i][j]= new Tile(i *64,j*64,64,64,TileValues.Block);
						break;
					case 1:
						map[i][j]= new Tile(i *64,j*64,64,64,TileValues.GrassR);
						break;
					case 2:
						map[i][j]= new Tile(i *64,j*64,64,64,TileValues.TallGrass);
						break;
					case 3:
						map[i][j]= new Tile(i *64,j*64,64,64,TileValues.TallGrass);
						break;
					case 4:
						map[i][j]= new Tile(i *64,j*64,64,64,TileValues.Grass);
						break;
					case 5:
						map[i][j]= new Tile(i *64,j*64,64,64,TileValues.Grass);
						break;
				}
			}
		}
		
	}
	
	public void setTile(int xCoord, int yCoord,TileValues type ) {
		map[xCoord][yCoord]= new Tile(xCoord * 64,yCoord*64,64,64,type);
	}
	//helps make sure tile is not out of bounce
	public Tile GetTile(int xPlace,int yPlace) {
		if(xPlace < tilesWide && yPlace<tilesHigh && xPlace > -1 && yPlace>-1) {
			return map[xPlace][yPlace];
		}else {
			return new Tile(0,0,0,0,TileValues.NULL);
		}
	}
	
	public void Draw() {
		for (int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				map[i][j].draw();
			}
		}
	}

	public int getTilesWide() {
		return tilesWide;
	}

	public void setTilesWide(int tilesWide) {
		this.tilesWide = tilesWide;
	}

	public int getTilesHigh() {
		return tilesHigh;
	}

	public void setTilesHigh(int tilesHigh) {
		this.tilesHigh = tilesHigh;
	}
	
}
