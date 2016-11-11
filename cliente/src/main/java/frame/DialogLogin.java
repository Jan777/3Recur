package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class DialogLogin extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	protected int y;
	protected int x;
	private String titulo;
	private String mensaje;

	/**
	 * Create the dialog.
	 */
	public DialogLogin(JFrame padre, String title, String mensaje, boolean modal) {
		super(padre, title, modal);
		this.titulo = title;
		this.mensaje = mensaje;
		inicializarVentana();
	}

	private void inicializarVentana() {
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setBounds(100, 100, 303, 300);
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);

		JPanel panelBarra = new JPanel();
		panelBarra.setBackground(new Color(0, 0, 0));
		panelBarra.setBounds(0, 0, 222, 23);
		contentPanel.add(panelBarra);
		panelBarra.setLayout(null);
		panelBarra.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		
		JLabel labelTitulo = new JLabel(titulo);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setForeground(Color.WHITE);
		labelTitulo.setFont(new Font("Verdana", Font.BOLD, 12));
		labelTitulo.setBounds(0, 0, 212, 23);
		panelBarra.add(labelTitulo);
		
		JButton button = new JButton("Cerrar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Verdana", Font.BOLD, 11));
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setBackground(new Color(0, 0, 102));
		button.setBounds(220, 0, 83, 23);
		contentPanel.add(button);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOk.setForeground(Color.BLACK);
		btnOk.setFont(new Font("Verdana", Font.BOLD, 11));
		btnOk.setFocusPainted(false);
		btnOk.setBorderPainted(false);
		btnOk.setBackground(Color.LIGHT_GRAY);
		btnOk.setBounds(78, 210, 144, 23);
		contentPanel.add(btnOk);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(24, 82, 255, 99);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblmensaje = new JLabel(mensaje);
		lblmensaje.setBounds(0, 0, 255, 99);
		lblmensaje.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblmensaje);
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
	}
}
