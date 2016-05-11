package jismen.utils_bundle;

import javafx.fxml.FXML;
import jismen.user_bundle.UserMngr;

/**
 * Created by Finelam on 24/04/2016.
 */
public class LayoutCtrl {

    private LayoutMngr manager;

    @FXML private void initUser(){
        manager.getMainApp().getUserMngr().initEntities();
    }

    @FXML private void initSupplier(){
        manager.getMainApp().getSupplierMngr().initEntities();
    }

    @FXML private void initProduct(){
        manager.getMainApp().getProductMngr().initEntities();
    }

    @FXML private void initCategory(){
        manager.getMainApp().getCategoryMngr().initCategory();
    }

    @FXML private void initSubcat(){
        manager.getMainApp().getSubcatMngr().initEntities();
    }

    @FXML private void initSize(){
        manager.getMainApp().getSizeMngr().initEntities();
    }

    @FXML private void initColor(){
        manager.getMainApp().getColorMngr().initColor();
    }

    @FXML private void initTva(){
        manager.getMainApp().getTvaMngr().initEntities();
    }

    public void setManager(LayoutMngr manager){
        this.manager = manager;
    }
}
