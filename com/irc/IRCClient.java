package com.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class IRCClient {
	
	// static String serverAddress;
	static Socket link;
	static BufferedReader in;
	static BufferedWriter out;
	

	public IRCClient() {
		// TODO Auto-generated constructor stub
	}
	
	public static void connect(String address,int port) {
		try {
			link = new Socket(address,port);
			System.out.println("Connected to server " + address);
			out = new BufferedWriter(new OutputStreamWriter(link.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sendMessage(String msg) {
		try {
			while (true) {
				out.write(msg);
	            out.newLine();
	            out.flush();

	            Thread.sleep(200);
	        }
	        } catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void main(String[] args) {
		// QuitProgram quitChecker = new QuitProgram();
		// Thread quitThread = new Thread(quitChecker);
		// quitThread.start();
		connect("localhost",1970);
		sendMessage("Hello from " + link.getLocalSocketAddress());
        
	}

}
