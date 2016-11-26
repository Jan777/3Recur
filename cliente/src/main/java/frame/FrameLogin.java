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
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import character.PC;
import client.Cliente;
import frame.DialogLogin;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FrameLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected int y;
	protected int x;
	private JTextField textFieldNombre;
	private JTextField textFieldContraseña;
	protected Cliente client;
	protected JDialog dialog;
	private static FrameLogin frame;
	private PC pj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FrameLogin();
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
		setLocationRelativeTo(null);

		JPanel panelBarra = new JPanel();
		panelBarra.setBackground(new Color(0, 0, 0));
		panelBarra.setBounds(0, 0, 488, 23);
		contentPane.add(panelBarra);
		panelBarra.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		panelBarra.setLayout(null);

		JLabel lblLoginMarvelJrpg = new JLabel("Login Marvel JRPG");
		lblLoginMarvelJrpg.setFont(new Font("Verdana", Font.BOLD, 12));
		lblLoginMarvelJrpg.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginMarvelJrpg.setForeground(Color.WHITE);
		lblLoginMarvelJrpg.setBounds(63, 0, 235, 23);
		panelBarra.add(lblLoginMarvelJrpg);
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

		JLabel labelContraseña = new JLabel("Contrase\u00F1a");
		labelContraseña.setForeground(Color.WHITE);
		labelContraseña.setFont(new Font("Verdana", Font.PLAIN, 12));
		labelContraseña.setBounds(10, 43, 133, 34);
		panelLogin.add(labelContraseña);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(161, 8, 165, 20);
		panelLogin.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldContraseña = new JPasswordField();
		textFieldContraseña.setBounds(161, 51, 165, 20);
		panelLogin.add(textFieldContraseña);
		textFieldContraseña.setColumns(10);

		JButton btnConectarse = new JButton("Conectarse");
		btnConectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldNombre.getText().equals("") || textFieldContraseña.getText().equals(""))
					return;

				try {
					client = new Cliente(textFieldNombre.getText(), textFieldContraseña.getText());
					
					if (client.logIn()) {
						dialog = new DialogLogin(frame, "Felicidades!!", "Se conecto con exito al servidor.", true);
						dialog.setVisible(true);

						pj = client.getPersonaje();
						
						if (pj != null) {
							client.conectarPersonaje(pj);
							setVisible(false);
						}

						else {
							DialogCreacionPj creacionPj = new DialogCreacionPj(frame);
							creacionPj.setVisible(true);
							client.crearPersonaje(pj);
							client.conectarPersonaje(pj);
							setVisible(false);
						}
					}

					else {
						dialog = new DialogLogin(frame, "ERROR", "El nombre y/o la contrase\u00F1a \r\nson invalidos.",
								true);
						dialog.setVisible(true);
					}
					
				} catch (IOException e1) {
					dialog = new DialogLogin(frame, "ERROR", "No se pudo conectar con el server.",
							true);
					dialog.setVisible(true);
				}
				
			}
		});

		btnConectarse.setBackground(new Color(0, 0, 51));
		btnConectarse.setForeground(Color.WHITE);
		btnConectarse.setBounds(96, 345, 152, 23);
		panelPpal.add(btnConectarse);
		btnConectarse.setFocusPainted(false);
		btnConectarse.setBorderPainted(false);

		JButton buttonRegistrarse = new JButton("Registrarse");
		buttonRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldNombre.getText().equals("") || textFieldContraseña.getText().equals(""))
					return;

				try {
					client = new Cliente(textFieldNombre.getText(), textFieldContraseña.getText());
					
					if (client.singIn()) {
						dialog = new DialogLogin(frame, "Felicidades!!", "Se registro con exito.", true);
						dialog.setVisible(true);
					}

					else {
						dialog = new DialogLogin(frame, "ERROR", "El nombre ya esta en uso", true);
						dialog.setVisible(true);
					}
				} catch (IOException e1) {
					dialog = new DialogLogin(frame, "ERROR", "No se pudo conectar con el server.",
							true);
					dialog.setVisible(true);
				}
			}
		});
		buttonRegistrarse.setForeground(Color.WHITE);
		buttonRegistrarse.setFocusPainted(false);
		buttonRegistrarse.setBorderPainted(false);
		buttonRegistrarse.setBackground(new Color(0, 0, 51));
		buttonRegistrarse.setBounds(299, 345, 152, 23);
		panelPpal.add(buttonRegistrarse);

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
				if (client != null)
					client.logOut();
				System.exit(1);
			}
		});
		btnSalir.setBackground(new Color(0, 0, 102));
		btnSalir.setFocusPainted(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public void setPj(PC pj) {
		this.pj = pj;
	}
}
