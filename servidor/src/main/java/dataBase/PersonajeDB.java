package dataBase;

import java.util.List;

import character.Personaje;
import user.Usuario;

public class PersonajeDB {

	private Connection conexion;
	private final String insertPJSQL = "insert into personajes (nombre, pj) values (?, ?);";
	private PreparedStatement insertPreparedStatement;
	private final String selectPJNombreSQL = "select * from personajes where nombre = ?;";
	private PreparedStatement selectPreparedStatement;
	private final String connectSQL = "update personajes set personaje = ? where nombre = ?;";
	private PreparedStatement updatePreparedStatement;

	public PersonajeDB(Connection conexion) {
		this.conexion = conexion;
	}
	
	public boolean create(Personaje pj) throws Exception {
		insertPreparedStatement = conexion.prepareStatement(insertSQL);
		Personaje aux = pj.clone();
		
		if (search(aux)) {
			return false;
		}
		//GETTERS PARA INSERTAR EN BD
		insertPreparedStatement.setString(1, pj.getNombre());
		insertPreparedStatement.setString(2, pj.getClase());
		insertPreparedStatement.setString(3, pj.calcularAtaque());
		insertPreparedStatement.setString(4, pj.calcularDefensa());
		insertPreparedStatement.setString(5, pj.calcularVelocidad());
		insertPreparedStatement.setString(6, pj.calcularDanoMagico());
		insertPreparedStatement.setString(7, pj.calcularEnergiaMaxima());
		insertPreparedStatement.setString(8, pj.calcularVidaMaxima());
		insertPreparedStatement.setString(9, pj.getExp());
		insertPreparedStatement.setString(10, pj.mostrarItemCabeza());
		insertPreparedStatement.setString(11, pj.mostrarItemPecho());
		insertPreparedStatement.setString(12, pj.mostrarItemManoDer());
		insertPreparedStatement.setString(13, pj.mostrarItemManoIzq());
		insertPreparedStatement.setString(14, pj.getNivel());
		insertPreparedStatement.executeUpdate();
		return true;
	}
	

	public void close() throws Exception {
		insertPreparedStatement.close();
		selectPreparedStatement.close();
	}
	public Personaje search(String user) throws Exception {
			
			selectPreparedStatement = conexion.prepareStatement(selectPJNombreSQL);

			selectPreparedStatement.setString(1, user);

			ResultSet res = selectPreparedStatement.executeQuery();
			
			if (res.next()) {
				
				//SETTERS VALORES PARA RECUPERAR DESDE BD
				pj.setNombre(user);
				pj.setClase(res.getString("clase"));
				pj.setAtaque(res.getString("ataque"));
				pj.setClase(res.getString("defensa"));
				pj.setClase(res.getString("velocidad"));
				pj.setClase(res.getString("danoMagico"));
				pj.setClase(res.getString("energiaMaxima"));
				pj.setClase(res.getString("vidaMaxima"));
				pj.setClase(res.getString("exp"));
				pj.setClase(res.getString("itemCabeza"));
				pj.setClase(res.getString("itemPecho"));
				pj.setClase(res.getString("itemManoDer"));
				pj.setClase(res.getString("itemManoIzq"));
				pj.setClase(res.getString("nivel"));
				return pj;
			}
		}
		return null;
	}

}
