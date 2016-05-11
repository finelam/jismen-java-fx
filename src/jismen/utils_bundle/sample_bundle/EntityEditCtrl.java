package jismen.utils_bundle.sample_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Finelam on 25/04/2016.
 */
public class EntityEditCtrl {

    @FXML private TextField rateTf;

    private Stage dialogStage;
    private Entity entity;
    private boolean okClicked;

    @FXML private void initialize(){}

    @FXML private void handleOk(){
        if (isInputValid()){
            entity.setRate(Double.valueOf(rateTf.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML private void handleCancel(){
        dialogStage.close();
    }

    private boolean isInputValid(){
        String error = "";
        if (rateTf.getText() == null || rateTf.getText().length() == 0){
            error += "Taux invalide\n";
        } else {
            try {
                Double.parseDouble(rateTf.getText());
            } catch (NumberFormatException e) {
                error += "Le format du taux n'est pas valide";
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

    public EntityEditCtrl() {
    }

    public EntityEditCtrl(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public TextField getRateTf() {
        return rateTf;
    }

    public void setRateTf(TextField nameTf) {
        this.rateTf = nameTf;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;

        rateTf.setText("" + entity.getRate());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setOkClicked(boolean okClicked) {
        this.okClicked = okClicked;
    }
}
