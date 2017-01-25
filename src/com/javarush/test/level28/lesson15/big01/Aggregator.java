package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.HHStrategy;
import com.javarush.test.level28.lesson15.big01.model.Model;
import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Aggregator
{
    public static void main(String[] args)
    {
    	String city = "";
	    try
	    {city = URLEncoder.encode("Челябинск", "UTF-8");
	    }catch (UnsupportedEncodingException e){}
	    HHStrategy hhStrategy = new HHStrategy();
//	    MoikrugStrategy moiKrugStrategy = new MoikrugStrategy();
    	Provider hhProvider = new Provider(hhStrategy);
//    	Provider moiKrugProvider = new Provider(moiKrugStrategy);
	    HtmlView view = new HtmlView();
//	    Model model = new Model(view, hhProvider, moiKrugProvider);
	    Model model = new Model(view, hhProvider);
	    Controller controller = new Controller(model);
	    view.setController(controller);
//	    view.userCitySelectEmulationMethod();
	    controller.onCitySelect(city);
    }
}
