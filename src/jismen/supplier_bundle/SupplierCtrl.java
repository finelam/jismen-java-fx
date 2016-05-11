package jismen.supplier_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import jismen.utils_bundle.RestClient;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Finelam on 24/04/2016.
 */
public class SupplierCtrl {
    @FXML private TableView<Supplier> entityTable;
    @FXML private TableColumn<Supplier, String> nameColumn;
    @FXML private Label nameLabel;
    @FXML private Label addressLabel;
    @FXML private Label postalCodeLabel;
    @FXML private Label cityLabel;
    @FXML private Label contractLabel;
    @FXML private Label errorLabel;

    private SupplierMngr manager;

    public SupplierCtrl(){}

    public void setManager(SupplierMngr manager){
        this.manager = manager;
        entityTable.setItems(manager.getSupplierList());
    }

    @FXML private void initialize(){
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        showDetails(null);
        entityTable.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> showDetails(newvalue));
    }

    private void showDetails(Supplier supplier){
        errorLabel.setText("");
        if (supplier != null) {
            nameLabel.setText(supplier.getName());
            addressLabel.setText(supplier.getAddress());
            postalCodeLabel.setText(supplier.getPostalCode());
            cityLabel.setText(supplier.getCity());
            contractLabel.setText(supplier.getContract());
        } else {
            nameLabel.setText("");
            addressLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            contractLabel.setText("");
        }
    }

    @FXML private void handleNew(){
        Supplier tempSupplier = new Supplier();
        boolean okClicked = manager.showDialog(tempSupplier);
        if (okClicked){
            HashMap<String, Object> params = new HashMap<>();
            params.put("name", tempSupplier.getName());
            params.put("address", tempSupplier.getAddress());
            params.put("postalCode", tempSupplier.getPostalCode());
            params.put("city", tempSupplier.getCity());
            params.put("contract", tempSupplier.getContract());

            JSONObject result = RestClient.post("java/new/supplier", params);
            if (result.getBoolean("success")){
                manager.getSupplierList().add(tempSupplier);
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
        Supplier supplier = entityTable.getSelectionModel().getSelectedItem();
        if (supplier != null){
            boolean okClicked = manager.showDialog(supplier);
            if (okClicked) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(manager.getMainApp().getPrimaryStage());
                alert.setTitle("Confirmation");
                alert.setHeaderText("Souhaitez-vous enregistrer les modifications ?");
                if(alert.showAndWait().get() == ButtonType.OK){
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("name", supplier.getName());
                    params.put("address", supplier.getAddress());
                    params.put("postalCode", supplier.getPostalCode());
                    params.put("city", supplier.getCity());
                    params.put("contract", supplier.getContract());
                    JSONObject result = RestClient.put("java/" +  + supplier.getId() + "/supplier", params);
                    if (result.getBoolean("success")){
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.initOwner(manager.getMainApp().getPrimaryStage());
                        alert.setTitle("Modification effectuée !");
                        alert.setContentText(result.getString("message"));
                        alert.showAndWait();
                        showDetails(supplier);
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
        Supplier supplier = entityTable.getSelectionModel().getSelectedItem();
        if (supplier != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Souhaitez-vous supprimer cet entrée ?");
            if(alert.showAndWait().get() == ButtonType.OK){
                JSONObject result = RestClient.delete("java/" +  supplier.getId() + "/supplier");
                if (result.getBoolean("success")){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(manager.getMainApp().getPrimaryStage());
                    alert.setTitle("Suppression effectuée");
                    alert.setHeaderText(result.getString("message"));
                    alert.showAndWait();
                    manager.getSupplierList().remove(supplier);
                } else {
                    errorLabel.setText("Une erreur est survenue");
                }
            }
        } else {
            errorLabel.setText("Aucune entrée sélectionnée");
        }
    }
}
