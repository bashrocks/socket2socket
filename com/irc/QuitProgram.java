package com.irc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuitProgram implements Runnable {

	@Override
	public void run() {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";

		try {
			while (line.equalsIgnoreCase("--quit") == false) {
				System.out.println("Type --quit to exit the program.");
				line = in.readLine();
			}

			in.close();
			System.exit(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
