package nlp_tester;

import nlp_control.WordProcessor;
import nlp_data_structure.FileIO;
import nlp_data_structure.PartOfSpeech;

/**
 * Created by Stephen on 3/5/14.
 */
public class TestRunner {
    public static void main(String args[]) {
        run();
    }

    static void run(){
        fileSearcherTest();

    }

    private static void fileSearcherTest() {
        // Expected Result:
        // Console Dialogue: "noun"
        FileIO testIO = new FileIO();
        System.out.println(
                testIO.findInDictionary("Dogs").getType());
        // Actual: null print

        // Expected Result:
        // Null print
        System.out.println(
                testIO.findInDictionary("lkasdfas"));
        // Actual: null print

        // Expected Result:
        // Unknown, handling of multiple strings has yet to be tested
        System.out.println(
                testIO.findInDictionary("Dog and The Flash"));
        // Actual: null print
    }
}
