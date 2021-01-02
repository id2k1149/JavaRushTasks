package com.javarush.task.task19.task1918;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/* 
Знакомство с тегами
*/

public class Solution {


    public static void main(String[] args) throws IOException {

        String fileName;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)))
        {
            fileName = bufferedReader.readLine();

        }

        StringBuilder readFileContent = new StringBuilder();//создаем заполняемое поле для хранения данных html файла


        try(BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {//вводим имя html файла, который нужно читать
            while (fileReader.ready()) {//выполнять следующее, пока файлРидер не прочитает файл полностью
                readFileContent = readFileContent.append(fileReader.readLine());//циклами заполняем данными дополняемое поле
            }

        }


        String fileContent = readFileContent.toString().replaceAll("\r\n", "");//перемещаем все данные из стрингБафера в стринг без переносов строки

//        String a_tag = "span";
        String a_tag = args[0];
        String openTag = "<" + a_tag;//обозначаем внешний вид тега-открытия
        String closingTag = "</" + a_tag;//обозначаем внешний вид тега-закрытия
        int tagLength = a_tag.length();//обозначаем длину тега
        int startTagIndex = 0;//обозначаем индекс начала тега, который будет в дальнейшем изменяться
        int endTagIndex = 0;//обозначаем индекс конца тега, который будет в дальнейшем изменяться

        ArrayList<String> tags = new ArrayList<>();//создаем список для хранения тегов

        while ((startTagIndex != -1) && (startTagIndex < fileContent.length())) {//выполнять следующее, пока индекс начала строки не равен -1(пока существует)
            startTagIndex = fileContent.indexOf(openTag, startTagIndex);//обозначаем первичные координаты тега-открытия
            endTagIndex = fileContent.indexOf(closingTag, startTagIndex + tagLength);//обозначаем первичные координаты тега-закрытия

            int indexInTag = startTagIndex + tagLength;//создаем виртуальный курсор и переставляем его в место конца уже обработанных данных и в начало еще не обработанных
            if (endTagIndex != -1) {//если теги-закрытия еще есть(или есть необработанные теги-закрытия), выполняем следующее
                while (fileContent.substring(indexInTag, endTagIndex).contains(openTag)) {//пока в обозреваемой области(от нашего виртуального курсора до индекса окончания тега) строки есть тег-открытие, выполняем следующее
                    indexInTag = endTagIndex + tagLength;//перемещаем виртуальный курсор в место по индексу окончания тега-закрытия
                    endTagIndex = fileContent.indexOf(closingTag, indexInTag);//ни хрена не пойму что тут делается
                }
            }
            if (startTagIndex != -1 && endTagIndex !=-1) {//выполнять следующее, если есть стартовый тег и есть конечный тег
                tags.add(fileContent.substring(startTagIndex, endTagIndex + tagLength + 3));//как я понимаю , записываем в список строки от индекса начала тега до индекса конца тега и хрен пойми зачем прибавляем к этому 3
                startTagIndex += tagLength;//изменяем индекс начала, чтобы, как я понимаю, не обрабатывать одно и то же поле
            }
        }
        for (String tag : tags) {
            System.out.println(tag);
        }
    }
}
