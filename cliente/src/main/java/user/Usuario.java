package user;

public class Usuario {

	private String nombre;
	private String contrase�a;
	private boolean conectado = false;
	
	public Usuario(String nombre, String contrase�a) {
		this.nombre = nombre;
		this.contrase�a = contrase�a;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
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
