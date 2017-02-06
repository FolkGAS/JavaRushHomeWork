package com.javarush.test.level33.lesson10.bonus01;


import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/* Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку - xml представление объекта obj.
В строке перед каждым тэгом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.

Пример вызова:  toXmlWithComment(firstSecondObject, "second", "it's a comment")
Пример результата:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<first>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second><![CDATA[need CDATA because of < and >]]></second>
    <!--it's a comment-->
    <second/>
</first>
*/
public class Solution
{
	public static String toXmlWithComment(Object obj, String tagName, String comment)
	{
		StringWriter writer = new StringWriter();
		try
		{
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			marshaller.marshal(obj, doc);
			NodeList nodes = doc.getElementsByTagName("*");
			for (int i = 0; i < nodes.getLength(); i++)
			{
				Comment comm = doc.createComment(comment);
				if (nodes.item(i).getNodeName().equals(tagName))
					nodes.item(i).getParentNode().insertBefore(comm, nodes.item(i));
			}
			nodes = doc.getElementsByTagName("*");
			Queue<Node> nodeQueue = new LinkedBlockingDeque<>();
			nodeQueue.offer(nodes.item(0));
			while (!nodeQueue.isEmpty())
			{
				Node subNode = nodeQueue.poll();
				if (subNode.getNodeType() == 3 && subNode.getTextContent().matches(".*[\"'&<>/]+.*"))
				{
					Node cdataSection = doc.createCDATASection(subNode.getTextContent());
					subNode.getParentNode().replaceChild(cdataSection, subNode);
				}
				if (subNode.hasChildNodes())
				{
					NodeList subNodes = subNode.getChildNodes();
					for (int i = 0; i < subNodes.getLength(); i++)
						nodeQueue.offer(subNodes.item(i));
				}
			}
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
		}
		catch (JAXBException | ParserConfigurationException | TransformerException e)
		{
			e.printStackTrace();
		}
		return writer.toString();
	}


	public static void main(String[] args)
	{
		TestClass testClass = new TestClass();
		testClass.tagString.add("one");
		testClass.tagString.add("two");
		testClass.tagString.add("three");
		testClass.tagString.add("here need a CDATA tag: <tagString> some text </tagString>");
		testClass.tagString.add("four");
		testClass.tagString.add("five");

		String res = toXmlWithComment(testClass, "tagString", "Some comment");

		System.out.println();
		System.out.println(res);
	}
}
