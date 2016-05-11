package jismen.utils_bundle.sample_bundle;

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
public class EntityMngr {

    private MainApp mainApp;
    private ObservableList<Entity> entityList = FXCollections.observableArrayList();

    public EntityMngr(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void getAllEntities(){
        try {
            entityList = FXCollections.observableArrayList();
            JSONObject allEntities = RestClient.get("java/get_all_tva", null);
            System.out.println(allEntities);
            for (Object o : allEntities.getJSONArray("tva")){
                JSONObject entity = (JSONObject) o;
                int id = entity.getInt("id");
                double rate = entity.getDouble("rate");
                entityList.add(new Entity(id, rate));
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

    public ObservableList<Entity> getEntityList(){
        return entityList;
    }

    public void initEntities(){
        getAllEntities();
        AnchorPane view = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EntityMngr.class.getResource("viewEntity.fxml"));
            view = loader.load();
            EntityCtrl controller = loader.getController();
            controller.setManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainApp.getLayoutMngr().changeView(view);
    }

    public boolean showDialog(Entity entity){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EntityMngr.class.getResource("viewDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("TVA");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getMainApp().getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EntityEditCtrl controller = loader.getController();
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
