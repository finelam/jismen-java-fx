package jismen.tva_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import jismen.utils_bundle.RestClient;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Finelam on 24/04/2016.
 */
public class TvaCtrl {
    @FXML private TableView<Tva> entityTable;
    @FXML private TableColumn<Tva, Number> rateColumn;
    @FXML private Label rateLabel;
    @FXML private Label errorLabel;

    private TvaMngr manager;

    public TvaCtrl(){}

    public void setManager(TvaMngr manager){
        this.manager = manager;
        entityTable.setItems(manager.getTvaList());
    }

    @FXML private void initialize(){
        rateColumn.setCellValueFactory(cellData -> cellData.getValue().rateProperty());
        showDetails(null);
        entityTable.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> showDetails(newvalue));
    }

    private void showDetails(Tva tva){
        errorLabel.setText("");
        if (tva != null) {
            rateLabel.setText("" + tva.getRate());
        } else {
            rateLabel.setText("");
        }
    }

    @FXML private void handleNew(){
        Tva tempTva = new Tva();
        boolean okClicked = manager.showDialog(tempTva);
        if (okClicked){
            HashMap<String, Object> params = new HashMap<>();
            params.put("rate", tempTva.getRate());

            JSONObject result = RestClient.post("java/new/tva", params);
            if (result.getBoolean("success")){
                manager.getTvaList().add(tempTva);
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
        Tva tva = entityTable.getSelectionModel().getSelectedItem();
        if (tva != null){
            boolean okClicked = manager.showDialog(tva);
            if (okClicked) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(manager.getMainApp().getPrimaryStage());
                alert.setTitle("Confirmation");
                alert.setHeaderText("Souhaitez-vous enregistrer les modifications ?");
                if(alert.showAndWait().get() == ButtonType.OK){
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("rate", tva.getRate());
                    JSONObject result = RestClient.put("java/" +  + tva.getId() + "/tva", params);
                    if (result.getBoolean("success")){
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.initOwner(manager.getMainApp().getPrimaryStage());
                        alert.setTitle("Modification effectuée !");
                        alert.setContentText(result.getString("message"));
                        alert.showAndWait();
                        showDetails(tva);
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
        Tva tva = entityTable.getSelectionModel().getSelectedItem();
        if (tva != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Souhaitez-vous supprimer cet entrée ?");
            if(alert.showAndWait().get() == ButtonType.OK){
                JSONObject result = RestClient.delete("java/" +  tva.getId() + "/tva");
                if (result.getBoolean("success")){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(manager.getMainApp().getPrimaryStage());
                    alert.setTitle("Suppression effectuée");
                    alert.setHeaderText(result.getString("message"));
                    alert.showAndWait();
                    manager.getTvaList().remove(tva);
                } else {
                    errorLabel.setText("Une erreur est survenue");
                }
            }
        } else {
            errorLabel.setText("Aucune entrée sélectionnée");
        }
    }
}
