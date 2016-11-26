package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import com.google.gson.Gson;
import character.Asgardiano;
import character.Hulk;
import character.Mutante;
import character.PC;
import dataBase.MarvelDB;
import user.Usuario;

public class AtencionCliente extends Thread {

	private Socket socket;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private MarvelDB marvel;
	private Usuario user;
	private PC pj;
	private Gson gson;
	private ArrayList<PC> listaPj;

	public AtencionCliente(Socket socket, MarvelDB marvel, ArrayList<PC> lista) {
		this.socket = socket;
		this.marvel = marvel;
		this.listaPj = lista;
		gson = new Gson();

		try {
			this.salida = new DataOutputStream(socket.getOutputStream());
			this.entrada = new DataInputStream(socket.getInputStream());
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
			while (!"logOut".equals((comando = entrada.readUTF()))) {

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

				case "getPersonaje":
					user = gson.fromJson(entrada.readUTF(), Usuario.class);
					pj = marvel.buscarPersonaje(user);
					if (pj != null) {
						salida.writeUTF("OK");
						salida.writeUTF(pj.getRaza());
						salida.writeUTF(gson.toJson(pj));
					}

					else {
						salida.writeUTF("KO");
					}
					break;

				case "newPersonaje":
					user = gson.fromJson(entrada.readUTF(), Usuario.class);
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

					marvel.crearPersonaje(user, pj);
					
					break;

				case "conectarPersonaje":
					user = gson.fromJson(entrada.readUTF(), Usuario.class);
					listaPj.add(pj);
					salida.writeUTF("OK");
					break;

				case "moverPersonaje":
					int x = Integer.valueOf(entrada.readUTF());
					int y = Integer.valueOf(entrada.readUTF());
					int indice = listaPj.indexOf(pj);
					listaPj.get(indice).setX(x);
					listaPj.get(indice).setY(y);
					int i = 0;
					salida.writeUTF(gson.toJson(listaPj.size()));

					while (i < listaPj.size()) {
						salida.writeUTF(gson.toJson(listaPj.get(i).getRaza()));
						salida.writeUTF(gson.toJson(listaPj.get(i)));
						i++;
					}
					break;
					
				case "desconectarPersonaje":
					String nombre = entrada.readUTF();
					i = 0;
					
					while(i < listaPj.size()) {
						if(listaPj.get(i).getName().equals(nombre)) {
							listaPj.remove(i);
						}
						
						i++;
					}		
					this.join();
				break;
				}
				salida.flush();
			}

			user = gson.fromJson(entrada.readUTF(), Usuario.class);
			res = marvel.desconectarUsuario(user);
			responder(res);

		} catch (IOException | InterruptedException e) {
			close();
		}
	}

	private void responder(boolean res) throws IOException {
		if (res)
			salida.writeUTF("OK");
		else
			salida.writeUTF("KO");
	}

	public void close() {
		try {
			entrada.close();
			salida.close();
			socket.close();
			marvel.desconectarUsuario(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
