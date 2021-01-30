package com.javarush.task.task31.task3110;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager_old {

    private Path zipFile;

    public ZipFileManager_old(Path zipFile) {
        this.zipFile = zipFile;
    }

//    public void createZip(Path source) {
//        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile));
//             InputStream inputStream = Files.newInputStream(source))
//        {
//            ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
//            zipOutputStream.putNextEntry(zipEntry);
//            while(inputStream.available() > 0) {
//                zipOutputStream.write(inputStream.read());
//            }
//            zipOutputStream.closeEntry();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public void createZip(Path source) throws Exception {
//
//        //Создаем объект Path: родительская директория целевого файла
//        Path zipPath = zipFile.getParent();
//        //Проверяем, существует ли эта директория. Если нет - создаем.
//        if (Files.notExists(zipPath))
//            Files.createDirectory(zipPath);
//
//        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile))) {
//
//            //Если переданный в параметре путь ведет к обычному файлу - вызываем метод
//            if (Files.isRegularFile(source))
//                addNewZipEntry(zipOutputStream, source.getParent(), source.getFileName());
//
//                //Если путь ведет к директории
//            else if (Files.isDirectory(source)){
//                FileManager manager = new FileManager(source);
//                List<Path> fileNames = manager.getFileList();
//
//                fileNames.forEach(x -> {
//                    try {
//                        addNewZipEntry(zipOutputStream, source, x);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                });
//                //Если путь не ведет никуда - исключение
//            } else throw new PathIsNotFoundException();
//        }
//    }

    public void createZip(Path source) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile));)
        {
            addNewZipEntry(zipOutputStream, source.getParent(), source.getFileName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception {

        try(InputStream inputStream = Files.newInputStream(filePath.resolve(fileName))) {
            ZipEntry zipEntry = new ZipEntry(String.valueOf(fileName));
            zipOutputStream.putNextEntry(zipEntry);

            copyData(inputStream, zipOutputStream);

            zipOutputStream.closeEntry();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[8 * 1024];
        while (in.available() > 0) {
            int b = in.read();
            out.write(b);
        }
    }
}
