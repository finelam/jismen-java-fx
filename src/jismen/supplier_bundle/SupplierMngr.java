package jismen.supplier_bundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jismen.MainApp;
import jismen.utils_bundle.RestClient;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Finelam on 24/04/2016.
 */
public class SupplierMngr {

    private MainApp mainApp;
    private ObservableList<Supplier> supplierList = FXCollections.observableArrayList();

    public SupplierMngr(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void getAllEntities(){
        try {
            supplierList = FXCollections.observableArrayList();
            JSONObject allEntities = RestClient.get("java/get_all_suppliers", null);
            for (Object o : allEntities.getJSONArray("suppliers")){
                JSONObject entity = (JSONObject) o;
                int id = entity.getInt("id");
                String name = entity.getString("name");
                String address = entity.getString("address");
                String postalCode = entity.getString("postalCode");
                String city = entity.getString("city");
                String contract= entity.getString("contract");
                supplierList.add(new Supplier(id, name, address, postalCode, city, contract));
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

    public ObservableList<Supplier> getSupplierList(){
        getAllEntities();
        return supplierList;
    }

    public void initEntities(){
        getAllEntities();
        AnchorPane view = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SupplierMngr.class.getResource("viewEntity.fxml"));
            view = loader.load();
            SupplierCtrl controller = loader.getController();
            controller.setManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainApp.getLayoutMngr().changeView(view);
    }

    public boolean showDialog(Supplier supplier){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SupplierMngr.class.getResource("viewDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Fournissueur");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getMainApp().getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            SupplierEditCtrl controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSupplier(supplier);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
