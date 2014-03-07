package nlp_tests;

import java.util.LinkedList;
import java.util.Stack;

import nlp_control.LanguageProcessor;
import nlp_data_structure.*;

public class AlgoTest {

	private LanguageProcessor lp;
	
	public AlgoTest() {
		
		
		LinkedList<PartOfSpeech> LL = new LinkedList<PartOfSpeech>();
		
		LL.offer(new Article("The"));
		LL.offer(new Noun("fish"));
		LL.offer(new Verb("chases"));
//		LL.offer(new Article("the"));
//		LL.offer(new Noun("dog"));
//		LL.offer(new Preposition("with"));
//		LL.offer(new Article("the"));
//		LL.offer(new Noun("cat"));
		
//		System.out.println(LL);
//		
//		System.out.println(LL.peek());
//		
//		Stack<PartOfSpeech> S = new Stack<PartOfSpeech>();
//		
//		S.push(new Article("a"));
//		S.push(new Noun("dog"));
//		
//		System.out.println(S);
//		System.out.println(S.peek());
//		System.out.println(S.get(S.size()-2));
		
		lp = new LanguageProcessor(LL);
		Sentence s = lp.process();
		System.out.println(s);
		System.out.println(s.getContentsByObject());
	}
}
