package app.controller.model;

import app.controller.MainApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Cleans up text, returns ArrayList of words
 */
public class WebsiteReader {

    private MainApp mainApp;
    private ArrayList<String> wordValues;

        public WebsiteReader(String link) {

            try {int counter = 0;
                wordValues = new ArrayList<String>();

                // get URL content
                URL url;
                url = new URL(link);
                URLConnection conn = url.openConnection();

                // open the stream and put it into BufferedReader
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));


                String inputLine;
                while ((inputLine = br.readLine()) != null) {

                    String[] splitStr = inputLine.replaceAll("<.*?>", "").split(" ");      // split by spaces&remove html tags

                    Pattern pattern = Pattern.compile("[-:=;_\\[\\]+}{)(/]");   // remove strings containing these elements

                    for (int i = 0; i < splitStr.length; i++) {        // assign elements by index to ArrayList
                        if ((splitStr[i].length()<20) && (splitStr[i].length()>1)){     //remove elements longer that 20 characters(long code strings)
                            Matcher matcher = pattern.matcher(splitStr[i]);             // removes most of remaining code
                            if (matcher.find()){
                            }else{
                          wordValues.add(splitStr[i].toUpperCase());    // change top uppercase and add to ArrayList
                    }   }  }
                }
                br.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            public ArrayList<String> getList() {
                return wordValues;
            }

    }