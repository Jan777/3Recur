package test;

import org.junit.Assert;
import org.junit.Test;

import dataBase.MarvelDB;
import user.Usuario;

public class testDataBase {

	private MarvelDB marvel;
	
	@Test
	public void insertDBTest() {
		marvel = new MarvelDB();
		marvel.connectDB();
		String nombre = "juan";
		String contraseña = "contraseña1";
		Usuario user = new Usuario();
		user.setNombre(nombre);
		user.setContraseña(contraseña);
		Assert.assertTrue(marvel.crearUsuario(user));
	}
}
