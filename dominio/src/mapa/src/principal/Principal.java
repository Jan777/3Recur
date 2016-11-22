package principal;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import gfx.Pantalla;
import gfx.SpriteSheet;


public class Principal extends Canvas implements Runnable{

	//definicion de constantes
	private static final long serialVersionUID = 1L;
	private static final int ALTO = 300;
	private static final int ANCHO = ALTO/12*9;
	private static final int ESCALA = 3;
	private static final String NOMBRE = "Mar vel";
	
	
	private boolean enEjecucion = false;
	
	private JFrame frame;
	private int contadorActualizaciones = 0;
	//creamos la variable para meter dentro del canvas
	
	private BufferedImage imagen = new BufferedImage(ALTO, ANCHO, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)imagen.getRaster().getDataBuffer()).getData();
	
	private Pantalla pantalla;
	private InputHandler input;
	
	//constructor de nuestra ventana
	public Principal(){
		//setea tamaño canvas
		setMinimumSize(new Dimension(ALTO*ESCALA,ANCHO*ESCALA));
		setMaximumSize(new Dimension(ALTO*ESCALA,ANCHO*ESCALA));
		setPreferredSize(new Dimension(ALTO*ESCALA,ANCHO*ESCALA));
		//Creamos Ventana
		frame = new JFrame(NOMBRE);
		//definimos la operacion del boton de cierre
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//definimos el Layout, para que el centro ocupe el maximo posible del tamaño de nuestro frame
		frame.setLayout(new BorderLayout()); 
		//agregamos al frame nuestro objeto this(nuestro canvas), en el centro del frame.
		frame.add(this,BorderLayout.CENTER);
		// configura el tamaño del frame para que coincida con el setMinimunSize 
		frame.pack();
		//No permitimos que se pueda redimensionar
		frame.setResizable(false);
		//Definimos como posición relativa en la que se mostrará nuestro frame al centro de la pantalla
		frame.setLocationRelativeTo(null);
		//hacemos que el frame sea visible
		frame.setVisible(true);
	}
	
	public void inicilizar()
	{
		pantalla = new Pantalla (ALTO, ANCHO, new SpriteSheet("/SpriteSheetBase.png"));
		input = new InputHandler(this);
	}
	
	private synchronized  void start() 
	{
		enEjecucion = true;
		new Thread(this).start();
		
		
	}
	//Todavia no utilizado
    private synchronized void stop()
	{
		enEjecucion = false;
	}
	@Override
	public void run() {
		//Para que en todos los dispositivos se vea igual, y 
		//no se vea mas rápido en uno que en otro, sincronizams las actualizaciones de pantalla (FPS)
		
		//obtenemos el tiempo actual
		long ultimo= System.nanoTime();
		//fijamos el tiempo en nanosegundos para hacer una actualizacion.
		double tiempoActualizacion = 1000000000D/60D; 
		
		int frames = 0;
		int actualizaciones = 0;
		
		long ultimoTimer = System.currentTimeMillis();
		//Indica cuantos nanoSegundos pasaron desde la ultimna actualizacion, cuando sea 1 se debe actualizar 
		double delta = 0;
		
		//inicializamos la pantalla
		inicilizar();
		
		
		while(enEjecucion)
		{
			long actual = System.nanoTime();
			delta += (actual - ultimo)/tiempoActualizacion;
			ultimo = actual;  
			boolean debeRenderizar = true;
			while(delta >= 1)
			{	
				actualizaciones ++;
				actualizar();
				delta -= 1;
				debeRenderizar = true;
				
			}
			//pausamos el hilo por 2 milisegundos
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			
			if(debeRenderizar){
				frames++;
				renderizar();
			
			}
			if(System.currentTimeMillis() - ultimoTimer >= 1000)
			{
				ultimoTimer += 1000;
				System.out.println("Actualizaciones: "+actualizaciones+"Frames: "+frames);
				frames = 0;
				actualizaciones = 0;
			}
				
			
		}
		
	}
	//actualiza elementos a imprimir
	public void actualizar()
	{
		contadorActualizaciones ++;
		
		//TECLADO
		if(input.getArriba().estaPresionada())
		{
			pantalla.setyOffset(pantalla.getyOffset()-1);
			
		}
		if(input.getAbajo().estaPresionada())
		{
			pantalla.setyOffset(pantalla.getyOffset()+1);
		}
		if(input.getIzquierda().estaPresionada())
		{
			pantalla.setxOffset(pantalla.getxOffset()-1);
		}
		if(input.getDerecha().estaPresionada())
		{
			pantalla.setxOffset(pantalla.getxOffset()+1);
		}
		//FIN TECLADO
		//MOUSE
		if(input.getClick())
		{
			//VER COMO HACER PARA QUE EL PERSONAJE SE MUEVA DE A POCO Y NO SEA UN PARPADEO DE UN PUNTO A OTRO, 
			//SI NO QUE RECORRA UN CAMINO (DIJKTRA?)
			pantalla.setxOffset(pantalla.getxOffset()+input.getDestino().x);
			pantalla.setyOffset(pantalla.getyOffset()+input.getDestino().y);
			input.setClick(false);
		}
		//FIN MOUSE
	}
	//imprime la imagen
	public void renderizar()
	{
		BufferStrategy bs = getBufferStrategy();
		//si no existe lo creamos
		if(bs == null)
		{
			 //truple buffer (3)
			createBufferStrategy(3);
			return;
		}
		//renderizamos la pantalla
		pantalla.render(pixels, 0, ALTO);
		
		Graphics g = bs.getDrawGraphics();
		//imprimimos la imagen que tenemos como atributo
		g.drawImage(imagen, 0,0,getWidth(), getHeight(), null);
		//Borramos g
		g.dispose();
		
		//mostramos el bs
		bs.show();
	}
	public static void main(String[] args) {
		Principal ppal = new Principal();
		ppal.start();
		
	}
	
	
}
