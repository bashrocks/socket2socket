package com.irc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener implements Runnable {

	ServerSocket listener;
	Socket connection;
	int port;
	
	public Listener(int port) { 
		// TODO Auto-generated constructor stub
		this.port = port;
		try {
			this.listener = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeAndOpen() {
		try {
			listener.close();
			this.listener = new ServerSocket(port);
			System.out.println("Listening on port " + listener.getLocalPort());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				System.out.println("Listening on port " + listener.getLocalPort());
				Thread connection = new Thread(new Connection(listener.accept()));
				connection.start();
				closeAndOpen();
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
