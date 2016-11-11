package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.google.gson.Gson;

import character.Personaje;
import user.Usuario;

public class Cliente {

	private Usuario user;
	private Socket socket;
	private Personaje pj;
	private final int PUERTO = 50000;
	private final String HOST = "localhost";
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Gson gson;
	
	public Cliente(String nombre, String contraseña) {
		try {
			socket = new Socket(HOST, PUERTO);
			user = new Usuario(nombre, contraseña);
			entrada = new ObjectInputStream(socket.getInputStream());
			salida = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
