package nlp_data_structure;

import com.sun.swing.internal.plaf.synth.resources.synth_sv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileIO {
	private File nouns = new File("Dictionary Files//nouns.txt");
	private File verbs = new File("Dictionary Files//verbs.txt");
	private File articles = new File("Dictionary Files//articles.txt");
	private File prepositions = new File("Dictionary Files//prepositions.txt");
	private File punctuations = new File("Dictionary Files//punctuations.txt");
	private File[] wordTypes = new File[] {nouns, verbs,
            articles, prepositions, punctuations};

    public PartOfSpeech findInDictionary(String wordToCheck) {
        PartOfSpeech returner = null;
        looping:
        for(File file : wordTypes) {
            try {
                if(Files.readAllLines(file.toPath(), StandardCharsets.US_ASCII).contains(wordToCheck.toLowerCase())) {
                    switch (file.getName()) {
                        case "Dictionary Files//nouns.txt":
                            returner = new Noun(wordToCheck);
                            return returner;
                        case "Dictionary Files//verbs.txt":
                            returner = new Verb(wordToCheck);
                            return returner;
                        case "Dictionary Files//articles.txt":
                            returner = new Article(wordToCheck);
                            return returner;
                        case "Dictionary Files//prepositions.txt":
                            returner = new Preposition(wordToCheck);
                            return returner;
                        case "Dictionary Files//punctuations.txt":
                            returner = new Punctuation();
                            return returner;
                        default:
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
