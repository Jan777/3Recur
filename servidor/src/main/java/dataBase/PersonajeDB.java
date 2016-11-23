package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import character.Asgardiano;
import character.Clase;
import character.Hulk;
import character.Mutante;
import character.PC;
import character.Personaje;
import user.Usuario;

public class PersonajeDB {

	private Connection conexion;
	private final String insertPJSQL = "insert into personajes (nombre, nombrePersonaje) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private PreparedStatement insertPreparedStatement;
	private final String selectPJNombreSQL = "select * from personajes where nombre = ?;";
	private PreparedStatement selectPreparedStatement;
	//private final String connectSQL = "update personajes set personaje = ? where nombre = ?;";
	//private PreparedStatement updatePreparedStatement;
	private PC pj;

	public PersonajeDB(Connection conexion) {
		this.conexion = conexion;
	}

	public boolean create(Usuario user, PC pj) throws Exception {
		insertPreparedStatement = conexion.prepareStatement(insertPJSQL);

		if (search(user) != null) {
			return false;
		}
		// GETTERS PARA INSERTAR EN BD
		insertPreparedStatement.setString(1, user.getNombre());
		insertPreparedStatement.setString(2, pj.getName());
		insertPreparedStatement.setString(3, pj.getRaza());
		insertPreparedStatement.setString(4, pj.getClase());
		insertPreparedStatement.setInt(5, pj.getExp());
		insertPreparedStatement.setString(6, pj.mostrarItemCabeza());
		insertPreparedStatement.setString(7, pj.mostrarItemPecho());
		insertPreparedStatement.setString(8, pj.mostrarItemManoDer());
		insertPreparedStatement.setString(9, pj.mostrarItemManoIzq());
		insertPreparedStatement.setInt(10, pj.getPuntosDisponibles());
		insertPreparedStatement.executeUpdate();
		return true;
	}

	public void close() throws Exception {
		insertPreparedStatement.close();
		selectPreparedStatement.close();
	}

	public Personaje search(Usuario user) throws Exception {

		selectPreparedStatement = conexion.prepareStatement(selectPJNombreSQL);
		selectPreparedStatement.setString(1, user.getNombre());

		ResultSet res = selectPreparedStatement.executeQuery();

		if (res.next()) {
			String nombrePersonaje = res.getString("nombrePersonaje");
			String raza = res.getString("raza");
			String clase = res.getString("clase");
			int experiencia = res.getInt("experiencia");

			switch (raza) {
			case "Asgardiano":
				switch (clase) {
				case "TANQUE":
					pj = new Asgardiano(nombrePersonaje, Clase.TANQUE);
					break;

				case "HECHIZERO":
					pj = new Asgardiano(nombrePersonaje, Clase.HECHIZERO);
					break;

				case "AGENTE":
					pj = new Asgardiano(nombrePersonaje, Clase.AGENTE);
					break;
				}
				break;
				
			case "Hulk":
				switch (clase) {
				case "TANQUE":
					pj = new Hulk(nombrePersonaje, Clase.TANQUE);
					break;

				case "HECHIZERO":
					pj = new Hulk(nombrePersonaje, Clase.HECHIZERO);
					break;

				case "AGENTE":
					pj = new Hulk(nombrePersonaje, Clase.AGENTE);
					break;
				}
				break;
				
			case "Mutante":
				switch (clase) {
				case "TANQUE":
					pj = new Mutante(nombrePersonaje, Clase.TANQUE);
					break;

				case "HECHIZERO":
					pj = new Mutante(nombrePersonaje, Clase.HECHIZERO);
					break;

				case "AGENTE":
					pj = new Mutante(nombrePersonaje, Clase.AGENTE);
					break;
				}
				break;
			}
			pj.ganarExp(experiencia);

			return pj;
		}

		return null;
	}

}
