package jismen.color_bundle;

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
public class ColorMngr {

    private MainApp mainApp;
    private ObservableList<Color> colorList = FXCollections.observableArrayList();

    public ColorMngr(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void getAllColors(){
        try {
            colorList = FXCollections.observableArrayList();
            JSONObject allColors = RestClient.get("java/get_all_colors", null);
            for (Object o : allColors.getJSONArray("colors")){
                JSONObject col = (JSONObject) o;
                int id = col.getInt("id");
                String name = col.getString("name");
                colorList.add(new Color(id, name));
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

    public ObservableList<Color> getColorList(){
        return colorList;
    }

    public void initColor(){
        getAllColors();
        AnchorPane view = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("color_bundle/viewEntity.fxml"));
            view = loader.load();
            ColorCtrl controller = loader.getController();
            controller.setManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainApp.getLayoutMngr().changeView(view);
    }

    public boolean showDialog(Color color){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ColorMngr.class.getResource("viewDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Couleur");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getMainApp().getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ColorEditCtrl controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setColor(color);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
