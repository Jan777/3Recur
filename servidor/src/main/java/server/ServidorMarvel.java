package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import dataBase.MarvelDB;

public class ServidorMarvel extends Thread {

	private MarvelDB marvel;
	private AtencionCliente atCliente;
	private ServerSocket server;
	Socket socket = null;
	private volatile boolean enFuncionamiento = false;
	private final int PUERTO = 50000;

	public ServidorMarvel() {
		try {
			marvel = new MarvelDB();
			server = new ServerSocket(PUERTO);
		} catch (IOException e) {
			try {
				if (server != null)
					server.close();

				if (marvel != null)
					marvel.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
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

				atCliente = new AtencionCliente(socket, marvel);
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
