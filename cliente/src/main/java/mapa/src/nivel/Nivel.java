package nivel;

import java.util.ArrayList;
import java.util.List;

import entidades.Entidad;
import gfx.Pantalla;
import tiles.Tile;

public class Nivel {

	// array de ids
	private byte[] tiles;

	private int ancho;
	private int alto;
	private List<Entidad> entidades = new ArrayList<Entidad>();
	public Nivel(int ancho, int alto) {
		tiles = new byte[ancho * alto];
		this.ancho = ancho;
		this.alto = alto;
		this.generarNivel();
	}

	public void generarNivel() {
		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {
				
				tiles[x + y * ancho] = Tile.PASTO.getId();
				/*if(x * y % 10 < 5)
				{
					tiles[x + y * ancho] = Tile.PASTO.getId();
				}
				else
				{
					tiles[x + y * ancho] = Tile.EDIFICIO.getId();
				}
				*/
			}
		}
	}
	public void actualizar()
	{
		for (Entidad e : entidades) {
			e.actualizar();
		}
	}
	public void renderTiles(Pantalla pantalla, int xOffset, int yOffset) {
		
		if (xOffset < 0) 
			xOffset = 0;
		
		if (xOffset > ((ancho << 3) - pantalla.getAncho()))
			xOffset = ((ancho << 3) - pantalla.getAncho());
		
		if (yOffset < 0)
			yOffset = 0;
		
		if (yOffset > ((alto << 3) - pantalla.getAlto()))
			yOffset = ((alto << 3) - pantalla.getAlto());
		
		pantalla.setOffset(xOffset, yOffset);

		for (int y = (yOffset >> 3); y < (yOffset + pantalla.getAlto() >> 3) + 1 ; y++)
		{
			for (int x = (xOffset >> 3); x < (xOffset + pantalla.getAncho() >> 3) + 1 ; x++) {
				getTile(x, y).render(pantalla, this, x << 3, y << 3);
			}
		}

	}
	public void renderizarEntidades(Pantalla pantalla)
	{
		for (Entidad e : entidades) {
			e.renderizar(pantalla);
		}
	}
	private Tile getTile(int x, int y)
	{
		if(x < 0 || x >= ancho || y < 0 || y >= alto)
			return Tile.VOID;
		return Tile.tiles[tiles[x + y * ancho]];
	}
	
	public int getAncho()
	{
		return ancho;
	}
	public int getAlto()
	{
		return alto;
	}
	
	public void addEntidad(Entidad entidad)
	{
		this.entidades.add(entidad);
	}
}
