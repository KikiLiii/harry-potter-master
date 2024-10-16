package edu.pro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static String cleanText(String url) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(url)));
        return content.replaceAll("[^A-Za-z ]", " ").toLowerCase(Locale.ROOT);
    }

    public static Map<String, Long> getWordFrequencies(String content) {
        // Розбити текст на слова і підрахувати частоту
        return Arrays.stream(content.split(" +"))
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    }

    public static void printTopFrequencies(Map<String, Long> wordFrequencies, int topCount) {
        // Сортувати за частотою та вивести топ N
        wordFrequencies.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(topCount)
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }

    public static void main(String[] args) throws IOException {
        LocalDateTime start = LocalDateTime.now();

        String content = cleanText("src/edu/pro/txt/harry.txt");

        Map<String, Long> wordFrequencies = getWordFrequencies(content);

        printTopFrequencies(wordFrequencies, 30);

        LocalDateTime finish = LocalDateTime.now();
        System.out.println("------");
        System.out.println(ChronoUnit.MILLIS.between(start, finish) + " ms");
    }
}
