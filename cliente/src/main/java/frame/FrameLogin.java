package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.Cliente;

import javax.swing.JTextField;

public class FrameLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected int y;
	protected int x;
	private JTextField textFieldNombre;
	private JTextField textFieldContrase�a;
	protected Cliente client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameLogin frame = new FrameLogin();
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
	public FrameLogin() {
		inicializarVentana();
	}

	private void inicializarVentana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		
		JPanel panelBarra = new JPanel();
		panelBarra.setBackground(new Color(0, 0, 0));
		panelBarra.setBounds(0, 0, 488, 23);
		contentPane.add(panelBarra);
		panelBarra.setLayout(null);
		panelBarra.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		panelBarra.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				x = arg0.getX();
				y = arg0.getY();
			}
		});
		panelBarra.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				setLocation(getLocation().x + arg0.getX() - x, getLocation().y + arg0.getY() - y);
			}
		});
		
		JPanel panelPpal = new JPanel();
		panelPpal.setBounds(10, 34, 550, 400);
		contentPane.add(panelPpal);
		panelPpal.setLayout(null);
		
		JButton buttonRegistrarse = new JButton("Registrarse");
		buttonRegistrarse.setForeground(Color.WHITE);
		buttonRegistrarse.setFocusPainted(false);
		buttonRegistrarse.setBorderPainted(false);
		buttonRegistrarse.setBackground(new Color(0, 0, 51));
		buttonRegistrarse.setBounds(299, 345, 152, 23);
		panelPpal.add(buttonRegistrarse);
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(Color.DARK_GRAY);
		panelLogin.setBounds(97, 11, 354, 77);
		panelPpal.add(panelLogin);
		panelLogin.setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNombreDeUsuario.setForeground(Color.WHITE);
		lblNombreDeUsuario.setBounds(10, 0, 133, 34);
		panelLogin.add(lblNombreDeUsuario);
		
		JLabel labelContrase�a = new JLabel("Contrase\u00F1a");
		labelContrase�a.setForeground(Color.WHITE);
		labelContrase�a.setFont(new Font("Verdana", Font.PLAIN, 12));
		labelContrase�a.setBounds(10, 43, 133, 34);
		panelLogin.add(labelContrase�a);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(161, 8, 165, 20);
		panelLogin.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldContrase�a = new JTextField();
		textFieldContrase�a.setBounds(161, 51, 165, 20);
		panelLogin.add(textFieldContrase�a);
		textFieldContrase�a.setColumns(10);
		
		JButton btnConectarse = new JButton("Conectarse");
		btnConectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client = new Cliente(textFieldNombre.getText(), textFieldContrase�a.getText());
				if(client.logIn()) {
					System.out.println("usuario conectado");
				}
				
				else
					System.out.println("no se encuentra el usuario");
			}
		});
		btnConectarse.setBackground(new Color(0, 0, 51));
		btnConectarse.setForeground(Color.WHITE);
		btnConectarse.setBounds(96, 345, 152, 23);
		panelPpal.add(btnConectarse);
		btnConectarse.setFocusPainted(false);
		btnConectarse.setBorderPainted(false);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(FrameLogin.class.getResource("/img/marvel2.png")));
		lblFondo.setBounds(0, 0, 550, 400);
		panelPpal.add(lblFondo);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(487, 0, 83, 23);
		contentPane.add(btnSalir);
		btnSalir.setFont(new Font("Verdana", Font.BOLD, 11));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(client != null)
					client.logOut();
				System.exit(1);
			}
		});
		btnSalir.setBackground(new Color(0, 0, 102));
		btnSalir.setFocusPainted(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
