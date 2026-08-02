package com.irc;

import java.io.IOException;
import java.net.ServerSocket;
import java.security.cert.CertificateException;
import java.util.ArrayList;

public class IRCServer {

	public IRCServer() {
		// TODO Auto-generated constructor stub
	}
	
	static ArrayList<String> usersList = new ArrayList<>();
	static String protocol = "bashrocks-socket2socket";
	static String version = "0.1";
	static Integer nextID = 1001;
	
	// success/failure codes
	static String successCode = "SUCCESS";
	static String errResourceInUse = "ERR_IN_USE";
	
	public static void startServer(int port) {
		
		Thread server = new Thread() {
			@Override
			public void run() {
				ServerSocket server;
				try {
					server = new ServerSocket(port);
					System.out.println("Sockets initialized."
							+ "\nListening on port " + port);
					/* Do/while loop watches for incoming connections and 
					 * creates a new thread for each connection request.
					 */
					do {
						Connection client = new Connection(server.accept());
						sendReceive(client); // starts the thread
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
				System.out.println("Connected to new client on port " + client.socket.getLocalPort());
				/*
				try {
					validateProtocol(client);
				} catch (CertificateException e) {
					e.printStackTrace();
					System.exit(0);
				}
				*/
				validateUsername(client);
				try {
					System.out.println(client.getUsername() + " can now send messages.");
					String messageIn = null;
					while ((messageIn = client.read()) != null) {
						System.out.println(messageIn);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		clientThread.start();
		// TODO how to close the thread when connection lost?
	}

	/**
	 * This asks the client for protocol and version information to verify
	 * server and client are running compatible software.
	 * @param client - Connection to check
	 * @throws CertificateException if protocol or version mismatch is found
	 */
	public static void validateProtocol(Connection client) throws CertificateException {
		try {
			System.out.println("Validating protocols.");
			client.write(protocol);
			System.out.println("Protocol information sent to client.");
			String clientProtocol = client.read();
			System.out.println("Protocol information received from client.");
			if(clientProtocol.contentEquals(protocol)) {
				System.out.println("Client and server protocol matches.");
			} else { 
				throw new CertificateException("Client/server protocol mismatch"); 
				}
			System.out.println("Validating versions.");
			client.write(version);
			System.out.println("Version information sent to client.");
			String clientVersion = client.read();
			if(clientVersion.contentEquals(version)) {
				System.out.println("Client and server version matches.");
			} else { 
				throw new CertificateException("Client/server version mismatch"); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setID(Connection client) {
		try {
			String userID = nextID.toString();
			client.write(userID);
			client.setUsername(userID);
			System.out.println("Client username set to " + client.getUsername());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void validateUsername(Connection client) {
		// TODO testing validation
		usersList.add("bashrocks");
		usersList.add("unnamedUser");
		try {
			System.out.println("Asking for username...");
			String username = client.read();
			System.out.println("Username received.");
			while(usersList.contains(username)) {
				System.out.println("Username taken. Asking again...");
				client.write(errResourceInUse);
				username = client.read();
			}
			if(username==null) {
				throw new NullPointerException("Username is null");
			}
			client.setUsername(username);
			client.write(successCode);
			System.out.println("Client username set to " + client.getUsername());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		startServer(60010);
	}

}
