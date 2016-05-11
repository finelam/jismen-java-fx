package jismen.user_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import jismen.utils_bundle.Utils;

/**
 * Created by Finelam on 25/04/2016.
 */
public class UserEditCtrl {

    @FXML private TextField usernameTf;
    @FXML private CheckBox passwordChangeCbx;
    @FXML private PasswordField passwordPf;
    @FXML private TextField emailTf;
    @FXML private TextField firstnameTf;
    @FXML private TextField familynameTf;
    @FXML private TextField addressTf;
    @FXML private TextField postalCodeTf;
    @FXML private TextField cityTf;
    @FXML private TextField birthdayTf;
    @FXML private CheckBox newsletterCbx;
    @FXML private CheckBox adminCbx;

    private Stage dialogStage;
    private User user;
    private boolean okClicked;

    @FXML private void initialize(){}

    @FXML private void handleOk(){
        if (isInputValid()){
            user.setUsername(usernameTf.getText());
            user.setPassword(passwordChangeCbx.isSelected() ? passwordPf.getText() : "");
            user.setEmail(emailTf.getText());
            user.setFirstname(firstnameTf.getText());
            user.setFamilyname(familynameTf.getText());
            user.setAddress(addressTf.getText());
            user.setPostalCode(postalCodeTf.getText());
            user.setCity(cityTf.getText());
            user.setBirthday(birthdayTf.getText());
            user.setNewsletter(newsletterCbx.isSelected());
            user.setAdmin(adminCbx.isSelected());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML private void handleCancel(){
        dialogStage.close();
    }

    private boolean isInputValid(){
        String error = "";
        if (usernameTf.getText() == null || usernameTf.getText().length() == 0){
            error += "Le nom d'utilisateur est incorrect\n";
        }
        if (passwordChangeCbx.isSelected() && (passwordPf.getText() == null || passwordPf.getText().length() == 0)){
            error += "Le mot de passe est incorrect\n";
        }
        if (emailTf.getText() == null || emailTf.getText().length() == 0){
            error += "L'addresse mail est incorrect\n";
        }
        if (firstnameTf.getText() == null || firstnameTf.getText().length() == 0){
            error += "Le prénom est incorrect\n";
        }
        if (familynameTf.getText() == null || familynameTf.getText().length() == 0){
            error += "Le nom est incorrect\n";
        }
        if (addressTf.getText() == null || addressTf.getText().length() == 0){
            error += "L'adresse est incorrecte\n";
        }
        if (postalCodeTf.getText() == null || postalCodeTf.getText().length() == 0){
            error += "Le code postal est incorrect\n";
        }
        if (cityTf.getText() == null || cityTf.getText().length() == 0){
            error += "La ville est incorrecte\n";
        }
        if (birthdayTf.getText() == null || birthdayTf.getText().length() == 0){
            error += "La date de naissance est incorrecte\n";
        } else {
            if (!Utils.dateIsValid(birthdayTf.getText())){
                error += "Le format de la date de naissance est incorrect : jj/mm/aaaa\n";
            }
        }

        if (error.length() == 0){
            return true;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Champ invalide");
        alert.setHeaderText("Veuillez corrigez les champs non valides");
        alert.setContentText(error);
        alert.showAndWait();
        return false;
    }

    public UserEditCtrl() {
    }

    public UserEditCtrl(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public TextField getUsernameTf() {
        return usernameTf;
    }

    public void setUsernameTf(TextField usernameTf) {
        this.usernameTf = usernameTf;
    }

    public PasswordField getPasswordPf() {
        return passwordPf;
    }

    public void setPasswordPf(PasswordField passwordPf) {
        this.passwordPf = passwordPf;
    }

    public TextField getEmailTf() {
        return emailTf;
    }

    public void setEmailTf(TextField emailTf) {
        this.emailTf = emailTf;
    }

    public TextField getFirstnameTf() {
        return firstnameTf;
    }

    public void setFirstnameTf(TextField firstnameTf) {
        this.firstnameTf = firstnameTf;
    }

    public TextField getFamilynameTf() {
        return familynameTf;
    }

    public void setFamilynameTf(TextField familynameTf) {
        this.familynameTf = familynameTf;
    }

    public TextField getAddressTf() {
        return addressTf;
    }

    public void setAddressTf(TextField addressTf) {
        this.addressTf = addressTf;
    }

    public TextField getPostalCodeTf() {
        return postalCodeTf;
    }

    public void setPostalCodeTf(TextField postalCodeTf) {
        this.postalCodeTf = postalCodeTf;
    }

    public TextField getCityTf() {
        return cityTf;
    }

    public void setCityTf(TextField cityTf) {
        this.cityTf = cityTf;
    }

    public TextField getBirthdayTf() {
        return birthdayTf;
    }

    public void setBirthdayTf(TextField birthdayTf) {
        this.birthdayTf = birthdayTf;
    }

    public CheckBox getNewsletterCbx() {
        return newsletterCbx;
    }

    public void setNewsletterCbx(CheckBox newsletterCbx) {
        this.newsletterCbx = newsletterCbx;
    }

    public CheckBox getAdminCbx() {
        return adminCbx;
    }

    public void setAdminCbx(CheckBox adminCbx) {
        this.adminCbx = adminCbx;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;

        usernameTf.setText(user.getUsername());
        passwordPf.setText("");
        emailTf.setText(user.getEmail());
        firstnameTf.setText(user.getFirstname());
        familynameTf.setText(user.getFamilyname());
        addressTf.setText(user.getAddress());
        postalCodeTf.setText(user.getPostalCode());
        cityTf.setText(user.getCity());
        birthdayTf.setText(user.getBirthday());
        newsletterCbx.setSelected(user.isNewsletter());
        adminCbx.setSelected(user.isAdmin());
        usernameTf.setText(user.getUsername());
        usernameTf.setText(user.getUsername());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setOkClicked(boolean okClicked) {
        this.okClicked = okClicked;
    }
}
