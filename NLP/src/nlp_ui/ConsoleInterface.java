package nlp_ui;

import java.util.Scanner;

public class ConsoleInterface implements UserInterface {
	private Scanner scanLee = new Scanner(System.in);

	@Override
	public String getInput() {
		if ( scanLee.hasNextLine() ) {
			return scanLee.nextLine();
		}
		return null;
	}

	@Override
	public void output( String output ){
		System.out.println(output);
	}
}
