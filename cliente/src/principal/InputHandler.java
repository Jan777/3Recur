package principal;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dijktra.Nodo;
import gfx.Pantalla;


public class InputHandler implements KeyListener, MouseListener {
	
	private Nodo destino;
	private boolean click;
	
	

	public InputHandler(Principal ppal)
	{
		ppal.addKeyListener(this);
		ppal.addMouseListener(this);
		this.click = false;
	}
	public class Key{
		private boolean presionada = false;
		private int cantPulsada = 0;
		
		public boolean estaPresionada()
		{
			return presionada;	
		}
		public int getCantPulsada()
		{
			return cantPulsada;
		}
		
		public void toggle(boolean presionada)
		{
			this.presionada = presionada;
			if(presionada){
				cantPulsada++;
			}
		}
	}
	private Key arriba = new Key();
	private Key abajo = new Key();
	private Key izquierda = new Key();
	private Key derecha = new Key();
	@Override
	public void keyPressed(KeyEvent e) {

		toggleKey(e.getKeyCode(), true);
	}

	
	@Override
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);
		
	}
	

	@Override
	public void keyTyped(KeyEvent e) {

		
	}
	
	public void toggleKey(int keyCode, boolean presionada)
	{

		
		if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP )
		{
			arriba.toggle(presionada);
		}
		if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN)
		{
			abajo.toggle(presionada);
		}
		if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT)
		{
			izquierda.toggle(presionada);
		}
		if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT)
		{
			derecha.toggle(presionada);
		}
	}
	//MOUSE---------------------------------------
	
	public  Point mouseAPunto(int x, int y) {
		Point punto = new Point();
		System.out.println(x + " "+y);
		

		return punto;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	/*	Point puntoDestino = e.getPoint(); 
	 * //Transformacion de punto obtenido con el mouse a punto real del mapa 
		destino = new Nodo(puntoDestino.x,puntoDestino.y);
		click = true;
		*/
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public Nodo getDestino()
	{
		return destino;
	}
	public boolean getClick()
	{
		return click;
	}
	public void setClick(boolean click)
	{
		this.click = click;
	}

	public Key getArriba() {
		return arriba;
	}

	public Key getAbajo() {
		return abajo;
	}

	public Key getIzquierda() {
		return izquierda;
	}

	public Key getDerecha() {
		return derecha;
	}
	
	
	

}
