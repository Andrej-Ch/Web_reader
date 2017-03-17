package app.controller.view;

import app.controller.MainApp;
import app.controller.model.ThreadObj;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by Andro on 20-Feb-17.
 */
public class WebsiteOverviewController {
    @FXML
    private TableView<ThreadObj> websiteTable;
    @FXML
    private TableColumn<ThreadObj, String> siteNameColumn;
    @FXML
    private TableColumn<ThreadObj, String> threadURLColumn;
    @FXML
    private TableColumn<ThreadObj, Integer> threadMoodColumn;

    // Reference to the mainApp application.
    private MainApp mainApp;

     //The constructor, called before the initialize() method.
    public WebsiteOverviewController() {
    }

    @FXML
    private void initialize() {
        // Initialize the website thread table with three columns.
        siteNameColumn.setCellValueFactory((TableColumn.CellDataFeatures<ThreadObj, String> cellData) -> {
            return cellData.getValue().threadNameProperty();
        });
        System.out.println();

        threadURLColumn.setCellValueFactory(cellData -> cellData.getValue().threadUrlProperty());
        threadMoodColumn.setCellValueFactory(cellData -> cellData.getValue().moodScoreProperty().asObject());

        // Listen for selection changes and show the website details when changed.
        //websiteTable.getSelectionModel().selectedItemProperty().addListener(
                //(observable, oldValue, newValue) -> showWebsiteData(newValue));
    }



    // Is called when the user clicks on the delete button.
    @FXML
    private void handleDeleteWebsite() {
        int selectedIndex = websiteTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            websiteTable.getItems().remove(selectedIndex);
            System.out.println("selection deleted");
        } else {
            System.out.println("nothing to delete");
        }
    }


    //Is called by the mainApp application to give a reference back to itself.
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        websiteTable.setItems( mainApp.getThreadData());
    }

}
