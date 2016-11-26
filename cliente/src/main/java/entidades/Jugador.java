package entidades;

import character.PC;
import gfx.Color;
import gfx.Fuente;
import gfx.Pantalla;
import nivel.Nivel;
import principal.InputHandler;

public class Jugador extends Grupo{

	private InputHandler input;
	private int color1;
	private int color2;
	private int color3;
	private int ySprite;
	private PC pj;
	private int color;
	
	public Jugador(PC pj, Nivel nivel, InputHandler input) {
		super(nivel, pj.getName(), pj.getX(), pj.getY(), 1);
		if(pj.getRaza().equals("Mutante"))
		{
			color1 =  0;
			color2 = 550;
			color3 = 114;
			ySprite = 26;
			
			
		}else if(pj.getRaza().equals("Hulk"))
		{
			color1 =  0;
			color2 = 127;
			color3 = 150;
			ySprite = 28;
			
		}else if(pj.getRaza().equals("Asgardiano"))
		{
			color1 = 0;
			color2 = 550;
			color3 = 542;
			ySprite = 24;
		}
		this.input = input;
		this.color = Color.get(-1, color1, color2, color3);
		this.pj = pj;
	}

	@Override
	public void actualizar() {
		int xa = 0;
		int ya = 0;
		
		// TECLADO

		if (input.getArriba().estaPresionada()) {
			ya--;
		}
		if (input.getAbajo().estaPresionada()) {
			ya++;
		}
		if (input.getIzquierda().estaPresionada()) {
			xa--;
		}
		if (input.getDerecha().estaPresionada()) {
			xa++;
		}
		// FIN TECLADO
				// MOUSE
				/*
				 * if(input.getClick()) { //VER COMO HACER PARA QUE EL PERSONAJE SE
				 * MUEVA DE A POCO Y NO SEA UN PARPADEO DE UN PUNTO A OTRO, //SI NO QUE
				 * RECORRA UN CAMINO (DIJKTRA?) x+=input.getDestino().x;
				 * y+=input.getDestino().y; input.setClick(false); } //FIN MOUSE
				 */
		
		//si se esta moviendo en alguna direccion
		if(xa != 0 || ya != 0)
		{
			mover(xa, ya);
			pj.setX(this.x);
			pj.setY(this.y);
			enMovimiento = true;
			
		}
		else
		{
			enMovimiento = false;
		}
		
		this.escala = 1;
	}

	@Override
	public void renderizar(Pantalla pantalla) {
		int xTile = 0;
		int yTile = ySprite;
		
		//Parametros para animacion
		int velocidadCaminar = 4;
		int flipTop = (numPasos >> velocidadCaminar) & 1;
		int flipBottom = (numPasos >> velocidadCaminar) & 1;
		if(direccionMovimiento ==  1)
		{
			xTile+=2;
		}
		else if(direccionMovimiento > 1)
		{
			xTile += 4 + ((numPasos >> velocidadCaminar) & 1) * 2;
			flipTop = (direccionMovimiento -1) % 2;
			
		}
		//modifica el tamaño del tile que estamos renderizando-
		int modificador = 8*escala;
		
		int xOffset = x - modificador / 2;
		int yOffset = y - modificador / 2 - 4;
		//renderizamos el personaje
		pantalla.render(xOffset + (modificador * flipTop), yOffset, xTile + yTile * 32, color, flipTop,escala);
		
		pantalla.render(xOffset + modificador - (modificador * flipTop), yOffset, (xTile + 1) + yTile * 32, color, flipTop,escala);
		
		pantalla.render(xOffset + (modificador * flipBottom), yOffset + modificador, xTile + (yTile + 1) * 32, color, flipBottom,escala);
		
		pantalla.render(xOffset+ modificador - (modificador * flipBottom), yOffset + modificador, (xTile+1) + (yTile+1) * 32, color, flipBottom,escala);
		// renderizamos el nombre de usuario
		
		if(pj.getName() != null)
		{
			Fuente.render(pj.getName(), pantalla, xOffset - ((pj.getName().length()-1)/2 * 8), yOffset - 10,Color.get(-1, -1, -1, 555), 1);
		}
	}


	@Override
	public boolean colision(int xa, int ya) {
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
		//ciclos para recorrer las cuatro direcciones del sector del sprite que va a ser caja colision
		for(int x = xMin; x < xMax; x++)
		{
			if(esTileSolido(xa,ya,x,yMin))
				return true;
		}
		for(int x = xMin; x < xMax; x++)
		{
			if(esTileSolido(xa,ya,x,yMax))
				return true;
		}
		for(int y = yMin; y < yMax; y++)
		{
			if(esTileSolido(xa,ya,xMin,y))
				return true;
		}
		for(int y = yMin; y < yMax; y++)
		{
			if(esTileSolido(xa,ya,xMax,y))
				return true;
		}
		

		return false;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
		
	}
	public void setXY(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public Nivel getNivel()
	{
		return this.nivel;
	}
	public InputHandler getInput()
	{
		return this.input;
	}

	@Override
	public String getName() {
		return pj.getName();
	}

}
