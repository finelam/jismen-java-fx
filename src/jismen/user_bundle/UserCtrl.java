package jismen.user_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import jismen.utils_bundle.RestClient;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Finelam on 24/04/2016.
 */
public class UserCtrl {
    @FXML private TableView<User> entityTable;
    @FXML private TableColumn<User, String> familynameColumn;
    @FXML private TableColumn<User, String> firstnameColumn;
    @FXML private Label usernameLabel;
    @FXML private Label emailLabel;
    @FXML private Label firstnameLabel;
    @FXML private Label familynameLabel;
    @FXML private Label addressLabel;
    @FXML private Label postalCodeLabel;
    @FXML private Label cityLabel;
    @FXML private Label birthdayLabel;
    @FXML private Label lastLoginLabel;
    @FXML private Label nbConnectionLabel;
    @FXML private CheckBox newsletterCbx;
    @FXML private CheckBox adminCbx;
    @FXML private Label errorLabel;

    private UserMngr manager;

    public UserCtrl(){}

    public void setManager(UserMngr manager){
        this.manager = manager;
        entityTable.setItems(manager.getUserList());
    }

    @FXML private void initialize(){
        showDetails(null);
        familynameColumn.setCellValueFactory(cellData -> cellData.getValue().familynameProperty());
        firstnameColumn.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());
        entityTable.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> showDetails(newvalue));
    }

    private void showDetails(User user){
        errorLabel.setText("");
        if (user != null) {
            usernameLabel.setText(user.getUsername());
            emailLabel.setText(user.getEmail());
            firstnameLabel.setText(user.getFirstname());
            familynameLabel.setText(user.getFamilyname());
            addressLabel.setText(user.getAddress());
            postalCodeLabel.setText(user.getPostalCode());
            cityLabel.setText(user.getCity());
            birthdayLabel.setText(user.getBirthday());
            lastLoginLabel.setText(user.getLastLogin());
            nbConnectionLabel.setText("" + user.getNbConnection());
            newsletterCbx.setSelected(user.isNewsletter());
            adminCbx.setSelected(user.isAdmin());
        } else {
            usernameLabel.setText("");
            emailLabel.setText("");
            firstnameLabel.setText("");
            familynameLabel.setText("");
            addressLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
            lastLoginLabel.setText("");
            nbConnectionLabel.setText("");
            newsletterCbx.setSelected(false);
            adminCbx.setSelected(false);
        }
    }

    @FXML private void handleNew(){
        User tempUser = new User();
        boolean okClicked = manager.showDialog(tempUser);
        if (okClicked){
            HashMap<String, Object> params = new HashMap<>();
            params.put("username", tempUser.getUsername());
            params.put("email", tempUser.getEmail());
            params.put("password", tempUser.getPassword());
            params.put("firstname", tempUser.getFirstname());
            params.put("familyname", tempUser.getFamilyname());
            params.put("address", tempUser.getAddress());
            params.put("postalCode", tempUser.getPostalCode());
            params.put("city", tempUser.getCity());
            params.put("birthday", tempUser.getBirthday());
            params.put("newsletter", tempUser.isNewsletter());
            params.put("admin", tempUser.isAdmin());

            JSONObject result = RestClient.post("java/new/user", params);
            if (result.getBoolean("success")){
                manager.getUserList().add(tempUser);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Création effectuée !");
                alert.setHeaderText(result.getString("message"));
                alert.showAndWait();
                manager.initEntities();
            } else {
                errorLabel.setText("Une erreur est survenue");
            }
        }
    }

    @FXML private void handleEdit(){
        User user = entityTable.getSelectionModel().getSelectedItem();
        if (user != null){
            boolean okClicked = manager.showDialog(user);
            if (okClicked) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(manager.getMainApp().getPrimaryStage());
                alert.setTitle("Confirmation");
                alert.setHeaderText("Souhaitez-vous enregistrer les modifications ?");
                if(alert.showAndWait().get() == ButtonType.OK){
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("username", user.getUsername());
                    params.put("email", user.getEmail());
                    params.put("password", user.getPassword());
                    params.put("firstname", user.getFirstname());
                    params.put("familyname", user.getFamilyname());
                    params.put("address", user.getAddress());
                    params.put("postalCode", user.getPostalCode());
                    params.put("city", user.getCity());
                    params.put("birthday", user.getBirthday());
                    params.put("newsletter", user.isNewsletter() ? 1 : 0);
                    params.put("admin", user.isAdmin());
                    JSONObject result = RestClient.put("java/" +  + user.getId() + "/user", params);
                    if (result.getBoolean("success")){
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.initOwner(manager.getMainApp().getPrimaryStage());
                        alert.setTitle("Modification effectuée !");
                        alert.setContentText(result.getString("message"));
                        alert.showAndWait();
                        showDetails(user);
                    } else {
                        errorLabel.setText("Une erreur est survenue");
                    }
                }
            }
        } else {
            errorLabel.setText("Aucune entrée sélectionnée");
        }
    }

    @FXML private void handleDelete(){
        User user = entityTable.getSelectionModel().getSelectedItem();
        if (user != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Souhaitez-vous supprimer cet entrée ?");
            if(alert.showAndWait().get() == ButtonType.OK){
                JSONObject result = RestClient.delete("java/" +  user.getId() + "/user");
                if (result.getBoolean("success")){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(manager.getMainApp().getPrimaryStage());
                    alert.setTitle("Suppression effectuée");
                    alert.setHeaderText(result.getString("message"));
                    alert.showAndWait();
                    manager.getUserList().remove(user);
                } else {
                    errorLabel.setText("Une erreur est survenue");
                }
            }
        } else {
            errorLabel.setText("Aucune entrée sélectionnée");
        }
    }
}
