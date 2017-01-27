package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.Connection;
import com.javarush.test.level30.lesson15.big01.ConsoleHelper;
import com.javarush.test.level30.lesson15.big01.Message;
import com.javarush.test.level30.lesson15.big01.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client
{
	protected Connection connection;
	private volatile boolean clientConnected = false;

	public class SocketThread extends Thread{

		protected void processIncomingMessage(String message){
			ConsoleHelper.writeMessage(message);
		}

		protected void informAboutAddingNewUser(String userName){
			ConsoleHelper.writeMessage(userName + " join to chat");
		}

		protected void informAboutDeletingNewUser(String userName){
			ConsoleHelper.writeMessage(userName + " leave chat");
		}

		protected void notifyConnectionStatusChanged(boolean clientConnected){
			Client.this.clientConnected = clientConnected;
			synchronized (Client.this){
				Client.this.notify();
			}
		}

		protected void clientHandshake() throws IOException, ClassNotFoundException{
			while (true){
				Message message = Client.this.connection.receive();
				if (message.getType().equals(MessageType.NAME_REQUEST)){
					String userName = getUserName();
					Client.this.connection.send(new Message(MessageType.USER_NAME, userName));
				}else if (message.getType().equals(MessageType.NAME_ACCEPTED)){
					notifyConnectionStatusChanged(true);
					return;
				}else throw new IOException("Unexpected MessageType");
			}
		}

		protected void clientMainLoop() throws IOException, ClassNotFoundException{
			while (true){
				Message message = Client.this.connection.receive();
				if (message.getType().equals(MessageType.TEXT)){
					processIncomingMessage(message.getData());
				}else if (message.getType().equals(MessageType.USER_ADDED)){
					informAboutAddingNewUser(message.getData());
				}else if (message.getType().equals(MessageType.USER_REMOVED)){
					informAboutDeletingNewUser(message.getData());
				}else throw new IOException("Unexpected MessageType");
			}
		}

		@Override
		public void run()
		{
			String address = getServerAddress();
			int port = getServerPort();
			try{
				Socket socket = new Socket(address, port);
				connection = new Connection(socket);
				clientHandshake();
				clientMainLoop();
			}catch (IOException | ClassNotFoundException e)
			{notifyConnectionStatusChanged(false);
			}
		}
	}

	protected String getServerAddress(){
		ConsoleHelper.writeMessage("input server address");
		String address = ConsoleHelper.readString();
		return address;
	}

	protected int getServerPort(){
		ConsoleHelper.writeMessage("input server port");
		int port = ConsoleHelper.readInt();
		return port;
	}

	protected String getUserName(){
		ConsoleHelper.writeMessage("input Nickname");
		String clientName = ConsoleHelper.readString();
		return clientName;
	}

	protected boolean shouldSentTextFromConsole(){
		return true;
	}

	protected SocketThread getSocketThread(){
		return new SocketThread();
	}

	protected void sendTextMessage(String text){
		Message message = new Message(MessageType.TEXT, text);
		try{
			connection.send(message);
		}catch (IOException e){
			ConsoleHelper.writeMessage("Message not sended");
			clientConnected = false;
		}
	}

	public void run(){
		SocketThread socketThread = getSocketThread();
		socketThread.setDaemon(true);
		socketThread.start();
		synchronized (this)
		{
			try
			{
				this.wait();
			}
			catch (InterruptedException e)
			{
				ConsoleHelper.writeMessage("interrupted");
				return;
			}
		}
		if (clientConnected)
			ConsoleHelper.writeMessage("connected. Type \"exit\" to exit");
		else ConsoleHelper.writeMessage("error during client run");
		String text = "";
		while (clientConnected && !(text = ConsoleHelper.readString()).equals("exit")){
			if (shouldSentTextFromConsole())
				sendTextMessage(text);
		}
	}

	public static void main(String[] args)
	{
		Client client = new Client();
		client.run();
	}
}
