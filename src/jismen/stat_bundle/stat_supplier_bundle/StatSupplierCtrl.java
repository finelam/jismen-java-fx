package jismen.stat_bundle.stat_supplier_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by Finelam on 23/05/2016.
 */
public class StatSupplierCtrl {

    private StatSupplierMngr manager;
    @FXML private Label totalSupplierLabel;

    public StatSupplierCtrl() {}

    public void setManager(StatSupplierMngr manager) {
        this.manager = manager;
    }

    @FXML private void initialize(){ }

    public void showData() {
        totalSupplierLabel.setText(String.valueOf(manager.getSuppliers().size()));
    }
}
