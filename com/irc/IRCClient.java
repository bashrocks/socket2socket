package com.irc;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Scanner;
	
public class IRCClient {
	
	static Scanner userInput = new Scanner(System.in);
	static Connection server;
	static String protocol = "bashrocks-socket2socket";
	static String version = "0.1";
	
	// success/failure codes
	static String successCode = "SUCCESS";
	static String errResourceInUse = "ERR_IN_USE";
	
	// new, needs testing
	// TODO change this for Connection object?
	public static void init(String addr, String arg2) {
		int port = Integer.parseInt(arg2);
		server = new Connection(addr,port);
		System.out.println("Connected to server on port " + port);
	}
	
	public static void init(String arg) {
		int port = Integer.parseInt(arg);
		server = new Connection(port);
		System.out.println("Connected to localhost on port " + port);
	}

	// check we're on the same protocol and program version
	public static void validateProtocol(Connection server) throws CertificateException {
		try {
			System.out.println("Validating protocols.");
			server.write(protocol);
			System.out.println("Protocol information sent to server.");
			String serverProtocol = server.read();
			System.out.println("Protocol information received from server.");
			if(serverProtocol.equals(protocol)) {
				System.out.println("Client and server protocol matches.");
			} else { 
				throw new CertificateException("Client/server protocol mismatch"); 
				}
			System.out.println("Validating versions.");
			server.write(version);
			System.out.println("Version information sent to server.");
			String serverVersion = server.read();
			if(serverVersion.equals(version)) {
				System.out.println("Client and server version matches.");
			} else { 
				throw new CertificateException("Client/server version mismatch"); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void validateUsername() {
		try {
			System.out.println("Please enter a username.");
			String username = userInput.nextLine();
			server.write(username);
			System.out.println("Sending username to server...");
			String serverResponse = server.read();
			while(serverResponse.equals(errResourceInUse)) {
				System.out.println("That username is taken. Try again");
				username = userInput.nextLine();
				server.write(username);
				serverResponse = server.read();
			}
			server.setUsername(username);
			System.out.println("Username set to " + server.getUsername());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendReceive() {
		System.out.println("You can now send and receive messages.");
		Thread outgoing = new Thread() {
			@Override
			public void run() {
				sendMessage();
			}
		};
		Thread incoming = new Thread() {
			@Override
			public void run() {
				receiveMessage();
			}		
		};
		outgoing.start();
		incoming.start();
	}
	
	public static void sendMessage() {
		String message;
		try {
			while(true) {
				message = server.getUsername() + ": " + userInput.nextLine();
				server.write(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void receiveMessage() {
		String message;
		try {
			while((message = server.read()) != null) {
				System.out.println(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// QuitProgram quitChecker = new QuitProgram();
		// Thread quitThread = new Thread(quitChecker);
		// quitThread.start();
		if(args.length == 2) { init(args[0],args[1]); }
		else init(args[0]);
		
		try {
			validateProtocol(server);
		} catch (CertificateException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		validateUsername();
		sendReceive();
	}

}
