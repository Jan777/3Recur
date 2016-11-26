package tiles;

import gfx.Pantalla;
import nivel.Nivel;

public class TileBasico extends Tile{

	
	protected int tileId;
	protected int tileColor;
	
	//Tile color: refiere al color del tile actual
	//nivelColor : refiere al index en el nivel (level tile)
	public TileBasico(int id, int x, int y, int tileColor, int nivelColor)
	{
		super(id, false, false, nivelColor);
		this.tileId = x + y * 32;
		this.tileColor = tileColor;
	
	}
	
	@Override
	public void render(Pantalla screen, Nivel level, int x, int y) {
		screen.render(x, y, tileId, tileColor,0x00,1);
	}
	

}
