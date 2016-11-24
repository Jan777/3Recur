package Window;



import Game.*;
import Items.CascoDeMagneto;
import Items.CascoDeNova;

public class Batallon extends Thread {
	static private Conector alertas;
	private PC p1;
	private PC p2;
	private boolean player1;

	public static void main(String[] args) throws InterruptedException {
		PC p1 = new Asgardiano("PersonajeDePrueba1", Clase.AGENTE);
		PC p2 = new Hulk("PersonajeDePrueba2", Clase.AGENTE);
		p2.equiparItemCabeza(new CascoDeNova());
		p1.equiparItemCabeza(new CascoDeMagneto());
		Batallon ventana1 = new Batallon(p1, p2,true);
		ventana1.start();
		Batallon ventana2 = new Batallon(p2, p1,false);
		Thread.sleep((long)1);
		ventana2.start();
	}

	public Batallon(PC p1, PC p2,boolean player1) {
		this.player1=player1;
		this.p1 = p1;
		this.p2 = p2;
		alertas = new Conector();
	}

	@Override
	public void run() {
		Batalla bata = new Batalla(p1, p2, alertas, player1);
		alertas.add(bata,player1);
		bata.setVisible(true);
	}
}
