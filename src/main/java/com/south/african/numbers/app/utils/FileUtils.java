package com.south.african.numbers.app.utils;

import com.south.african.numbers.app.exception.ErrorEnum;
import com.south.african.numbers.app.exception.UnsupportedFileFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;

public class FileUtils {

    public static final String CSV_FORMAT = "text/csv";

    public static void checkFileFormat(MultipartFile file) throws UnsupportedFileFormatException {

        if (!file.getContentType().equals(CSV_FORMAT))
            throw new UnsupportedFileFormatException(ErrorEnum.CSV_FORMAT_ERROR.getCode());
    }


}
