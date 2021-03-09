package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.view.View;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;


public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers) {
        if (view == null) {
            throw new IllegalArgumentException("View can not be null");
        }

        if (providers == null) {
            throw new IllegalArgumentException("Providers can not be null");
        }

        if (providers.length == 0) {
            throw new IllegalArgumentException("It must be one or more provider");
        }

        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city) {
        ArrayList<Vacancy> vacancies = new ArrayList<>();

        for (Provider provider: providers) {
            vacancies.addAll(provider.getJavaVacancies(city))  ;
        }

        view.update(vacancies);
    }
}
