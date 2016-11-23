package gfx;

public class Pantalla {
	public static final int ANCHO_MAPA = 64;
	public static final int MASCARA_ANCHO_MAPA = ANCHO_MAPA -1;
	
	public static final byte BIT_ESPEJO_X = 0x01;
	public static final byte BIT_ESPEJO_Y = 0x02;
	
	
	
	//contiene informacion de colores 
	private int [] pixels;
	
	//x de la camara e y de la camara para scrolling
	private int xOffset = 0;
	private int yOffset = 0;
	
	private int ancho;
	private int alto;
	
	private SpriteSheet sheet;
	
	public Pantalla(int ancho, int alto, SpriteSheet sheet)
	{
		this.ancho = ancho;
		this.alto = alto;
		this.sheet = sheet;
		
		pixels = new int [ancho * alto];
		
		
	}
	
	
	//el tileIndex representa el numero de tile del spriteSheet que se va a utilizar.
	//los parametros espejo son para invertir la imagen en esa direccion
	public void render(int xPos, int yPos, int tileIndex, int color, int espejoDir, int escala)
	{
		//asignamos el valor al espejo del sprite true o false
		int escalaMapa = escala -1;
		boolean espejoX = (espejoDir & BIT_ESPEJO_X) > 0;
		boolean espejoY = (espejoDir & BIT_ESPEJO_Y) > 0;
		xPos -= xOffset ;
		yPos -= yOffset ;
		
		//Tratamos el spritesheet como una matriz donde cada tile (baldoza) estara identificada por un unico numero
		//que calcularemos a continuacion (recordar que el spritesheet es de 32 x 32 tiles
		
		//para obtener la coordenada en x sacamos el resto de dividir el index por 32
		//para obtener la coordenada en y dividimos el index por 32
		int xTile = tileIndex % 32;
		int yTile = tileIndex / 32;
		
		int tileOffset = (xTile << 3) + (yTile<<3) * sheet.getAncho();
		//con estos dos ciclos recorremos el tile que es de 8 pixels por 8 pixels dentro del spritesheet y obtenemos su contenido.
		for(int y = 0; y< 8; y++)
		{
			int ySheet = y;
			if(espejoY) 
			{
				ySheet = 7 - y;
			}
			int yPixel = y + yPos +(y * escalaMapa) - ((escalaMapa << 3)/2); //centrado de pixels;
			
			for(int x = 0; x<8; x++)
			{
				
				int xSheet = x;
				if(espejoX) 
				{
					xSheet = 7 - x;
				}
				
				int xPixel = x + xPos + (x * escalaMapa) - ((escalaMapa << 3)/2);
				//La siguiente linea nos da el color correspondiente del array de colores relativo al tile donde estoy.
				int col = (color >> (sheet.getPixels()[xSheet + ySheet * sheet.getAncho() + tileOffset]* 8)) & 255;
				//renderizamos cada pixel, si la escala es 1 renderizamos de a uno si es dos, renderizamos dos...
				if(col<255){
					for(int escalaY = 0; escalaY < escala; escalaY++){
					//si no me fui del rango del tile en y
						if(yPixel + escalaY < 0 || yPixel + escalaY >= alto)
							continue;
						for(int escalaX = 0; escalaX < escala; escalaX++)
						{
							if(xPixel + escalaX < 0 || xPixel + escalaX >= ancho)
								continue;
							pixels[(xPixel + escalaX) + (yPixel + escalaY) * ancho] = col;
						}
							
					}
					
				
					
				}
			}
		}
		
		
		
	}
	
	public void setOffset(int xOffset, int yOffset)
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	public int getxOffset() {
		return xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}
	public int getAncho() {
		return ancho;
	}
	public int getAlto() {
		return alto;
	}
	public int[] getPixels() {
		return pixels;
	}
	
}
