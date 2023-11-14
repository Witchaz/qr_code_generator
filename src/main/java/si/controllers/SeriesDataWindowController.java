package si.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    
    private Series currentSelectedSeries;


    @FXML
    private void initialize(){
        currentSelectedSeries = Data.getData().getCurrentSelectedSeries();
        if(currentSelectedSeries != null){
            currentSelectedSeries = Data.getData().getCurrentSelectedSeries();
            constantTextTextField.setText(currentSelectedSeries.getConstantText());
            startNumberTextField.setText(String.format("%d",currentSelectedSeries.getStartNumber()));
            endNumberTextField.setText(String.format("%d",currentSelectedSeries.getEndNumber()));
            spaceNumberTextField.setText(String.format("%d",currentSelectedSeries.getSpace()));
            fillWithZeroCheckBox.setSelected(currentSelectedSeries.isFillWithZero());
            updatePreview();
            
        }
        
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
       
        if(isHaveError())return;
        
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
        if (isHaveError()) return;
        
        String constantText = constantTextTextField.getText();
        
        
       
            if (constantOnlyCheckBox.isSelected()) {
                if(Data.getData().getCurrentSelectedSeries() != null){
                    currentSelectedSeries.setConstantText(constantText);
                }
                else Data.getData().getProject().addSeries(constantText);
                
            } else {
                
                int startNumber = Integer.parseInt(startNumberTextField.getText());
                int endNumber = Integer.parseInt(endNumberTextField.getText());
                int spaceNumber = Integer.parseInt(spaceNumberTextField.getText());
                boolean fillWithZero = fillWithZeroCheckBox.isSelected();
                if (fillWithZero) {
                    if(Data.getData().getCurrentSelectedSeries() != null){
                        currentSelectedSeries.setConstantText(constantText);
                        currentSelectedSeries.setStartNumber(startNumber);
                        currentSelectedSeries.setEndNumber(endNumber);
                        currentSelectedSeries.setSpace(spaceNumber);
                        currentSelectedSeries.setFillWithZero(true);
                        
                    }
                    else Data.getData().getProject().addSeries(constantText, startNumber, endNumber, spaceNumber, true);
                } else {
                    if(Data.getData().getCurrentSelectedSeries() != null){
                        currentSelectedSeries.setConstantText(constantText);
                        currentSelectedSeries.setStartNumber(startNumber);
                        currentSelectedSeries.setEndNumber(endNumber);
                        currentSelectedSeries.setSpace(0);
                        currentSelectedSeries.setFillWithZero(false);
                        
                    }
                    else Data.getData().getProject().addSeries(constantText, startNumber, endNumber, 0, false);
                }
            }
            
            ((Stage) constantOnlyCheckBox.getScene().getWindow()).close();
        
        try {
            FXRouter.goTo("start");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    private boolean isHaveError(){
        //เช็คช่อง constant อย่างเดียว
        if(constantTextTextField.getText().isEmpty()){
            previewLabel.setText("กรุณาใส่ให้ครบทุกช่อง");
            previewLabel.setTextFill(Color.RED);
            return true;
        }
        
        if (!constantOnlyCheckBox.isSelected()) {
            
            //เช็คช่อง startNumber กับ endNumber ว่าว่างหรือป่าว
            if (startNumberTextField.getText().isEmpty() || endNumberTextField.getText().isEmpty()) {
                previewLabel.setText("กรุณาใส่ให้ครบทุกช่อง");
                previewLabel.setTextFill(Color.RED);
                return true;
            }
            
            if (fillWithZeroCheckBox.isSelected()) {
                // เช็คช่องของ space เมื่อมีการเลือกให้ใส่ 0 ลงช่องว่าง
                    if (spaceNumberTextField.getText().isEmpty()) {
                        previewLabel.setText("กรุณาใส่ให้ครบทุกช่อง");
                        previewLabel.setTextFill(Color.RED);
                        return true;
                }
                    else if (Integer.parseInt(spaceNumberTextField.getText()) < 0){
                        previewLabel.setText("ค่าที่ใส่เข้ามาต้องไม่น้อยกว่า 0");
                        previewLabel.setTextFill(Color.RED);
                        return true;
                    }
            }
            if (Integer.parseInt(startNumberTextField.getText()) < 0 || Integer.parseInt(endNumberTextField.getText()) < 0){
                previewLabel.setText("ค่าที่ใส่เข้ามาต้องไม่น้อยกว่า 0");
                previewLabel.setTextFill(Color.RED);
                return true;
                
            }
            
        }
        return false;
    }
}

