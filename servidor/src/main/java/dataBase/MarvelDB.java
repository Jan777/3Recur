package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import user.*;
import character.*;

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
			pjDB = new PersonajeDB(conexion);
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
	
	public boolean crearPersonaje(Usuario user, PC pj) {
		try {
			
			return pjDB.create(user, pj);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public PC buscarPersonaje(Usuario user) {
		try {
			if(user.verEstado())
				return pjDB.search(user);
			
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
			e.printStackTrace();
		}
	}
}
