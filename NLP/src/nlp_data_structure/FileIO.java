package nlp_data_structure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
	private final File nouns = new File("Dictionary Files//nouns.txt");
	private final File verbs = new File("Dictionary Files//verbs.txt");
	private final File articles = new File("Dictionary Files//articles.txt");
	private final File prepositions = new File("Dictionary Files//prepositions.txt");
	private final File punctuations = new File("Dictionary Files//punctuations.txt");
	private final File[] wordTypes = new File[] {nouns, verbs, articles,
            prepositions, punctuations};

	public void handleInputViaDictionary(String[] inputString) {
		ArrayList<PartOfSpeech> suggestedPartsOfSpeech = new ArrayList<>();
        for (int i = 0, j = inputString.length; i < j; i++) {
            suggestedPartsOfSpeech.add(
                    findInDictionary(inputString[i])
            );
        }

        for (int i = 1, j = suggestedPartsOfSpeech.size(); i < j; i++) {
        	if (suggestedPartsOfSpeech.get(i) instanceof Verb) {
				if (suggestedPartsOfSpeech.get(i - 1) instanceof Article) {
					// Replace the improper verb with its noun form. 
					suggestedPartsOfSpeech.set(i, new Noun(suggestedPartsOfSpeech.get(i).toString()));
				}
			}
        }

    }	
	
    public PartOfSpeech findInDictionary(String wordToCheck) {
        PartOfSpeech returner = null;
        looping:
        for(File file : wordTypes) {
            try {
            	List<String> fileAsList = Files.readAllLines(file.toPath(), StandardCharsets.US_ASCII);
                if(fileAsList.contains(wordToCheck) || fileAsList.contains(wordToCheck + "s")) {
                    switch (file.getName()) {
                        case "nouns.txt":
                            returner = new Noun(wordToCheck);
                            return returner;
                        case "verbs.txt":
                            returner = new Verb(wordToCheck);
                            return returner;
                        case "articles.txt":
                            returner = new Article(wordToCheck);
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
