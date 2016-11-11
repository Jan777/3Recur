package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import dataBase.MarvelDB;

public class ServidorMarvel {

	private MarvelDB marvel;
	private AtencionCliente atCliente;
	private ServerSocket server;
	Socket socket = null;
	private boolean enFuncionamiento = false;
	private final int PUERTO = 50000;
	
	public ServidorMarvel() {
		try {
			marvel = new MarvelDB();
			server = new ServerSocket(PUERTO);
		} catch (IOException e) {
			try {
				if(server != null)
					server.close();
				
				if(marvel != null)
					marvel.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void runServer() {
		enFuncionamiento = true;
		
		
		while(enFuncionamiento) {
			try {
				socket = server.accept();
				
				atCliente = new AtencionCliente(socket, marvel);
				atCliente.start();
				
			} catch (IOException e) {
				try {
					closeServer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	}
	
	public void closeServer() throws IOException {
		enFuncionamiento = false;
		
		if(marvel != null)
			marvel.close();
		
		if(socket != null)
			socket.close();
		
		if(server != null)
			server.close();
	}
}
