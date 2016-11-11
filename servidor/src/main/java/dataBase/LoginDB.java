package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import user.Usuario;

public class LoginDB implements DataAccessObject<Usuario>{

	private Connection conexion;
	private final String insertSQL = "insert into usuarios (nombre, contraseña) values (?, ?);";
	private PreparedStatement insertPreparedStatement;
	private final String selectNombreSQL = "select * from usuarios where nombre = ?;";
	private PreparedStatement selectPreparedStatement;
	private final String logueoSQL = "update usuarios set conectado = ? where nombre = ?;";
	private PreparedStatement updatePreparedStatement;
	
	public LoginDB(Connection conexion) {
		this.conexion = conexion;
	}

	@Override
	public boolean create(Usuario user) throws Exception {
		insertPreparedStatement = conexion.prepareStatement(insertSQL);
		
		if(search(user)) {
			return false;
		}
		
		insertPreparedStatement.setString(1, user.getNombre());
		insertPreparedStatement.setString(2, user.getContraseña());
		insertPreparedStatement.executeUpdate();
		return true;
	}

	@Override
	public boolean search(Usuario user) throws Exception {
		selectPreparedStatement = conexion.prepareStatement(selectNombreSQL);
		
		selectPreparedStatement.setString(1, user.getNombre());
		
		ResultSet res = selectPreparedStatement.executeQuery();
		
		if(res.next()) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean update(Usuario entidad) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Usuario entidad) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close() throws Exception {
		insertPreparedStatement.close();
		selectPreparedStatement.close();
	}

	public boolean loguear(Usuario user) throws Exception{
		updatePreparedStatement = conexion.prepareStatement(logueoSQL);
		
		try {
			updatePreparedStatement.setInt(1, 1);
			updatePreparedStatement.setString(2, user.getNombre());
			user.conectar();
			return true;
		} catch (Exception e) {
			user.desconectar();
			return false;
		}
	}

}
