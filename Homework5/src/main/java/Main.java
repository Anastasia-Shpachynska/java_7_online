import controller.DictionaryController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        DictionaryController dictionaryController = new DictionaryController();
        dictionaryController.start();
    }
}