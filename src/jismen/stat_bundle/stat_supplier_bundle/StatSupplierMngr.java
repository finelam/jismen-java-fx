package jismen.stat_bundle.stat_supplier_bundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import jismen.MainApp;
import jismen.supplier_bundle.Supplier;

import java.io.IOException;

/**
 * Created by Finelam on 23/05/2016.
 */
public class StatSupplierMngr {

    MainApp mainApp;
    ObservableList<Supplier> suppliers = FXCollections.observableArrayList();

    public StatSupplierMngr(MainApp mainApp){
        this.mainApp=  mainApp;
    }

    public void initView() {
        suppliers = mainApp.getSupplierMngr().getSupplierList();

        AnchorPane view = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StatSupplierMngr.class.getResource("StatSupplierView.fxml"));
            view = loader.load();
            StatSupplierCtrl controller = loader.getController();
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

    public ObservableList<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(ObservableList<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}
