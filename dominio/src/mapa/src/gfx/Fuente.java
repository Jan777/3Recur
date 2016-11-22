package gfx;

public class Fuente {
	
	private static String chars = ""+"ABCDEFGHIJKLMNOPQRSTUVWXYZ      "+
	"0123456789.,:;\'\"!?$%()-=+/      ";
	
	public static void render(String msj, Pantalla pantalla, int x, int y, int color)
	{
		msj = msj.toUpperCase();
		for(int i = 0; i < msj.length(); i++)
		{
			
			int charIndex = chars.indexOf(msj.charAt(i));
			if(charIndex >= 0)
			{
				pantalla.render(x+(i*8),y, charIndex + 30 * 32 , color);
			}
		}
	}

}
