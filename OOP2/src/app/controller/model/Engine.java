package app.controller.model;

import app.controller.MainApp;

import java.io.IOException;
import java.util.*;

/**
 * Compares List of words, retuns Mood Score
 */
public class Engine{

    MainApp mainApp;
    Runner runner;
    private String Name;

    FileReader fileReader = new FileReader();
    HashMap<String, Integer> hashmap = fileReader.getMap();


    public static int score;

    public Engine() throws IOException {
    }

    public synchronized int ListComparison(ArrayList<String>weblist) throws IOException {
        score = 0;

        // Get a set of the entries
        Set set = hashmap.entrySet();
        // Get an iterator
        Iterator it = set.iterator();
            for (int i = 0; i < weblist.size(); i++) {
                for (String key : hashmap.keySet()) {
                    if (key.equals(weblist.get(i))) {
                        Map.Entry me = (Map.Entry)it.next();
                        score += Integer.valueOf((Integer) me.getValue());
                        //System.out.println(key + " " + weblist.get(i) + "Score is: " + score);
                    }
                }
            }
            System.out.println("Mood score is: " + score);
        return score;
        }

}
