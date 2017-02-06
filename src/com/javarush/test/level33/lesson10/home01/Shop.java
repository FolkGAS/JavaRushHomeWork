package com.javarush.test.level33.lesson10.home01;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement
@XmlType(name = "shop")
public class Shop
{
	@XmlElementWrapper(name = "goods", nillable = true)
	public List<String> names;
	public int count = 12;
	public double profit = 123.4;
	public List<String> secretData;
}
