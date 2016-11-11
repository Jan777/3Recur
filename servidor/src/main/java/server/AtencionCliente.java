package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.google.gson.Gson;

import dataBase.MarvelDB;
import user.Usuario;

public class AtencionCliente extends Thread {

	private Socket socket;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private MarvelDB marvel;
	private Usuario user;
	private Gson gson;

	public AtencionCliente(Socket socket, MarvelDB marvel) {
		this.socket = socket;
		this.marvel = marvel;
		gson = new Gson();

		try {
			this.salida = new ObjectOutputStream(socket.getOutputStream());
			this.entrada = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			close();
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String comando;
		boolean res = false;
		try {
			while (!"logOut".equals(comando = entrada.readUTF())) {

				switch (comando) {
					case "singIn":
						user = gson.fromJson(entrada.readUTF(), Usuario.class);
						res = marvel.crearUsuario(user);		
						responder(res);
					break;
					
					case "logIn":
						user = gson.fromJson(entrada.readUTF(), Usuario.class);
						res = marvel.loguearUsuario(user);
						responder(res);
					break;
				}
			}
			
			user = gson.fromJson(entrada.readUTF(), Usuario.class);
			res = marvel.desconectarUsuario(user);
			responder(res);

		} catch (IOException e) {
			close();
		}
	}

	private void responder(boolean res) throws IOException {
		if(res)
			salida.writeUTF("OK");
		
		salida.writeUTF("KO");
	}
	
	public void close() {
		try {
			entrada.close();
			salida.close();
			socket.close();
			marvel.desconectarUsuario(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
