package dijktra;

import java.util.ArrayList;
import java.util.Collections;


import nivel.Nivel;

public class Grafo {

	private int anchoNivel;
	private int altoNivel;
	private ArrayList<Nodo> nodos;	
	private int matrizAdy[][];
	
	
	public Grafo(Nivel nivel) {
		nodos = new ArrayList<Nodo>();
		for (int x = 0; x < nivel.getAncho(); x++) {
			for (int y = 0; y < nivel.getAlto(); y++) {
				// creamos un nodo para la pos actual y cargamos lista de nodos
				// vecinos.
				if (!nivel.getObstaculos(x, y)) {
					Nodo nodoNue = new Nodo(x, y, nivel);
					nodos.add(nodoNue);
				}

			}

		}
		this.anchoNivel = nivel.getAncho();
		this.altoNivel = nivel.getAlto();
		cargarMatriz();
	}
	
	//ver de recibir parametros para cargar solo la parte visible de la pantalla
	private void cargarMatriz()
	{
		matrizAdy = new int [anchoNivel * altoNivel][altoNivel* anchoNivel];
		//Inicializo en infinito
		for(int i = 0; i < matrizAdy.length; i++)
		{
			for(int j = 0; j < matrizAdy.length; j++)
			{		
					matrizAdy[i][j] =  Integer.MAX_VALUE;
			}
		}
		//cargamos costos para todos los nodos, numero de nodo == x+y*10
		//para cada nodo
		for (Nodo actual : nodos) 
		{
			//calculamos su numero de nodo
			int nNodo =  actual.getPosicion().x * 10 + actual.getPosicion().y;
			for (Nodo nodo : actual.getVecinos()) {
				//a la matrizAdy de numero de nodo sub nodo vecino le ponemos el costo correspondiente
				matrizAdy[nNodo][nodo.getPosicion().x * 10 + nodo.getPosicion().y] = actual.getPesoArista(nodo);
			}	
		}
	}
	public ArrayList<Nodo> caminoMasCorto(Nodo origen, Nodo destino)
	{
		Nodo aux;
		ArrayList <Nodo>camino;
		ArrayList<Nodo> v = new ArrayList<Nodo>();
		for (Nodo nodo : nodos) {
			v.add(nodo.clone());
		}
		int[] costos = new int [matrizAdy.length];
		int[] padre = new int [matrizAdy.length];
		Nodo w = new Nodo(origen.getPosicion(),origen.getVecinos());
		v.remove(w);
		//inicializamos costos con los costos directos
		for(int j = 0; j < costos.length; j++)
			costos[j] = matrizAdy[getNumeroNodo(origen)][j];
		//inicializamos padre con origen
		for(int j = 0; j < padre.length; j++)
		{
			if(costos[j] != Integer.MAX_VALUE)
				padre[j] = 	getNumeroNodo(origen);
		}
		while(!v.isEmpty())
		{
			w = v.get(0);
			//Obtener nodo costo minimo
			for (Nodo actual : v) {
				if(costos[getNumeroNodo(actual)] != Integer.MAX_VALUE && costos[getNumeroNodo(w)] > costos[getNumeroNodo(actual)])
				{
					w = v.get(v.indexOf(actual));
				}
			}
			//en w tenemos el nodo de costo minimo.
			v.remove(w);
			//recalculamos costos
			for (Nodo actual : v) {
				if(costos[getNumeroNodo(w)] != Integer.MAX_VALUE && matrizAdy[getNumeroNodo(w)][getNumeroNodo(actual)] != Integer.MAX_VALUE && costos[getNumeroNodo(actual)] > costos[getNumeroNodo(w)] + matrizAdy[getNumeroNodo(w)][getNumeroNodo(actual)])
				{
					costos[getNumeroNodo(actual)] = costos[getNumeroNodo(w)] + matrizAdy[getNumeroNodo(w)][getNumeroNodo(actual)];
					padre[getNumeroNodo(actual)] = getNumeroNodo(w);
				}
			}
		
		}
		camino = new ArrayList<Nodo>();
		aux = destino;
		camino.add(aux);
		while(aux != null && !aux.equals(origen))
		{
			int x = padre[getNumeroNodo(aux)]/10;
			int y = padre[getNumeroNodo(aux)]%10;
			Nodo nod = new Nodo(x,y);
			if(nodos.indexOf(nod)>=0)
			{
				camino.add(nodos.get(nodos.indexOf(nod)));
			}
			else
			{
				System.out.println("sin camino");
				return null;
			}
			aux = nodos.get(nodos.indexOf(nod));
		}
		if(aux.equals(origen))
		{
			Collections.reverse(camino);
			return camino;
		}
		else
		{
			return null;
		}
	}
	
	public int getNumeroNodo(Nodo nodo)
	{
		return nodo.getPosicion().x * 10 + nodo.getPosicion().y;
	}
}
