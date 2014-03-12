package nlp_control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import nlp_data_structure.Article;
import nlp_data_structure.Noun;
import nlp_data_structure.PartOfSpeech;
import nlp_data_structure.Preposition;
import nlp_data_structure.Punctuation;
import nlp_data_structure.Verb;

public class WordWrapper {
	private final File nouns = new File("Dictionary Files//nouns.txt");
	private final File verbs = new File("Dictionary Files//verbs.txt");
	private final File articles = new File("Dictionary Files//articles.txt");
	private final File prepositions = new File("Dictionary Files//prepositions.txt");
	private final File punctuations = new File("Dictionary Files//punctuations.txt");
	private final File[] wordTypes = new File[] {nouns, verbs, articles,
            prepositions, punctuations};

	public ArrayList<PartOfSpeech> handleInputViaDictionary(String[] inputString) {
		ArrayList<PartOfSpeech> suggestedPartsOfSpeech = new ArrayList<>();
        for (int i = 0, j = inputString.length; i < j; i++) {
            try{
                suggestedPartsOfSpeech.add(
                    findInDictionary(inputString[i])
                );
            } catch(NullPointerException e) {
                System.err.println("The word " + inputString[i] + " as typed does not exist in the file.");
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
	
    public PartOfSpeech findInDictionary(String wordToCheck) {
        PartOfSpeech returner = null;
        looping:
        for(File file : wordTypes) {
            try {
            	List<String> fileAsList = Files.readAllLines(file.toPath(), StandardCharsets.US_ASCII);
                if(fileAsList.contains(wordToCheck) || fileAsList.contains(wordToCheck + "s")) {
                    switch (file.getName()) {
	                    case "articles.txt":
	                        returner = new Article(wordToCheck);
	                        return returner;
                        case "nouns.txt":
                            returner = new Noun(wordToCheck);
                            return returner;
                        case "verbs.txt":
                            returner = new Verb(wordToCheck);
                            return returner;
                        case "prepositions.txt":
                            returner = new Preposition(wordToCheck);
                            return returner;
                        case "punctuations.txt":
                            returner = new Punctuation();
                            return returner;
                        default:
                        	System.out.println("nothing");
                            returner = null;
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
        return returner;
    }
    
    private boolean checkVerb(PartOfSpeech p) {
    	boolean isVerb = false;
    	try {
    		List<String> fileAsList = Files.readAllLines(verbs.toPath(), StandardCharsets.US_ASCII);
			 isVerb = fileAsList.contains(p.toString()) || fileAsList.contains(p.toString() + "s");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("I don't know what " + p.toString() + " means.\nIs it meant to be a verb?");
		}
    	return isVerb;
    }
    
    private boolean checkNoun(PartOfSpeech p) {
    	boolean isNoun = false;
    	try {
    		List<String> fileAsList = Files.readAllLines(nouns.toPath(), StandardCharsets.US_ASCII);
			 isNoun = fileAsList.contains(p.toString()) || fileAsList.contains(p.toString() + "s");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch(Exception e) {
			System.err.println("I don't know what " + p.toString() + " means.");
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
