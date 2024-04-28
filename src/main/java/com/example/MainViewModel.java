package com.example;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class MainViewModel {
    @FXML
    private Label bmiLabel;

    @FXML
    private Button calcButton;

    @FXML
    private TextField heightField;
    
    @FXML
    private TextField weightField;

	public DoubleProperty cmHeight = new SimpleDoubleProperty();
	public DoubleProperty kgWeight = new SimpleDoubleProperty();
	public DoubleProperty bmi = new SimpleDoubleProperty();	
    
    private Model model;
    
    private double round(double value) {
        return Math.round(value * 10) / 10.0;
    }
    
    public void initModel(Model model) {
		if (this.model != null)
			throw new IllegalStateException("Model can only be initialized once");
		
    	this.model = model;

    	// Load initial value
    	bmi.set(round(model.load()));
    	
		// Event Handler
		calcButton.setOnAction(e -> {
			double newBmi = model.calc(cmHeight.get() / 100, kgWeight.get());
			bmi.set(round(newBmi));
        });			
    }
    
    public void initialize(){
    	// Bind ViewModel to View
		bmiLabel.textProperty().bind(bmi.asString());
		heightField.textProperty().bindBidirectional(cmHeight, new NumberStringConverter());
		weightField.textProperty().bindBidirectional(kgWeight, new NumberStringConverter());    	
    }
}