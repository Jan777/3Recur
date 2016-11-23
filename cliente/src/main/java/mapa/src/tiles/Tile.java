package tiles;

import gfx.Color;
import gfx.Pantalla;
import nivel.Nivel;

public abstract class Tile {
	
	public static final Tile[] tiles = new Tile[256];
	public static final Tile VOID = new TileSolidoBasico(0,0,0, Color.get(000, -1, -1, -1));
	public static final Tile PASTO = new TileBasico(2,2,0, Color.get(-1, 131, 141, -1));
	public static final Tile EDIFICIO = new TileSolidoBasico(1,1,0, Color.get(-1, 333, -1, -1));
	//public static final Tile CALLE = new TileBasico(1,1,0, Color.get(-1, 222, -1, -1));
	protected byte id;
	protected boolean solido;
	protected boolean emitter;
	
	public Tile(int id, boolean esSolido, boolean esEmitter)
	{
		this.id = (byte)id;
		if(tiles[id] != null) throw new RuntimeException("Duplicate tile id on "+id);
		tiles[id] = this;
		this.solido = esSolido;
		this.emitter = esEmitter;
		
	}
	
	public byte getId()
	{
		return id;
	}
	public boolean esSolido()
	{
		return solido;
	}
	public boolean esEmitter()
	{
		return emitter;
	}
	
	//public abstract void actualizar();
	
	public abstract void render(Pantalla screen, Nivel level, int x, int y);
	

	
}
