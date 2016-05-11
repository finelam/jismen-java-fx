package jismen.product_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import jismen.utils_bundle.RestClient;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Finelam on 24/04/2016.
 */
public class ProductCtrl {
    @FXML private TableView<Product> entityTable;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private Label nameLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label supplierLabel;
    @FXML private Label priceHtLabel;
    @FXML private Label quantityLabel;
    @FXML private Label colorLabel;
    @FXML private Label sizeLabel;
    @FXML private Label tvaLabel;
    @FXML private Label subcatLabel;
    @FXML private Label pathLabel;
    @FXML private Label errorLabel;
    @FXML private CheckBox enabledCbx;
    @FXML private CheckBox newsCbx;
    @FXML private CheckBox vipCbx;
    @FXML private CheckBox promoCbx;

    private ProductMngr manager;

    public ProductCtrl(){}

    public void setManager(ProductMngr manager){
        this.manager = manager;
        entityTable.setItems(manager.getProductList());
    }

    @FXML private void initialize(){
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        showDetails(null);
        entityTable.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> showDetails(newvalue));
    }

    private void showDetails(Product product){
        errorLabel.setText("");
        if (product != null) {
            nameLabel.setText(product.getName());
            descriptionLabel.setText(product.getDescription());
            supplierLabel.setText(product.getSupplier().getName());
            priceHtLabel.setText("" + product.getPriceHt());
            quantityLabel.setText("" + product.getQuantity());
            colorLabel.setText(product.getColor().getName());
            sizeLabel.setText(product.getSize().getName());
            tvaLabel.setText("" + product.getTva().getRate());
            subcatLabel.setText(product.getSubcat().getName());
            pathLabel.setText(product.getPath());
            enabledCbx.setSelected(product.isEnabled());
            vipCbx.setSelected(product.isVip());
            promoCbx.setSelected(product.isPromo());
            newsCbx.setSelected(product.isNews());
        } else {
            nameLabel.setText("");
            descriptionLabel.setText("");
            supplierLabel.setText("");
            priceHtLabel.setText("");
            quantityLabel.setText("");
            colorLabel.setText("");
            sizeLabel.setText("");
            tvaLabel.setText("");
            subcatLabel.setText("");
            pathLabel.setText("");
            enabledCbx.setSelected(false);
            vipCbx.setSelected(false);
            promoCbx.setSelected(false);
            newsCbx.setSelected(false);
        }
    }

    @FXML private void handleNew(){
        Product tempProduct = new Product();
        boolean okClicked = manager.showDialog(tempProduct);
        if (okClicked){
            System.out.println(tempProduct);
            HashMap<String, Object> params = new HashMap<>();
            params.put("name", tempProduct.getName());
            params.put("description", tempProduct.getDescription());
            params.put("supplier", tempProduct.getSupplier().getId());
            params.put("priceHt", tempProduct.getPriceHt());
            params.put("quantity", tempProduct.getQuantity());
            params.put("color", tempProduct.getColor().getId());
            params.put("size", tempProduct.getSize().getId());
            params.put("tva", tempProduct.getTva().getId());
            params.put("subcat", tempProduct.getSubcat().getId());
            params.put("path", tempProduct.getPath());
            params.put("enabled", tempProduct.isEnabled() ? 1 : 0);
            params.put("vip", tempProduct.isVip() ? 1 : 0);
            params.put("promo", tempProduct.isPromo() ? 1 : 0);
            params.put("new", tempProduct.isNews() ? 1 : 0);

            JSONObject result = RestClient.post("java/new/product", params);
            if (result.getBoolean("success")){
                manager.getProductList().add(tempProduct);
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
        Product product = entityTable.getSelectionModel().getSelectedItem();
        if (product != null){
            boolean okClicked = manager.showDialog(product);
            if (okClicked) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(manager.getMainApp().getPrimaryStage());
                alert.setTitle("Confirmation");
                alert.setHeaderText("Souhaitez-vous enregistrer les modifications ?");
                if(alert.showAndWait().get() == ButtonType.OK){
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("name", product.getName());
                    params.put("description", product.getDescription());
                    params.put("supplier", product.getSupplier());
                    params.put("priceHt", product.getPriceHt());
                    params.put("quantity", product.getQuantity());
                    params.put("color", product.getColor());
                    params.put("size", product.getSize());
                    params.put("tva", product.getTva());
                    params.put("subcat", product.getSubcat());
                    params.put("path", product.getPath());
                    params.put("enabled", product.isEnabled() ? 1 : 0);
                    params.put("vip", product.isVip() ? 1 : 0);
                    params.put("promo", product.isPromo() ? 1 : 0);
                    params.put("news", product.isNews() ? 1 : 0);
                    JSONObject result = RestClient.put("java/" +  + product.getId() + "/product", params);
                    if (result.getBoolean("success")){
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.initOwner(manager.getMainApp().getPrimaryStage());
                        alert.setTitle("Modification effectuée !");
                        alert.setContentText(result.getString("message"));
                        alert.showAndWait();
                        showDetails(product);
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
        Product product = entityTable.getSelectionModel().getSelectedItem();
        if (product != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Souhaitez-vous supprimer cet entrée ?");
            if(alert.showAndWait().get() == ButtonType.OK){
                JSONObject result = RestClient.delete("java/" +  product.getId() + "/product");
                if (result.getBoolean("success")){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(manager.getMainApp().getPrimaryStage());
                    alert.setTitle("Suppression effectuée");
                    alert.setHeaderText(result.getString("message"));
                    alert.showAndWait();
                    manager.getProductList().remove(product);
                } else {
                    errorLabel.setText("Une erreur est survenue");
                }
            }
        } else {
            errorLabel.setText("Aucune entrée sélectionnée");
        }
    }
}
