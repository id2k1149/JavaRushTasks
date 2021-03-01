package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "https://hh.ru/search/vacancy?text=java+%s&page=%d";


    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        int pageNumber = 1;

        try {
//            Document doc = Jsoup.connect(url).get();
            Document doc = Jsoup.connect(String.format(URL_FORMAT, searchString, pageNumber)).get();
        } catch (IOException e) {
//            e.printStackTrace();
        }

        return vacancies;
    }
}
