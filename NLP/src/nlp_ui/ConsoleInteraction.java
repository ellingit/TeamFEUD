package nlp_ui;

import java.util.Scanner;

public class ConsoleInteraction {
	private Scanner scanLee = new Scanner(System.in);
	private String input, output;
	
	public String getInput(){
		if(scanLee.hasNextLine()) return scanLee.nextLine();
		else return null;
	}
	public void output(String output){
		System.out.println(output);
	}
}
