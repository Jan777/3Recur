package Game;

public enum Clase 
{
	AGENTE   (1f,   1.3f, 0.7f),
	HECHIZERO(0.7f, 1f,   1.3f),
	TANQUE   (1.3f, 0.7f,   1f);		
	
	private float bonusFuerza;
	private float bonusDestreza;
	private float bonusInteligencia;
	private final int bonusAtaque=4;
	private final int bonusDefensa=4;
	private final int bonusVelocidad=5;
	private final int bonusEvacion=2;
	private final int bonusDanoMagico=4;
	private final int bonusEnergiaMax=10;
	
	Clase(float bonusFuerza,float bonusDestreza,float bonusInteligencia)
	{
		this.bonusFuerza=bonusFuerza;
		this.bonusDestreza=bonusDestreza;
		this.bonusInteligencia=bonusInteligencia;
	}
	
	public int getAtaqueBonus(int puntos)
	{
		return (int)(bonusFuerza*bonusAtaque*puntos);
	}
	
	public int getDefensaBonus(int puntos)
	{
		return (int)(bonusFuerza*bonusDefensa*puntos);
	}
	
	public int getVelocidadBonus(int puntos)
	{
		return (int)(bonusDestreza*bonusVelocidad*puntos);
	}
	
	public int getEvacionBonus(int puntos)
	{
		return (int)(bonusDestreza*bonusEvacion*puntos);
	}
	
	public int getDanoMagicoBonus(int puntos)
	{
		return (int)(bonusInteligencia*bonusDanoMagico*puntos);
	}
	
	public int getEnergiaMaxBonus(int puntos)
	{
		return (int)(bonusInteligencia*bonusEnergiaMax*puntos);
	}
}