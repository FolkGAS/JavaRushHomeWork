package com.javarush.test.level16.lesson13.bonus01;

import com.javarush.test.level16.lesson13.bonus01.common.*;

public class ImageReaderFactory
{
    public static ImageReader getReader(ImageTypes image) {
        if (image == (null))
            throw new IllegalArgumentException("Неизвестный тип картинки");
        switch (image) {
            case BMP: { return new BmpReader();}
            case JPG: { return new JpgReader();}
            case PNG: { return new PngReader();}
            default: throw new IllegalArgumentException("Неизвестный тип картинки");
        }
    }
}
