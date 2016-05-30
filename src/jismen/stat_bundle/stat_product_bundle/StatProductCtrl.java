package jismen.stat_bundle.stat_product_bundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by Finelam on 23/05/2016.
 */
public class StatProductCtrl {

    @FXML private Label totalProductsLabel;
    private StatProductMngr manager;

    public StatProductCtrl(){}

    public void setManager(StatProductMngr manager) {
        this.manager = manager;
    }

    public void showData() {
        totalProductsLabel.setText(String.valueOf(manager.getProducts().size()));
    }

    @FXML
    private void initialize(){  }
}
