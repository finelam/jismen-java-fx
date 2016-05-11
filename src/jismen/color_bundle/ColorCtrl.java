package jismen.color_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import jismen.utils_bundle.RestClient;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Finelam on 24/04/2016.
 */
public class ColorCtrl {
    @FXML private TableView<Color> colorTable;
    @FXML private TableColumn<Color, String> nameColumn;
    @FXML private Label nameLabel;
    @FXML private Label errorLabel;

    private ColorMngr manager;

    public ColorCtrl(){}

    public void setManager(ColorMngr manager){
        this.manager = manager;
        colorTable.setItems(manager.getColorList());
    }

    @FXML private void initialize(){
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        showDetails(null);
        colorTable.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> showDetails(newvalue));
    }

    private void showDetails(Color color){
        errorLabel.setText("");
        if (color != null) {
            nameLabel.setText(color.getName());
        } else {
            nameLabel.setText("");
        }
    }

    @FXML private void handleNew(){
        Color tempColor = new Color();
        boolean okClicked = manager.showDialog(tempColor);
        if (okClicked){
            HashMap<String, Object> params = new HashMap<>();
            params.put("name", tempColor.getName());

            JSONObject result = RestClient.post("java/new/color", params);
            if (result.getBoolean("success")){
                manager.getColorList().add(tempColor);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Création effectuée !");
                alert.setHeaderText(result.getString("message"));
                alert.showAndWait();
                manager.initColor();
            } else {
                errorLabel.setText("Une erreur est survenue");
            }
        }
    }

    @FXML private void handleEdit(){
        Color color = colorTable.getSelectionModel().getSelectedItem();
        if (color != null){
            boolean okClicked = manager.showDialog(color);
            if (okClicked) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(manager.getMainApp().getPrimaryStage());
                alert.setTitle("Confirmation");
                alert.setHeaderText("Souhaitez-vous enregistrer les modifications ?");
                if(alert.showAndWait().get() == ButtonType.OK){
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("name", color.getName());
                    JSONObject result = RestClient.put("java/" +  + color.getId() + "/color", params);
                    if (result.getBoolean("success")){
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.initOwner(manager.getMainApp().getPrimaryStage());
                        alert.setTitle("Modification effectuée !");
                        alert.setContentText(result.getString("message"));
                        alert.showAndWait();
                        showDetails(color);
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
        Color color = colorTable.getSelectionModel().getSelectedItem();
        if (color != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Souhaitez-vous supprimer cet entrée ?");
            if(alert.showAndWait().get() == ButtonType.OK){
                JSONObject result = RestClient.delete("java/" +  color.getId() + "/color");
                if (result.getBoolean("success")){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(manager.getMainApp().getPrimaryStage());
                    alert.setTitle("Suppression effectuée");
                    alert.setHeaderText(result.getString("message"));
                    alert.showAndWait();
                    manager.getColorList().remove(color);
                } else {
                    errorLabel.setText("Une erreur est survenue");
                }
            }
        } else {
            errorLabel.setText("Aucune entrée sélectionnée");
        }
    }
}
