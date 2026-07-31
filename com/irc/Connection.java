package com.irc;

import java.net.Socket;

public class Connection implements Runnable {
	Socket socket;

	public Connection(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	@Override
	public void run() {
		System.out.println("Connected with " + socket.getInetAddress());
		// TODO Auto-generated method stub

	}

}
