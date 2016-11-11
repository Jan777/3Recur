package user;

public class Usuario {

	private String nombre;
	private String contraseña;
	private boolean conectado = false;
	
	public Usuario(String nombre, String contraseña) {
		this.nombre = nombre;
		this.contraseña = contraseña;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public void conectar() {
		this.conectado = true;
	}
	
	public void desconectar() {
		this.conectado = false;
	}
	
	public boolean verEstado() {
		return conectado;
	}
}
