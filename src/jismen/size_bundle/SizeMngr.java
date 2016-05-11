package jismen.size_bundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jismen.MainApp;
import jismen.color_bundle.Color;
import jismen.color_bundle.ColorCtrl;
import jismen.color_bundle.ColorEditCtrl;
import jismen.utils_bundle.RestClient;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Finelam on 24/04/2016.
 */
public class SizeMngr {

    private MainApp mainApp;
    private ObservableList<Size> entityList = FXCollections.observableArrayList();

    public SizeMngr(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void getAllEntities(){
        try {
            entityList = FXCollections.observableArrayList();
            JSONObject allEntities = RestClient.get("java/get_all_sizes", null);
            for (Object o : allEntities.getJSONArray("sizes")){
                JSONObject entity = (JSONObject) o;
                int id = entity.getInt("id");
                String name = entity.getString("name");
                entityList.add(new Size(id, name));
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

    public ObservableList<Size> getEntityList(){
        return entityList;
    }

    public void initEntities(){
        getAllEntities();
        AnchorPane view = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("size_bundle/viewEntity.fxml"));
            view = loader.load();
            SizeCtrl controller = loader.getController();
            controller.setManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainApp.getLayoutMngr().changeView(view);
    }

    public boolean showDialog(Size entity){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SizeMngr.class.getResource("viewDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Taille");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getMainApp().getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            SizeEditCtrl controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setEntity(entity);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
