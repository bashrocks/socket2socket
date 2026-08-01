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
	
	public static void startSender() {
		Thread sender = new Thread() {
			@Override
			public void run() {
				try {
					Socket socket = new Socket("localhost", 60010);
					BufferedWriter toServer = new BufferedWriter(
							new OutputStreamWriter(socket.getOutputStream()));

					while (true) {
						sendMessage(toServer);
					}

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		};
		sender.start();
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
