package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Model {
	public DoubleProperty cmHeight = new SimpleDoubleProperty();
	public DoubleProperty kgHeight = new SimpleDoubleProperty();
	public DoubleProperty bmi = new SimpleDoubleProperty();
	
	private String filePath = "bmi.txt";
	
	private void load() {
		try {
			bmi.set(Double.valueOf(Files.readString(Path.of(filePath))));
		} catch (NoSuchFileException e) {
			System.out.println("File not found: " + filePath);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			Files.writeString(Path.of(filePath), bmi.getValue().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void calc() {
		double height = cmHeight.getValue() / 100;
		double weight = kgHeight.getValue();
		bmi.set(Math.round(weight / (height * height)));
		save();
	}
	
	public Model() {
		load();
	}
	
}
