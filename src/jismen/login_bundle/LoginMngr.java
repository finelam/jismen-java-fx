package jismen.login_bundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jismen.MainApp;

import java.io.IOException;

/**
 * Created by Finelam on 24/04/2016.
 */
public class LoginMngr {
    private MainApp mainApp;
    private Stage loginStage;

    public LoginMngr(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void initLogin(){
        try {
            loginStage = new Stage();
            loginStage.setTitle("Connexion");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("login_bundle/Login.fxml"));
            AnchorPane login = loader.load();
            Scene scene = new Scene(login);
            LoginCtrl loginCtrl = loader.getController();
            loginCtrl.setManager(this);
            loginStage.setScene(scene);
            loginStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeLogin(){
        loginStage.close();
    }

    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
