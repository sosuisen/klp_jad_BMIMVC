package com.example;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class MainViewModel {
	private final double METER_FEET = 3.2808;
	private final double KG_POUNDS = 2.2046;
	
    @FXML
    private Label bmiLabel;

    @FXML
    private Button calcButton;

    @FXML
    private TextField heightField;
    
    @FXML
    private TextField weightField;

	public DoubleProperty feetHeight = new SimpleDoubleProperty();
	public DoubleProperty poundsWeight = new SimpleDoubleProperty();
	public DoubleProperty bmi = new SimpleDoubleProperty();	
    
    private Model model;
    
    public void initModel(Model model) {
		if (this.model != null)
			throw new IllegalStateException("Model can only be initialized once");
		
    	this.model = model;
    	// Load initial value
    	bmi.set(model.load());
    	
		// Event Handler
		calcButton.setOnAction(e -> {		
			var mHeight = feetHeight.doubleValue() / METER_FEET;
			var kgWeight = poundsWeight.doubleValue() / KG_POUNDS;

			double newBmi = model.calc(mHeight, kgWeight);
			bmi.set(newBmi);
        });			
    }
    
    public void initialize(){
    	// Bind ViewModel to View
		bmiLabel.textProperty().bind(bmi.asString());
		heightField.textProperty().bindBidirectional(feetHeight, new NumberStringConverter());
		weightField.textProperty().bindBidirectional(poundsWeight, new NumberStringConverter());    	
    }
}