package entidades;

import gfx.Pantalla;
import nivel.Nivel;

public abstract class Entidad {
	
	protected int x, y;
	protected Nivel nivel;
	protected String nombre;
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
	
	public abstract String getName();
	
	public abstract void setXY(int x, int y);
	
	

}
