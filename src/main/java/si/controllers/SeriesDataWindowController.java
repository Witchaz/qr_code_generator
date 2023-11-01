package si.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

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

    private String constantText;
    private int startNumber;
    private int endNumber;
    private int spaceNumber;
    private boolean fillWithZero;

    @FXML
    private void initialize(){

        constantTextTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            updatePreview();
        });
        startNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            updatePreview();
        });

        endNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            updatePreview();
        });

        spaceNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {updatePreview();
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
        
        if(constantOnlyCheckBox.isSelected() && constantTextTextField.getText().isEmpty()){
            previewLabel.setText("please enter constant text");
            previewLabel.setTextFill(Color.RED);
            return;
        }
        else if(fillWithZeroCheckBox.isSelected() && spaceNumberTextField.getText().isEmpty()){
            previewLabel.setText("please enter space number");
            previewLabel.setTextFill(Color.RED);
            return;
        }
        else if (!constantOnlyCheckBox.isSelected() && (constantTextTextField.getText().isEmpty() || startNumberTextField.getText().isEmpty() || endNumberTextField.getText().isEmpty())){
            previewLabel.setText("please enter the rest");
            previewLabel.setTextFill(Color.RED);
            return;
        }
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
    }
}
