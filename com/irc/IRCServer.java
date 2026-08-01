package com.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class IRCServer {

	public IRCServer() {
		// TODO Auto-generated constructor stub
	}
	
	public static void startServer(int port) {
		
		Thread server = new Thread() {
			@Override
			public void run() {
				ServerSocket server;
				try {
					server = new ServerSocket(port);
					do {
						Connection client = new Connection(server.accept());
						sendReceive(client.socket);
					} while(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		server.start();
	}
	
	public static void sendReceive(Socket socket) {
		Thread client = new Thread() {
			@Override
			public void run() {
				BufferedReader fromClient;
				BufferedWriter toClient;
				try {
					toClient = new BufferedWriter(
							new OutputStreamWriter(socket.getOutputStream()));
					fromClient = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
					String messageIn = null;
					String messageOut = null;
					while ((messageIn = fromClient.readLine()) != null) {
						System.out.println(messageIn);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		client.start();
	}
	
	public static void handshake(Connection client) {
		// check we're on the same protocol
		checkUsername(client);
	}
	
	public static void checkUsername(Connection client) {
		
	}

	public static void main(String[] args) {
		startServer(60010);
	}

}
