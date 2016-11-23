package items;

import character.PC;

public abstract class ItemEquipable extends Item
{
	public abstract void equipar(PC pj);
	public abstract void desequipar(PC pj);
}
