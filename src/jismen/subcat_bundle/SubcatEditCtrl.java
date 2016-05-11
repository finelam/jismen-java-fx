package jismen.subcat_bundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jismen.category_bundle.Category;
import jismen.utils_bundle.RestClient;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by Finelam on 25/04/2016.
 */
public class SubcatEditCtrl {

    @FXML private TextField nameTf;
    @FXML private ComboBox<Category> catCmb;
    @FXML private CheckBox enableCbx;

    private Stage dialogStage;
    private Subcat subcat;
    private boolean okClicked;

    private ObservableList<Category> categoryList = FXCollections.observableArrayList();

    @FXML private void initialize(){
        try {
            categoryList = FXCollections.observableArrayList();
            JSONObject allCategories = RestClient.get("java/get_all_categories", null);
            for (Object o : allCategories.getJSONArray("categories")){
                JSONObject cat = (JSONObject) o;
                int id = cat.getInt("id");
                String name = cat.getString("name");
                boolean enabled = cat.getBoolean("enabled");
                catCmb.getItems().add(new Category(id, name, enabled));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        catCmb.setConverter(new StringConverter<Category>() {
            @Override
            public String toString(Category object) {
                if (object == null){
                    return "";
                } else {
                    return object.getName();
                }
            }

            @Override
            public Category fromString(String string) {
                return null;
            }
        });
    }

    @FXML private void handleOk(){
        if (isInputValid()){
            subcat.setName(nameTf.getText());
            subcat.setCategory((Category) catCmb.getSelectionModel().getSelectedItem());
            subcat.setEnabled(enableCbx.isSelected());

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
        if (catCmb.getSelectionModel().getSelectedItem() == null){
            error += "Vous devez sélectioner une catégorie\n";
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

    public SubcatEditCtrl() {
    }

    public SubcatEditCtrl(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public TextField getNameTf() {
        return nameTf;
    }

    public void setNameTf(TextField nameTf) {
        this.nameTf = nameTf;
    }

    public ComboBox getCatCmb() {
        return catCmb;
    }

    public void setCatCmb(ComboBox catCmb) {
        this.catCmb = catCmb;
    }

    public CheckBox getEnableCbx() {
        return enableCbx;
    }

    public void setEnableCbx(CheckBox enableCbx) {
        this.enableCbx = enableCbx;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Subcat getSubcat() {
        return subcat;
    }

    public void setSubcat(Subcat subcat) {
        this.subcat = subcat;
        Category cat = subcat.getCategory();

        nameTf.setText("" + subcat.getName());
        if (cat != null){
            catCmb.getSelectionModel().select(cat);
        }
        enableCbx.setSelected(subcat.isEnabled());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setOkClicked(boolean okClicked) {
        this.okClicked = okClicked;
    }
}
