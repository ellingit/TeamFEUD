package nlp_data_structure;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileIO {
<<<<<<< HEAD
	private File nouns = new File("Dictionary Files//nouns.txt");
	private File verbs = new File("Dictionary Files//verbs.txt");
	private File articles = new File("Dictionary Files//articles.txt");
	private File prepositions = new File("Dictionary Files//prepositions.txt");
	private File punctuations = new File("Dictionary Files//punctuations.txt");
=======
	private File nouns = new File("nouns.txt");
	private File verbs = new File("verbs.txt");
	private File articles = new File("articles.txt");
	private File prepositions = new File("prepositions.txt");
	private File punctuations = new File("punctuations.txt");
<<<<<<< HEAD
	private File[] wordTypes = new File[] {nouns, verbs,
            articles, prepositions, punctuations};

    private PartOfSpeech findInDictionary(String wordToCheck) {
        PartOfSpeech returner = null;
        for(File file : wordTypes) {
            try {
                if(Files.readAllLines(file.toPath()).contains(wordToCheck)) {
                    switch (file.getName()) {
                        case "nouns.txt":
                            returner = new Noun(wordToCheck);
                        case "verbs.txt":
                            returner = new Verb(wordToCheck);
                        case "articles.txt":
                            returner = new Article(wordToCheck);
                        case "prepositions.txt":
                            returner = new Preposition(wordToCheck);
                        case "punctuations.txt":
                            returner = new Punctuation();
                        default:
                            returner = null;
                    }
                }
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
        return returner;
    }

=======
	
	public PartOfSpeech wrapWord(String word){
		
	}
>>>>>>> 8b29355275838d368752864233a0783122ea6545
}
