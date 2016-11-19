package Window;

import java.awt.EventQueue;
import Game.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CharacterCreator {

	private JFrame frame;
	private PC personaje;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CharacterCreator window = new CharacterCreator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CharacterCreator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 330, 195);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(30, 34, 46, 14);
		frame.getContentPane().add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(10, 59, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblClase = new JLabel("Clase");
		lblClase.setBounds(267, 6, 46, 14);
		frame.getContentPane().add(lblClase);
		
		JRadioButton claseAgente = new JRadioButton("Agente");
		claseAgente.setBounds(229, 28, 109, 23);
		frame.getContentPane().add(claseAgente);
		
		JRadioButton claseHechizero = new JRadioButton("Hechizero");
		claseHechizero.setBounds(229, 58, 109, 23);
		frame.getContentPane().add(claseHechizero);
		
		JRadioButton claseTanque = new JRadioButton("Tanque");
		claseTanque.setBounds(229, 87, 109, 23);
		frame.getContentPane().add(claseTanque);
		
		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setBounds(154, 6, 46, 14);
		frame.getContentPane().add(lblRaza);
		
		JRadioButton razaMutante = new JRadioButton("Mutante");
		razaMutante.setBounds(118, 30, 109, 23);
		frame.getContentPane().add(razaMutante);
		
		JRadioButton razaAsgardiano = new JRadioButton("Asgardiano");
		razaAsgardiano.setBounds(118, 58, 109, 23);
		frame.getContentPane().add(razaAsgardiano);
		
		JRadioButton razaHulk = new JRadioButton("Hulk");
		razaHulk.setBounds(118, 87, 109, 23);
		frame.getContentPane().add(razaHulk);
		
		ButtonGroup clase=new ButtonGroup();
		clase.add(claseTanque);
		clase.add(claseHechizero);
		clase.add(claseAgente);
		
		ButtonGroup raza=new ButtonGroup();
		raza.add(razaHulk);
		raza.add(razaMutante);
		raza.add(razaAsgardiano);
		
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clase clasePersonaje=Clase.TANQUE;
				if(clase.isSelected(claseTanque.getModel()))
					clasePersonaje=Clase.TANQUE;
				else if(clase.isSelected(claseAgente.getModel()))
					clasePersonaje=Clase.AGENTE;
				else if(clase.isSelected(claseHechizero.getModel()))
					clasePersonaje=Clase.HECHIZERO;
				
				if(raza.isSelected(razaHulk.getModel()))
					personaje=new Hulk(textField.getText(),clasePersonaje);
				if(raza.isSelected(razaAsgardiano.getModel()))
					personaje=new Asgardiano(textField.getText(),clasePersonaje);
				if(raza.isSelected(razaMutante.getModel()))
					personaje=new Mutante(textField.getText(),clasePersonaje);
				personaje.mostrarPersonaje();
				CharacterStats cs= new CharacterStats(personaje);
				cs.setVisible(true);
			}
		});
		btnCrear.setBounds(118, 117, 89, 23);
		frame.getContentPane().add(btnCrear);
		

	}
}
