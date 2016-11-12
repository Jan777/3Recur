package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import user.Usuario;

public class UserDB {

	private Connection conexion;
	private final String insertSQL = "insert into usuarios (nombre, contraseña) values (?, ?);";
	private PreparedStatement insertPreparedStatement;
	private final String selectNombreSQL = "select * from usuarios where nombre = ?;";
	private PreparedStatement selectPreparedStatement;
	private final String connectSQL = "update usuarios set conectado = ? where nombre = ?;";
	private PreparedStatement updatePreparedStatement;

	public UserDB(Connection conexion) {
		this.conexion = conexion;
	}

	public boolean create(Usuario user) throws Exception {
		insertPreparedStatement = conexion.prepareStatement(insertSQL);
		Usuario aux = user.clone();
		
		if (search(aux)) {
			return false;
		}

		insertPreparedStatement.setString(1, user.getNombre());
		insertPreparedStatement.setString(2, user.getContraseña());
		System.out.println(user.getNombre()+user.getContraseña());
		insertPreparedStatement.executeUpdate();
		return true;
	}

	public boolean search(Usuario user) throws Exception {
		selectPreparedStatement = conexion.prepareStatement(selectNombreSQL);

		selectPreparedStatement.setString(1, user.getNombre());

		ResultSet res = selectPreparedStatement.executeQuery();

		if (res.next()) {
			user.setContraseña(res.getString("contraseña"));
			return true;
		}

		return false;
	}

	public void close() throws Exception {
		insertPreparedStatement.close();
		selectPreparedStatement.close();
	}

	public boolean connect(Usuario user) throws Exception {
		updatePreparedStatement = conexion.prepareStatement(connectSQL);

		Usuario aux = user.clone();
		if(!search(aux) || !aux.getContraseña().equals(user.getContraseña()))
			return false;
		
		updatePreparedStatement.setInt(1, 1);
		updatePreparedStatement.setString(2, user.getNombre());
		user.conectar();
		updatePreparedStatement.executeUpdate();
		return true;
	}

	public boolean disconnect(Usuario user) throws Exception {
		updatePreparedStatement = conexion.prepareStatement(connectSQL);

		try {
			updatePreparedStatement.setInt(1, 0);
			updatePreparedStatement.setString(2, user.getNombre());
			user.conectar();
			updatePreparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			user.desconectar();
			return false;
		}
	}

}
