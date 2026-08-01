package com.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
	
public class IRCClient {

	public IRCClient() {
		// TODO Auto-generated constructor stub
	}
	
	static Scanner userInput = new Scanner(System.in);
	static Socket socket;
	static String username;
	static BufferedWriter toServer;
	
	// new, needs testing
	public static void init() {
		try {
			socket = new Socket("localhost", 60010);
			toServer = new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setUsername() {
		System.out.println("Please enter a username.");
		
	}
	
	// not sure this needs to be threaded? but i should git commit before i mess with it
	public static void startSender() {

					while (true) {
						sendMessage(toServer);
					}
			
		
	}
	
	public static void sendMessage(BufferedWriter toServer) {
		String message = userInput.nextLine();
		try {
			toServer.write(message);
			toServer.newLine();
			toServer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// QuitProgram quitChecker = new QuitProgram();
		// Thread quitThread = new Thread(quitChecker);
		// quitThread.start();
		startSender();
	}

}
