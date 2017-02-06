package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest
{
	public void testStorage(Shortener shortener){

		String s1 = "string";
		String s2 = "another string";
		String s3 = "string";

		Long key1 = shortener.getId(s1);
		Long key2 = shortener.getId(s2);
		Long key3 = shortener.getId(s3);

		Assert.assertNotEquals(s1, s2);
		Assert.assertNotEquals(s3, s2);
		Assert.assertEquals(s1, s3);

		String value1 = shortener.getString(key1);
		String value2 = shortener.getString(key2);
		String value3 = shortener.getString(key3);

		Assert.assertEquals(s1, value1);
		Assert.assertEquals(s2, value2);
		Assert.assertEquals(s3, value3);
	}

	@Test
	public void testHashMapStorageStrategy(){
		testStorage(new Shortener(new HashMapStorageStrategy()));
	}

	@Test
	public void testOurHashMapStorageStrategy(){
		testStorage(new Shortener(new OurHashMapStorageStrategy()));
	}

	@Test
	public void testFileStorageStrategy(){
		testStorage(new Shortener(new FileStorageStrategy()));
	}

	@Test
	public void testHashBiMapStorageStrategy(){
		testStorage(new Shortener(new HashBiMapStorageStrategy()));
	}

	@Test
	public void testDualHashBidiMapStorageStrategy(){
		testStorage(new Shortener(new DualHashBidiMapStorageStrategy()));
	}

	@Test
	public void testOurHashBiMapStorageStrategy(){
		testStorage(new Shortener(new OurHashBiMapStorageStrategy()));
	}
}
