package jismen.size_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import jismen.utils_bundle.RestClient;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Finelam on 24/04/2016.
 */
public class SizeCtrl {
    @FXML private TableView<Size> entityTable;
    @FXML private TableColumn<Size, String> nameColumn;
    @FXML private Label nameLabel;
    @FXML private Label errorLabel;

    private SizeMngr manager;

    public SizeCtrl(){}

    public void setManager(SizeMngr manager){
        this.manager = manager;
        entityTable.setItems(manager.getEntityList());
    }

    @FXML private void initialize(){
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        showDetails(null);
        entityTable.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> showDetails(newvalue));
    }

    private void showDetails(Size entity){
        errorLabel.setText("");
        if (entity != null) {
            nameLabel.setText(entity.getName());
        } else {
            nameLabel.setText("");
        }
    }

    @FXML private void handleNew(){
        Size tempEntity = new Size();
        boolean okClicked = manager.showDialog(tempEntity);
        if (okClicked){
            HashMap<String, Object> params = new HashMap<>();
            params.put("name", tempEntity.getName());

            JSONObject result = RestClient.post("java/new/size", params);
            if (result.getBoolean("success")){
                manager.getEntityList().add(tempEntity);
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
        Size entity = entityTable.getSelectionModel().getSelectedItem();
        if (entity != null){
            boolean okClicked = manager.showDialog(entity);
            if (okClicked) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(manager.getMainApp().getPrimaryStage());
                alert.setTitle("Confirmation");
                alert.setHeaderText("Souhaitez-vous enregistrer les modifications ?");
                if(alert.showAndWait().get() == ButtonType.OK){
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("name", entity.getName());
                    JSONObject result = RestClient.put("java/" +  + entity.getId() + "/size", params);
                    if (result.getBoolean("success")){
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.initOwner(manager.getMainApp().getPrimaryStage());
                        alert.setTitle("Modification effectuée !");
                        alert.setContentText(result.getString("message"));
                        alert.showAndWait();
                        showDetails(entity);
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
        Size entity = entityTable.getSelectionModel().getSelectedItem();
        if (entity != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Souhaitez-vous supprimer cet entrée ?");
            if(alert.showAndWait().get() == ButtonType.OK){
                JSONObject result = RestClient.delete("java/" +  entity.getId() + "/size");
                if (result.getBoolean("success")){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(manager.getMainApp().getPrimaryStage());
                    alert.setTitle("Suppression effectuée");
                    alert.setHeaderText(result.getString("message"));
                    alert.showAndWait();
                    manager.getEntityList().remove(entity);
                } else {
                    errorLabel.setText("Une erreur est survenue");
                }
            }
        } else {
            errorLabel.setText("Aucune entrée sélectionnée");
        }
    }
}
