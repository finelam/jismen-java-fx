package jismen.utils_bundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import jismen.MainApp;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Finelam on 24/04/2016.
 */
public class Utils {

    public static AnchorPane generateView(String viewName){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(viewName + ".fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            return pane;
        } catch (IOException e) {
            System.out.println("test");
            return null;
        }
    }

    public static boolean dateIsValid(String date){
        Pattern pattern = Pattern.compile("^[0-3]\\d\\/[0-1]\\d\\/\\d{4}$");
        Matcher m = pattern.matcher(date);
        return m.matches();
    }

}
