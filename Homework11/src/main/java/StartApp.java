import controller.Controller;

public class StartApp implements Runnable {

    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        Controller mainController = new Controller();
        mainController.start();
    }
}
