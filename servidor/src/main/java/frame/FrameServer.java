package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import server.ServidorMarvel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class FrameServer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ServidorMarvel server;
	protected int x;
	protected int y;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameServer frame = new FrameServer();
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
	public FrameServer() {
		try {
			server = new ServidorMarvel();
			inicializarVentana();
		} catch (IOException e) {
			DialogLogin dialog = new DialogLogin(this, "ERROR", "El numero de puerto ya esta en uso", true);
			dialog.setVisible(true);
			dispose();
			System.exit(1);
		}
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
		panelBarra.setBackground(Color.BLACK);
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
		
		JCheckBox chckbxPonerEnLnea = new JCheckBox("Poner en l\u00EDnea !");
		chckbxPonerEnLnea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxPonerEnLnea.isSelected()) {
					server.start();
				}
				
				else {
					server.closeServer();
				}
			}
		});
		chckbxPonerEnLnea.setForeground(Color.LIGHT_GRAY);
		chckbxPonerEnLnea.setBackground(Color.DARK_GRAY);
		chckbxPonerEnLnea.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxPonerEnLnea.setBounds(216, 43, 123, 23);
		panelPpal.add(chckbxPonerEnLnea);
		chckbxPonerEnLnea.setFocusPainted(false);
		chckbxPonerEnLnea.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(FrameServer.class.getResource("/img/marvel2.png")));
		lblFondo.setBounds(0, 0, 550, 400);
		panelPpal.add(lblFondo);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(487, 0, 83, 23);
		contentPane.add(btnSalir);
		btnSalir.setFont(new Font("Verdana", Font.BOLD, 11));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				server.closeServer();
				System.exit(1);
			}
		});
		btnSalir.setBackground(Color.GRAY);
		btnSalir.setFocusPainted(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
