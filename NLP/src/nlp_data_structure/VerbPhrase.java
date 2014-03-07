package nlp_data_structure;

public class VerbPhrase {
	
	private Verb v = null;
	private NounPhrase np = null;
	
	public VerbPhrase(Verb v) {
		this.v = v;
	}
	
	public VerbPhrase(Verb v, NounPhrase np) {
		this.v = v;
		this.np = np;
	}
	
	public String toString() {
		if (np == null) {
			return v.toString();
		}
		else {
			return v + " " + np;
		}
	}
}
