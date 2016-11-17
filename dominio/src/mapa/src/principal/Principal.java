package principal;

import java.awt.BorderLayout;

import java.awt.Canvas;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;


public class Principal extends Canvas implements Runnable{

	//definicion de constantes
	private static final long serialVersionUID = 1L;
	private static final int ANCHO = 600;
	private static final int ALTO = 600;
	private static final String NOMBRE = "Marvel";
	
	private boolean enEjecucion = false;
	
	private JFrame frame;
	private int contadorActualizaciones = 0;
	//creamos la variable para meter dentro del canvas
	
	private BufferedImage imagen = new BufferedImage(ALTO, ANCHO, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)imagen.getRaster().getDataBuffer()).getData();
	
	//constructor de nuestra ventana
	public Principal(){
		
		//Creamos la ventana
		frame = new JFrame(NOMBRE);
		//definimos la operacion del boton de cierre
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//definimos el Layout, para que el centro ocupe el maximo posible del tamaño de nuestro frame
		frame.setLayout(new BorderLayout());
		//agregamos al frame nuestro objeto this(nuestro canvas), en el centro del frame.
		frame.add(this, BorderLayout.CENTER);
		//seteamos el tamaño de nuestro frame
		frame.setSize(ALTO, ANCHO);
		//No permitimos que se pueda redimensionar
		frame.setResizable(false);
		//Definimos como posición relativa en la que se mostrará nuestro frame al centro de la pantalla
		frame.setLocationRelativeTo(null);
		//hacemos que el frame sea visible
		frame.setVisible(true);
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
		for(int i = 0; i<pixels.length; i++)
		{
			pixels[i] = i + contadorActualizaciones;
		}
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
