package si.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Modality;
import javafx.stage.Stage;
import si.models.SeriesList;
import si.models.Series;
import si.services.Data;
import si.services.QRGenerator;

import java.io.IOException;

public class StartPageController {
    private QRGenerator qrGenerator;
    
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private ListView<Series> seriesListView;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button editButton;
    @FXML
    private Button generateButton;
    private SeriesList seriesList;
    private ObservableList<Series> items;
    
    @FXML
    private void initialize(){
        seriesList = Data.getData().getProject();
        editButton.setVisible(false);
        removeButton.setVisible(false);
        
        items = FXCollections.observableArrayList(seriesList.getSeriesList());
        seriesListView.setItems(items);
        if (seriesList.getSeriesList().isEmpty()){
            generateButton.setVisible(false);
        }
        
        
       
        seriesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Series>() {
            
            @Override
            public void changed(ObservableValue<? extends Series> observable, Series oldValue, Series newValue) {
                Data.getData().setCurrentSelectedSeries(newValue);
                editButton.setVisible(true);
                removeButton.setVisible(true);
            }
        });
    }
    
    @FXML
    private void addButtonOnAction() {
        Data.getData().setCurrentSelectedSeries(null);
        openNewWindow("Add series");
    }
    @FXML
    private void removeButtonOnAction() {
        if (seriesListView.getSelectionModel().getSelectedItems() != null){
            Series s = seriesListView.getSelectionModel().getSelectedItem();
            seriesList.removeSeries(s);
            items.remove(s);
            seriesListView.setItems(items);
        }
    }
    @FXML
    private void editButtonOnAction() {
        Data.getData().setCurrentSelectedSeries(seriesListView.getSelectionModel().getSelectedItem());
        openNewWindow("Edit series");
    }
    
    private void openNewWindow(String title){
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/si/views/seriesWindow.fxml"));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        stage.setTitle(title);

        stage.setScene( new Scene(parent));
        stage.show();
        
    }
    
    public void generateButtonOnAction() {
        progressIndicator.setVisible(true);
        generateButton.setDisable(true);
        createQRCode();
        
    }
    
    private void createQRCode(){
        if (qrGenerator != null && qrGenerator.isRunning()) qrGenerator.cancel();
        
        qrGenerator = new QRGenerator();
        qrGenerator.valueProperty().addListener(((observableValue, oldValue, newValue) -> generateButton.setDisable(newValue != 0)));
        progressIndicator.progressProperty().bind(qrGenerator.progressProperty());
        
        Thread th = new Thread(qrGenerator);
        th.setDaemon(true);
        th.start();
    }
}
