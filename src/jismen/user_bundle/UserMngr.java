package jismen.user_bundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jismen.MainApp;
import jismen.utils_bundle.RestClient;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Finelam on 24/04/2016.
 */
public class UserMngr {

    private MainApp mainApp;
    private ObservableList<User> userList = FXCollections.observableArrayList();

    public UserMngr(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void getAllEntities(){
        try {
            userList = FXCollections.observableArrayList();
            JSONObject allEntities = RestClient.get("java/get_all_users", null);
            for (Object o : allEntities.getJSONArray("users")){
                JSONObject entity = (JSONObject) o;
                int id = entity.getInt("id");
                String username = entity.getString("username");
                String email = entity.getString("email");
                String firstname = entity.getString("firstname");
                String familyname = entity.getString("familyname");
                String address = entity.getString("address");
                String postalCode = entity.getString("postalCode");
                String city = entity.getString("city");
                String birthday = entity.getString("birthday");
                String lastLogin = entity.getString("lastLogin");
                int nbConnection = entity.getInt("nbConnection");
                boolean newsletter = entity.getBoolean("newsletter");
                boolean admin = entity.getBoolean("admin");
                userList.add(new User(id, username, email, "", firstname, familyname, address, postalCode, city, birthday, lastLogin, nbConnection, newsletter, admin));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public ObservableList<User> getUserList(){
        return userList;
    }

    public void initEntities(){
        getAllEntities();
        AnchorPane view = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UserMngr.class.getResource("viewEntity.fxml"));
            view = loader.load();
            UserCtrl controller = loader.getController();
            controller.setManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainApp.getLayoutMngr().changeView(view);
    }

    public boolean showDialog(User user){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UserMngr.class.getResource("viewDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Utilisateurs");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getMainApp().getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            UserEditCtrl controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(user);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
