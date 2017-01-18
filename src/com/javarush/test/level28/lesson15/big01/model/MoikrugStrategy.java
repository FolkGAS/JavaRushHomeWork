package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy
{
	private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s";
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0";
	private static final String REFERRER = "https://moikrug.ru/";
	private static final int timeout = 10 * 1000;

	@Override
	public List<Vacancy> getVacancies(String searchString)
	{
		int page = 1;
		String title, salary, city, companyName, siteName, url;
		Element elUrlAndTitle, elSalary, elCity, elCompanyName;
		List<Vacancy> vacancies = new ArrayList<>();
		List<Element> elements;
		Vacancy vacancy;
		while (true)
		{
			Document document = new Document("localhost");

			try{
				document = getDocument(searchString, page++);
			}catch (IOException e){}

			elements = document.getElementsByClass("job");
			if (elements.size() == 0)
				break;
			for (Element element : elements){
				vacancy = new Vacancy();
				elUrlAndTitle = element.getElementsByClass("Title").first().getElementsByAttribute("href").first();
				if (elUrlAndTitle != null)
				{
					url = elUrlAndTitle.attr("abs:href");
					title = elUrlAndTitle.html();
				}
				else {
					url = "";
					title = "";
				}
				elSalary = element.getElementsByClass("count").first();
				if (elSalary != null)
					salary = elSalary.text();
				else
					salary = "";

				elCompanyName = element.getElementsByClass("company_name").first();
				if (elCompanyName != null)
					companyName = elCompanyName.text();
				else
					companyName = "";

				elCity = element.getElementsByClass("location").first();
				if (elCity != null)
					city = elCity.text();
				else
					city = "";

				if (document.title() != null || !document.title().equals(""))
					siteName = document.title();
				else siteName = "";

				vacancy.setTitle(title);
				vacancy.setSalary(salary);
				vacancy.setCity(city);
				vacancy.setCompanyName(companyName);
				vacancy.setSiteName(siteName);
				vacancy.setUrl(url);
				vacancies.add(vacancy);
			}
		}
		return vacancies;
	}

	protected Document getDocument(String searchString, int page) throws IOException
	{
		return Jsoup.connect(String.format(URL_FORMAT, page, searchString)).userAgent(USER_AGENT).referrer(REFERRER).get();
	}
}
