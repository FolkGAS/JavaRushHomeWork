package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.HHStrategy;
import com.javarush.test.level28.lesson15.big01.model.Model;
import com.javarush.test.level28.lesson15.big01.model.MoikrugStrategy;
import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;

public class Aggregator
{
    public static void main(String[] args)
    {
    	HHStrategy hhStrategy = new HHStrategy();
	    MoikrugStrategy moiKrugStrategy = new MoikrugStrategy();
    	Provider hhProvider = new Provider(hhStrategy);
    	Provider moiKrugProvider = new Provider(moiKrugStrategy);
	    HtmlView view = new HtmlView();
	    Model model = new Model(view, hhProvider, moiKrugProvider);
	    Controller controller = new Controller(model);
	    view.setController(controller);
	    view.userCitySelectEmulationMethod();
    }
}
