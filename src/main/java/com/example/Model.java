package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class Model {
	private String filePath = "bmi.txt";
	
	public double load() {
		try {
			return Double.valueOf(Files.readString(Path.of(filePath)));
		} catch (NoSuchFileException e) {
			System.out.println("File not found: " + filePath);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void save(double bmi) {
		try {
			Files.writeString(Path.of(filePath), String.valueOf(bmi));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public double calc(double meterHeight, double kgWeight) {
		double bmi = kgWeight / (meterHeight * meterHeight);
		save(bmi);
		return bmi;
	}	
}
