package com.javarush.task.task31.task3110;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {

    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile));
             InputStream inputStream = Files.newInputStream(source))
        {
            ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
            zipOutputStream.putNextEntry(zipEntry);
            while(inputStream.available() > 0) {
                zipOutputStream.write(inputStream.read());
            }
            zipOutputStream.closeEntry();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception {
        File file = new File(String.valueOf(filePath), String.valueOf(fileName));
        try(InputStream inputStream = new FileInputStream(file)) {
            ZipEntry zipEntry = new ZipEntry(String.valueOf(fileName));
            zipOutputStream.putNextEntry(zipEntry);

            copyData(inputStream, zipOutputStream);

            zipOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void copyData(InputStream in, OutputStream out) throws Exception {
        while (in.available() > 0) {
            int b = in.read();
            out.write(b);
        }
    }
}
