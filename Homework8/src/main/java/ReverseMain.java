import controller.ReverseController;

import java.io.IOException;

public class ReverseMain {
    public static void main(String[] args) throws IOException {
        ReverseController reverseController = new ReverseController();
        reverseController.start();
    }
}