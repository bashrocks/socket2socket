package com.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
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
	}

	// check we're on the same protocol and program version
	public static void validateProtocol(Connection server) throws CertificateException {
		try {
			if(server.reader.readLine().contentEquals(protocol)) {
				System.out.println("Client and server protocol matches.");
				server.writer.write(protocol);
			} else { 
				throw new CertificateException("Client/server protocol mismatch"); 
				}
			if(server.reader.readLine().contentEquals(version)) {
				System.out.println("Client and server version matches.");
				server.writer.write(version);
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendMessage();
	}

}
