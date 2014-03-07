package nlp_data_structure;

public abstract class Word extends PartOfSpeech {
	public final String word;
	
	public Word(String contents){
		word = contents;
	}
	
	public String toString() {
		return this.word;
	}
}
