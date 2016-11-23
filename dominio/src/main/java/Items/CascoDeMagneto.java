package items;

import character.PC;
import statListeners.DefenseListener;

public class CascoDeMagneto extends ItemCabeza implements DefenseListener
{

	public CascoDeMagneto()
	{
		nombre="Caso de Magneto";
		descripcion="Casco que protege de los poderes psiquicos."
				+ "\n\n+20 Defensa";
	}
	
	@Override
	public void equipar(PC pj) {
		pj.defenseSubscribe(this);
	}

	@Override
	public void desequipar(PC pj) {
		pj.defenseUnsubscribe(this);
	}

	@Override
	public int getDefenseBonus() {
		return 20;
	}

}
