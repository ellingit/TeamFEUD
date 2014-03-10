package nlp_control;

import nlp_tests.AlgoTest;

public class Driver {

	public static void main(String[] args) {
		for(int i=0; i<15; i++){
			new WordProcessor().run();
		}
//		new AlgoTest();
	}

}
