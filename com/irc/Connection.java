package com.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Connection {
	
	public Socket socket;
	public BufferedWriter writer;
	public BufferedReader reader;
	String username = "unnamedUser";
	
	public String getUsername() { return this.username; }
	public void setUsername(String username) { this.username = username; }
	
	public Connection(Socket socket) {
		this.socket = socket;
		this.setIO();
	}

	public Connection(String address, int port) {
		// TODO Auto-generated constructor stub
		try {
			this.socket = new Socket(address,port);
			setIO();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setIO() {
		try {
			this.writer = new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream()));
			this.reader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
