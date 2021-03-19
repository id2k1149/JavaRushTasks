package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.HHStrategy;
import com.javarush.task.task28.task2810.model.Model;
import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.view.HtmlView;


public class Aggregator_14 {
    public static void main(String[] args) {
        // Создай вью, модель, контроллер.
        HtmlView htmlView = new HtmlView();
        // Модели нужен минимум один провайдер.
        Provider provider_1 = new Provider(new HHStrategy());
        Model model = new Model(htmlView, provider_1);
        Controller controller = new Controller(model);
        // Засэть во вью контроллер.
        htmlView.setController(controller);
        // Вызови у вью метод userCitySelectEmulationMethod.
        htmlView.userCitySelectEmulationMethod();

    }
}
