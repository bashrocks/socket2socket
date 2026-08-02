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
				try {
					System.out.println("Connected to new client on port " + client.socket.getLocalPort());
					validateProtocol(client);
					// validateUsername(client);
				} catch (CertificateException e) {
					e.printStackTrace();
					System.exit(0);
				}
				try {
					String messageIn = null;
					String messageOut = null;
					while ((messageIn = client.reader.readLine()) != null) {
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
			client.writer.write(protocol);
			System.out.println("Protocol information sent to client.");
			String clientProtocol = client.reader.readLine();
			System.out.println("Protocol information received from client.");
			if(clientProtocol.contentEquals(protocol)) {
				client.writer.write("Client and server protocol matches.");
			} else { 
				throw new CertificateException("Client/server protocol mismatch"); 
				}
			System.out.println("Validating versions.");
			client.writer.write(version);
			System.out.println("Version information sent to client.");
			String clientVersion = client.reader.readLine();
			if(clientVersion.contentEquals(version)) {
				client.writer.write("Client and server version matches.");
			} else { 
				throw new CertificateException("Client/server version mismatch"); 
			}
		} catch (IOException e) {
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
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		startServer(60010);
	}

}
