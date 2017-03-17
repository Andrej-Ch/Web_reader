package app.controller.view;

import app.controller.MainApp;
import app.controller.model.Website;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ThreadAddDialogController {
    @FXML
    private TextField threadNameField;

    @FXML
    ComboBox cmb_locations;

    private Stage ThreadAddDialogStage;
    private Website website;
    private boolean addClicked = false;
    private MainApp mainApp;


    public ThreadAddDialogController() {
    }

    //Initialize the controller class.
    @FXML
    private void initialize() {
        for(Website website : MainApp.WebsiteData) {
            cmb_locations.getItems().add(website.getSiteName());
        }
    }

    // sets the stage of this dialog.
    public void setThreadDialogStage(Stage ThreadAddDialogStage) {
        this.ThreadAddDialogStage = ThreadAddDialogStage;
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
    private void handleAddThread() {
        System.out.println("ThreadObj add works");

        if(threadNameField.getLength() > 0){
            mainApp.addThreadUrl((String)cmb_locations.getValue(),threadNameField.getText(), 0);
            Alert alert = new Alert(Alert.AlertType.NONE, "ThreadObj Added Successfully!", ButtonType.OK);
            alert.getDialogPane().setPrefSize(200, 100);
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.NONE, "Please Enter Valid URL.", ButtonType.OK);
            alert.getDialogPane().setPrefSize(200, 100);
            alert.showAndWait();
        }
        threadNameField.clear();
    }


    //Called when the user clicks cancel.
    @FXML
    private void handleCancel() {
        ThreadAddDialogStage.close();
    }

}
