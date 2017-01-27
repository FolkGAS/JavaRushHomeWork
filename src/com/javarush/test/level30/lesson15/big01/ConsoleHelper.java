package com.javarush.test.level30.lesson15.big01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper
{
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void writeMessage(String message){
		System.out.println(message);
	}

	public static String readString(){
		String str = null;
		boolean inputFail = true;
		while (inputFail){
			try{
				str = reader.readLine();
				inputFail = false;
			}catch (IOException e){
				System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
				inputFail = true;
			}
		}
		return str;
	}

	public static int readInt(){
		boolean inputFail = true;
		int num = 0;
		while (inputFail){
			String str = readString();
			try{
				num = Integer.parseInt(str);
				inputFail = false;
			}catch (NumberFormatException e){
				System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
				inputFail = true;
			}
		}
		return num;
	}
}
