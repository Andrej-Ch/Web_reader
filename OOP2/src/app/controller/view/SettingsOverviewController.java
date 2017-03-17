package app.controller.view;

import app.controller.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.stage.Stage;


public class SettingsOverviewController {

    @FXML
    Slider slider;

    private Stage SettingsOverviewStage;
    private MainApp mainApp;
    private boolean OkClicked = false;

    public SettingsOverviewController() {
    }

    @FXML
    private void initialize(){
        this.slider.setValue(GraphOverviewController.timeframe);

    }

    public void setSettingsOverviewStage(Stage SettingsOverviewStage) {
        this.SettingsOverviewStage = SettingsOverviewStage;
    }


    @FXML
    private void handleSettingsOk(){
        GraphOverviewController.timeframe = (int) slider.getValue();
        System.out.println("Timeframe set to: " + GraphOverviewController.timeframe);
    }


    @FXML
    private void handleSettingsCancel(){
        System.out.println("cancel clicked");
        SettingsOverviewStage.close();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public boolean isOkClicked() {return OkClicked;
    }
}
