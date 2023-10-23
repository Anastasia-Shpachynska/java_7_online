package service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamService {

    public List<Map.Entry<String, Long>> streamSort(String text) {
        List<String> words = Arrays.stream(text.split("[^\\p{L}]+"))
                .map(String::toLowerCase).toList();

        Map<String, Long> wordFrequency = words.stream()
                .collect(Collectors
                        .groupingBy(Function.identity(), Collectors.counting()));

        List<Map.Entry<String, Long>> sortWords = wordFrequency.entrySet().stream()
                .sorted((less, more) -> more.getValue()
                        .compareTo(less.getValue()))
                .collect(Collectors.toList());

        return sortWords;
    }

    public int getAllWordsCount(String text) {
        return Arrays.asList(text.split("\\s+")).size();
    }

    public List<String> getSentenceWithWord(String text, String word) {
        List<String> sentences = Arrays.asList(text.split("[.!?]"));
        return sentences.stream()
                .filter(sentence -> sentence.toLowerCase().contains(word.toLowerCase()))
                .collect(Collectors.toList());
    }

    public int getAllSentences(String text) {
        List<String> sentences = Arrays.asList(text.split("[.!?]"));
        return sentences.size();
    }
}
