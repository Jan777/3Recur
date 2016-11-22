package gfx;

public class Pantalla {
	public static final int ALTO_MAPA = 64;
	public static final int MASCARA_ALTO_MAPA = ALTO_MAPA -1;
	
	
	private int [] tiles = new int[ALTO_MAPA * ALTO_MAPA];
	//contiene informacion sobre que color tiene cada tile
	private int [] colores = new int[ALTO_MAPA * ALTO_MAPA * 4];
	
	//x de la camara e y de la camara para scrolling
	private int xOffset = 0;
	private int yOffset = 0;
	
	private int alto;
	private int ancho;
	
	private SpriteSheet sheet;
	
	public Pantalla(int alto, int ancho, SpriteSheet sheet)
	{
		this.alto = alto;
		this.ancho = ancho;
		this.sheet = sheet;
		
		
		//seteamos datos para el array de colores
		
		for(int i = 0; i < tiles.length; i++)
		{
			//Convertimos los colores del array de coores negro gris gris blanco
			//a colores cyan, amarillo, magenta, y blanco 
			colores[i * 4 + 0] = 0xff00ff;
			colores[i * 4 + 1] = 0x00ffff;
			colores[i * 4 + 2] = 0xffff00;
			colores[i * 4 + 3] = 0xffffff;
		}
	}
	
	public void render(int[]pixels, int offset, int row)
	{
		//imprime los tiles en la pantalla
		for(int yTile = yOffset >> 3; yTile <= (yOffset + ancho) >> 3; yTile++)// >>completa a izquierda con n ceros
		{
			int yMin = yTile * 8 - yOffset;
			int yMax = yMin + 8;
			
			if(yMin < 0)
				yMin = 0;
			if(yMax > ancho)
				yMax = ancho;
			for(int xTile = xOffset >> 3; xTile <= (xOffset + alto) >> 3; xTile++)
			{
				int xMin = xTile * 8 - xOffset;
				int xMax = xMin + 8;
				
				if(xMin < 0)
					xMin = 0;
				if(xMax > alto)
					xMax = alto;
				//Index del tile donde estamos en este momento 
				int tileIndex = (xTile & (MASCARA_ALTO_MAPA)) + (yTile & (MASCARA_ALTO_MAPA)) * ALTO_MAPA; //& es la operacion a nivel bits
				
				for(int y = yMin; y<yMax; y++)
				{
					//Buscamos el lugar donde va a estar nuestro sheet dentro del spritesheet
					int sheetPixel = ((y+yOffset) & 7) * sheet.getAlto() + ((xMin + xOffset) & 7);
					
					//posicion del tile
					int tilePixel = offset + xMin + y * row;
					for(int x = xMin; x < xMax; x++)
					{
						//obtenemo el color que corresponde al tile
						int color = tileIndex * 4 + sheet.getPixels()[sheetPixel++];
						//setea el color al pixel que corresponde al tile
						pixels[tilePixel++] = colores[color];
					}
				}
			}
		}
	}

	public int getxOffset() {
		return xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}
	
}
