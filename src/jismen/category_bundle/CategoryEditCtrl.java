package jismen.category_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Finelam on 25/04/2016.
 */
public class CategoryEditCtrl {

    @FXML private TextField nameTf;
    @FXML private CheckBox enabledCbx;

    private Stage dialogStage;
    private Category category;
    private boolean okClicked;

    @FXML private void initialize(){}

    @FXML private void handleOk(){
        if (isInputValid()){
            category.setName(nameTf.getText());
            category.setEnabled(enabledCbx.isSelected());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML private void handleCancel(){
        dialogStage.close();
    }

    private boolean isInputValid(){
        String error = "";
        if (nameTf.getText() == null || nameTf.getText().length() == 0){
            error += "Nom invalid\n";
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

    public CategoryEditCtrl() {
    }

    public CategoryEditCtrl(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public TextField getNameTf() {
        return nameTf;
    }

    public void setNameTf(TextField nameTf) {
        this.nameTf = nameTf;
    }

    public CheckBox getEnabledCbx() {
        return enabledCbx;
    }

    public void setEnabledCbx(CheckBox enabledCbx) {
        this.enabledCbx = enabledCbx;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;

        nameTf.setText(category.getName());
        enabledCbx.setSelected(category.isEnabled());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setOkClicked(boolean okClicked) {
        this.okClicked = okClicked;
    }
}
