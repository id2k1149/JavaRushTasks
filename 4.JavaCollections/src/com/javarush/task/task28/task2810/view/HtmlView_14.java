package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class HtmlView_14 implements View {
    private Controller controller;
    private final String filePath = "./4.JavaCollections/src/"
            + this.getClass().getPackage().getName().replace('.', '/')
            + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        System.out.println(vacancies.size());
        try {
            String newContent = getUpdatedFileContent(vacancies);
            updateFile(newContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private void updateFile(String stringContent) {
        File file = new File(filePath);

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(stringContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document document;
        try {
            document = getDocument();

            Element templateOriginal = document.getElementsByClass("template").first();
            Element copyTemplate = templateOriginal.clone();

            copyTemplate.removeAttr("style");
            copyTemplate.removeClass("template");

            document.select("tr[class=vacancy]").remove().not("tr[class=vacancy template");

            for (Vacancy vacancy : vacancies) {
                Element localClone = copyTemplate.clone();

                localClone.getElementsByClass("city").first().text(vacancy.getCity());
                localClone.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                localClone.getElementsByClass("salary").first().text(vacancy.getSalary());

                Element link =localClone.getElementsByTag("a").first();

                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());

                templateOriginal.before(localClone.outerHtml());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        return document.html();
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }


}
