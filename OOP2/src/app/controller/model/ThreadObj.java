package app.controller.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ThreadObj{

    private StringProperty name;
    private StringProperty threadUrl;
    private IntegerProperty moodScore;

    public ThreadObj(String name, String threadUrl, Integer moodScore){
        this.name = new SimpleStringProperty(name);
        this.threadUrl = new SimpleStringProperty(threadUrl);
        this.moodScore = new SimpleIntegerProperty(moodScore);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty threadNameProperty() {
        return name;
    }

    public String getThreadUrl() {
        return threadUrl.get();
    }

    public void setThreadUrl(String threadUrl) {
        this.threadUrl.set(threadUrl);
    }

    public StringProperty threadUrlProperty() {
        return threadUrl;
    }

    public Integer getMoodScore() {
        return moodScore.get();
    }

    public void setMoodScore(Integer moodScore) {
        this.moodScore.set(moodScore);
    }

    public IntegerProperty moodScoreProperty() {
        return moodScore;
    }
}