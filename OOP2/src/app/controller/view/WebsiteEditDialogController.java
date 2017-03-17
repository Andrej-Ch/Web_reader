package app.controller.view;

import app.controller.MainApp;
import app.controller.model.Website;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

    public class WebsiteEditDialogController {

        @FXML
        private TextField siteNameField;

        private Stage websiteEditDialogStage;
        private Website website;
        private boolean addClicked = false;
        private MainApp mainApp;

        public WebsiteEditDialogController() {
        }

        //Initialize the controller class.
        @FXML
        private void initialize() {
        }

        // sets the stage of this dialog.
        public void setDialogStage(Stage websiteEditDialogStage) {
            this.websiteEditDialogStage = websiteEditDialogStage;
        }

        public void setMainApp(MainApp mainApp) {
            this.mainApp = mainApp;
        }

        // Returns true if the user clicked Add, false otherwise.
        public boolean isAddClicked() {
            return addClicked;
        }



        //Called when the user clicks add.
        @FXML
        private void handleAdd() {
            System.out.println("add website clicked");

            // check input
            if(siteNameField.getLength() > 0){
                mainApp.addWebsiteURL(siteNameField.getText());
               Alert alert = new Alert(Alert.AlertType.NONE, "Website Added Successfully!", ButtonType.OK);
               alert.getDialogPane().setPrefSize(200, 100);
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.NONE, "Please Enter Website URL.", ButtonType.OK);
                alert.getDialogPane().setPrefSize(200, 100);
                alert.showAndWait();
            }
            siteNameField.clear();
        }

        //Called when the user clicks cancel.
        @FXML
        private void handleCancel() {
            websiteEditDialogStage.close();
        }

    }

