package app.controller;

import app.controller.model.ChartObj;
import app.controller.model.ThreadObj;
import app.controller.model.TimerEx;
import app.controller.model.Website;
import app.controller.view.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

//The data as an observable list of websites
public static ObservableList<Website> WebsiteData = FXCollections.observableArrayList();
public static ObservableList<ThreadObj> ThreadData = FXCollections.observableArrayList();
public static ObservableList<ChartObj> ChartData = FXCollections.observableArrayList();


    //Constructor
    public MainApp() {

         //Add some sample data
//        ThreadData.add(new ThreadObj("www.bbc.com", "http://www.bbc.com/news/science-environment-39115201", 0));
//        ThreadData.add(new ThreadObj("www.bbc.com", "http://www.bbc.com/news/technology-39073378", 0));
//        ThreadData.add(new ThreadObj("www.bbc.com", "http://www.bbc.com/news/science-environment-39034050", 0));

    }
    public ObservableList<ThreadObj> getThreadData() {
        return ThreadData;
    }



    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AppName");
        initRootLayout();
    }

    //Initializes the root layout.
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Shows the website overview inside the root layout.
    public void showWebsiteOverview() {
        try {
            // Load website-thread overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/WebsiteOverview.fxml"));
            AnchorPane WebsiteOverview =  loader.load();

            rootLayout.setCenter(WebsiteOverview);

            // Give the controller access to the main app.
            WebsiteOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean showWebsiteEditDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/WebsiteEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage WebDialogStage = new Stage();
            WebDialogStage.setTitle("New Website");
            WebDialogStage.initModality(Modality.WINDOW_MODAL);
            WebDialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            WebDialogStage.setScene(scene);

            // Set the website into the controller.
            WebsiteEditDialogController controller = loader.getController();
            controller.setDialogStage(WebDialogStage);

            // Show the dialog and wait until the user closes it
            controller.setMainApp(this);
            WebDialogStage.showAndWait();

            return controller.isAddClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //public void addWebsiteUrl
    public void addWebsiteURL(String siteName){


        WebsiteData.add(new Website(siteName));
        //WebsiteData.add(new Website("first site"));
    }

    public boolean showThreadAddDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ThreadAddDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage ThreadAddDialogStage = new Stage();
            ThreadAddDialogStage.setTitle("New ThreadObj");
            ThreadAddDialogStage.initModality(Modality.WINDOW_MODAL);
            ThreadAddDialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            ThreadAddDialogStage.setScene(scene);

            // Set the website into the controller.
            ThreadAddDialogController controller = loader.getController();
            controller.setThreadDialogStage(ThreadAddDialogStage);

            // Show the dialog and wait until the user closes it
            controller.setMainApp(this);
            ThreadAddDialogStage.showAndWait();

            return controller.isAddClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addThreadUrl(String name, String threadUrl, int moodScore) {
        for ( Website w : WebsiteData ) {
            if (name == w.getSiteName()) {
                ThreadData.add(new ThreadObj(name, threadUrl, moodScore));
            }
        }//check output of thread data
        for (ThreadObj t : ThreadData ) {
            System.out.println(t.getName() + " " + t.getThreadUrl());
        }
    }

    public static void addMoodScore(int score){
        //System.out.println(Runner.currentThread().getName());
        for(ThreadObj t : ThreadData){
            if (t.getThreadUrl().equals(Thread.currentThread().getName())){
                t.setMoodScore(score);
           }
        }
    }

    TimerEx timerEx = new TimerEx();

    public void startEngine() throws IOException, InterruptedException, IllegalStateException {
        timerEx.runTimer();
    }

    public void stopEngine(){
        timerEx.stopTimer();
    }


    public void showGraphOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/GraphOverview.fxml"));
            AnchorPane GraphOverview =  loader.load();

            rootLayout.setCenter(GraphOverview);

            GraphOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showSettingsOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SettingsOverview.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage SettingsOverviewStage = new Stage();
            SettingsOverviewStage.setTitle("Settings");
            SettingsOverviewStage.initModality(Modality.WINDOW_MODAL);
            SettingsOverviewStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            SettingsOverviewStage.setScene(scene);

            // Set the website into the controller.
            SettingsOverviewController controller = loader.getController();
            controller.setSettingsOverviewStage(SettingsOverviewStage);

            // Show the dialog and wait until the user closes it
            controller.setMainApp(this);
            SettingsOverviewStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

        //returns main stage
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }




}
