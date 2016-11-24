package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import character.Asgardiano;
import character.Clase;
import character.Hulk;
import character.Mutante;
import character.PC;

public class DialogCreacionPj extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	protected PC personaje;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		try {
			DialogCreacionPj dialog = new DialogCreacionPj();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogCreacionPj(FrameLogin padre) {
		super(padre, "Creacion del Personaje", true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(padre);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setBounds(100, 100, 330, 195);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(30, 34, 46, 14);
		getContentPane().add(lblNombre);
		
		JTextField textField = new JTextField();
		textField.setBounds(10, 59, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblClase = new JLabel("Clase");
		lblClase.setBounds(267, 6, 46, 14);
		getContentPane().add(lblClase);
		
		JRadioButton claseAgente = new JRadioButton("Agente");
		claseAgente.setBounds(229, 28, 109, 23);
		getContentPane().add(claseAgente);
		
		JRadioButton claseHechizero = new JRadioButton("Hechizero");
		claseHechizero.setBounds(229, 58, 109, 23);
		getContentPane().add(claseHechizero);
		
		JRadioButton claseTanque = new JRadioButton("Tanque");
		claseTanque.setBounds(229, 87, 109, 23);
		getContentPane().add(claseTanque);
		
		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setBounds(154, 6, 46, 14);
		getContentPane().add(lblRaza);
		
		JRadioButton razaMutante = new JRadioButton("Mutante");
		razaMutante.setBounds(118, 30, 109, 23);
		getContentPane().add(razaMutante);
		
		JRadioButton razaAsgardiano = new JRadioButton("Asgardiano");
		razaAsgardiano.setBounds(118, 58, 109, 23);
		getContentPane().add(razaAsgardiano);
		
		JRadioButton razaHulk = new JRadioButton("Hulk");
		razaHulk.setBounds(118, 87, 109, 23);
		getContentPane().add(razaHulk);
		
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
				
				padre.setPj(personaje);
				dispose();
			}
		});
		btnCrear.setBounds(118, 117, 89, 23);
		getContentPane().add(btnCrear);
	}

	public PC getPersonaje() {
		return personaje;
	}
}
