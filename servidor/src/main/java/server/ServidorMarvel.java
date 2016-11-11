package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import dataBase.MarvelDB;

public class ServidorMarvel {

	private MarvelDB marvel;
	private AtencionCliente atCliente;
	private ServerSocket server;
	private boolean enFuncionamiento = false;
	
	public ServidorMarvel(int puerto) {
		try {
			marvel = new MarvelDB();
			server = new ServerSocket(puerto);
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
				Socket socket = server.accept();
				
				atCliente = new AtencionCliente(socket, marvel);
				atCliente.start();
			} catch (IOException e) {
				enFuncionamiento = false;
				e.printStackTrace();
			}
		}
	}
}
