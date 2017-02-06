package com.javarush.test.level33.lesson15.big01.strategies;

public class FileStorageStrategy implements StorageStrategy
{
	private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
	private long bucketSizeLimit = 10000;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public FileStorageStrategy()
	{
		for (int i = 0; i < table.length; i++)
			table[i] = new FileBucket();
	}

	public long getBucketSizeLimit()
	{
		return bucketSizeLimit;
	}

	public void setBucketSizeLimit(long bucketSizeLimit)
	{
		this.bucketSizeLimit = bucketSizeLimit;
	}

	private int hash(Long k)
	{
		return k.hashCode();
	}

	private int indexFor(int hash, int length)
	{
		return hash & (length - 1);
	}

	private Entry getEntry(Long key)
	{
		int hash = (key == null) ? 0 : hash(key);
		for (Entry e = table[indexFor(hash, table.length)].getEntry(); e != null; e = e.next)
		{
			Long k;
			if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
				return e;
		}
		return null;
	}

	private void resize(int newCapacity)
	{
		FileBucket[] oldTable = table;
		int oldCapacity = oldTable.length;
		if (oldCapacity == 1 << 30)
		{
			return;
		}

		FileBucket[] newTable = new FileBucket[newCapacity];
		transfer(newTable);
		for (FileBucket bucket : table)
			bucket.remove();
		table = newTable;
	}

	private void transfer(FileBucket[] newTable)
	{
		FileBucket[] src = table;
		int newCapacity = newTable.length;
		for (int j = 0; j < src.length; j++)
		{
			Entry e = src[j].getEntry();
			if (e != null)
			{
				src[j] = null;
				do
				{
					Entry next = e.next;
					int i = indexFor(e.hash, newCapacity);
					e.next = newTable[i].getEntry();
					newTable[i].putEntry(e);
					e = next;
				}
				while (e != null);
			}
		}
	}

	private void addEntry(int hash, Long key, String value, int bucketIndex)
	{
		if (table[bucketIndex] == null)
			table[bucketIndex] = new FileBucket();
		Entry e = table[bucketIndex].getEntry();
		table[bucketIndex].putEntry(new Entry(hash, key, value, e));
		if (table[bucketIndex].getFileSize() > bucketSizeLimit)
			resize(2 * table.length);
	}

	private void createEntry(int hash, Long key, String value, int bucketIndex)
	{
		if (table[bucketIndex] == null)
			table[bucketIndex] = new FileBucket();
		Entry e = table[bucketIndex].getEntry();
		table[bucketIndex].putEntry(new Entry(hash, key, value, e));
	}


	@Override
	public boolean containsKey(Long key)
	{
		return getEntry(key) != null;
	}

	@Override
	public boolean containsValue(String value)
	{
		if (value == null)
			return false;
		FileBucket[] tab = table;
		for (int i = 0; i < tab.length; i++)
		{
			if (tab[i] != null)
				for (Entry e = tab[i].getEntry(); e != null; e = e.next)
					if (value.equals(e.value))
						return true;
		}
		return false;
	}

	@Override
	public void put(Long key, String value)
	{
		if (key == null)
			return;
		int hash = hash(key);
		int i = indexFor(hash, table.length);
		addEntry(hash, key, value, i);
	}

	@Override
	public Long getKey(String value)
	{
		if (value == null)
			return 0L;
		for (FileBucket bucket : table)
		{
			while (bucket != null && bucket.getEntry() != null)
			{
				if (value.equals(bucket.getEntry().getValue()))
					return bucket.getEntry().key;
				bucket.putEntry(bucket.getEntry().next);
			}
		}
		return null;
	}

	@Override
	public String getValue(Long key)
	{
		if (key == null)
			return null;
		int hash = hash(key);
		if (table[indexFor(hash, table.length)] == null)
			return null;
		for (Entry e = table[indexFor(hash, table.length)].getEntry(); e != null; e = e.next)
		{
			Long k;
			if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
				return e.value;
		}
		return null;
	}
}
