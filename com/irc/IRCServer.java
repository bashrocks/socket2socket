package com.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.cert.CertificateException;
import java.util.ArrayList;

public class IRCServer {

	public IRCServer() {
		// TODO Auto-generated constructor stub
	}
	
	static ArrayList<String> usersList = new ArrayList<>();
	static String protocol = "bashrocks-socket2socket";
	static String version = "0.1";
	
	public static void startServer(int port) {
		
		Thread server = new Thread() {
			@Override
			public void run() {
				ServerSocket server;
				try {
					server = new ServerSocket(port);
					do {
						Connection client = new Connection(server.accept());
						try {
							validateProtocol(client);
						} catch (CertificateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.exit(0);
						}
						// validateUsername(client);
						sendReceive(client);
					} while(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		server.start();
	}
	
	public static void sendReceive(Connection client) {
		Thread clientThread = new Thread() {
			@Override
			public void run() {
				try {
					String messageIn = null;
					String messageOut = null;
					while ((messageIn = client.reader.readLine()) != null) {
						System.out.println(messageIn);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		clientThread.start();
	}

	// check we're on the same protocol and program version
	public static void validateProtocol(Connection client) throws CertificateException {
		try {
			client.writer.write(protocol);
			if(client.reader.readLine().contentEquals(protocol)) {
				client.writer.write("Client and server protocol matches.");
			} else { 
				throw new CertificateException("Client/server protocol mismatch"); 
				}
			client.writer.write(version);
			if(client.reader.readLine().contentEquals(version)) {
				client.writer.write("Client and server version matches.");
			} else { 
				throw new CertificateException("Client/server version mismatch"); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void validateUsername(Connection client) {
		try {
			String username = client.reader.readLine();
			while(usersList.contains(username)) {
				client.writer.write("Username " + username + " is already in "
						+ "use. Try again.");
				username = client.reader.readLine();
			}
			client.setUsername(username);
			client.writer.write("Username set to " + client.getUsername());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		startServer(60010);
	}

}
