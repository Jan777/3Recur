package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Caret;
import javax.swing.JTextField;
import java.awt.event.InputMethodListener;
import java.util.Scanner;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaCreacion extends JFrame {

	private String nombre;
	private String raza;
	private JPanel contentPane;
	private JTextField txtEscribaRaza;
	private JTextField txtEscribaNombre;
	private JButton btnNewButton;
	private JButton btnCrearPersonaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaCreacion frame = new ventanaCreacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ventanaCreacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEscribaRaza = new JTextField();
		txtEscribaRaza.setText("Escriba raza\r\n");
		txtEscribaRaza.setBounds(10, 44, 159, 20);
		contentPane.add(txtEscribaRaza);
		txtEscribaRaza.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				raza = txtEscribaRaza.getText();
			}
		});
		btnAceptar.setBounds(220, 43, 128, 23);
		contentPane.add(btnAceptar);
		
		txtEscribaNombre = new JTextField();
		txtEscribaNombre.setText("Escriba nombre ");
		txtEscribaNombre.setBounds(10, 90, 159, 20);
		contentPane.add(txtEscribaNombre);
		txtEscribaNombre.setColumns(10);
		
		btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombre = txtEscribaRaza.getText();
			}
		});
		
		btnCrearPersonaje = new JButton("Crear Personaje");
		btnCrearPersonaje.addActionListener(new ActionListener() {
		Hulk h1;
		Asgardiano a1;
		Mutante m1;

			public void actionPerformed(ActionEvent e) {
				if(raza == "Hulk"){
					contentPane.add(btnCrearPersonaje);
					h1 = new Hulk(nombre,Clase.TANQUE);
				}
				else{
					if(raza == "Asgardiano"){
						a1 = new Asgardiano(nombre, Clase.HECHIZERO);
					}
					else{
						if(raza == "Mutante"){
							m1 = new Mutante(nombre, Clase.AGENTE);
						}
						else{
							JButton btnSalir = new JButton("Salir");
							btnSalir.setBounds(487, 0, 83, 23);
							contentPane.add(btnSalir);
							btnSalir.setFont(new Font("Verdana", Font.BOLD, 11));
							btnSalir.setForeground(Color.WHITE);
							btnSalir.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									System.exit(1);
								}
							});
							
						}
					}
				}
					
			}
		});
		btnCrearPersonaje.setBounds(147, 216, 111, 23);
		contentPane.add(btnCrearPersonaje);
	}
}
