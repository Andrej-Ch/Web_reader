package app.controller.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Website {

    private StringProperty siteName;

    public Website(String siteName) {
        this.siteName = new SimpleStringProperty(siteName);
    }


    public String getSiteName() {
        return siteName.get();
    }

    public void setSiteName(String siteName) {
        this.siteName.set(siteName);
    }

    public StringProperty siteNameProperty() {
        return siteName;
    }
}