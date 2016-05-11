package jismen.supplier_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Finelam on 25/04/2016.
 */
public class SupplierEditCtrl {

    @FXML private TextField nameTf;
    @FXML private TextField addressTf;
    @FXML private TextField postalCodeTf;
    @FXML private TextField cityTf;
    @FXML private TextField contractTf;

    private Stage dialogStage;
    private Supplier supplier;
    private boolean okClicked;

    @FXML private void initialize(){}

    @FXML private void handleOk(){
        if (isInputValid()){
            supplier.setName(nameTf.getText());
            supplier.setAddress(addressTf.getText());
            supplier.setPostalCode(postalCodeTf.getText());
            supplier.setCity(cityTf.getText());
            supplier.setContract(contractTf.getText());

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
            error += "Nom invalide\n";
        }
        if (addressTf.getText() == null || addressTf.getText().length() == 0){
            error += "Adresse invalide\n";
        }
        if (postalCodeTf.getText() == null || postalCodeTf.getText().length() == 0){
            error += "Code postal invalide\n";
        }
        if (cityTf.getText() == null || cityTf.getText().length() == 0){
            error += "Ville invalide\n";
        }
        if (contractTf.getText() == null || contractTf.getText().length() == 0){
            error += "Contrat invalide\n";
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

    public SupplierEditCtrl() {
    }

    public SupplierEditCtrl(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public TextField getNameTf() {
        return nameTf;
    }

    public void setNameTf(TextField nameTf) {
        this.nameTf = nameTf;
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

    public TextField getContractTf() {
        return contractTf;
    }

    public void setContractTf(TextField contractTf) {
        this.contractTf = contractTf;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;

        nameTf.setText(supplier.getName());
        addressTf.setText(supplier.getAddress());
        postalCodeTf.setText(supplier.getPostalCode());
        cityTf.setText(supplier.getCity());
        contractTf.setText(supplier.getContract());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setOkClicked(boolean okClicked) {
        this.okClicked = okClicked;
    }
}
