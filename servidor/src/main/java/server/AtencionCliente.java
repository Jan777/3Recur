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
		try {
			String comando;

			while (!"desconectar".equals(comando = entrada.readUTF())) {

				switch (comando) {
					case "registrar":
						user = gson.fromJson(entrada.readUTF(), Usuario.class);
						marvel.crearUsuario(user);
					break;
					
					case "loguearse":
						user = gson.fromJson(entrada.readUTF(), Usuario.class);
						marvel.loguearUsuario(user);
					break;
				}
			}

		} catch (IOException e) {
			close();
		}
	}

	public void close() {
		try {
			entrada.close();
			salida.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
