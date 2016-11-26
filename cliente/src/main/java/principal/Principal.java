package principal;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JFrame;
import com.google.gson.Gson;
import character.Asgardiano;
import character.Hulk;
import character.Mutante;
import character.PC;
import dijktra.Grafo;
import entidades.Jugador;
import gfx.Color;
import gfx.Pantalla;
import gfx.SpriteSheet;
import nivel.Nivel;

public class Principal extends Canvas implements Runnable {

	// definicion de constantes
	private static final long serialVersionUID = 1L;
	public static final int ANCHO = 300;
	public static final int ALTO = ANCHO / 12 * 9;
	private static final int ESCALA = 3;
	private static final String NOMBRE = "Marvel";
	private JFrame frame;
	private boolean enEjecucion = false;
	@SuppressWarnings("unused")
	private int contadorActualizaciones = 0;

	// private Cliente cliente;
	// creamos la variable para meter dentro del canvas

	private BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();
	// contiene informacion sobre que color tiene cada tile 6 shades para r, 6
	// para g, 6 para b
	private int[] colores = new int[6 * 6 * 6];
	private Pantalla pantalla;
	private InputHandler input;
	private Nivel nivel;
	private Jugador jugador;
	@SuppressWarnings("unused")
	private Grafo obs;
	private Socket socket;
	private PC pj;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private ArrayList<PC> listaPj;
	private Gson gson;

