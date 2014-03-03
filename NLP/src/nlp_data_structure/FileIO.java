package nlp_data_structure;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileIO {
	private File nouns = new File("nouns.txt");
	private File verbs = new File("verbs.txt");
	private File articles = new File("articles.txt");
	private File prepositions = new File("prepositions.txt");
	private File punctuations = new File("punctuations.txt");
	private File[] wordTypes = new File[] {nouns, verbs,
            articles, prepositions, punctuations};

    public PartOfSpeech findInDictionary(String wordToCheck) {
        PartOfSpeech returner = null;
        for(File file : wordTypes) {
            try {
                if(Files.readAllLines(file.toPath(), StandardCharsets.US_ASCII).contains(wordToCheck)) {
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
}
