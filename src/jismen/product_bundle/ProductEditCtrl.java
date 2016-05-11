package jismen.product_bundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import jismen.category_bundle.Category;
import jismen.color_bundle.Color;
import jismen.size_bundle.Size;
import jismen.subcat_bundle.Subcat;
import jismen.supplier_bundle.Supplier;
import jismen.tva_bundle.Tva;
import jismen.utils_bundle.RestClient;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by Finelam on 25/04/2016.
 */
public class ProductEditCtrl {

    @FXML private TableView<Product> entityTable;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TextField nameTf;
    @FXML private TextField descriptionTf;
    @FXML private TextField priceHtTf;
    @FXML private TextField quantityTf;
    @FXML private TextField pathTf;
    @FXML private ComboBox<Supplier> supplierCmb;
    @FXML private ComboBox<Color> colorCmb;
    @FXML private ComboBox<Size> sizeCmb;
    @FXML private ComboBox<Tva> tvaCmb;
    @FXML private ComboBox<Subcat> subcatCmb;
    @FXML private CheckBox enabledCbx;
    @FXML private CheckBox newsCbx;
    @FXML private CheckBox vipCbx;
    @FXML private CheckBox promoCbx;

    private ObservableList<Subcat> subcatList;

    private Stage dialogStage;
    private Product product;
    private boolean okClicked;

