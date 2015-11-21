package com.bvan.cs.jsonio.util;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author bvanchuhov
 */
public class JsonUtils {
    public static String readJson(String fileName) {
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(fileName))) {
            return readJson(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public static String readJson(InputStream reader) throws IOException {
        return IOUtils.toString(reader);
    }
}
