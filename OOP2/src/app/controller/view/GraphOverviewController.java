package app.controller.view;

import app.controller.MainApp;
import app.controller.model.ChartObj;
import app.controller.model.ThreadObj;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static app.controller.MainApp.ThreadData;
import static app.controller.model.TimerEx.loopcount;

public class GraphOverviewController implements Initializable{

    private MainApp mainApp;
    private ExecutorService executor;
    public static int timeframe = 1;    // from settings
    int timeFrame;      // timeframe*loops, for chart X axis
    int counter = 0;


    @FXML
    private VBox chartBox;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    LineChart<Number, Number> lineChart;

    List<XYChart.Series<Number, Number>> seriesList = new ArrayList<XYChart.Series<Number, Number>>();



    public GraphOverviewController() {}


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        for (int i = 0; i < ThreadData.size(); i++) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName("Website" + i);
            seriesList.add(series);

        }
        Platform.runLater(() -> {
            lineChart.getData().addAll(seriesList);    // draw chart
        });
        Platform.setImplicitExit(false);
        exec();
    }

    public void calculate() {       // get value from Thread Data and assign to Chart values
        timeFrame = loopcount * timeframe;
        int count = 0;
            for (ThreadObj t : ThreadData) {
                ChartObj chartData = new ChartObj("Series" + counter, t.getThreadUrl(), t.getMoodScore());
                seriesList.get(count).getData().add(new XYChart.Data(timeFrame, chartData.getMoodScore()));
                count++;
        }
    }


    public void exec(){
    executor = Executors.newCachedThreadPool(new ThreadFactory() {

        @Override
        public Thread newThread(Runnable r) {
            Thread thread;
            thread = new Thread(r);

            thread.setDaemon(false);
            return thread;
        }
    });

    AddToQueue addToQueue = new AddToQueue();

    executor.execute(addToQueue);

    //-- Prepare Timeline
    prepareTimeline();

}


    private class AddToQueue implements Runnable {

        public void run() {
            try {Platform.runLater(new Runnable(){
                @Override
                public void run() {

                calculate();

                }
            });
               // data.add(timeFrame);
                Thread.sleep(timeframe * 1000);

                executor.execute(this);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        }


    // Timeline gets called in the JavaFX Main thread
    private void prepareTimeline() {
        // Every frame to take any data from queue and add to chart
        new AnimationTimer() {

            @Override
            public void handle(long now) {
                //addDataToCharts(); unused
            }
        }.start();
    }

    public void setMainApp(MainApp mainApp) {this.mainApp = mainApp;
    }

    }


