package jismen.login_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import jismen.utils_bundle.RestClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Finelam on 24/04/2016.
 */
public class LoginCtrl {
    private LoginMngr manager;
    @FXML private TextField idTF;
    @FXML private PasswordField passPF;
    @FXML private Button loginButton;
    @FXML private Label errorLabel;

    @FXML private void handleLogin(){
        String login = idTF.getText();
        boolean loginIsValid = (login != null && login.length()>0);
        String pass = passPF.getText();
        boolean passIsValid = (pass != null && pass.length()>0);

        String message = "";
        if (loginIsValid && passIsValid){
            HashMap<String, Object> credentials = new HashMap<>();
            credentials.put("username", login);
            credentials.put("password", pass);
            JSONObject result = RestClient.post("check_pass", credentials);

            if (result.getBoolean("success")){
                JSONObject user = new JSONObject(result.getJSONObject("user").toString());
                if (user.getBoolean("isAdmin")){
                    manager.closeLogin();
                    manager.getMainApp().getLayoutMngr().initLayout();
                } else {
                    message += "Droits insuffisants...";
                }
            } else {
                message += result.getString("message");
            }
        } else {
            message += loginIsValid ? "" : "Identifiant invalide\n";
            message += passIsValid ? "" : "Mot de passe invalide\n";
        }
        errorLabel.setText(message);
    }

    @FXML private void initialize(){}

    public void setManager(LoginMngr manager){
        this.manager = manager;
    }
}
