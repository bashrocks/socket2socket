package com.irc;

import java.net.ServerSocket;
import java.net.Socket;

public class IRCServer {
	
	static ServerSocket listener;
	static Socket connection;

	public IRCServer() {
		// TODO Auto-generated constructor stub
	}
	
	public static void listen(int port) {
		try {
			listener = new ServerSocket(port);
			System.out.println("Listening on port " + port);
			connection = listener.accept();
			System.out.println("Connected with " + connection.getInetAddress());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		listen(0);

	}

}
