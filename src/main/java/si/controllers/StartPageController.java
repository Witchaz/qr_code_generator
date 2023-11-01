package si.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import si.models.Project;
import si.models.Series;
import si.services.Data;
import si.services.FXRouter;

import java.io.IOException;

public class StartPageController {

    @FXML
    private ListView<Series> seriesListView;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button editButton;

    private Project project;
    private ObservableList<Series> items;
    @FXML
    private void initialize(){
        project = Data.getData().getProject();

        items = FXCollections.observableArrayList(project.getSeriesList());
        items.add(new Series("Test",1,20,4,true));
        seriesListView.setItems(items);
    }
    @FXML
    private void addButtonOnAction() {
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/si/views/seriesWindow.fxml"));
        System.out.println(fxmlLoader.getLocation());
        Parent parent = null;
        try {
            parent = fxmlLoader.load();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setTitle("Add series");
        stage.setScene( new Scene(parent));
        stage.show();
    }
    @FXML
    private void removeButtonOnAction() {
        if (seriesListView.getSelectionModel().getSelectedItems() != null){
            Series s = seriesListView.getSelectionModel().getSelectedItem();
            project.removeSeries(s);
            items.remove(s);
            seriesListView.setItems(items);
        }
    }
    @FXML
    private void editButtonOnAction() {
    }
}
