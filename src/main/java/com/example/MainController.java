package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class MainController {
    @FXML
    private Label bmiLabel;

    @FXML
    private Button calcButton;

    @FXML
    private TextField heightField;
    
    @FXML
    private TextField weightField;

    
    private Model model;
    
    public void initModel(Model model) {
		if (this.model != null)
			throw new IllegalStateException("Model can only be initialized once");
		
    	this.model = model;
    	
    	// Bind Model to View
		bmiLabel.textProperty().bind(model.bmi.asString());
		heightField.textProperty().bindBidirectional(model.cmHeight, new NumberStringConverter());
		weightField.textProperty().bindBidirectional(model.kgHeight, new NumberStringConverter());
		
		// Event Handler
		calcButton.setOnAction(e -> {
			model.calc();
        });			
    }
    
	public void initialize() {
		// Write your initialization code here
	}
}