package controller;

import service.StreamService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class StreamController {
    StreamService streamService = new StreamService();

    public void run() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println();
        System.out.println("Enter the text - 1");
        System.out.println("Exit - 2");
        String select;
        for (select = bufferedReader.readLine(); select != null; select = bufferedReader.readLine()) {
            select(bufferedReader, select);
        }
    }

    private void select(BufferedReader bufferedReader, String select) {
        switch (select) {
            case "1" -> inputTextAndPrintResult(bufferedReader);
            case "2" -> System.exit(0);
            default -> System.out.println("Please, select something from the proposed menu!");
        }
    }

    private void inputTextAndPrintResult(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter the text:" + "\n");
            String text = bufferedReader.readLine();
            if (text.isEmpty()) {
                System.out.println("The text can not contain less than one character!");
            }
            List<Map.Entry<String, Long>> streamServiceList = streamService.streamSort(text);

            int totalWords = streamService.getAllWordsCount(text);
            int sentencesCount = streamService.getAllSentences(text);
            int previousCount = -1;
            int rating = 0;

            String wordDashesStr = "-".repeat(20 - 2);
            String ratingDashesStr = "-".repeat(10 - 1);
            String countDashesStr = "-".repeat(10 - 1);
            String totalPercentageDashesStr = "-".repeat(25 - 1);
            String sentencePercentageDashesStr = "-".repeat(25 - 1);

            System.out.println("┌" + wordDashesStr + "┐" + ratingDashesStr + "┐" + countDashesStr + "┐" + totalPercentageDashesStr + "┐" + sentencePercentageDashesStr + "┐");
            System.out.printf("%-20s%-10s%-10s%-25s%-25s\n", " Word", "Rating", "Count", "Total Percentage (%)", "Sentence Percentage (%)");
            for (Map.Entry<String, Long> list : streamServiceList) {
                String word = list.getKey();
                long count = list.getValue();
                if (count != previousCount) {
                    rating++;
                }
                previousCount = (int)count;
                float totalPercentage = (float) count / totalWords * 100;
                List<String> sentencesWithWord = streamService.getSentenceWithWord(text, word);
                float sentencePercentage = (float) sentencesWithWord.size() / sentencesCount * 100;
                System.out.println("|" + wordDashesStr + "|" + ratingDashesStr + "|" + countDashesStr + "|" + totalPercentageDashesStr + "|" + sentencePercentageDashesStr + "|");
                System.out.printf("%-20s%-10d%-10d%-25.2f%-25.2f\n", " " + word, rating, count, totalPercentage, sentencePercentage);
            }
            System.out.println("└" + wordDashesStr + "┘" + ratingDashesStr + "┘" + countDashesStr + "┘" + totalPercentageDashesStr + "┘" + sentencePercentageDashesStr + "┘");
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again.");
        }
    }
}
