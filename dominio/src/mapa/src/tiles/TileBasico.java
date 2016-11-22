package tiles;

import gfx.Pantalla;
import nivel.Nivel;

public class TileBasico extends Tile{

	
	protected int tileId;
	protected int tileColor;
	
	public TileBasico(int id, int x, int y, int tileColor)
	{
		super(id, false, false);
		this.tileId = x + y * 32;
		this.tileColor = tileColor;
	}
	
	@Override
	public void render(Pantalla screen, Nivel level, int x, int y) {
		screen.render(x, y, tileId, tileColor);
	}
	

}
