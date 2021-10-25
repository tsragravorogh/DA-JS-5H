package ru.dataart.academy.java;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Calculator {
    /**
     * @param zipFilePath -  path to zip archive with text files
     * @param character   - character to find
     * @return - how many times character is in files
     */
    public Integer getNumberOfChar(String zipFilePath, char character) {
        Integer countOfChar = 0;

        try {
            final ZipFile zipFile = new ZipFile(zipFilePath);
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                final ZipEntry zipEntry = entries.nextElement();
                InputStream input = zipFile.getInputStream(zipEntry);

                BufferedReader bf = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
                int symbol = bf.read();
                while (symbol != -1) {
                    if (character == (char) symbol) {
                        countOfChar++;
                    }
                    symbol = bf.read();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countOfChar;
    }

    /**
     * @param zipFilePath - path to zip archive with text files
     * @return - max length
     */

    public Integer getMaxWordLength(String zipFilePath) {
        int maxLength = 0;

        try {
            final ZipFile zipFile = new ZipFile(zipFilePath);
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                final ZipEntry zipEntry = entries.nextElement();
                InputStream input = zipFile.getInputStream(zipEntry);

                BufferedReader bf = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
                String line;
                while ((line = bf.readLine()) != null) {
                    String[] words = line.split(" ");
                    for (String w : words) {
                        if(w.length() > maxLength) {
                            maxLength = w.length();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maxLength;
    }
}
