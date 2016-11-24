package entidades;

import java.util.ArrayList;

import dijktra.Grafo;
import dijktra.Nodo;
import gfx.Color;
import gfx.Fuente;
import gfx.Pantalla;
import nivel.Nivel;
import principal.InputHandler;

public class Jugador extends Grupo {

	private InputHandler input;
	private int color = Color.get(-1, 000, 127, 150);
	private Grafo grafo;
	private String nombreUsuario;
	public Jugador(Nivel nivel, int x, int y, InputHandler input, Grafo grafo, String userName) {
		super(nivel, "Jugador", x, y, 1);
		this.input = input;
		this.grafo = grafo;
		this.nombreUsuario = userName;
	}

	@Override
	public void actualizar() {
		int xa = 0;
		int ya = 0;
		
		// TECLADO
		/*//prueba dijktra 
		Nodo origen = new Nodo(1,1);//posicion actual del jugador en el mapa
		 Nodo destino = new Nodo(8,8);
		  ArrayList <Nodo>recorrido;
		 
		  recorrido = grafo.caminoMasCorto(origen , destino);
		  int i = 0;
		  while (input.getArriba().estaPresionada() && !recorrido.isEmpty()) {
			Nodo nodo = recorrido.remove(0);
			xa = nodo.getPosicion().x;
			ya = nodo.getPosicion().y;
			i++;
		  }
		  */
		
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
				/*// MOUSE
			
		  if(input.getClick()) { 
			  
			 Nodo origen = new Nodo(x,y); //posicion actual del jugador en el mapa
			  ArrayList <Nodo>recorrido;
			  recorrido = grafo.caminoMasCorto(origen , input.getDestino());
			  for (Nodo nodo : recorrido) {
				xa = nodo.getPosicion().x;
				ya = nodo.getPosicion().y;
			}
			  input.setClick(false);
			  } //FIN MOUSE
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
		// renderizamos el nombre de usuario
		
		if(nombreUsuario != null)
		{
			Fuente.render(nombreUsuario, pantalla, xOffset - ((nombreUsuario.length()-1)/2 * 8), yOffset - 10,Color.get(-1, -1, -1, 555), 1);
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
}
