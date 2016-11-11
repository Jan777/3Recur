package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import character.Personaje;
import user.Usuario;

public class MarvelDB {

	private final String RUTA = "db/marvelJRPG.db";
	private Connection conexion;
	private UserDB userDB;

	public void connectDB() {
		try {
			conexion = DriverManager.getConnection("jdbc:sqlite:" + RUTA);
			userDB = new UserDB(conexion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean crearUsuario(Usuario user) {
		try {
			return userDB.create(user);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean buscarUsuario(Usuario user) {
		try {
			return userDB.search(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean loguearUsuario(Usuario user) {
		try {
			return userDB.connect(user);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean desconectarUsuario(Usuario user) {
		try {
			return userDB.connect(user);
			
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
