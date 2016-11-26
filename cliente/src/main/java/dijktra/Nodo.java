package dijktra;

import java.awt.Point;
import java.util.ArrayList;

import nivel.Nivel;

public class Nodo {
	
	private Point posicion;
	//lista de todos los nodos que rodean al actual, 8 nodos como maximo
	private ArrayList<Nodo> vecinos;
	private Nivel nivel;
	public Nodo(Point posicion, ArrayList<Nodo> vecinos)
	{
		this.posicion = new Point(posicion.x, posicion.y);
		this.vecinos = vecinos;
	}
	public Nodo(int x, int y)
	{
		this.posicion = new Point(x,y);
		this.posicion.y = y;
		this.vecinos = new ArrayList<Nodo>();
	}

	public Nodo(int x, int y, Nivel nivel)
	{
		this.nivel = nivel;
		this.posicion = new Point(x,y);
		this.vecinos = new ArrayList<Nodo>();
		// cargo la lista de nodos vecinos
		int i = x;
		int j = y;
		int maxI = nivel.getAncho();
		int maxJ = nivel.getAlto();
		if (posicionValida(i - 1, j, maxI, maxJ) && !nivel.getObstaculos(i-1, j)) {
			this.addVecino(new Nodo(i - 1, j));
		}
		if (posicionValida(i + 1, j, maxI, maxJ) && !nivel.getObstaculos(i+1, j)) {
			this.addVecino(new Nodo(i + 1, j));
		}
		if (posicionValida(i, j - 1, maxI, maxJ) && !nivel.getObstaculos(i, j-1)) {
			this.addVecino(new Nodo(i, j - 1));
		}
		if (posicionValida(i, j + 1, maxI, maxJ) && !nivel.getObstaculos(i, j+1)) {
			this.addVecino(new Nodo(i, j + 1));
		}
		if (posicionValida(i - 1, j - 1, maxI, maxJ) && !nivel.getObstaculos(i-1, j-1)) {
			this.addVecino(new Nodo(i - 1, j - 1));
		}
		if (posicionValida(i + 1, j + 1, maxI, maxJ) && !nivel.getObstaculos(i+1, j+1)) {
			this.addVecino(new Nodo(i + 1, j + 1));
		}
		if (posicionValida(i - 1, j + 1, maxI, maxJ) && !nivel.getObstaculos(i-1, j+1)) {
			this.addVecino(new Nodo(i - 1, j + 1));
		}
		if (posicionValida(i + 1, j - 1, maxI, maxJ) && !nivel.getObstaculos(i+1, j-1)) {
			this.addVecino(new Nodo(i + 1, j - 1));
		}

	}
	public void addVecino(Nodo nodo)
	{
		this.vecinos.add(nodo);
	}
	public double distanciaA(Nodo nodo)
	{
		return this.posicion.distance(nodo.posicion);
	}
	
	public Point getPosicion()
	{
		return this.posicion;
	}
	public ArrayList<Nodo>getVecinos()
	{
		return this.vecinos;
	}
	
	public boolean equals(Object nodo)
	{
		Nodo n2 = (Nodo)nodo;
		if(this.getPosicion().x == n2.getPosicion().x && this.getPosicion().y == n2.getPosicion().y)
			return true;
		return false;
	}
	public Nodo clone()
	{
		Nodo n2 = new Nodo(this.getPosicion().x,this.getPosicion().y, nivel);
		n2.vecinos = new ArrayList<Nodo>();
		for (Nodo nodo : vecinos) {
			n2.addVecino(nodo);
		}
		return n2;
	}
	

	public int getPesoArista(Nodo p)
	{
		//camino por rectas en suma 2+2 = 4 dara mayor que por diagonal = 3
		if((this.posicion.x == p.posicion.x) || (this.posicion.y == p.posicion.y))
			return 2;
		//camino mas corto por diagonal
		return 3;
	}
	private boolean posicionValida(int i, int j, int maxI, int maxJ) {
		if (i < maxI && j < maxJ && i >= 0 && j >= 0)
			return true;
		return false;
	}

}
