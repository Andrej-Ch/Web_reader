package app.controller.model;

public class ChartObj {
    private String seriesName;
    private String threadUrl;
    private Integer moodScore;

    public ChartObj(String seriesName, String threadUrl, Integer moodScore) {
        this.seriesName = seriesName;
        this.threadUrl = threadUrl;
        this.moodScore = moodScore;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getThreadUrl() {
        return threadUrl;
    }

    public void setThreadUrl(String threadUrl) {
        this.threadUrl = threadUrl;
    }

    public Integer getMoodScore() {
        return moodScore;
    }

    public void setMoodScore(Integer moodScore) {
        this.moodScore = moodScore;
    }


}
