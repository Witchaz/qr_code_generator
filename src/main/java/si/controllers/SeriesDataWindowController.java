package si.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import si.models.Series;
import si.services.Data;
import si.services.FXRouter;

import java.io.IOException;
import java.text.DecimalFormat;

public class SeriesDataWindowController {
    @FXML
    private Label previewLabel;
    @FXML
    private TextField constantTextTextField;
    @FXML
    private TextField startNumberTextField;
    @FXML
    private TextField endNumberTextField;
    @FXML
    private TextField spaceNumberTextField;
    @FXML
    private CheckBox fillWithZeroCheckBox;
    @FXML
    private CheckBox constantOnlyCheckBox;


    @FXML
    private void initialize(){

        constantTextTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            updatePreview();
        });
        startNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                startNumberTextField.setText(newValue.replaceAll("\\D", ""));
            }
            updatePreview();
        });

        endNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                endNumberTextField.setText(newValue.replaceAll("\\D", ""));
            }
            updatePreview();
        });

        spaceNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                spaceNumberTextField.setText(newValue.replaceAll("\\D", ""));
            }
            updatePreview();
            });

        constantOnlyCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
            toggle(constantOnlyCheckBox.isSelected());
            updatePreview();
            }
        });
        
        fillWithZeroCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                spaceNumberTextField.setDisable(!fillWithZeroCheckBox.isSelected());
                updatePreview();
            }
        });
    }

    private void toggle(boolean status){
        startNumberTextField.setDisable(status);
        endNumberTextField.setDisable(status);
        spaceNumberTextField.setDisable(status);
        fillWithZeroCheckBox.setDisable(status);
        if (!fillWithZeroCheckBox.isSelected() && !status) spaceNumberTextField.setDisable(true);
        
    }
    private void updatePreview(){
       
        if(!check_right())return;
        
        previewLabel.setTextFill(Color.BLACK);
        
        
        if (constantOnlyCheckBox.isSelected()) {
            previewLabel.setText("Preview : " + constantTextTextField.getText());
        }
        
        else if (fillWithZeroCheckBox.isSelected()){
            String s = "";
            for (int i = 0 ;i < Integer.parseInt(spaceNumberTextField.getText()) ;i++) s += '0';
            DecimalFormat df = new DecimalFormat(s);
            previewLabel.setText("Preview : " + constantTextTextField.getText() + df.format(Integer.parseInt(startNumberTextField.getText())));
        }
        else{
            previewLabel.setText("Preview : " + constantTextTextField.getText() + startNumberTextField.getText());
        }
    }
    
    public void clearButtonOnAction() {
        constantTextTextField.setText("");
        startNumberTextField.setText("");
        endNumberTextField.setText("");
        spaceNumberTextField.setText("");
        
    }
    
    public void ConfirmButtonOnAction() {
        if (!check_right()) return;
        String constantText = constantTextTextField.getText();
        int startNumber = Integer.parseInt(startNumberTextField.getText());
        int endNumber = Integer.parseInt(startNumberTextField.getText());
        int spaceNumber = Integer.parseInt(startNumberTextField.getText());
        boolean fillWithZero = fillWithZeroCheckBox.isSelected();
        
        if(constantOnlyCheckBox.isSelected()){
            Data.getData().getProject().addSeries(constantText);
            
        }
        else{
            if (fillWithZero){
                Data.getData().getProject().addSeries(constantText,startNumber,endNumber,spaceNumber,true);
            }
            else{
                Data.getData().getProject().addSeries(constantText,startNumber,endNumber,0,false);
            }
        }
        
        ((Stage) constantOnlyCheckBox.getScene().getWindow()).close();
        
        try {
            FXRouter.goTo("start");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    private boolean check_right(){
        //เช็คช่อง constant อย่างเดียว
        if(constantTextTextField.getText().isEmpty()){
            previewLabel.setText("กรุณาใส่ให้ครบทุกช่อง");
            previewLabel.setTextFill(Color.RED);
            return false;
        }
        
        if (!constantOnlyCheckBox.isSelected()) {
            
            //เช็คช่อง startNumber กับ endNumber ว่าว่างหรือป่าว
            if (startNumberTextField.getText().isEmpty() || endNumberTextField.getText().isEmpty()) {
                previewLabel.setText("กรุณาใส่ให้ครบทุกช่อง");
                previewLabel.setTextFill(Color.RED);
                return false;
            }
            
            if (fillWithZeroCheckBox.isSelected()) {
                // เช็คช่องของ space เมื่อมีการเลือกให้ใส่ 0 ลงช่องว่าง
                    if (spaceNumberTextField.getText().isEmpty()) {
                        previewLabel.setText("กรุณาใส่ให้ครบทุกช่อง");
                        previewLabel.setTextFill(Color.RED);
                        return false;
                }
                    else if (Integer.parseInt(spaceNumberTextField.getText()) < 0){
                        previewLabel.setText("ค่าที่ใส่เข้ามาต้องไม่น้อยกว่า 0");
                        previewLabel.setTextFill(Color.RED);
                        return false;
                    }
            }
            if (Integer.parseInt(startNumberTextField.getText()) < 0 || Integer.parseInt(endNumberTextField.getText()) < 0){
                previewLabel.setText("ค่าที่ใส่เข้ามาต้องไม่น้อยกว่า 0");
                previewLabel.setTextFill(Color.RED);
                return false;
                
            }
            
        }
        return true;
    }
}

