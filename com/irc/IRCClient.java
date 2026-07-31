package com.irc;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class IRCClient {
	
	// static String serverAddress;
	static Socket connection;

	public IRCClient() {
		// TODO Auto-generated constructor stub
	}
	
	public static void connect(String address,int port) {
		try {
			connection = new Socket(address,port);
			System.out.println("Connected to server " + address);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		connect("localhost",1970);
	}

}
