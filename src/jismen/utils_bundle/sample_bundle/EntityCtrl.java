package jismen.utils_bundle.sample_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import jismen.utils_bundle.RestClient;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Finelam on 24/04/2016.
 */
public class EntityCtrl {
    @FXML private TableView<Entity> entityTable;
    @FXML private TableColumn<Entity, Number> rateColumn;
    @FXML private Label rateLabel;
    @FXML private Label errorLabel;

    private EntityMngr manager;

    public EntityCtrl(){}

    public void setManager(EntityMngr manager){
        this.manager = manager;
        entityTable.setItems(manager.getEntityList());
    }

    @FXML private void initialize(){
        rateColumn.setCellValueFactory(cellData -> cellData.getValue().rateProperty());
        showDetails(null);
        entityTable.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> showDetails(newvalue));
    }

    private void showDetails(Entity entity){
        errorLabel.setText("");
        if (entity != null) {
            rateLabel.setText("" + entity.getRate());
        } else {
            rateLabel.setText("");
        }
    }

    @FXML private void handleNew(){
        Entity tempEntity = new Entity();
        boolean okClicked = manager.showDialog(tempEntity);
        if (okClicked){
            HashMap<String, Object> params = new HashMap<>();
            params.put("rate", tempEntity.getRate());

            JSONObject result = RestClient.post("java/new/tva", params);
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
        Entity entity = entityTable.getSelectionModel().getSelectedItem();
        if (entity != null){
            boolean okClicked = manager.showDialog(entity);
            if (okClicked) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(manager.getMainApp().getPrimaryStage());
                alert.setTitle("Confirmation");
                alert.setHeaderText("Souhaitez-vous enregistrer les modifications ?");
                if(alert.showAndWait().get() == ButtonType.OK){
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("rate", entity.getRate());
                    JSONObject result = RestClient.put("java/" +  + entity.getId() + "/tva", params);
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
        Entity entity = entityTable.getSelectionModel().getSelectedItem();
        if (entity != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Souhaitez-vous supprimer cet entrée ?");
            if(alert.showAndWait().get() == ButtonType.OK){
                JSONObject result = RestClient.delete("java/" +  entity.getId() + "/tva");
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
