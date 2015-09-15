package edu.wpi.cs4341.ga;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

public class ParseFile {

    List <int[]> gridWeights = new ArrayList<>();

    public  List<String> getFromFile(String fileName) {
        URL location = getClass().getResource(fileName);
        try {
            return Files.readAllLines(Paths.get(location.toURI()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }



}
