package nlp_control;

import nlp_data_structure.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LanguageProcessor {

	private Stack stack;
	private Queue inputList;
	
	public LanguageProcessor(LinkedList<PartOfSpeech> input) {
		this.stack = new Stack();
		this.inputList = input;
	}
	
	public LanguageProcessor(){
		this.stack = new Stack();
	}
	
	public void setInput(LinkedList<PartOfSpeech> input) {
		this.inputList = input;
	}
	
	public Sentence process() {
		boolean canReduce;
		
		while(!inputList.isEmpty()) {
			
			stack.push(inputList.poll());
			canReduce = true;
			
			while (canReduce) {
				
				if((stack.size() > 1) 
						&& (stack.get(stack.size()-2)) instanceof NounPhrase 
						&& stack.peek() instanceof VerbPhrase) 
				{
					VerbPhrase x = (VerbPhrase)stack.pop();
					NounPhrase y = (NounPhrase)stack.pop();
					stack.push(new Sentence(y,x));
					
				}
				else if((stack.size() > 1)
						&& (stack.get(stack.size()-2)) instanceof Article
						&& (stack.peek() instanceof Noun
						&& !(inputList.peek() instanceof Preposition))) 
				{
					Noun x = (Noun)stack.pop();
					Article y = (Article)stack.pop();
					stack.push(new NounPhrase(y,x));
				}
				else if((stack.size() > 2 && inputList.isEmpty())
						&& (stack.get(stack.size()-3)) instanceof Article
						&& (stack.get(stack.size()-2)) instanceof Noun
						&& stack.peek() instanceof Preposition) 
				{
					Preposition x = (Preposition)stack.pop();
					Noun y = (Noun)stack.pop();
					Article z = (Article)stack.pop();
					stack.push(new NounPhrase(z,y,x));
				}
				else if (stack.peek() instanceof Verb
						&& !(inputList.peek() instanceof Article)
						&& !(inputList.peek() instanceof Noun))
				{
					Verb x = (Verb)stack.pop();
					stack.push(new VerbPhrase(x));
				}
				else if((stack.size() > 1) 
						&& (stack.get(stack.size()-2)) instanceof Verb 
						&& stack.peek() instanceof NounPhrase) 
				{
					NounPhrase x = (NounPhrase)stack.pop();
					Verb y = (Verb)stack.pop();
					stack.push(new VerbPhrase(y,x));
				}
				else if((stack.size() > 1) 
						&& (stack.get(stack.size()-2)) instanceof Preposition 
						&& stack.peek() instanceof NounPhrase)
				{
					NounPhrase x = (NounPhrase)stack.pop();
					Preposition y = (Preposition)stack.pop();
					stack.push(new Preposition(y,x));
				}
				else {
					canReduce = false;
				}
			}//end reduce loop
		}//end pushLoop
		
		if (stack.peek() instanceof Sentence) {
			return (Sentence) stack.pop();
		}
		else {
			return null;
		}
	}
}
