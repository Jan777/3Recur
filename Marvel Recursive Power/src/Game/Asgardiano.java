package Game;

public class Asgardiano extends PC
{
	public Asgardiano(String nombre,Clase clase)
	{
		this.ataqueBase=13;
		this.defensaBase=12;
		this.velocidadBase=12;
		this.danoMagicoBase=15;
		this.energiaMaximaBase=26;
		this.vidaMaximaBase=62;
		this.vidaMaximaPorNivel=5;
		
		this.nombre=nombre;
		this.clase=clase;
		
		this.exp=0;
	}

}
