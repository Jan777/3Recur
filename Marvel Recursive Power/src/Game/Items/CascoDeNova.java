package Game.Items;

import Game.PC;
import Game.StatListeners.*;

public class CascoDeNova extends ItemCabeza implements SpeedListener, DanoMagicoListener
{
	
	public CascoDeNova()
	{
		nombre="Caso de Nova";
		descripcion="Casco que otorga el poder de los Nova Corps"
				+ "\n\n+15 Daño Magico\n+10 Velocidad";
	}

	@Override
	public int getDanoMagicoBonus() {
		return 15;
	}

	@Override
	public int getSpeedBonus() {
		return 10;
	}

	@Override
	public void equipar(PC pj) 
	{
		pj.speedSubscribe(this);
		pj.danoMagicoSubscribe(this);
	}

	@Override
	public void desequipar(PC pj) {
		pj.speedUnsubscribe(this);
		pj.danoMagicoUnsubscribe(this);
	}
	
}
