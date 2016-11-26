package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import character.PC;
import dataBase.MarvelDB;

public class ServidorMarvel extends Thread {

	private MarvelDB marvel;
	private AtencionCliente atCliente;
	private ServerSocket server;
	private Socket socket = null;
	private volatile boolean enFuncionamiento = false;
	private int PUERTO;
	private ArrayList<PC> lista;

	public ServidorMarvel() throws IOException {
		Scanner scan = new Scanner(new File("serverConfig.config"));
		PUERTO = scan.nextInt();
		marvel = new MarvelDB();
		server = new ServerSocket(PUERTO);
		lista = new ArrayList<PC>();
		scan.close();
	}

	@Override
	public void run() {
		runServer();
		closeServer();
	}

	private void runServer() {
		enFuncionamiento = true;

		try {
			while (enFuncionamiento) {
				socket = server.accept();

				atCliente = new AtencionCliente(socket, marvel, lista);
				atCliente.start();
			}
		} catch (IOException e) {
			try {
				server.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void closeServer() {
		enFuncionamiento = false;

		if (marvel != null)
			marvel.close();

		try {
			if (server != null)
				server.close();

			if (socket != null)
				socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
