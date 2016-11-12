package frame;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JButton;
import servidor.*;

public class VentanaCreacionPersonaje extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel lblCreacinDePersonaje;
	private JLabel lblRaza;
	private JLabel lblClase;
	private JComboBox<String> cmbRaza;
	private JComboBox <String>cmbClase;
	private JButton btnCrearPersonaje;
	private String nombre;
	private Personaje pj;
	
	/**
	 * Launch the application.
	 */
	//la ventana se crea desde la pantalla de login

	/**
	 * Create the frame.
	 */
	public VentanaCreacionPersonaje(Usuario usuario) {
		this.nombre = usuario.getNombre();
		String [] razas = new String[3];
		String [] clases = new String[3];
		
		razas[0] = new String("Asgardiano");
		razas[1]= new String("Hulk");
		razas[2]= new String("Mutante");
		clases[0]= new String("Agente");
		clases[1]= new String("Hechizero");
		clases[2]= new String("Tanque");
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCreacinDePersonaje = new JLabel("Creaci\u00F3n de personaje: ");
		lblCreacinDePersonaje.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCreacinDePersonaje.setBounds(10, 11, 163, 22);
		contentPane.add(lblCreacinDePersonaje);
		
		lblRaza = new JLabel("Raza:");
		lblRaza.setBounds(45, 77, 46, 14);
		contentPane.add(lblRaza);
		
		lblClase = new JLabel("Clase:");
		lblClase.setBounds(215, 77, 46, 14);
		contentPane.add(lblClase);
		
		cmbRaza = new JComboBox<String>();
		cmbRaza.setBounds(45, 102, 115, 20);
		contentPane.add(cmbRaza);
		cmbRaza.addItem("Asgardiano");
		cmbRaza.addItem("Hulk");
		cmbRaza.addItem("Mutante");
		
		cmbClase = new JComboBox<String>();
		cmbClase.setBounds(215, 102, 115, 20);
		contentPane.add(cmbClase);
		cmbClase.addItem("Tanque");
		cmbClase.addItem("Hechizero");
		cmbClase.addItem("Agente");
		
		btnCrearPersonaje = new JButton("Crear Personaje");
		btnCrearPersonaje.setBounds(284, 228, 127, 23);
		contentPane.add(btnCrearPersonaje);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	        if (e.getSource()==btnCrearPersonaje) {
	            String raza=(String)cmbRaza.getSelectedItem();
	            String stringClase=(String)cmbClase.getSelectedItem();
	            Clase clase = null;
	            if(stringClase.equals("Tanque"))
	            	clase = Clase.TANQUE;
	            if(stringClase.equals("Agente"))
	            	clase = Clase.AGENTE;
	            if(stringClase.equals("Hechizero"))
	            	clase = Clase.HECHIZERO;
	            
	            if(raza == "Hulk")
	            	pj = new Hulk(this.nombre, clase);
	            if(raza == "Asgardiano")
	            	pj = new Asgardiano(this.nombre, clase);
	            if(raza == "Mutante")
	            	pj = new Mutante(this.nombre,clase);	            
				}
	        //Guardar en base de datos, tabla personajes el personaje que se acaba de crear.
	        
	    }
	 
		
	
}
