package jismen.size_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jismen.color_bundle.Color;

/**
 * Created by Finelam on 25/04/2016.
 */
public class SizeEditCtrl {

    @FXML private TextField nameTf;

    private Stage dialogStage;
    private Size entity;
    private boolean okClicked;

    @FXML private void initialize(){}

    @FXML private void handleOk(){
        if (isInputValid()){
            entity.setName(nameTf.getText());

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

    public SizeEditCtrl() {
    }

    public SizeEditCtrl(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public TextField getNameTf() {
        return nameTf;
    }

    public void setNameTf(TextField nameTf) {
        this.nameTf = nameTf;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Size getEntity() {
        return entity;
    }

    public void setEntity(Size entity) {
        this.entity = entity;

        nameTf.setText(entity.getName());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setOkClicked(boolean okClicked) {
        this.okClicked = okClicked;
    }
}
