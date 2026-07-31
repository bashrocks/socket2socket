package com.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class IRCServer {
	
	static ServerSocket listener;
	static Socket connection;
	static BufferedReader in;
	static BufferedWriter out;

	public IRCServer() {
		// TODO Auto-generated constructor stub
	}
	
	public static void listen(int port) {
		Thread listenThread = new Thread(new Listener(port));
		listenThread.start();
	}
	
	public static void connect() {
		Thread connectThread = new Thread() {
			@Override
			public void run() {
				
			}
		};
		connectThread.start();
	}
	
	public static void getMessage() {
		try {
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
			String line = null;
	        while ((line = in.readLine()) != null) {
	        	System.out.println(line);
	        }
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		QuitProgram quitChecker = new QuitProgram();
		Thread quitThread = new Thread(quitChecker);
		quitThread.start();
		
		
		
		listen(1970);
	}

}
