package jismen.stat_bundle.stat_user_bundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Label;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by Finelam on 23/05/2016.
 */
public class StatUserCtrl {

    @FXML private Label totalUsersLabel;

    private StatUserMngr manager;

    public StatUserCtrl(){}

    public void setManager(StatUserMngr manager) {
        this.manager = manager;
    }

    @FXML
    private void initialize(){}

    public void showData(){
        totalUsersLabel.setText(String.valueOf(manager.getUserList().size()));
    }
}
