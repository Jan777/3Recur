package entidades;

import gfx.Color;
import gfx.Pantalla;
import nivel.Nivel;
import principal.InputHandler;

public class Jugador extends Grupo {

	private InputHandler input;
	private int color = Color.get(-1, 000, 127, 150);
	
	
	
	public Jugador(Nivel nivel, int x, int y, InputHandler input) {
		super(nivel, "Jugador", x, y, 1);
		this.input = input;

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
		int yTile = 28;
		
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
}
