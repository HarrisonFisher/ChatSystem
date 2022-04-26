import Client.*;
import Shared.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class ClientDriver {
	
	public static boolean SocketNotClosed = true;
	
	private static int port = 7777;
	private static InetAddress serverIP;
	private static Socket socket;
	
	
	private static OutputStream outputStream;
	private static ObjectOutputStream objectOutputStream;
	private static InputStream inputStream;
	private static ObjectInputStream objectInputStream;
	
	
	public static Boolean Login(String Username, String Password) throws ClassNotFoundException {
		
				
		try {
			objectOutputStream.writeObject(new Message("login", Username+'\n'+Password, "","","",""));
			Message NewMessage = (Message) objectInputStream.readObject();
			PrintMessage(NewMessage);
			
			if (NewMessage.getStatus().equals(new String("success"))) {
				return true;	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return false;
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		int port = 7777;
		InetAddress serverIP;
		try {
			serverIP = InetAddress.getByName("127.0.0.1");
			socket = new Socket(serverIP, port);
			outputStream = socket.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			inputStream = socket.getInputStream();
			objectInputStream = new ObjectInputStream(inputStream);
			
			LoginWindow loginWindow = new LoginWindow(socket, objectOutputStream, objectInputStream);
			loginWindow.processCommands();
			
			ChatWindow chatWindow = new ChatWindow(socket, objectOutputStream, objectInputStream);
			chatWindow.processCommands();
			


		} catch (UnknownHostException e) {

			System.out.println("Could not get ip address");
			return;
		} catch (IOException e) {
			System.out.println("Could not create socket");
			return;
		} 

		
		
		//
//		// creating client socket
//		int port = 7777;
//		InetAddress serverIP;
//		Socket socket;
//		try {
//			serverIP = InetAddress.getByName("127.0.0.1");
//			socket = new Socket(serverIP, port);
//
//		} catch (UnknownHostException e) {
//
//			System.out.println("Could not get ip address");
//			return;
//		} catch (IOException e) {
//
//			System.out.println("Could not create socket");
//			return;
//		}
//
//		System.out.println("Test 1");
//		// creating object streams
//		OutputStream outputStream;
//		ObjectOutputStream objectOutput;
//		InputStream inputStream;
//		ObjectInputStream objectInput;
//
//		System.out.println("Test 2");
//		try {
//			System.out.println("Test 3");
//			outputStream = socket.getOutputStream();
//			System.out.println("Test 4");
//
//			objectOutput = new ObjectOutputStream(outputStream);
//			System.out.println("Test 5");
//
//			inputStream = socket.getInputStream();
//			System.out.println("Test 6");
//
//			objectInput = new ObjectInputStream(inputStream);
//			System.out.println("Test 7");
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.out.println("could not create ObjectOutputStream");
//
//			return;
//		}
//		
//		System.out.println("Test 3");
//		
//		LoginWindow loginWindow = new LoginWindow(socket, objectOutput, objectInput);
//		loginWindow.processCommands();
//		
//		System.out.println("Test 4");
//
//		// closing client socket
//		try {
//			socket.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	}
	

	
	
	private static void PrintMessage(Message msg) {
		System.out.println("Type: " + msg.getType());
		System.out.println("Status: " + msg.getStatus());
		System.out.println("Data: " + msg.getData());
		System.out.println("-----------------------------------------------------------");
	}
}


