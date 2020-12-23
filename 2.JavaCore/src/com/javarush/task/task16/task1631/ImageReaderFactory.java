package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;


public class ImageReaderFactory {
    public static ImageReader getImageReader(ImageTypes imageTypes) {

        if (imageTypes == null) {
            throw new IllegalArgumentException("IllegalArgumentException");
        } else {
            switch (imageTypes) {
                case JPG:
                    return new JpgReader();
                case BMP:
                    return new BmpReader();
                case PNG:
                    return new PngReader();
                default:
                    throw new IllegalArgumentException("IllegalArgumentException");
            }
        }

        // Java
//        ImageReader imageReader;
//        if (type == ImageTypes.BMP) {
//            imageReader = new BmpReader();
//        } else if (type == ImageTypes.JPG) {
//            imageReader = new JpgReader();
//        } else if (type == ImageTypes.PNG) {
//            imageReader = new PngReader();
//        } else {
//            throw new IllegalArgumentException("Неизвестный тип картинки");
//        }
//        return imageReader;
    }
}

