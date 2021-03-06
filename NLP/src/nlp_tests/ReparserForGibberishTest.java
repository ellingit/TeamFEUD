package nlp_tests;

import java.util.ArrayList;

import nlp_control.WordWrapper;
import nlp_data_structure.PartOfSpeech;

public class ReparserForGibberishTest {
	WordWrapper temp = new WordWrapper();
	
	public ReparserForGibberishTest(){
		ArrayList<PartOfSpeech> printable = 
				temp.handleInputViaDictionary("the dog bites".split(" "));
//		if (printable != null) {
			// Expect the program to return the valid collection of PoS and print their types as proofs
			for (PartOfSpeech partOfSpeech : printable) {
				System.out.println(partOfSpeech.getContentsByObject());
			}
        
	}
}
