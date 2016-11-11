package character;

public class Hulk extends PC
{
	public Hulk(String nombre,Clase clase)
	{
		this.ataqueBase=11;
		this.defensaBase=14;
		this.velocidadBase=6;
		this.danoMagicoBase=9;
		this.energiaMaximaBase=13;
		this.vidaMaximaBase=57;
		this.vidaMaximaPorNivel=5;
		
		this.nombre=nombre;
		this.clase=clase;
		
		this.exp=0;
	}
}
