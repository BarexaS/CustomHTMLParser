package parser.tools;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Collecting words from text using regex
 */
public class WordsCollector {
    /**
     * Collecting words from text in {@code HashMap<String, Integer>}, where String - word, Integer - frequency in text.
     * Using regex [- ,;:.!?\s]+ witch means ( chars '-',' ',';',':','.','!','\n','\t','\f','\r')
     *
     * @param text - text with words to collect.
     * @return {@code HashMap<String, Integer>} with words and it frequency
     */
    public static HashMap<String, Integer> collectingWords(String text){
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
}
