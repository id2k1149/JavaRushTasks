package com.javarush.task.task31.task3110;

public enum Operation_old {
    CREATE("упаковать файлы в архив"),
    ADD("добавить файл в архив"),
    REMOVE("удалить файл из архива"),
    EXTRACT("распаковать архив"),
    CONTENT("просмотреть содержимое архива"),
    EXIT("выход");

    private String comment;

    Operation_old(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return comment;
    }
}
