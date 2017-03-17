package app.controller.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;


public class FileReader {

    HashMap<String, Integer> list;

    public FileReader() throws IOException {

         list = new HashMap<>();

        java.io.FileReader fr = new java.io.FileReader("csvFile/WordValues.csv");
        BufferedReader buf = new BufferedReader(fr);

        String line;
        int count = 0;
        while ((line = buf.readLine()) != null) {

            String str = line.split(";")[0];        // delimiter
            int val = Integer.parseInt((line.split(";")[1]));
            list.put(str, val);
            count++;
        }
    }

        public HashMap<String, Integer> getMap(){
        return list;
    }
}
