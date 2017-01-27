package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server
{
	private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();


	private static class Handler extends Thread{
		private Socket socket;
		public Handler(Socket socket){
			super();
			this.socket = socket;
		}

		private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
			String clientName = null;
			while (true){
				connection.send(new Message(MessageType.NAME_REQUEST));
				Message handShake = connection.receive();
				clientName = handShake.getData();
				if (clientName != null && !clientName.equals("") && !connectionMap.containsKey(clientName)){
					connectionMap.put(clientName, connection);
					connection.send(new Message(MessageType.NAME_ACCEPTED));
					break;
				}
			}
			return clientName;
		}

		private void sendListOfUsers(Connection connection, String userName) throws IOException{
			for (Map.Entry<String, Connection> client : connectionMap.entrySet()){
				if (!client.getKey().equals(userName))
					connection.send(new Message(MessageType.USER_ADDED, client.getKey()));
			}
		}

		private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
			while (true){
				Message receive = connection.receive();
				if (receive.getType().equals(MessageType.TEXT)){
					Message message = new Message(MessageType.TEXT, userName + ": " + receive.getData());
					sendBroadcastMessage(message);
				}else ConsoleHelper.writeMessage("Wrong message type");
			}
		}

		@Override
		public void run()
		{
			String clientName = "";
			ConsoleHelper.writeMessage("new connection: " + socket.getRemoteSocketAddress());
			try(Connection connection = new Connection(socket)){
				clientName = serverHandshake(connection);
				sendBroadcastMessage(new Message(MessageType.USER_ADDED, clientName));
				sendListOfUsers(connection, clientName);
				serverMainLoop(connection, clientName);
			}catch (IOException | ClassNotFoundException e){
				ConsoleHelper.writeMessage("Communication error occurred");
			}
			if (clientName != null)
			{
				connectionMap.remove(clientName);
				sendBroadcastMessage(new Message(MessageType.USER_REMOVED, clientName));
			}
			ConsoleHelper.writeMessage("remote connection closed.");
		}
	}


	public static void sendBroadcastMessage(Message message){
		for (Map.Entry<String, Connection> client : connectionMap.entrySet())
			try{
				client.getValue().send(message);
			}catch (IOException e){
				ConsoleHelper.writeMessage("Error sending message");
			}
	}

	public static void main(String[] args){
		ConsoleHelper.writeMessage("Input port");
		int port = ConsoleHelper.readInt();
		try (ServerSocket serverSocket = new ServerSocket(port)){
			ConsoleHelper.writeMessage("Server started.");
			while (true){
				Socket socket = serverSocket.accept();
				new Handler(socket).start();
			}
		}
		catch (IOException e){
			ConsoleHelper.writeMessage("Socket Error");
		}
	}
}


