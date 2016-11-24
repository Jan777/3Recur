package Window;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.*;

import javax.swing.JLabel;

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
	private Conector alertas;
	private Personaje jugador2;
	private ArrayList<Ataque> ataques = new ArrayList<Ataque>();
	private JButton btnAtaqueFisico;
	private JButton btnAtaqueMagico;
	private JLabel lblJugador;
	private JLabel mensajesServer;
	private JLabel lblJugador_1;
	private JLabel lblVidajugador;
	private JLabel lblVidajugador_1;
	public JLabel getMensajesServer() {
		return mensajesServer;
	}

	public JLabel getLblVidajugador() {
		return lblVidajugador;
	}

	public JLabel getLblVidajugador_1() {
		return lblVidajugador_1;
	}

	private JLabel lblEnergia;
	private boolean player1;
	boolean primero;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			//		Batalla frame = new Batalla(new Asgardiano("Jose", Clase.AGENTE),
				//			new Hulk("Jorge", Clase.HECHIZERO));
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Batalla(PC jugador1, PC jugador2,Conector alertas,boolean player1) {
		this.player1 = player1;
		this.alertas = alertas;
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
		lblJugador.setBounds(20, 25, 106, 21);
		contentPane.add(lblJugador);

		lblJugador_1 = new JLabel("Jugador 2");
		lblJugador_1.setBounds(309, 25, 97, 20);
		contentPane.add(lblJugador_1);

		


		lblVidajugador = new JLabel("VidaJugador1");
		lblVidajugador.setBounds(20, 57, 193, 14);
		contentPane.add(lblVidajugador);
		lblVidajugador.setText("Vida Actual " + jugador1.getVidaActual());

		lblVidajugador_1 = new JLabel("VidaJugador2");
		lblVidajugador_1.setBounds(309, 57, 114, 14);
		contentPane.add(lblVidajugador_1);

    lblVidajugador_1.setText("Vida Actual " + jugador2.getVidaActual());


		lblEnergia = new JLabel("Energia");
		lblEnergia.setBounds(20, 80, 193, 14);
		contentPane.add(lblEnergia);
		lblEnergia.setText("Energia Actual " + jugador1.getEnergiaActual());
		

		btnAtaqueFisico = new JButton("Ataque Fisico");
		btnAtaqueFisico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarAtaque(new Ataque(jugador1.calcularAtaque(),jugador1.calcularVelocidad(), jugador2, true));
			}
		});
		btnAtaqueFisico.setBounds(68, 160, 114, 23);
		contentPane.add(btnAtaqueFisico);

		btnAtaqueMagico = new JButton("Ataque Magico");
		btnAtaqueMagico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jugador1.getEnergiaActual() >= 10) {
					jugador1.perderEnergia(10);
					lblEnergia.setText("Energia Actual "+ jugador1.getEnergiaActual());
					agregarAtaque(new Ataque(jugador1.calcularDanoMagico(), jugador1.calcularVelocidad(), jugador2, false));

				} else
				{
						mensajesServer.setText("No tienes Energia");
				}
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
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(56, 0, 350, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setText("Player"+(player1?1:2)+" Nombre: "+jugador1.getName());
		if(!player1)
		{
		btnAtaqueFisico.setEnabled(false);
		btnAtaqueMagico.setEnabled(false);
		}
	}

	public void agregarAtaque(Ataque attack) {
		ataques.add(attack);
		if (ataques.size() == 2)
		{
			btnAtaqueFisico.setEnabled(false);
			btnAtaqueMagico.setEnabled(false);
			iniciarTurno();
		}
	}

	public void iniciarTurno() {
		ataques.sort(null);
		int i = 0;
		int cant=ataques.size();
		while (!jugador1.estaMuerto() && !jugador2.estaMuerto() && i < cant) {
			ataques.get(0).atacar();
			alertas.mostrarVida(player1,jugador2.getVidaActual());
			
			ataques.remove(0);
			if (jugador2.estaMuerto()) {
				jugador1.ganarExp(50);
				alertas.mostrarMensajesDeServer("Muere el player"+((player1)?2:1)+" y Player"+((player1)?1:2)+" gana 50 xp");
			} 

			i++;
			try {
				TimeUnit.SECONDS.sleep((long) 0.5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!jugador2.estaMuerto())
			alertas.habilitarBotones(!player1);
			btnAtaqueFisico.setEnabled(false);
			btnAtaqueMagico.setEnabled(false);		
		}
	}

	public JButton getBtnAtaqueFisico() {
		return btnAtaqueFisico;
	}

	public JButton getBtnAtaqueMagico() {
		return btnAtaqueMagico;
	}
}
