package jismen.stat_bundle.stat_user_bundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import jismen.MainApp;
import jismen.user_bundle.User;

import java.io.IOException;

/**
 * Created by Finelam on 23/05/2016.
 */
public class StatUserMngr {

    private MainApp mainApp;
    private ObservableList<User> userList = FXCollections.observableArrayList();

    public StatUserMngr(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void initView(){
        userList = mainApp.getUserMngr().getUserList();
        AnchorPane view = null;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StatUserMngr.class.getResource("StatUserView.fxml"));
            view = loader.load();
            StatUserCtrl controller = loader.getController();
            controller.setManager(this);
            controller.showData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainApp.getLayoutMngr().changeView(view);
    }

    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public ObservableList<User> getUserList() {
        return userList;
    }

    public void setUserList(ObservableList<User> userList) {
        this.userList = userList;
    }
}
