package Window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.*;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CharacterStats extends JFrame {

	private JPanel contentPane;
	private PC pj;
	private JTextField expCant;
	JLabel lblCharactername;
	JLabel lblCharacterlevel;
	JLabel lblCharacterexp;
	JLabel lblCharacterattack;
	JLabel lblCharacterdeffense;
	JLabel lblCharacterspeed;
	JLabel lblCharacterevasion;
	JLabel lblCharacterdanomagico;
	JLabel lblCharactermaxenergy;
	JLabel lblCharactermaxlife;
	JLabel lblCharacterfuerza;
	JLabel lblCharacterdestreza;
	JLabel lblCharacterinteligencia;
	JLabel lblCharacterpuntosdisponibles;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CharacterStats frame = new CharacterStats(new Mutante("asd",Clase.TANQUE));
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
	
	
	public void updateStats()
	{
		lblCharactername.setText(pj.getName());
		lblCharacterlevel.setText("nivel "+pj.getNivel());
		lblCharacterexp.setText("Exp:"+pj.getExp()+"/"+PC.requisitoExp[pj.getNivel()-1]+" ("+pj.expRestante()+" puntos para el siguiente nivel)");
		lblCharacterattack.setText("Ataque:"+pj.calcularAtaque());
		lblCharacterdeffense.setText("Defensa:"+pj.calcularDefensa());
		lblCharacterdanomagico.setText("Daño Magico:"+pj.calcularDanoMagico());
		lblCharacterevasion.setText("Evasion:"+pj.calcularEvasion());
		lblCharacterspeed.setText("Velocidad:"+pj.calcularVelocidad());
		lblCharactermaxlife.setText("Vida Maxima:"+pj.calcularVidaMaxima());
		lblCharactermaxenergy.setText("Energia Maxima:"+pj.calcularEnergiaMax());
		
		lblCharacterpuntosdisponibles.setText(pj.getPuntosDisponibles()+" puntos de habilidad disponibles");
		lblCharacterfuerza.setText("Fuerza:"+pj.getPuntosFuerza());
		lblCharacterdestreza.setText("Destreza:"+pj.getPuntosDestreza());
		lblCharacterinteligencia.setText("Inteligencia:"+pj.getPuntosInteligencia());
		
		
	}
	
	public CharacterStats(PC personaje) {
		pj=personaje;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCharactername = new JLabel("character_name");
		lblCharactername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCharactername.setBounds(10, 16, 162, 14);
		contentPane.add(lblCharactername);
		
		lblCharacterlevel = new JLabel("character_level");
		lblCharacterlevel.setBounds(145, 17, 87, 14);
		contentPane.add(lblCharacterlevel);
		
		lblCharacterexp = new JLabel("exp_info");
		lblCharacterexp.setBounds(10, 39, 254, 14);
		contentPane.add(lblCharacterexp);
		
		lblCharacterattack = new JLabel("character_Attack");
		lblCharacterattack.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
		lblCharacterattack.setBounds(10, 119, 162, 14);
		contentPane.add(lblCharacterattack);
		
		lblCharacterdeffense = new JLabel("character_Deffense");
		lblCharacterdeffense.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
		lblCharacterdeffense.setBounds(10, 144, 162, 14);
		contentPane.add(lblCharacterdeffense);
		
		lblCharacterspeed = new JLabel("character_Speed");
		lblCharacterspeed.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
		lblCharacterspeed.setBounds(10, 169, 162, 14);
		contentPane.add(lblCharacterspeed);
		
		lblCharacterevasion = new JLabel("character_Evasion");
		lblCharacterevasion.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
		lblCharacterevasion.setBounds(10, 194, 162, 14);
		contentPane.add(lblCharacterevasion);
		
		lblCharacterdanomagico = new JLabel("character_DanoMagico");
		lblCharacterdanomagico.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
		lblCharacterdanomagico.setBounds(10, 219, 162, 14);
		contentPane.add(lblCharacterdanomagico);
		
		lblCharactermaxenergy = new JLabel("character_Max_Energy");
		lblCharactermaxenergy.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
		lblCharactermaxenergy.setBounds(10, 94, 162, 14);
		contentPane.add(lblCharactermaxenergy);
		
		lblCharactermaxlife = new JLabel("character_Max_Life");
		lblCharactermaxlife.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
		lblCharactermaxlife.setBounds(10, 69, 162, 14);
		contentPane.add(lblCharactermaxlife);
		
		lblCharacterfuerza = new JLabel("character_Fuerza");
		lblCharacterfuerza.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblCharacterfuerza.setBounds(231, 102, 105, 25);
		contentPane.add(lblCharacterfuerza);
		
		lblCharacterdestreza = new JLabel("character_Destreza");
		lblCharacterdestreza.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblCharacterdestreza.setBounds(231, 133, 105, 25);
		contentPane.add(lblCharacterdestreza);
		
		lblCharacterinteligencia = new JLabel("character_Inteligencia");
		lblCharacterinteligencia.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblCharacterinteligencia.setBounds(231, 165, 105, 25);
		contentPane.add(lblCharacterinteligencia);
		
		lblCharacterpuntosdisponibles = new JLabel("character_Puntos_Disponibles");
		lblCharacterpuntosdisponibles.setBounds(201, 195, 207, 14);
		contentPane.add(lblCharacterpuntosdisponibles);
		
		updateStats();
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pj.getPuntosDisponibles()>0)
				{
					pj.subirDestreza();
					updateStats();
				}
				else 
					{
					PuntosHabilidadInsuficientes alert=new PuntosHabilidadInsuficientes();
					alert.setVisible(true);
					}
			}
		});
		button.setIcon(new ImageIcon(CharacterStats.class.getResource("/recursos/plus_icon3.png")));
		button.setFont(new Font("Tahoma", Font.BOLD, 6));
		button.setBounds(356, 136, 25, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pj.getPuntosDisponibles()>0)
				{
					pj.subirFuerza();
					updateStats();
				}
				else 
					{
					PuntosHabilidadInsuficientes alert=new PuntosHabilidadInsuficientes();
					alert.setVisible(true);
					}
			}
		});
		button_1.setIcon(new ImageIcon(CharacterStats.class.getResource("/recursos/plus_icon3.png")));
		button_1.setFont(new Font("Tahoma", Font.BOLD, 6));
		button_1.setBounds(355, 103, 25, 25);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pj.getPuntosDisponibles()>0)
				{
					pj.subirInteligencia();
					updateStats();
				}
				else 
					{
					PuntosHabilidadInsuficientes alert=new PuntosHabilidadInsuficientes();
					alert.setVisible(true);
					}
			}
		});
		button_2.setIcon(new ImageIcon(CharacterStats.class.getResource("/recursos/plus_icon3.png")));
		button_2.setFont(new Font("Tahoma", Font.BOLD, 6));
		button_2.setBounds(356, 166, 25, 25);
		contentPane.add(button_2);
		
		expCant = new JTextField();
		//expCant.setDropMode(DropMode.ON);
		expCant.setBounds(302, 36, 72, 20);
		contentPane.add(expCant);
		expCant.setColumns(10);
		
		JButton btnGanarExp = new JButton("Ganar Exp");
		btnGanarExp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int exp=Integer.parseInt(expCant.getText());
				pj.ganarExp(exp);
				expCant.setText("");
				updateStats();
			}
		});
		btnGanarExp.setBounds(293, 9, 89, 23);
		contentPane.add(btnGanarExp);
	}
}
