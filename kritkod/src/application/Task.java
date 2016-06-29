package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
	private String label = "";
	private String description = "";
	Date startDate = new Date();
	SimpleDateFormat startDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");

	public void setLabel() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		label = br.readLine();
	}
	
	public void setDescription() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		description = br.readLine();
	}
	
	public void setStartDate() {
		
		//»—œ–¿¬»“‹!!11
		startDate = new Date();
		
	}

	
	public String getStartDate() {
		
		return startDateFormat.format(startDate);
	}
	
	public String getLabel() {
		
		return label;
	}
	
	public String getDescription() {
		
		return description;
	}
}