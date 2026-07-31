package com.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class IRCClient {
	
	// static String serverAddress;
	static Socket connection;
	static BufferedReader in;
	static BufferedWriter out;

	public IRCClient() {
		// TODO Auto-generated constructor stub
	}
	
	public static void connect(String address,int port) {
		try {
			connection = new Socket(address,port);
			System.out.println("Connected to server " + address);
			out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// QuitProgram quitChecker = new QuitProgram();
		// Thread quitThread = new Thread(quitChecker);
		// quitThread.start();
		connect("localhost",1970);

        try {
		while (true) {
			out.write("Hello World!");
            out.newLine();
            out.flush();

            Thread.sleep(200);
        }
        } catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
