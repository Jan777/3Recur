package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	private int ancho;
	private int alto;
	//array de rgb (informacion de colores)
	private int[] pixels;
	
	public SpriteSheet(String path)
	{
		BufferedImage imagen = null;
		//Obtenemos una referencia local a la imagen .png de la carpeta recursos
		try {
			imagen = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//solo por si no entra al bloque try/catch
		if(imagen == null)
		{
			return;
		}
		this.path = path;
		this.ancho = imagen.getWidth();
		this.alto = imagen.getHeight();
		
		pixels = imagen.getRGB(0, 0, ancho, alto, null, 0, ancho); 
		
		for(int i = 0; i<pixels.length; i++)
		{
			pixels[i] = (pixels[i] & 0xff)/64; //eliminamos el canal alpha(transparencias) y dividimos por 64 para
											//que nos complete el array pixels con 0,1,2,3,4
						 					//es para referirnos a cada color (4 colores_negro_gris oscuro_gris claro_blanco)
		}
		
		
		
	}
	public String getPath()
	{
		return this.path;
	}
	
	public int getAlto()
	{
		return this.ancho;
	}
	public int getAncho()
	{
		return this.alto;
	}
	public int[] getPixels()
	{
		return this.pixels;
	}
}
