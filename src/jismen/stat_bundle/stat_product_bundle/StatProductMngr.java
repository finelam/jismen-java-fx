package jismen.stat_bundle.stat_product_bundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import jismen.MainApp;
import jismen.product_bundle.Product;

import java.io.IOException;

/**
 * Created by Finelam on 23/05/2016.
 */
public class StatProductMngr {

    private MainApp mainApp;
    private ObservableList<Product> products = FXCollections.observableArrayList();

    public StatProductMngr(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void initView(){
        products = mainApp.getProductMngr().getProductList();

        AnchorPane view = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StatProductMngr.class.getResource("StatProductView.fxml"));
            view = loader.load();
            StatProductCtrl controller = loader.getController();
            controller.setManager(this);
            controller.showData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainApp.getLayoutMngr().changeView(view);
    }

    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public ObservableList<Product> getProducts() {
        return products;
    }

    public void setProducts(ObservableList<Product> products) {
        this.products = products;
    }
}
