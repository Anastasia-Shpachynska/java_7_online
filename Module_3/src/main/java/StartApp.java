import controller.Controller;

public class StartApp implements Runnable {


    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        Controller controller = new Controller();
        controller.start();
    }
}
