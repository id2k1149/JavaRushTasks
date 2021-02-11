package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Конвертация из одного класса в другой используя JSON Ӏ 3304
На вход в метод подается объект класса X и класс Y.
Поля у классов X и Y одинаковые (по типам и названиям).
Метод должен вернуть объект класса Y,
идентичный подаваемому объекту класса X.
Т.е. суть в том, что нужно сериализовать в JSON объект класса X,
а потом десериализовать его уже как объект класса Y.
И вернуть. Всё.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Second s = (Second) convertOneToAnother(new First(), Second.class);
        First f = (First) convertOneToAnother(new Second(), First.class);
    }

//    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {
//        StringWriter stringWriter = new StringWriter();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(stringWriter, one);
//        String jsonString = stringWriter.toString()
//                .replaceFirst(one.getClass().getSimpleName().toLowerCase(),
//                        resultClassObject.getSimpleName().toLowerCase());
//        return mapper.readValue(jsonString, resultClassObject);
//    }

    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(one);
        jsonString = jsonString.replaceFirst(one.getClass().getSimpleName().toLowerCase(),
                                            resultClassObject.getSimpleName().toLowerCase());
        return mapper.readValue(jsonString, resultClassObject);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
    @JsonSubTypes(@JsonSubTypes.Type(value = First.class, name = "first"))
    public static class First {
        public int i;
        public String name;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
    @JsonSubTypes(@JsonSubTypes.Type(value = Second.class, name = "second"))
    public static class Second {
        public int i;
        public String name;
    }
}
