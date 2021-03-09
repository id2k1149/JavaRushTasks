package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./4.JavaCollections/src/"
            + this.getClass().getPackage().getName().replace('.', '/')
            + "/vacancies.html";

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    @Override
    public void update(List<Vacancy> vacancies) {
        System.out.println(vacancies.size());
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateFile(String stringContent) {
        File file = new File(filePath);

        try(Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {

            writer.write(stringContent);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getUpdatedFileContent(List<Vacancy> vacancyList) {
        return null;
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
