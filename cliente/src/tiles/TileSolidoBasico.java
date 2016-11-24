package tiles;

public class TileSolidoBasico extends TileBasico{

	//Tile color: refiere al color del tile actual
	//nivelColor : refiere al index en el nivel (level tile)
	public TileSolidoBasico(int id, int x, int y, int tileColor, int nivelColor) {
		super(id, x, y, tileColor, nivelColor);
		this.solido = true;
		
	}

}
