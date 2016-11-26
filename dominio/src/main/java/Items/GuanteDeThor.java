package items;

import character.PC;
import statListeners.*;

public class GuanteDeThor extends ItemMano implements SpeedListener , EvasionListener,AttackListener{

	public GuanteDeThor() {
		nombre = "Guante de Thor";
		descripcion = "Guante que dismuye el transcurso del tiempo" + "\n\n+20 Velocidad";
	}

	@Override
	public void equipar(PC pj) {
		pj.speedSubscribe(this);
		pj.evasionSubscribe(this);
		pj.attackSubscribe(this);
	}

	@Override
	public void desequipar(PC pj) {
		pj.speedUnsubscribe(this);
		pj.evasionUnsubscribe(this);
		pj.attackUnsubscribe(this);
	}

	@Override
	public int getSpeedBonus() {
		return 20;
	}

	@Override
	public int getEvasionBonus() {
		return 20;
	}

	@Override
	public int getAttackBonus() {
		return 5;
	}

}
