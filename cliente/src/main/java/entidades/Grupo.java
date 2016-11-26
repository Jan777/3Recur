package entidades;

import nivel.Nivel;
import tiles.Tile;

public abstract class Grupo extends Entidad {

	// nombre de la multitud
	

	protected int velocidad;
	// cantidad de pasos que caminaron
	protected int numPasos = 0;
	protected boolean enMovimiento;
	protected int direccionMovimiento = 1;
	protected int escala = 1;

	public Grupo(Nivel nivel, String nombre, int x, int y, int velocidad) {
		super(nivel);
		this.nombre = nombre;
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;

	}

	public void mover(int xa, int ya) {
		// si se esta por mover en ambas direcciones, primero muevo en una
		// direccion y luego en la otra
		if (xa != 0 && ya != 0) {
			mover(xa, 0);
			mover(0, ya);
			// decrementamos ya que como estamos moviendonos en ambas
			// direcciones
			// lo contaria como dos pasos pero es un paso en diagonal
			numPasos--;
			return;
		}
		numPasos++;
		if (!colision(xa, ya)) {
			if (ya < 0)
				direccionMovimiento = 0;
			if (ya > 0)
				direccionMovimiento = 1;
			if (xa < 0)
				direccionMovimiento = 2;
			if (xa > 0)
				direccionMovimiento = 3;
			x += xa * velocidad;
			y += ya * velocidad;
		}
		
	}

	protected boolean esTileSolido(int xa, int ya, int x, int y) {
		if (nivel == null) {
			return false;
		}
		Tile tileOrigen = nivel.getTile((this.x + x) >> 3, (this.y + y) >> 3);
		Tile tileDestino = nivel.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
		if (!tileOrigen.equals(tileDestino) && tileDestino.esSolido()) {
			return true;
		}

		return false;
	}

	public abstract boolean colision(int xa, int ya);

	public String getNombre() {
		return nombre;
	}

}
