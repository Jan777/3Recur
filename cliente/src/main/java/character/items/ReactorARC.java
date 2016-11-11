package character.items;

import character.PC;
import character.statListeners.EnergiaMaximaListener;

public class ReactorARC extends ItemPecho implements EnergiaMaximaListener
{
	
	public ReactorARC()
	{
		nombre="Reactor ARC";
		descripcion="Fuente de energía que alimenta el traje Iron Man."
				+ "\n\n+50 energia";
	}

	@Override
	public void equipar(PC pj) {
		pj.energiaMaximaSubscribe(this);
	}

	@Override
	public void desequipar(PC pj) {
		pj.energiaMaximaUnsubscribe(this);
	}

	@Override
	public int getEnergiaMaximaBonus() {
		return 50;
	}

}
