package Window;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.*;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Batalla extends JFrame {

	private JPanel contentPane;
	private PC jugador1;
	private Personaje jugador2;
	private ArrayList<Ataque> ataques = new ArrayList<Ataque>();
	private JButton btnAtaqueFisico;
	private JButton btnAtaqueMagico;
	JLabel lblJugador;
	JLabel mensajesServer;
	JLabel lblJugador_1;
	JLabel lblVidajugador;
	JLabel lblVidajugador_1;
	JLabel lblEnergia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Batalla frame = new Batalla(new Asgardiano("Jose", Clase.AGENTE),
							new Hulk("Jorge", Clase.HECHIZERO));
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
	public Batalla(PC jugador1, PC jugador2) {
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		jugador1.empezarPelea();
		jugador2.empezarPelea();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblJugador = new JLabel("Jugador 1");
		lblJugador.setBounds(10, 13, 106, 21);
		contentPane.add(lblJugador);

		lblJugador_1 = new JLabel("Jugador 2");
		lblJugador_1.setBounds(327, 14, 97, 20);
		contentPane.add(lblJugador_1);

		lblVidajugador = new JLabel("VidaJugador1");
		lblVidajugador.setBounds(21, 38, 193, 14);
		contentPane.add(lblVidajugador);
		lblVidajugador.setText("Vida Actual " + jugador1.getVidaActual());

		lblVidajugador_1 = new JLabel("VidaJugador2");
		lblVidajugador_1.setBounds(299, 38, 114, 14);
		contentPane.add(lblVidajugador_1);

		lblVidajugador_1.setText("Vida Actual " + jugador2.getVidaActual());
		lblVidajugador.setText("Vida Actual " + jugador2.getVidaActual());

		lblEnergia = new JLabel("Energia");
		lblEnergia.setBounds(24, 63, 193, 14);
		contentPane.add(lblEnergia);
		lblEnergia.setText("Energia Actual " + jugador1.getEnergiaActual());

		btnAtaqueFisico = new JButton("Ataque Fisico");
		btnAtaqueFisico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarAtaque(new Ataque(10, 20, jugador2, true));
			}
		});
		btnAtaqueFisico.setBounds(68, 160, 114, 23);
		contentPane.add(btnAtaqueFisico);

		btnAtaqueMagico = new JButton("Ataque Magico");
		btnAtaqueMagico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jugador1.getEnergiaActual() > 10) {
					// reducir energia;
					agregarAtaque(new Ataque(40, 30, jugador2, false));

				} else
					mensajesServer.setText("No tienes suficiente Energia ahora");
			}
		});
		btnAtaqueMagico.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAtaqueMagico.setBounds(221, 160, 106, 23);
		contentPane.add(btnAtaqueMagico);

		JLabel lblElAtaqueMagico = new JLabel(
				"<html>(El ataque magico cuesta 10 de energia, pero no se ve afectado por la defensa del enemigo)</html>");
		lblElAtaqueMagico.setHorizontalAlignment(SwingConstants.CENTER);
		lblElAtaqueMagico.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblElAtaqueMagico.setBounds(211, 194, 159, 46);
		contentPane.add(lblElAtaqueMagico);

		mensajesServer = new JLabel("");
		mensajesServer.setBounds(21, 118, 364, 14);
		contentPane.add(mensajesServer);
	}

	public void agregarAtaque(Ataque attack) {
		ataques.add(attack);
		if (ataques.size() == 2)
			iniciarTurno();
	}

	public void iniciarTurno() {
		ataques.sort(null);
		int i = 0;
		int cant=ataques.size();
		while (!jugador1.estaMuerto() && !jugador2.estaMuerto() && i < cant) {
			ataques.get(0).atacar();
			lblVidajugador_1.setText("Vida Actual " + jugador2.getVidaActual());
			ataques.remove(0);
			if (jugador2.estaMuerto()) {
				jugador1.ganarExp(50);
				mensajesServer.setText("Muere Jugador2"+'\n'+"   Jugador 1 gana 50 xp");
			}

			i++;
			try {
				TimeUnit.SECONDS.sleep((long) 0.5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

		jugador1.finalTurno();
		// recupera energia el jugador1
		try {
			TimeUnit.SECONDS.sleep((long) 0.5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		jugador2.finalTurno();
		try {
			TimeUnit.SECONDS.sleep((long) 0.5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		btnAtaqueFisico.setEnabled(true);
		btnAtaqueMagico.setEnabled(true);
	}
}
