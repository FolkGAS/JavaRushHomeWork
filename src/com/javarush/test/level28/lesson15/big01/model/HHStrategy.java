package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0";
	private static final String REFERRER = "https://hh.ru/";
	private static final int timeout = 10 * 1000;

	@Override
	public List<Vacancy> getVacancies(String searchString)
	{
		int page = 0;
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

			elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
			if (elements.size() == 0)
				break;
			for (Element element : elements){
				vacancy = new Vacancy();
					elUrlAndTitle = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").first();
					if (elUrlAndTitle != null)
					{
						url = elUrlAndTitle.attr("href");
						title = elUrlAndTitle.html();
					}
					else {
						url = "";
						title = "";
					}
					elSalary = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").first();
					if (elSalary != null)
						salary = elSalary.text();
					else
						salary = "";

					elCompanyName = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").first();
					if (elCompanyName != null)
						companyName = elCompanyName.text();
					else
						companyName = "";

					elCity = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").first();
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

	protected Document getDocument(String searchString, int page) throws IOException{
		return Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent(USER_AGENT).referrer(REFERRER).get();
	}
}
