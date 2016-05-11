package jismen.category_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import jismen.utils_bundle.RestClient;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Finelam on 24/04/2016.
 */
public class CategoryCtrl {
    @FXML private TableView<Category> categoryTable;
    @FXML private TableColumn<Category, String> nameColumn;
    @FXML private Label nameLabel;
    @FXML private CheckBox enabledCbx;
    @FXML private Label errorLabel;

    private CategoryMngr manager;

    public CategoryCtrl(){}

    public void setManager(CategoryMngr manager){
        this.manager = manager;
        categoryTable.setItems(manager.getCategoryList());
    }

    @FXML private void initialize(){
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        showDetails(null);
        categoryTable.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> showDetails(newvalue));
    }

    private void showDetails(Category cat){
        errorLabel.setText("");
        if (cat != null) {
            nameLabel.setText(cat.getName());
            enabledCbx.setSelected(cat.isEnabled());
        } else {
            nameLabel.setText("");
            enabledCbx.setSelected(false);
        }
    }

    @FXML private void handleNew(){
        Category tempCategory = new Category();
        boolean okClicked = manager.showDialog(tempCategory);
        if (okClicked){
            HashMap<String, Object> params = new HashMap<>();
            params.put("name", tempCategory.getName());
            params.put("enabled", tempCategory.isEnabled() ? 1 : 0);

            JSONObject result = RestClient.post("java/new/category", params);
            if (result.getBoolean("success")){
                manager.getCategoryList().add(tempCategory);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Création effectuée !");
                alert.setHeaderText(result.getString("message"));
                alert.showAndWait();
                manager.initCategory();
            } else {
                errorLabel.setText("Une erreur est survenue");
            }
        }
    }

    @FXML private void handleEdit(){
        Category category = categoryTable.getSelectionModel().getSelectedItem();
        if (category != null){
            boolean okClicked = manager.showDialog(category);
            if (okClicked) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(manager.getMainApp().getPrimaryStage());
                alert.setTitle("Confirmation");
                alert.setHeaderText("Souhaitez-vous enregistrer les modifications ?");
                if(alert.showAndWait().get() == ButtonType.OK){
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("name", category.getName());
                    params.put("enabled", category.isEnabled() ? 1 : 0);
                    JSONObject result = RestClient.put("java/" +  + category.getId() + "/category", params);
                    if (result.getBoolean("success")){
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.initOwner(manager.getMainApp().getPrimaryStage());
                        alert.setTitle("Modification effectuée !");
                        alert.setContentText(result.getString("message"));
                        alert.showAndWait();
                        showDetails(category);
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
        Category category = categoryTable.getSelectionModel().getSelectedItem();
        if (category != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Souhaitez-vous supprimer cet entrée ?");
            if(alert.showAndWait().get() == ButtonType.OK){
                JSONObject result = RestClient.delete("java/" +  category.getId() + "/category");
                if (result.getBoolean("success")){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(manager.getMainApp().getPrimaryStage());
                    alert.setTitle("Suppression effectuée");
                    alert.setHeaderText(result.getString("message"));
                    alert.showAndWait();
                    manager.getCategoryList().remove(category);
                } else {
                    errorLabel.setText("Une erreur est survenue");
                }
            }
        } else {
            errorLabel.setText("Aucune entrée sélectionnée");
        }
    }
}
