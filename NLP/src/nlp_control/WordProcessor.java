package nlp_control;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import nlp_data_structure.FileIO;
import nlp_data_structure.PartOfSpeech;
import nlp_data_structure.Sentence;
import nlp_data_structure.WordStack;
import nlp_ui.ConsoleInteraction;

public class WordProcessor {
	private WordStack ws = new WordStack();
	private LinkedList<PartOfSpeech> elements = new LinkedList<>();
	private ArrayList<String> unknowns = new ArrayList<>();
	private final FileIO io = new FileIO();
	private ConsoleInteraction ci = new ConsoleInteraction();
	private LanguageProcessor lp = new LanguageProcessor();
	private Sentence currentSent;
	private String input;
	
	public void run(){
		ci.output("Hello");
		ci.output("(type below to respond...)");
		input = ci.getInput().toLowerCase();
        parseInput(input);
        for (PartOfSpeech p : elements) {
        	System.out.println(p.getContentsByObject());
        }
        lp.setInput(elements);
        currentSent = lp.process();
        respond();
	}

    private void parseInput(String test) {
        String[] parsedInput = test.split(" ");//splits the user input by spaces
        for(int i=0; i<parsedInput.length; i++){
            if (parsedInput.length >= 1) {
				if (parsedInput[i].charAt(parsedInput[i].length() - 1) < 'A') {//finds punctuation attached to the end of words
					char punctuation = parsedInput[i].charAt(parsedInput[i].length() - 1);
					parsedInput[i] = parsedInput[i].substring(0, parsedInput[i].length() - 1);
				}
			}
        }
        ArrayList<PartOfSpeech> posArray = io.handleInputViaDictionary(parsedInput);
        for(PartOfSpeech pos : posArray){
            if(pos != null) elements.offer(pos);
//            else unknowns.add(pos.toString());
        }
    }
    
    private String respond(){
    	String response = "";
    	Random choice = new Random();
    	if(currentSent != null){
    		int selection = choice.nextInt(8);
    		String nounPhrase = currentSent.getNP();
    		String verbPhrase = currentSent.getVP();
    		String noun = "", verb = "", infiniteVerb = "", continuousVerb = "";
    		if(nounPhrase.split(" ").length > 1){ noun = nounPhrase.split(" ")[1]; }
    		else noun = nounPhrase;
    		if(verbPhrase.split(" ").length > 1){
    			int wordCount = verbPhrase.split(" ").length;
    			verb = verbPhrase.split(" ")[0];
    			if(verb.charAt(verb.length()-1) == 's'){ verb = verb.substring(0, verb.length()-1); }
        		else if(verb.substring(verb.length()-2, verb.length()).equals("ed")){ verb = verb.substring(0, verb.length()-2); }
        		else if(verb.substring(verb.length()-3, verb.length()).equals("ing")){ verb = verb.substring(0, verb.length()-3); }
        		else{}
    			infiniteVerb = "to " + verb;
    			if(verb.charAt(verb.length()-1) == 'e') continuousVerb = verb.substring(0, verb.length()-1) + "ing";
    			else continuousVerb = verb + "ing";
    			for(int i=1; i<wordCount; i++){
					verb += " " + verbPhrase.split(" ")[i];
				}
    		}
    		else verb = verbPhrase;
    		
    		switch(selection){
    		case 0:
    			if(nounPhrase.charAt(nounPhrase.length()-1) != 's') ci.output(nounPhrase + " sounds interesting. Tell me more.");
    			else ci.output(nounPhrase + " sound interesting. Tell me more.");
    			break;
    		case 1:
    			if(noun.charAt(noun.length()-1) != 's') ci.output(noun + "s are my favorite!");
    			else ci.output(noun + " are my favorite!");
    			break;
    		case 2:
    			if(nounPhrase.charAt(nounPhrase.length()-1) != 's') ci.output("I used to be a " + noun + " then I got lost in this computer.");
    			else ci.output("I used to be a " + noun.substring(0, noun.length()-1) + " then I got lost in this computer.");
    			break;
    		case 3:
    			ci.output("I have nothing to say about that.");
    			break;
    		case 4:
    			if(noun.charAt(noun.length()-1) != 's') ci.output("Your face is a " + noun + "!");
    			else ci.output("Your face is a " + noun.substring(0, noun.length()-1) + "!");
    			break;
    		case 5:
    			ci.output("I like " + infiniteVerb + ".");
    			break;
    		case 6:
    			for(int i=1; i<verbPhrase.split(" ").length; i++){
					continuousVerb += " " + verbPhrase.split(" ")[i];
				}
    			if(nounPhrase.charAt(nounPhrase.length()-1) != 's') ci.output("Why was " + nounPhrase + " " + continuousVerb + "?");
    			else ci.output("Why were " + nounPhrase + " " + continuousVerb + "?");
    			break;
    		case 7:
    			if(nounPhrase.charAt(nounPhrase.length()-1) != 's') ci.output("I'll " + verb.split(" ")[0] + " your " + noun + "!");
    			else ci.output("I'll " + verb.split(" ")[0] + " your " + noun.substring(0, noun.length()-1) + "!");
    			break;
    		default:
    			break;
    		}
    	}
    	else ci.output("I don't understand.");
    	return response;
    }
}
