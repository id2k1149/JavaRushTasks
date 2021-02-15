package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        String string = f.getName().toLowerCase();
        if (f.isDirectory()) return true;
        return (string.endsWith(".html") || string.endsWith(".htm"));
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
