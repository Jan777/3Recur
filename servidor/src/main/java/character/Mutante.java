package character;

public class Mutante extends PC
{
	public Mutante(String nombre,Clase clase)
	{
		this.ataqueBase=11;
		this.defensaBase=10;
		this.velocidadBase=17;
		this.danoMagicoBase=11;
		this.energiaMaximaBase=20;
		this.vidaMaximaBase=54;
		this.vidaMaximaPorNivel=4;
		
		this.nombre=nombre;
		this.clase=clase;
		
		this.exp=0;
	}
}
