package server;

import java.net.Socket;

import dataBase.MarvelDB;

public class AtencionCliente extends Thread{

	private Socket socket;
	private MarvelDB marvel;
	
	public AtencionCliente(Socket socket, MarvelDB marvel) {
		this.socket = socket;
		this.marvel = marvel;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
}
