package nlp_data_structure;

public class Sentence {
	
	private NounPhrase np;
	private VerbPhrase vp;
	
	public Sentence (NounPhrase np, VerbPhrase vp) {
		this.np = np;
		this.vp = vp;
	}
	
	public String toString() {
		return np + " " + vp;
	}

}
