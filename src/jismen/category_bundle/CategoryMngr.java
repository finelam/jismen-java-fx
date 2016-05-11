package jismen.category_bundle;

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
public class CategoryMngr {

    private MainApp mainApp;
    private ObservableList<Category> categoryList = FXCollections.observableArrayList();

    public CategoryMngr(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void getAllCategories(){
        try {
            categoryList = FXCollections.observableArrayList();
            JSONObject allCategories = RestClient.get("java/get_all_categories", null);
            for (Object o : allCategories.getJSONArray("categories")){
                JSONObject cat = (JSONObject) o;
                int id = cat.getInt("id");
                String name = cat.getString("name");
                boolean enabled = cat.getBoolean("enabled");
                categoryList.add(new Category(id, name, enabled));
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

    public ObservableList<Category> getCategoryList(){
        return categoryList;
    }

    public void initCategory(){
        getAllCategories();
        AnchorPane view = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("category_bundle/viewEntity.fxml"));
            view = loader.load();
            CategoryCtrl controller = loader.getController();
            controller.setManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainApp.getLayoutMngr().changeView(view);
    }
    public boolean showDialog(Category category){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CategoryMngr.class.getResource("viewDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Categorie");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getMainApp().getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            CategoryEditCtrl controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCategory(category);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
