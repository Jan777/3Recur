package entidades;

import gfx.Pantalla;
import nivel.Nivel;

public abstract class Entidad {
	
	protected int x, y;
	protected Nivel nivel;
	
	public Entidad(Nivel nivel)
	{
		init(nivel);
	}
	
	public final void init(Nivel level)
	{
		this.nivel = level;
	}
	
	public abstract void actualizar();
	
	public abstract void renderizar(Pantalla screen);
	

}
