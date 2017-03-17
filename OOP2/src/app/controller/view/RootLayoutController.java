package app.controller.view;


import app.controller.MainApp;
import javafx.fxml.FXML;

import java.io.IOException;

//The controller for the root layout.

    public class RootLayoutController {

        // Reference to the mainApp application
        private MainApp mainApp;

        // Is called by the mainApp application to give a reference back to itself.
        public void setMainApp(MainApp mainApp) {
            this.mainApp = mainApp;
        }

        @FXML
        private void handleMenuAdd() {
            mainApp.showWebsiteEditDialog();
        }

        @FXML
        private void handleThreadAdd(){mainApp.showThreadAddDialog();
        }

        @FXML
        private void handleMenuShowWebList(){
            mainApp.showWebsiteOverview();
        }

        @FXML
        private void handleStartEngine() throws IOException, InterruptedException {
            mainApp.startEngine();
        }

        @FXML
        private void handleStopEngine(){
            mainApp.stopEngine();
            //TimerEx.stopTimer();
        }

        @FXML
        private void handleShowGraph(){mainApp.showGraphOverview();         }

        @FXML
        private void handleShowSettings(){mainApp.showSettingsOverview();}

        //Closes the application.
        @FXML
        private void handleExit() {
            System.exit(0);
        }
    }