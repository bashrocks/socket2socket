package com.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class IRCServer {

	public IRCServer() {
		// TODO Auto-generated constructor stub
	}
	
	public static void startServer() {
		
		Thread server = new Thread() {
			@Override
			public void run() {
				ServerSocket server;
				try {
					server = new ServerSocket(60010);

					Socket socket = server.accept();

					BufferedReader in = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
					String messageIn = null;
					while ((messageIn = in.readLine()) != null) {
						System.out.println(messageIn);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		server.start();
	}

	public static void main(String[] args) {
		startServer();
	}

}
