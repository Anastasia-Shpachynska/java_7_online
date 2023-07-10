package utill;

import controller.CatsController;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        CatsController catsController = new CatsController();
        catsController.start();
    }
}