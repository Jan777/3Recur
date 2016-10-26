package Tests;

import Game.*;
import Game.Items.*;

public class PersonajeTests {

	public static void main(String[] args) 
	{
		PC pj= new Mutante("Wolverine",Clase.AGENTE);
		ItemCabeza item= new CascoDeMagneto();
		ItemCabeza item2=new CascoDeNova();
		ItemPecho item3=new ReactorARC();
		pj.ganarExp(54);
		pj.subirFuerza();
		pj.subirFuerza();
		pj.subirDestreza();
		pj.subirInteligencia();
		pj.equiparItemCabeza(item);
		pj.equiparItemCabeza(item2);
		pj.equiparItemPecho(item3);
		pj.mostrarPersonaje();
		
		
		
	}

}
