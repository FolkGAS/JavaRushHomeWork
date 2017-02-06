package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution
{
	public static Set<Long> getIds(Shortener shortener, Set<String> strings){
		Set<Long> ids = new HashSet<>();
		for (String string : strings)
			ids.add(shortener.getId(string));
		return ids;
	}

	public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
		Set<String> strings = new HashSet<>();
		for (Long id : keys)
			strings.add(shortener.getString(id));
		return strings;
	}

	public static void testStrategy(StorageStrategy strategy, long elementsNumber){
		Helper.printMessage(strategy.getClass().getSimpleName());
		Set<String> inputStrings = new HashSet<>();
		for (long l = 0; l < elementsNumber; l++)
			inputStrings.add(Helper.generateRandomString());
		Shortener shortener = new Shortener(strategy);
		long start = new Date().getTime();
		Set<Long> ids = getIds(shortener, inputStrings);
		long stop = new Date().getTime();
		long duration = stop - start;
		Helper.printMessage(String.valueOf(duration));
		start = new Date().getTime();
		Set<String> outputStrings = getStrings(shortener, ids);
		stop = new Date().getTime();
		duration = stop - start;
		Helper.printMessage(String.valueOf(duration));
		if (inputStrings.equals(outputStrings))
			Helper.printMessage("Тест пройден.");
		else Helper.printMessage("Тест не пройден.");
	}

	public static void main(String[] args)
	{
		StorageStrategy strategy = new HashMapStorageStrategy();
		testStrategy(strategy, 10000);
		strategy = new OurHashMapStorageStrategy();
		testStrategy(strategy, 10000);
		strategy = new FileStorageStrategy();
		testStrategy(strategy, 100);
		strategy = new OurHashBiMapStorageStrategy();
		testStrategy(strategy, 100000);
		strategy = new HashBiMapStorageStrategy();
		testStrategy(strategy, 100000);
		strategy = new DualHashBidiMapStorageStrategy();
		testStrategy(strategy, 100000);
	}
}
