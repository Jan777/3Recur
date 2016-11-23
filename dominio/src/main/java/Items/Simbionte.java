package items;

import character.PC;
import statListeners.VidaMaximaListener;

public class Simbionte extends ItemPecho implements VidaMaximaListener
{
	public Simbionte()
	{
		nombre="Simbionte";
		descripcion="Ser alienigena capaz de alcanzar una union perfecta con el cuerpo humano."
				+ "\n\n+25 Vida Maxima";
	}
	
	@Override
	public void equipar(PC pj) {
		pj.vidaMaximaSubscribe(this);
	}

	@Override
	public void desequipar(PC pj) {
		pj.vidaMaximaUnsubscribe(this);
		
	}

	@Override
	public int getVidaMaximaBonus() 
	{
		return 25;
	}

}
