package nlp_control;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import nlp_data_structure.PartOfSpeech;
import nlp_data_structure.Sentence;

public class LanguageProcessor {

	private Stack stack;
	private Queue<PartOfSpeech> inputList;
	
	public LanguageProcessor(LinkedList<PartOfSpeech> input) {
		this.stack = new Stack();
		this.inputList = input;
	}
	
	private void setInput(LinkedList<PartOfSpeech> input) {
		this.inputList = input;
	}
	
	public Sentence process() {
		return null;
	}
}
