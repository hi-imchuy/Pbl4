package com.demo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

	private Socket socket;
	private String serverAddress;
	private int serverPort;

	public Client(String serverAddress, int serverPort) {
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
		connectToServer();
		sendMessage("Hello from Client");
	}

	private void connectToServer() {
		try {
			socket = new Socket(serverAddress, serverPort);
			System.out.println("Connected to Python Server at " + serverAddress + ":" + serverPort);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String message) {
		try {
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write(message.getBytes());
			outputStream.flush(); // Đảm bảo dữ liệu được gửi đi ngay lập tức

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String response = reader.readLine();
			System.out.println("Response from Python Server: " + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String serverAddress = "localhost";
		int serverPort = 8888;

		Client client = new Client(serverAddress, serverPort);
		

		// Do some other work...

		// Close the connection when you're done
		client.close();
	}
}
