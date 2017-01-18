package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View
{
	private Controller controller;
	private final String filePath = "./src/" + this.getClass().getPackage().getName().replace(".", "/") + "/vacancies.html";

	@Override
	public void update(List<Vacancy> vacancies)
	{
		updateFile(getUpdatedFileContent(vacancies));
	}

	@Override
	public void setController(Controller controller)
	{
		this.controller = controller;
	}

	public void userCitySelectEmulationMethod()
	{
		controller.onCitySelect("Odessa");
	}

	private String getUpdatedFileContent(List<Vacancy> vacancies)
	{
		Document document = null;
		try
		{
			document = getDocument();

			Element element = document.select(".template").first();
			if (element == null) return "";
			Element copyTemplate = element.clone();
			copyTemplate.removeClass("template");
			copyTemplate.removeAttr("style");
			for (Element remove : document.select(".vacancy"))
				if (!remove.hasClass("template"))
					remove.remove();
			for (Vacancy vacancy : vacancies)
			{
				Element vacancyTemplate = copyTemplate.clone();
				vacancyTemplate.getElementsByClass("city").first().appendText(vacancy.getCity());
				vacancyTemplate.getElementsByClass("companyName").first().appendText(vacancy.getCompanyName());
				vacancyTemplate.getElementsByClass("salary").first().appendText(vacancy.getSalary());
				vacancyTemplate.getElementsByTag("a").first().attr("href", vacancy.getUrl()).text(vacancy.getTitle());
				element.before(vacancyTemplate.outerHtml());
			}
		}
		catch (IOException e){
			e.printStackTrace();
			System.out.println("Some exception occurred");
		}
		return document.toString();
	}

	private void updateFile(String string)
	{
		try (FileWriter writer = new FileWriter(filePath))
		{
			writer.write(string);
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	protected Document getDocument() throws IOException
	{
		return Jsoup.parse(new File(filePath), "UTF-8");
	}
}
