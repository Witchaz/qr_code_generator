package si.controllers;

import com.google.zxing.WriterException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Modality;
import javafx.stage.Stage;
import si.models.Project;
import si.models.Series;
import si.services.Data;
import si.services.QRGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.commons.io.FileUtils;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class StartPageController {

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
    private Project project;
    private ObservableList<Series> items;
    
    @FXML
    private void initialize(){
        project = Data.getData().getProject();
        editButton.setVisible(false);
        removeButton.setVisible(false);
        
        items = FXCollections.observableArrayList(project.getSeriesList());
        seriesListView.setItems(items);
        if (project.getSeriesList().isEmpty()){
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
            project.removeSeries(s);
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
        generateButton.setText("generating");
        
        createQRCode();
        generateButton.setText("done");
    }
    
    private void createQRCode(){
        
        String path = (System.getProperty("user.dir"));
        path = path + '\\' + "data";
        Path filePath = Path.of(path);
        
        if (Files.notExists(filePath)){
            try {
                Files.createDirectories(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            FileUtils.cleanDirectory(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        
        for (Series tempSeries : project.getSeriesList()){
            
            ArrayList<String> series=  tempSeries.getSeries();
            for (String temp : series){
                
                String thisPath = "%s\\%s.png".formatted(path, temp);
                File qrFile = new File(thisPath);
                try {
                    QRGenerator.createQRImage(qrFile,temp,256,"png");
                } catch (WriterException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