	// constructor de nuestra ventana
	public Principal(Socket socket, PC pj) {
		// Creamos Ventana
		frame = new JFrame(NOMBRE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrar();
				frame.dispose();
				System.exit(1);
			}
		});
		this.pj = pj;
		this.socket = socket;
		try {
			entrada = new DataInputStream(this.socket.getInputStream());
			salida = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// setea tamaño canvas
		setMinimumSize(new Dimension(ANCHO * ESCALA, ALTO * ESCALA));
		setMaximumSize(new Dimension(ANCHO * ESCALA, ALTO * ESCALA));
		setPreferredSize(new Dimension(ANCHO * ESCALA, ALTO * ESCALA));
		
		// definimos la operacion del boton de cierre
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// definimos el Layout, para que el centro ocupe el maximo posible del
		// tamaño de nuestro frame
		frame.setLayout(new BorderLayout());
		// agregamos al frame nuestro objeto this(nuestro canvas), en el centro
		// del frame.
		frame.add(this, BorderLayout.CENTER);
		// configura el tamaño del frame para que coincida con el setMinimunSize
		frame.pack();
		// No permitimos que se pueda redimensionar
		frame.setResizable(false);
		// Definimos como posición relativa en la que se mostrará nuestro frame
		// al centro de la pantalla
		frame.setLocationRelativeTo(null);
		// hacemos que el frame sea visible
		frame.setVisible(true);
	}

	public void inicilizar() {
		int index = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					// 255 sera transparente
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);
					colores[index++] = rr << 16 | gg << 8 | bb;
				}
			}

		}
		pantalla = new Pantalla(ANCHO, ALTO, new SpriteSheet("/SpriteSheetBase.png"));
		input = new InputHandler(this);
		nivel = new Nivel("/niveles/nivel64_64.png");
		obs = new Grafo(nivel);
		// Esto se va a hacer desde el cliente
		jugador = new Jugador(pj, nivel, input);
		nivel.addEntidad(jugador);
		//nivel.addPj(pj);
	}

	public synchronized void start() {
		enEjecucion = true;
		new Thread(this).start();
	}

	// Todavia no utilizado
	@SuppressWarnings("unused")
	private synchronized void stop() {
		enEjecucion = false;
	}

	@Override
	public void run() {
		gson = new Gson();
		// Para que en todos los dispositivos se vea igual, y
		// no se vea mas rápido en uno que en otro, sincronizams las
		// actualizaciones de pantalla (FPS)

		// obtenemos el tiempo actual
		long ultimo = System.nanoTime();
		// fijamos el tiempo en nanosegundos para hacer una actualizacion.
		double tiempoActualizacion = 1000000000D / 60D;

		int frames = 0;
		int actualizaciones = 0;

		long ultimoTimer = System.currentTimeMillis();
		// Indica cuantos nanoSegundos pasaron desde la ultimna actualizacion,
		// cuando sea 1 se debe actualizar
		double delta = 0;

		// inicializamos la pantalla
		inicilizar();
		obs = new Grafo(nivel);
		while (enEjecucion) {
			long actual = System.nanoTime();
			delta += (actual - ultimo) / tiempoActualizacion;
			ultimo = actual;
			boolean debeRenderizar = true;
			while (delta >= 1) {
				actualizaciones++;
				actualizar();
				delta -= 1;
				debeRenderizar = true;

			}
			// pausamos el hilo por 2 milisegundos
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			if (debeRenderizar) {
				frames++;

				try {
					salida.flush();
					salida.writeUTF("moverPersonaje");
					salida.writeUTF(String.valueOf(pj.getX()));
					salida.writeUTF(String.valueOf(pj.getY()));
					int dimension = gson.fromJson(entrada.readUTF(), Integer.class);
					int i = 0;
					listaPj = new ArrayList<PC>();

					while (i < dimension) {
						String raza = entrada.readUTF();
						PC pcAux;
						raza = raza.substring(1, raza.length() - 1);
						switch (raza) {
						case "Asgardiano":
							pcAux = gson.fromJson(entrada.readUTF(), Asgardiano.class);
							if(!pj.getName().equals(pcAux.getName()))
								listaPj.add(pcAux);
							break;

						case "Mutante":
							pcAux = gson.fromJson(entrada.readUTF(), Mutante.class);
							if(!pj.getName().equals(pcAux.getName()))
								listaPj.add(pcAux);
							break;
							

						case "Hulk":
							pcAux = gson.fromJson(entrada.readUTF(), Hulk.class);
							if(!pj.getName().equals(pcAux.getName()))
								listaPj.add(pcAux);
							break;
						}
						i++;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				nivel.recibirListaNueva(listaPj);
				renderizar();

			}
			if (System.currentTimeMillis() - ultimoTimer >= 1000) {
				ultimoTimer += 1000;
				frame.setTitle("Actualizaciones: " + actualizaciones + "Frames: " + frames);
				frames = 0;
				actualizaciones = 0;
			}

		}
	}

	// actualiza elementos a imprimir
	public void actualizar() {
		contadorActualizaciones++;

		nivel.actualizar();
	}

	// imprime la imagen
	public void renderizar() {
		BufferStrategy bs = getBufferStrategy();
		// si no existe lo creamos
		if (bs == null) {
			// truple buffer (3)
			createBufferStrategy(3);
			return;
		}

		int xOffset = jugador.getX() - (pantalla.getAncho() / 2);
		int yOffset = jugador.getY() - (pantalla.getAlto() / 2);

		nivel.renderTiles(pantalla, xOffset, yOffset);

		for (int x = 0; x < nivel.getAncho(); x++) {
			@SuppressWarnings("unused")
			int color = Color.get(-1, -1, -1, 000);
			if (x % 10 == 0 && x != 0) {
				color = Color.get(-1, -1, -1, 500);
			}
		}
		nivel.renderizarEntidades(pantalla);

		// Copiamos la informacion de los pixels de la pantalla en el frame
		// principal (juego)
		for (int y = 0; y < pantalla.getAlto(); y++) {
			for (int x = 0; x < pantalla.getAncho(); x++) {
				int codigoColor = pantalla.getPixels()[x + y * pantalla.getAncho()];
				if (codigoColor < 255) {
					pixels[x + y * ANCHO] = colores[codigoColor];
				}
			}
		}

		Graphics g = bs.getDrawGraphics();
		// imprimimos la imagen que tenemos como atributo
		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
		// Borramos g
		g.dispose();

		// mostramos el bs
		bs.show();
	}
	/*
	 * public static void main(String[] args) { Principal ppal = new
	 * Principal(); ppal.start();
	 * 
	 * }
	 */
	
	public int cerrar() {
		try {
			salida.writeUTF("desconectarPersonaje");
			salida.writeUTF(pj.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JFrame.EXIT_ON_CLOSE;
	}
}
