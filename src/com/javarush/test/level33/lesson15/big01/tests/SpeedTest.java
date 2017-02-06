package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest
{
	public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids){

		long start = new Date().getTime();
		for (String str : strings)
			ids.add(shortener.getId(str));
		long stop = new Date().getTime();
		return stop - start;
	}

	public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings){

		long start = new Date().getTime();
		for (Long id : ids)
			strings.add(shortener.getString(id));
		long stop = new Date().getTime();
		return stop - start;
	}

	@Test
	public void testHashMapStorage(){
		Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
		Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
		Set<String> origStrings = new HashSet<>();
		for (int i = 0; i < 10000; i++)
			origStrings.add(Helper.generateRandomString());
		Set<Long> ids1 = new HashSet<>();
		Set<Long> ids2 = new HashSet<>();
		long duration1 = getTimeForGettingIds(shortener1, origStrings, ids1);
		long duration2 = getTimeForGettingIds(shortener2, origStrings, ids2);
		Assert.assertTrue(duration1 > duration2);
		duration1 = getTimeForGettingStrings(shortener1, ids1, new HashSet<String>());
		duration2 = getTimeForGettingStrings(shortener2, ids2, new HashSet<String>());
		Assert.assertEquals(duration1, duration2, 5);
	}
}
