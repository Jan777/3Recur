package principal;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import entidades.Jugador;
import gfx.Color;

import gfx.Pantalla;
import gfx.SpriteSheet;
import nivel.Nivel;


public class Principal extends Canvas implements Runnable{

	//definicion de constantes
	private static final long serialVersionUID = 1L;
	private static final int ANCHO = 400;
	private static final int ALTO = ANCHO/12*9;
	private static final int ESCALA = 3;
	private static final String NOMBRE = "Marvel";
	
	
	
	
	private JFrame frame;
	
	private boolean enEjecucion = false;
	@SuppressWarnings("unused")
	private int contadorActualizaciones = 0;
	
	//creamos la variable para meter dentro del canvas
	
	private BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)imagen.getRaster().getDataBuffer()).getData();
	
	//contiene informacion sobre que color tiene cada tile 6 shades para r, 6 para g, 6 para b
	private int [] colores = new int[6 * 6 * 6];
	
	private Pantalla pantalla;
	
	private InputHandler input;
	
	private Nivel nivel;
	
	private Jugador jugador;
	//coordenada x e y que se mueven por la pantalla.
	
	
	//constructor de nuestra ventana
		public Principal(){
			//setea tamaño canvas
			setMinimumSize(new Dimension(ANCHO*ESCALA,ALTO*ESCALA));
			setMaximumSize(new Dimension(ANCHO*ESCALA,ALTO*ESCALA));
			setPreferredSize(new Dimension(ANCHO*ESCALA,ALTO*ESCALA));
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
		int index = 0;
		for(int r = 0; r<6;r++)
		{
			for(int g = 0; g<6;g++)
			{
				for(int b = 0; b<6;b++)
				{
					//255 sera transparente
					int rr = (r*255/5);
					int gg = (g*255/5);
					int bb = (b*255/5);
					colores[index ++] = rr << 16 | gg << 8 | bb;
				}
			}
			
		}
		pantalla = new Pantalla (ANCHO, ALTO, new SpriteSheet("/SpriteSheetBase.png"));
		input = new InputHandler(this);
		nivel = new Nivel(64,64);
		jugador = new Jugador(nivel, 0, 0, input);
		nivel.addEntidad(jugador);
	}
	
	private synchronized  void start() 
	{
		enEjecucion = true;
		new Thread(this).start();	
	}
	//Todavia no utilizado
    @SuppressWarnings("unused")
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
		
		
		nivel.actualizar();
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
		
		int xOffset = jugador.getX() - (pantalla.getAncho()/2);
		int yOffset = jugador.getY() - (pantalla.getAlto()/2);
		
		nivel.renderTiles(pantalla, xOffset, yOffset);
		
		for (int x = 0; x < nivel.getAncho(); x++)
		{
			int color = Color.get(-1, -1, -1, 000);
			if(x % 10 == 0 && x != 0)
			{
				color = Color.get(-1, -1, -1, 500);
			}
		}
		nivel.renderizarEntidades(pantalla);
		
		/*
		//Renderizamos el mensaje
		
		String mensaje = "P";
		Fuente.render(mensaje, pantalla, x, y, Color.get(-1,-1,-1,011));
		*/
		
		
		//Copiamos la informacion de los pixels de la pantalla en el frame principal (juego)
		for(int y = 0; y < pantalla.getAlto(); y++)
		{
			for(int x = 0; x < pantalla.getAncho(); x++)
			{
				int codigoColor = pantalla.getPixels()[x+y * pantalla.getAncho()];
				if(codigoColor < 255)
				{
					pixels[x+y*ANCHO] = colores[codigoColor];
				}
			}
		}
		
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
