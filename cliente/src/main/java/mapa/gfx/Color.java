package gfx;



public class Color {
	
	//recibe numeros del 0 al 5 y los convierte en colores rgb y mezcla los 4 para obtener gama de colores
	public static int get(int color1, int color2, int color3, int color4)
	{
		return (get(color4) << 24) +(get(color3) <<16) + (get(color2)<<8) + (get(color1));
	}

	private static int get(int color) {
		if(color <  0) return 255; // si es negativo, retornamos color transparente
		
		//de un numero de tres cifras obtenemos la primera para r, la segunda para g, la tercera para b
		int r = color / 100 % 10;
		int g = color / 10 % 10;
		int b = color % 10;
		return r * 36 + g * 6 + b;
	}
	static{
		Color.get(555,543,542,123);
	}
}
