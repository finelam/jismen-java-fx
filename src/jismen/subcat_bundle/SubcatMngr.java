package jismen.subcat_bundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jismen.MainApp;
import jismen.category_bundle.Category;
import jismen.utils_bundle.RestClient;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Finelam on 24/04/2016.
 */
public class SubcatMngr {

    private MainApp mainApp;
    private ObservableList<Subcat> subcatList = FXCollections.observableArrayList();

    public SubcatMngr(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void getAllEntities(){
        try {
            subcatList = FXCollections.observableArrayList();
            JSONObject allEntities = RestClient.get("java/get_all_subcat", null);
            for (Object o : allEntities.getJSONArray("subcats")){
                JSONObject entity = (JSONObject) o;
                int id = entity.getInt("id");
                String name = entity.getString("name");
                JSONObject jsonCat = entity.getJSONObject("category");
                int catId = jsonCat.getInt("id");
                String catName = jsonCat.getString("name");
                boolean catEnbaled = jsonCat.getBoolean("enabled");
                boolean enabled = entity.getBoolean("enabled");
                subcatList.add(new Subcat(id, name, new Category(catId, catName, catEnbaled), enabled));
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

    public ObservableList<Subcat> getSubcatList(){
        return subcatList;
    }

    public void initEntities(){
        getAllEntities();
        AnchorPane view = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SubcatMngr.class.getResource("viewEntity.fxml"));
            view = loader.load();
            SubcatCtrl controller = loader.getController();
            controller.setManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainApp.getLayoutMngr().changeView(view);
    }

    public boolean showDialog(Subcat subcat){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SubcatMngr.class.getResource("viewDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Sous-catégorie");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getMainApp().getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            SubcatEditCtrl controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSubcat(subcat);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
