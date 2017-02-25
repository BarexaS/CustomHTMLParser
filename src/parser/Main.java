package parser;

import parser.tools.HtmlCleaner;
import parser.tools.HtmlCleanerImpl;
import parser.tools.PageReader;
import parser.tools.PageReaderImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        String url = retrieveURL();

        PageReader reader = new PageReaderImpl();
        String page = reader.getPage(url);
        HtmlCleaner cleaner = new HtmlCleanerImpl();
        String text = cleaner.clean(page);

        HashMap<String, Integer> list = collectingWords(text);

        List forsort = new ArrayList(list.entrySet());
        Collections.sort(forsort, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return ((b.getValue() - a.getValue()) > 0) ? 1 : -1;
            }
        });

        int amount = 0;
        for (String key: list.keySet()) {
            amount += list.get(key);
        }

        System.out.println("Total amount of words - "+amount);
        System.out.println(forsort);
    }

    private static HashMap<String, Integer> collectingWords(String text) {
        HashMap<String, Integer> list = new HashMap<>();
        String[] words = text.split("[- ,;:.!?\\s]+");
        Pattern p = Pattern.compile("\\p{Punct}");
        for (String word : words) {
            if (word.matches("\\D+") && !p.matcher(word).find()) {
                if (list.containsKey(word)) {
                    list.put(word, (list.get(word) + 1));
                } else {
                    list.put(word, 1);
                }
            }
        }
        return list;
    }

    private static String retrieveURL() {
        String url = "";
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Input URL:");

                url = console.readLine();

                if (!url.substring(0, 7).equalsIgnoreCase("HTTP://")) {
                    System.out.println("Please input correct URL - HTTP://*URL*");
                } else {
                    return url;
                }

            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Wrong input");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return url;
    }
}
