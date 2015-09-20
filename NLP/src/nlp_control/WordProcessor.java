package nlp_control;

import nlp_data_structure.PartOfSpeech;
import nlp_data_structure.Sentence;
import nlp_ui.ConsoleInterface;
import nlp_ui.UserInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WordProcessor {
    private static final int RESPONSE_COUNT = 8;
    private final WordWrapper io = new WordWrapper();
    private UserInterface userInterface;
    private LanguageProcessor lp;

    private Sentence currentSentence;

    public WordProcessor() {
        userInterface = new ConsoleInterface();
    }

    public WordProcessor(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void run() {
        String input;
        do {
            userInterface.output("...");
            input = userInterface.getInput().toLowerCase();
        } while ( input.length() < 1 );

        lp = new LanguageProcessor(parseInput(input));
        currentSentence = lp.process();
        respond();
    }

    private LinkedList<PartOfSpeech> parseInput( String test ) {
        String[] parsedInput = test.split(" ");
        for ( int i = 0; i < parsedInput.length; i++ ) {
            if ( parsedInput.length >= 1 ) {
                if ( parsedInput[i].charAt(parsedInput[i].length() - 1) < 'A' ) {
                    //finds punctuation attached to the end of words
                    // The above ^^^^ is never utilized
//                    char punctuation = parsedInput[i].charAt(parsedInput[i].length() - 1);
                    parsedInput[i] = parsedInput[i].substring(0, parsedInput[i].length() - 1);
                }
            }
        }
        ArrayList<PartOfSpeech> posArray = io.handleInputViaDictionary(parsedInput);
        List<PartOfSpeech> collect = posArray.stream().filter(pos -> pos != null).collect(Collectors.toList());
        return new LinkedList<>(collect);
    }

    private String respond() {
        String response = "";
        Random choice = new Random();
        if ( currentSentence != null ) {
            int selection = choice.nextInt(RESPONSE_COUNT);
            String nounPhrase = currentSentence.getNP();
            String verbPhrase = currentSentence.getVP();
            String noun, verb, infiniteVerb, continuousVerb;

            String[] verbPhraseSplit = verbPhrase.split(" "),
                    nounPhraseSplit = nounPhrase.split(" ");

            noun = nounPhraseSplit.length > 1 ? nounPhraseSplit[1] : nounPhrase;
            verb = verbPhraseSplit.length > 1 ? verbPhraseSplit[0] : verbPhrase;

            // FIXME: this is not very explanatory
            if ( verb.charAt(verb.length() - 1) == 's' ) {
                verb = verb.substring(0, verb.length() - 1);
            }
            else if ( verb.substring(verb.length() - 2, verb.length()).equals("ed") ) {
                verb = verb.substring(0, verb.length() - 2);
            }
            else if ( verb.length() > 2 && verb.substring(verb.length() - 3, verb.length()).equals("ing") ) {
                verb = verb.substring(0, verb.length() - 3);
            }
            /*else {
                System.err.println("No cases matched for verb '" + verb + "' and noun '" + noun + "'");
            }*/

            infiniteVerb = "to " + verb;

            continuousVerb = verb.charAt(verb.length() - 1) == 'e'
                    ? verb.substring(0, verb.length() - 1) + "ing"
                    : verb + "ing";

            if ( verbPhraseSplit.length > 1 ) {
                int wordCount = verbPhraseSplit.length;
                for ( int i = 1; i < wordCount; i++ ) {
                    verb += " " + verbPhraseSplit[i];
                }
            }

            boolean npEndsInAnS = nounPhrase.charAt(nounPhrase.length() - 1) != 's';

            String singularNoun = noun.substring(0, noun.length() - 1);
            switch (selection) {
                case 0:
                    if ( npEndsInAnS )
                        userInterface.output(nounPhrase + " sounds interesting. Tell me more.");
                    else userInterface.output(nounPhrase + " sound interesting. Tell me more.");
                    break;
                case 1:
                    if ( noun.charAt(noun.length() - 1) != 's' ) userInterface.output(noun + "s are my favorite!");
                    else userInterface.output(noun + " are my favorite!");
                    break;
                case 2:
                    if ( npEndsInAnS )
                        userInterface.output("I used to be a " + noun + " then I got lost in this computer.");
                    else
                        userInterface.output("I used to be a " + singularNoun + " then I got lost in this computer.");
                    break;
                case 3:
                    userInterface.output("I have nothing to say about that.");
                    break;
                case 4:
                    if ( noun.charAt(noun.length() - 1) != 's' ) userInterface.output("Your face is a " + noun + "!");
                    else userInterface.output("Your face is a " + singularNoun + "!");
                    break;
                case 5:
                    for ( int i = 1; i < verbPhraseSplit.length; i++ ) {
                        infiniteVerb += " " + verbPhraseSplit[i];
                    }
                    userInterface.output("I like " + infiniteVerb + ".");
                    break;
                case 6:
                    for ( int i = 1; i < verbPhraseSplit.length; i++ ) {
                        continuousVerb += " " + verbPhraseSplit[i];
                    }
                    if ( npEndsInAnS )
                        userInterface.output("Why was " + nounPhrase + " " + continuousVerb + "?");
                    else userInterface.output("Why were " + nounPhrase + " " + continuousVerb + "?");
                    break;
                case 7:
                    String[] verbSplit = verb.split(" ");
                    if ( npEndsInAnS )
                        userInterface.output("I'll " + verbSplit[0] + " your " + noun + "!");
                    else
                        userInterface.output("I'll " + verbSplit[0] + " your " + singularNoun + "!");
                    break;
                default:
                    userInterface.output(String.format("Response choice #%d was no found", selection));
                    break;
            }
        }
        else userInterface.output("I don't understand.");
        return response;
    }
}
