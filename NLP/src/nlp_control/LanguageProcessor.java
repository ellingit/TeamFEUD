package nlp_control;

import java.util.LinkedList;
import java.util.Stack;

import nlp_data_structure.Sentence;

public class LanguageProcessor {

	private Stack stack;
	private LinkedList inputList;
	
	public LanguageProcessor(LinkedList input) {
		this.stack = new Stack();
		this.inputList = input;
	}
	
	private void setInput(LinkedList input) {
		this.inputList = input;
	}
	
	private Sentence process() {
		return null;
	}
}
