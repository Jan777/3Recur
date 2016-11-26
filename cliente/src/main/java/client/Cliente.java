package client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import com.google.gson.Gson;
import character.Asgardiano;
import character.Hulk;
import character.Mutante;
import character.PC;
import principal.Principal;
import user.Usuario;

public class Cliente {

	private Usuario user;
	private Socket socket;
	private PC pj;
	private int PUERTO = 50000;
	private String HOST = "localhost";
	private DataInputStream entrada;
	private DataOutputStream salida;
	private Gson gson;
	private Principal mapa;
	
	public Cliente(String nombre, String contraseña) throws UnknownHostException, IOException {
		Scanner scan = null;
		scan = new Scanner(new File("clienteConfig.config"));
		HOST = scan.nextLine();
		PUERTO = scan.nextInt();
		socket = new Socket(HOST, PUERTO);
		user = new Usuario(nombre, contraseña);
		entrada = new DataInputStream(socket.getInputStream());
		salida = new DataOutputStream(socket.getOutputStream());
		gson = new Gson();
		scan.close();
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

	public void crearPersonaje(PC pj) {
		try {
			salida.writeUTF("newPersonaje");
			salida.writeUTF(gson.toJson(user));
			salida.writeUTF(gson.toJson(pj.getRaza()));
			salida.writeUTF(gson.toJson(pj));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean conectarPersonaje(PC personaje) {
		try {
			
			this.pj = personaje;
			salida.writeUTF("conectarPersonaje");
			salida.writeUTF(gson.toJson(user));
			mapa = new Principal(socket, pj);
			mapa.start();
			
			if(entrada.readUTF().equals("OK"))
				return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
