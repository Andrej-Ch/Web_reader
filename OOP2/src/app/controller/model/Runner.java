package app.controller.model;

import app.controller.MainApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*

 */

public class Runner extends Thread {
    private Thread t;
    private String ThreadName;
    private MainApp mainApp;
    Engine engine;
    private String Url;
    int value;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    Runner(Engine eng, String threadName) throws IOException {
        engine = eng;
        ThreadName = threadName;
    }

    @Override
    public void run() {
        synchronized (engine) {
            try {
                currentThread().setName(getThreadName());
                WebsiteReader websiteReader = new WebsiteReader(getThreadName());   // get Website data by url
                ArrayList<String> weblist = websiteReader.getList();        // Put website data on the Arraylist
                engine.ListComparison(weblist);   // pass parameter to List comparison
                mainApp.addMoodScore(Engine.score);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void runEngine() throws IOException, InterruptedException {

            Engine engine = new Engine();
            ExecutorService executor = Executors.newFixedThreadPool(10);
            for (ThreadObj t : MainApp.ThreadData) {   // iterate through ThreadData
                Runner thread = new Runner(engine, t.getThreadUrl()); // create thread
                executor.execute(thread);
                Thread.sleep(500);
            }
                    executor.shutdown();

        while (!executor.isTerminated()) {
        }
        System.out.println("Refreshing..");

    }

    public int getValue() {
        return value;
    }

    public String getThreadName() {
        return ThreadName;
    }
}

