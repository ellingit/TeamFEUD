package nlp_tests;

import java.util.ArrayList;

import nlp_data_structure.FileIO;
import nlp_data_structure.PartOfSpeech;

public class ReparserForGibberishTest {
	FileIO temp = new FileIO();
	
	public ReparserForGibberishTest(){
		ArrayList<PartOfSpeech> printable = 
				temp.handleInputViaDictionary("the dog bites".split(" "));
//		if (printable != null) {
			// Expect the program to return the valid collection of PoS and print their types as proofs
			for (PartOfSpeech partOfSpeech : printable) {
				System.out.println(partOfSpeech.getType() + " " + partOfSpeech.toString());
			}
		
	}
}
