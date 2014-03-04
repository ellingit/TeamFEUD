package nlp_control;

import java.util.ArrayList;

import nlp_data_structure.*;
import nlp_ui.ConsoleInteraction;

public class WordProcessor {
	private WordStack ws = new WordStack();
	private ArrayList<PartOfSpeech> elements = new ArrayList<>();
	private ArrayList<String> unknowns = new ArrayList<>();
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
				char punctuation = s.charAt(s.length()-1);
				s = s.substring(0, s.length()-1) + " " + punctuation;
			}
		}
		for(String wordToCheck : parsedInput){
			PartOfSpeech pos = io.findInDictionary(wordToCheck);
			if(pos != null) elements.add(pos);
			else unknowns.add(wordToCheck);
		}
	}
}
