package com.irc;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Scanner;
	
public class IRCClient {

	public IRCClient() {
		// TODO Auto-generated constructor stub
	}
	
	static Scanner userInput = new Scanner(System.in);
	static String username;
	static Connection server;
	static String protocol = "bashrocks-socket2socket";
	static String version = "0.1";
	
	// new, needs testing
	// TODO change this for Connection object?
	public static void init(String addr, int port) {
		server = new Connection(addr,port);
		System.out.println("Connected to server on port " + port);
	}

	// check we're on the same protocol and program version
	public static void validateProtocol(Connection server) throws CertificateException {
		try {
			System.out.println("Validating protocols.");
			server.writer.write(protocol);
			System.out.println("Protocol information sent to server.");
			String serverProtocol = server.reader.readLine();
			System.out.println("Protocol information received from server.");
			if(serverProtocol.contentEquals(protocol)) {
				System.out.println("Client and server protocol matches.");
			} else { 
				throw new CertificateException("Client/server protocol mismatch"); 
				}
			System.out.println("Validating versions.");
			server.writer.write(version);
			System.out.println("Version information sent to server.");
			String serverVersion = server.reader.readLine();
			if(serverVersion.contentEquals(version)) {
				System.out.println("Client and server version matches.");
			} else { 
				throw new CertificateException("Client/server version mismatch"); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void askForUsername() {
		System.out.println("Please enter a username.");
		username = userInput.nextLine();
		try {
			server.writer.write(username);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendMessage() {
		String message;
		try {
			while(true) {
				message = userInput.nextLine();
				server.writer.write(message);
				server.writer.newLine();
				server.writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// QuitProgram quitChecker = new QuitProgram();
		// Thread quitThread = new Thread(quitChecker);
		// quitThread.start();
		init("localhost", 60010);
		
		try {
			validateProtocol(server);
		} catch (CertificateException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		sendMessage();
	}

}
