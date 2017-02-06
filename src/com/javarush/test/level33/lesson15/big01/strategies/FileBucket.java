package com.javarush.test.level33.lesson15.big01.strategies;

import com.javarush.test.level33.lesson15.big01.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket
{
	private Path path;

	public FileBucket()
	{
		try
		{
			path = Files.createTempFile(null, null);
			path.toFile().deleteOnExit();
		}
		catch (IOException e)
		{
			ExceptionHandler.log(e);
		}
	}

	public long getFileSize()
	{
		try
		{
			return Files.size(path);
		}
		catch (IOException e)
		{
			ExceptionHandler.log(e);
		}
		return 0;
	}

	public void putEntry(Entry entry)
	{
		try (FileOutputStream fos = new FileOutputStream(path.toFile());
		     ObjectOutputStream oos = new ObjectOutputStream(fos))
		{
			oos.writeObject(entry);
		}
		catch (IOException exc)
		{
			ExceptionHandler.log(exc);
		}
	}

	public Entry getEntry()
	{
		Entry entry = null;
		try
		{
			if (Files.notExists(path) || Files.size(path) == 0)
				return null;
			try (FileInputStream fis = new FileInputStream(path.toFile());
			     ObjectInputStream ois = new ObjectInputStream(fis))
			{
				entry = (Entry) ois.readObject();
			}
		}
		catch (IOException | ClassNotFoundException e)
		{
			ExceptionHandler.log(e);
		}
		return entry;
	}

	public void remove()
	{
		try
		{
			Files.delete(path);
		}
		catch (IOException e)
		{
			ExceptionHandler.log(e);
		}
	}
}
