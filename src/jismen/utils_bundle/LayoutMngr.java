package jismen.utils_bundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jismen.MainApp;

import java.io.IOException;

/**
 * Created by Finelam on 24/04/2016.
 */
public class LayoutMngr {

    private MainApp mainApp;
    private BorderPane layout;

    public LayoutMngr(MainApp mainApp){
        this.mainApp= mainApp;
    }

    public void initLayout(){
        Stage primaryStage = mainApp.getPrimaryStage();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("utils_bundle/Layout.fxml"));
            layout = loader.load();
            Scene scene = new Scene(layout);
            LayoutCtrl controller = loader.getController();
            controller.setManager(this);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeView(AnchorPane view){
        layout.setCenter(view);
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public MainApp getMainApp(){
        return mainApp;
    }

    public BorderPane getLayout() { return layout; }

    public void setLayout(BorderPane layout) { this.layout = layout; }
}
