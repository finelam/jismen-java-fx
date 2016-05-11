package jismen.subcat_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import jismen.utils_bundle.RestClient;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Finelam on 24/04/2016.
 */
public class SubcatCtrl {
    @FXML private TableView<Subcat> entityTable;
    @FXML private TableColumn<Subcat, String> nameColumn;
    @FXML private Label nameLabel;
    @FXML private Label categoryLabel;
    @FXML private CheckBox enabledCbx;
    @FXML private Label errorLabel;

    private SubcatMngr manager;

    public SubcatCtrl(){}

    public void setManager(SubcatMngr manager){
        this.manager = manager;
        entityTable.setItems(manager.getSubcatList());
    }

    @FXML private void initialize(){
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        showDetails(null);
        entityTable.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> showDetails(newvalue));
    }

    private void showDetails(Subcat subcat){
        errorLabel.setText("");
        if (subcat != null) {
            nameLabel.setText("" + subcat.getName());
            categoryLabel.setText(subcat.getCategory().getName());
            enabledCbx.setSelected(subcat.isEnabled());
        } else {
            nameLabel.setText("");
            categoryLabel.setText("");
            enabledCbx.setSelected(false);
        }
    }

    @FXML private void handleNew(){
        Subcat tempSubcat = new Subcat();
        boolean okClicked = manager.showDialog(tempSubcat);
        if (okClicked){
            HashMap<String, Object> params = new HashMap<>();
            params.put("name", tempSubcat.getName());
            params.put("category", tempSubcat.getCategory().getId());
            params.put("enabled", tempSubcat.isEnabled());

            JSONObject result = RestClient.post("java/new/subcat", params);
            if (result.getBoolean("success")){
                manager.getSubcatList().add(tempSubcat);
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
        Subcat subcat = entityTable.getSelectionModel().getSelectedItem();
        if (subcat != null){
            boolean okClicked = manager.showDialog(subcat);
            if (okClicked) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(manager.getMainApp().getPrimaryStage());
                alert.setTitle("Confirmation");
                alert.setHeaderText("Souhaitez-vous enregistrer les modifications ?");
                if(alert.showAndWait().get() == ButtonType.OK){
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("name", subcat.getName());
                    params.put("category", subcat.getCategory().getId());
                    params.put("enabled", subcat.isEnabled() ? 1 : 0);
                    JSONObject result = RestClient.put("java/" +  + subcat.getId() + "/subcat", params);
                    if (result.getBoolean("success")){
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.initOwner(manager.getMainApp().getPrimaryStage());
                        alert.setTitle("Modification effectuée !");
                        alert.setContentText(result.getString("message"));
                        alert.showAndWait();
                        showDetails(subcat);
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
        Subcat subcat = entityTable.getSelectionModel().getSelectedItem();
        if (subcat != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Souhaitez-vous supprimer cet entrée ?");
            if(alert.showAndWait().get() == ButtonType.OK){
                JSONObject result = RestClient.delete("java/" +  subcat.getId() + "/subcat");
                if (result.getBoolean("success")){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(manager.getMainApp().getPrimaryStage());
                    alert.setTitle("Suppression effectuée");
                    alert.setHeaderText(result.getString("message"));
                    alert.showAndWait();
                    manager.getSubcatList().remove(subcat);
                } else {
                    errorLabel.setText("Une erreur est survenue");
                }
            }
        } else {
            errorLabel.setText("Aucune entrée sélectionnée");
        }
    }
}
