package app.controller.model;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
Handles engine
 */

public class TimerEx implements Runnable {


    public static int loopcount = 0;

    ScheduledExecutorService executor = null;

        public void runTimer() {
            {
                Runnable helloRunnable = new Runnable() {
                    @Override
                    public void run() {
                        loopcount+=1;
                        System.out.println("Loop: " + loopcount);
                        try {
                            Runner.runEngine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                    executor = Executors.newScheduledThreadPool(1);
                    executor.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS);
            }
        }

    public void stopTimer() {
        executor.shutdown();
    }


    @Override
    public void run() {
       // System.out.println("Hello world");
    }


}