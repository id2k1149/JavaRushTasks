package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerStrategy_m implements Strategy {
    private static final String URL_FORMAT = "https://career.habr.com/vacancies?q=java+%s&page=%d";
//    private static final String URL_FORMAT = "https://javarush.ru/testdata/big28data2.html";
    private static final String SITE_URL = "https://career.habr.com";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        int pageNumber = 0;

        try {
            Document document = getDocument(searchString, pageNumber);
            while (true) {
                Elements elements = document.getElementsByClass( "vacancy-card__title-link");

                if (elements.size() == 0) {
                    break;
                }

                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();

                    vacancy.setSiteName(SITE_URL);

                    Element salaryElement = element.getElementsByClass("salary").first();
                    String salary = salaryElement.text();
                    vacancy.setSalary(salary.length() == 0 ? "" : salary);

                    Element infoElement = element.getElementsByClass("info").first();
                    vacancy.setCity(infoElement.getElementsByClass("location").text());
                    vacancy.setCompanyName(infoElement.getElementsByClass("company_name").text());

                    Element titleElement = infoElement.getElementsByClass("title").first();
                    vacancy.setTitle(titleElement.text());

                    String link = SITE_URL + titleElement.getElementsByTag("a").attr("href");
                    vacancy.setUrl(link);

                    vacancies.add(vacancy);
                }

                pageNumber++;
                document = getDocument(searchString, pageNumber);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.192 Safari/537.36")
                .referrer("")
                .get();
        return document;
    }
}
