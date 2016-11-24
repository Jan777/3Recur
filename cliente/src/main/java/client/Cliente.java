package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import character.Asgardiano;
import character.Hulk;
import character.Mutante;
import character.PC;
import user.Usuario;

public class Cliente {

	private Usuario user;
	private Socket socket;
	private PC pj;
	private final int PUERTO = 50000;
	private final String HOST = "localhost";
	private DataInputStream entrada;
	private DataOutputStream salida;
	private Gson gson;
	
	public Cliente(String nombre, String contraseņa) {
		try {
			socket = new Socket(HOST, PUERTO);
			user = new Usuario(nombre, contraseņa);
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
			
			if("OK".equals(entrada.readUTF())) {
				user.conectar();
				return true;
			}
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
			
			if("OK".equals(entrada.readUTF())) {
				user.desconectar();
				return true;
			}
			else
				return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean singIn() {
		try {
			entrada = new DataInputStream(socket.getInputStream());
			salida = new DataOutputStream(socket.getOutputStream());
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
	
	public PC getPersonaje() {
		try {
			salida.writeUTF("getPersonaje");
			salida.writeUTF(gson.toJson(user));
			
			if("OK".equals(entrada.readUTF())) {
				String raza = entrada.readUTF();
				
				switch (raza) {
				case "Asgardiano":
					pj = gson.fromJson(entrada.readUTF(), Asgardiano.class);
					break;
					
				case "Hulk":
					pj = gson.fromJson(entrada.readUTF(), Hulk.class);
					break;
					
				case "Mutante":
					pj = gson.fromJson(entrada.readUTF(), Mutante.class);
					break;
				}
				
				return pj;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public PC crearPersonaje(PC pj) {
		try {
			salida.writeUTF("newPersonaje");
			salida.writeUTF(gson.toJson(user));
			salida.writeUTF(gson.toJson(pj.getRaza()));
			salida.writeUTF(gson.toJson(pj));
			
			if("OK".equals(entrada.readUTF())) {
				String raza = entrada.readUTF();
				raza = raza.substring(1, raza.length() - 1);
				switch (raza) {
				case "Asgardiano":
					pj = gson.fromJson(entrada.readUTF(), Asgardiano.class);
				break;
				
				case "Mutante":
					pj = gson.fromJson(entrada.readUTF(), Mutante.class);
				break;
				
				case "Hulk":
					pj = gson.fromJson(entrada.readUTF(), Hulk.class);
				break;
				}
				
				return pj;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
