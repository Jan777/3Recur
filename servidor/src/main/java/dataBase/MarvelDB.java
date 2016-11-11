package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import character.Personaje;
import user.Usuario;

public class MarvelDB {

	private final String RUTA = "Sqliteman/Bases de datos/marvelJRPG.db";
	private Connection conexion;
	private LoginDB login;

	public void connect() {
		try {
			conexion = DriverManager.getConnection("jdbc:sqlite:" + RUTA);
			login = new LoginDB(conexion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean crearUsuario(Usuario user) {
		try {
			return login.create(user);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean buscarUsuario(Usuario user) {
		try {
			return login.search(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean crearPersonaje(Usuario user, Personaje pj) {
		// AUN NO IMPLEMENTADO
		return false;
	}

	public void close() {
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
