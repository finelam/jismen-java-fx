package jismen.product_bundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jismen.MainApp;
import jismen.color_bundle.Color;
import jismen.size_bundle.Size;
import jismen.subcat_bundle.Subcat;
import jismen.supplier_bundle.Supplier;
import jismen.tva_bundle.Tva;
import jismen.utils_bundle.RestClient;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Finelam on 24/04/2016.
 */
public class ProductMngr {

    private MainApp mainApp;
    private ObservableList<Product> productList = FXCollections.observableArrayList();

    public ProductMngr(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void getAllEntities(){
        try {
            productList = FXCollections.observableArrayList();
            JSONObject allEntities = RestClient.get("java/get_all_products", null);
            for (Object o : allEntities.getJSONArray("products")){
                JSONObject entity = (JSONObject) o;
                productList.add(
                        new Product(
                                entity.getInt("id"),
                                entity.getString("name"),
                                entity.getString("description"),
                                entity.getInt("quantity"),
                                entity.getString("path"),
                                entity.getDouble("priceHt"),
                                entity.getBoolean("enabled"),
                                entity.getBoolean("vip"),
                                entity.getBoolean("promo"),
                                entity.getBoolean("news"),
                                new Tva(entity.getJSONObject("tva")),
                                new Color(entity.getJSONObject("color")),
                                new Size(entity.getJSONObject("size")),
                                new Supplier(entity.getJSONObject("supplier")),
                                new Subcat(entity.getJSONObject("subcat"))
                        )
                );
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public ObservableList<Product> getProductList(){
        getAllEntities();
        return productList;
    }

    public void initEntities(){
        getAllEntities();
        AnchorPane view = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProductMngr.class.getResource("viewEntity.fxml"));
            view = loader.load();
            ProductCtrl controller = loader.getController();
            controller.setManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainApp.getLayoutMngr().changeView(view);
    }

    public boolean showDialog(Product product){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProductMngr.class.getResource("viewDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Articles");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getMainApp().getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ProductEditCtrl controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(product);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
