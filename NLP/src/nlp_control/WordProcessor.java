package nlp_control;

import java.util.ArrayList;

import nlp_data_structure.*;
import nlp_ui.ConsoleInteraction;

public class WordProcessor {
	private WordStack ws = new WordStack();
	private ArrayList<PartOfSpeech> words = new ArrayList<>();
	private FileIO io = new FileIO();
	private ConsoleInteraction ci = new ConsoleInteraction();
	private String input;
	
	public void run(){
		ci.output("Hello");
		ci.output("(type below to respond...)");
		input = ci.getInput();
	}
	
	private void parseInput(){
		String[] parsedInput = input.split(" ");//splits the user input by spaces
		for(String s : parsedInput){
			if(s.charAt(s.length()-1) < 65){//finds punctuation attached to the end of words
				//do something with the punctuation
				char punctuation = s.charAt(s.length()-1);
				s = s.substring(0, s.length()-1);
			}
		}
		for(String t : parsedInput){
			words.add(io.wrapWord(t));
		}
	}
}