    @FXML private void initialize(){
        try {
            subcatList = FXCollections.observableArrayList();
            JSONObject allEntities = RestClient.get("java/get_all_subcat", null);
            for (Object o : allEntities.getJSONArray("subcats")){
                JSONObject entity = (JSONObject) o;
                subcatList.add(new Subcat(entity));
            }
            subcatCmb.setItems(subcatList);

            ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
            allEntities = RestClient.get("java/get_all_suppliers", null);
            for (Object o : allEntities.getJSONArray("suppliers")) {
                JSONObject entity = (JSONObject) o;
                supplierList.add(new Supplier(entity));
            }
            supplierCmb.setItems(supplierList);

            ObservableList<Size> sizeList = FXCollections.observableArrayList();
            allEntities = RestClient.get("java/get_all_sizes", null);
            for (Object o : allEntities.getJSONArray("sizes")){
                JSONObject entity = (JSONObject) o;
                sizeList.add(new Size(entity));
            }
            sizeCmb.setItems(sizeList);

            ObservableList<Tva> tvaList = FXCollections.observableArrayList();
            allEntities = RestClient.get("java/get_all_tva", null);
            for (Object o : allEntities.getJSONArray("tva")){
                JSONObject entity = (JSONObject) o;
                tvaList.add(new Tva(entity));
            }
            tvaCmb.setItems(tvaList);

            ObservableList<Color> colorList = FXCollections.observableArrayList();
            JSONObject allColors = RestClient.get("java/get_all_colors", null);
            for (Object o : allColors.getJSONArray("colors")){
                JSONObject col = (JSONObject) o;
                colorList.add(new Color(col));
            }
            colorCmb.setItems(colorList);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        supplierCmb.setConverter(new StringConverter<Supplier>() {
            @Override
            public String toString(Supplier object) {
                if (object == null){
                    return "";
                } else {
                    return object.getName();
                }
            }

            @Override
            public Supplier fromString(String string) {
                return null;
            }
        });
        colorCmb.setConverter(new StringConverter<Color>() {
            @Override
            public String toString(Color object) {
                if (object == null){
                    return "";
                } else {
                    return object.getName();
                }
            }

            @Override
            public Color fromString(String string) {
                return null;
            }
        });
        sizeCmb.setConverter(new StringConverter<Size>() {
            @Override
            public String toString(Size object) {
                if (object == null){
                    return "";
                } else {
                    return object.getName();
                }
            }

            @Override
            public Size fromString(String string) {
                return null;
            }
        });
        subcatCmb.setConverter(new StringConverter<Subcat>() {
            @Override
            public String toString(Subcat object) {
                if (object == null){
                    return "";
                } else {
                    return object.getName();
                }
            }

            @Override
            public Subcat fromString(String string) {
                return null;
            }
        });
        tvaCmb.setConverter(new StringConverter<Tva>() {
            @Override
            public String toString(Tva object) {
                if (object == null){
                    return "";
                } else {
                    return "" + object.getRate();
                }
            }

            @Override
            public Tva fromString(String string) {
                return null;
            }
        });
    }

    @FXML private void handleOk(){
        if (isInputValid()){
            product.setName(nameTf.getText());
            product.setDescription(descriptionTf.getText());
            product.setPriceHt(Double.valueOf(priceHtTf.getText()));
            product.setQuantity(Integer.valueOf(quantityTf.getText()));
            product.setPath(pathTf.getText());
            product.setSupplier(supplierCmb.getSelectionModel().getSelectedItem());
            product.setColor(colorCmb.getSelectionModel().getSelectedItem());
            product.setSize(sizeCmb.getValue());
            product.setTva(tvaCmb.getValue());
            product.setSubcat(subcatCmb.getValue());
            product.setEnabled(enabledCbx.isSelected());
            product.setNews(newsCbx.isSelected());
            product.setVip(vipCbx.isSelected());
            product.setPromo(promoCbx.isSelected());

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
        if (descriptionTf.getText() == null || descriptionTf.getText().length() == 0){
            error += "Description invalide\n";
        }
        if (priceHtTf.getText() == null || priceHtTf.getText().length() == 0){
            error += "Prix invalide\n";
        } else {
            try {
                double price = Double.parseDouble(priceHtTf.getText());
                if (price == 0){
                    error += "Le prix ne peut pas être à 0.\n";
                }
            } catch (NumberFormatException e) {
                error += "Le format du prix n'est pas valide\n";
            }
        }
        if (quantityTf.getText() == null || quantityTf.getText().length() == 0){
            error += "Taux invalide\n";
        } else {
            try {
                Integer.parseInt(quantityTf.getText());
            } catch (NumberFormatException e) {
                error += "La quantité n'est pas valide\n";
            }
        }
        if (pathTf.getText() == null || pathTf.getText().length() == 0){
            error += "Photo invalide\n";
        }
        if (supplierCmb.getSelectionModel().getSelectedItem() == null){
            error += "Vous devez choisir un fournisseur.\n";
        }
        if (colorCmb.getSelectionModel().getSelectedItem() == null){
            error += "Vous devez choisir une couleur.\n";
        }
        if (sizeCmb.getSelectionModel().getSelectedItem() == null){
            error += "Vous devez choisir une couleur.\n";
        }
        if (subcatCmb.getSelectionModel().getSelectedItem() == null){
            error += "Vous devez choisir une sous-catégorie.\n";
        }
        if (tvaCmb.getSelectionModel().getSelectedItem() == null){
            error += "Vous devez choisir un taux de TVA.\n";
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

    public ProductEditCtrl() {
    }

    public ProductEditCtrl(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;

        nameTf.setText(product.getName());
        descriptionTf.setText(product.getDescription());
        pathTf.setText(product.getPath());
        priceHtTf.setText("" + product.getPriceHt());
        quantityTf.setText("" + product.getQuantity());
        supplierCmb.setValue(product.getSupplier());
        colorCmb.setValue(product.getColor());
        sizeCmb.setValue(product.getSize());
        tvaCmb.setValue(product.getTva());
        subcatCmb.setValue(product.getSubcat());
        enabledCbx.setSelected(product.isEnabled());
        newsCbx.setSelected(product.isNews());
        vipCbx.setSelected(product.isVip());
        promoCbx.setSelected(product.isPromo());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setOkClicked(boolean okClicked) {
        this.okClicked = okClicked;
    }
}
