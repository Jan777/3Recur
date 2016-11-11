package client;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import user.Usuario;

public class Cliente {

	private Usuario user;
	private Socket socket;
	//private Personaje pj;
	private final int PUERTO = 50000;
	private final String HOST = "localhost";
	private DataInputStream entrada;
	private DataOutput salida;
	private Gson gson;
	
	public Cliente(String nombre, String contraseña) {
		try {
			socket = new Socket(HOST, PUERTO);
			user = new Usuario(nombre, contraseña);
			entrada = new DataInputStream(socket.getInputStream());
			salida = new DataOutputStream(socket.getOutputStream());
			gson = new Gson();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean logIn() {
		try {
			salida.writeUTF("logIn");
			salida.writeUTF(gson.toJson(user));
			
			if("OK".equals(entrada.readUTF()))
				return true;
			else
				return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean logOut() {
		try {
			salida.writeUTF("logOut");
			salida.writeUTF(gson.toJson(user));
			
			if("OK".equals(entrada.readUTF()))
				return true;
			else
				return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean singIn() {
		try {
			salida.writeUTF("singIn");
			salida.writeUTF(gson.toJson(user));
			
			if("OK".equals(entrada.readUTF()))
				return true;
			else
				return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
