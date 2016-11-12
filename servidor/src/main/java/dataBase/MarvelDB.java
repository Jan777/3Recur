package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import character.Personaje;
import user.Usuario;

public class MarvelDB {

	private final String RUTA = "db/marvelJRPG.db";
	private Connection conexion;
	private UserDB userDB;
	private PersonajeDB pjDB;

	public MarvelDB() {
		connectDB();
	}
	public void connectDB() {
		try {
			conexion = DriverManager.getConnection("jdbc:sqlite:" + RUTA);
			userDB = new UserDB(conexion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean crearUsuario(Usuario user) {
		try {
			return userDB.create(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean buscarUsuario(Usuario user) {
		try {
			return userDB.search(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean loguearUsuario(Usuario user) {
		try {
			return userDB.connect(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean desconectarUsuario(Usuario user) {
		try {
			return userDB.disconnect(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean crearPersonaje(Usuario user, Personaje pj) {
		try {
			return pjDB.create(user, pj);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Personaje> buscarPersonajes(Usuario user) {
		try {
			
			if(user.verEstado())
				return pjDB.searchAll(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void close() {
		try {
			if(conexion != null)
				conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
