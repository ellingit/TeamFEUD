package nlp_control;

import nlp_data_structure.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordWrapper {
	private final static File nouns = new File("Dictionary Files//nouns.txt");
	private final static File verbs = new File("Dictionary Files//verbs.txt");
	private final static File articles = new File("Dictionary Files//articles.txt");
	private final static File prepositions = new File("Dictionary Files//prepositions.txt");
	private final static File punctuations = new File("Dictionary Files//punctuations.txt");
	private final static File[] wordTypes = new File[] {articles, verbs, nouns,
            prepositions, punctuations};
	private final static HashMap<String, List<String>> dictionary = new HashMap<>();

	{
		try {
			dictionary.put("nouns", Files.readAllLines(nouns.toPath(), StandardCharsets.US_ASCII));
			dictionary.put("verbs", Files.readAllLines(verbs.toPath(), StandardCharsets.US_ASCII));
			dictionary.put("articles", Files.readAllLines(articles.toPath(), StandardCharsets.US_ASCII));
			dictionary.put("prepositions", Files.readAllLines(prepositions.toPath(), StandardCharsets.US_ASCII));
			dictionary.put("punctuations", Files.readAllLines(punctuations.toPath(), StandardCharsets.US_ASCII));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<PartOfSpeech> handleInputViaDictionary(String[] inputString) {
		ArrayList<PartOfSpeech> suggestedPartsOfSpeech = new ArrayList<>();
		for ( String anInputString : inputString ) {
			try {
				suggestedPartsOfSpeech.add(findInDictionary(anInputString));
			}
			catch (NullPointerException e) {
				System.err.println("The word " + anInputString + " as typed does not exist in the file.");
			}
		}

        for (int i = 1, j = suggestedPartsOfSpeech.size(); i < j; i++) {
        	if (suggestedPartsOfSpeech.get(i) instanceof Verb) {
				if (suggestedPartsOfSpeech.get(i - 1) instanceof Article) {
					// Replace the improper verb with its noun form. 
					suggestedPartsOfSpeech.set(i, new Noun(suggestedPartsOfSpeech.get(i).toString()));
				}
			}
        	if(i >= 2) {
            	if(suggestedPartsOfSpeech.get(i-2) instanceof Article && suggestedPartsOfSpeech.get(i-1) instanceof Noun) {
            		if (checkNoun(suggestedPartsOfSpeech.get(i))) {
						// When we have stand sentence order, then proceeded by a second noun
						if (checkVerb(suggestedPartsOfSpeech.get(i))) {
							// if the PoS is in the verbs file, not a noun (thus not being gibberish),
							// make the false noun a verb
							suggestedPartsOfSpeech.set(i, new Verb(
									suggestedPartsOfSpeech.get(i).toString()));
						}
					}
            	}
        	}
        }
        
        return suggestedPartsOfSpeech;
    }	

	//
	/*
	This algorithm complexity is BEST CASE
	* 1 (it's in the first file) ) * the following:
	* + m
	* + m * length (contains() looks at m items and compares against <code>length</code> characters
	Worst case is 24 (8 * 3) that ^^^^
	* where
	* length = wordToCheck.length
	* m = lines in the current file
	*/
    public PartOfSpeech findInDictionary(String wordToCheck) {
		for ( Map.Entry<String, List<String>> keyValPairs: dictionary.entrySet() ) {
			PartOfSpeech returner = null;
			StringBuilder checker = new StringBuilder();
			char[] tempCharArray = wordToCheck.toCharArray();

			if ( tempCharArray[tempCharArray.length - 1] == 's' ) { // this is an effective 'substring()' call
				for ( int i = 0; i < tempCharArray.length - 1; i++ ) {
					checker.append(tempCharArray[i]);
				}
			}
			List<String> fileAsList = keyValPairs.getValue();

			if ( fileAsList.contains(wordToCheck) || fileAsList.contains(wordToCheck + "s") || fileAsList.contains(checker.toString()) ) {

				switch (keyValPairs.getKey()) {
					case "articles":
						returner = new Article(wordToCheck);
						break;
					case "nouns":
						returner = new Noun(wordToCheck);
						break;
					case "verbs":
						returner = new Verb(wordToCheck);
						break;
					case "prepositions":
						returner = new Preposition(wordToCheck);
						break;
					case "punctuations":
						returner = new Punctuation();
						break;
					default:
						System.err.println(String.format("%s wasn't found in any dictionaries", wordToCheck));
						break;
				}

				return returner;
			}
		}
        return null;
    }
    
    private boolean checkVerb(PartOfSpeech p) {
    	boolean isVerb = false;
    	try {
    		List<String> fileAsList = Files.readAllLines(verbs.toPath(), StandardCharsets.US_ASCII);
			 isVerb = fileAsList.contains(p.toString()) || fileAsList.contains(p.toString() + "s");
		} catch (IOException e) {
//			System.err.println("I don't know what " + p.toString() + " means.\nIs it meant to be a verb?");
		}
    	return isVerb;
    }
    
    private boolean checkNoun(PartOfSpeech p) {
    	boolean isNoun = false;
    	try {
    		String checker = "";
    		char[] tempCharArray = p.toString().toCharArray();
    		if(tempCharArray[tempCharArray.length-1] == 's'){
    			for (int i = 0; i < tempCharArray.length - 1; i++) {
					checker += tempCharArray[i];
				}
    		}
    		List<String> fileAsList = Files.readAllLines(nouns.toPath(), StandardCharsets.US_ASCII);
			 if(checker.length() > 0){
                 isNoun = fileAsList.contains(p.toString()) || fileAsList.contains(p.toString() + "s")
					 || fileAsList.contains(checker);
             } else {
                 isNoun = fileAsList.contains(p.toString()) || fileAsList.contains(p.toString() + "s");
             }
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch(Exception e) {
//			System.err.println("I don't know what " + p.toString() + " means.");
		}
    	return isNoun;
    }
    
    public void append(String toAppend, String word){
		try{
			OutputStream fileOut = new FileOutputStream(toAppend, true);
			fileOut.write(word.getBytes());
			fileOut.close();
			} catch (Exception e) {
				System.out.println("File append dint work nub.");
			}
	}
}
