package com.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
	
public class IRCClient {

	public IRCClient() {
		// TODO Auto-generated constructor stub
	}
	
	public static void startSender(String message) {
		Thread sender = new Thread() {
			@Override
			public void run() {
				try {
					Socket socket = new Socket("localhost", 60010);
					BufferedWriter toServer = new BufferedWriter(
							new OutputStreamWriter(socket.getOutputStream()));

					while (true) {
						toServer.write(message);
						toServer.newLine();
						toServer.flush();
					 
						Thread.sleep(1000);
					}

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		sender.start();
	}

	public static void main(String[] args) {
		// QuitProgram quitChecker = new QuitProgram();
		// Thread quitThread = new Thread(quitChecker);
		// quitThread.start();
		String message = "Hello World!";
		if(args.length>0) { message = args[0]; }
		startSender(message);
	}

}
