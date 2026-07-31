package com.irc;

import java.io.IOException;
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
			System.out.println("Listening on port " + listener.getLocalPort());
			connect();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void connect() {
		try {
			connection = listener.accept();
			listener.close();
			System.out.println("Connected with " + connection.getInetAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		listen(1970);
		listen(1970);

	}

}
