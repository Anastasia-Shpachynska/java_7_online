import controller.StreamController;

import java.io.IOException;

public class StreamMain {
    public static void main(String[] args) throws IOException {
        StreamController streamController = new StreamController();
        streamController.run();
    }
}