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
		//modifica el tamaño del tile que estamos renderizando-
		int modificador = 8*escala;
		
		int xOffset = x - modificador / 2;
		int yOffset = y - modificador / 2 - 4;
		//renderizamos el personaje
		pantalla.render(xOffset, yOffset, xTile + yTile * 32, color, 0x00,escala);
		
		pantalla.render(xOffset + modificador, yOffset, (xTile + 1) + yTile * 32, color, 0x00,escala);
		
		pantalla.render(xOffset , yOffset + modificador, xTile + (yTile + 1) * 32, color, 0x00,escala);
		
		pantalla.render(xOffset+ modificador, yOffset + modificador, (xTile+1) + (yTile+1) * 32, color, 0x00,escala);
		
	}

	@Override
	public boolean colision(int xa, int ya) {

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
