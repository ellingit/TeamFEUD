package nlp_data_structure;

public class NounPhrase {

	private Article a = null;
	private Noun n = null;
	private Preposition p = null;
	//private NounPhrase np = null;
	
	public NounPhrase(Article a, Noun n) {
		this.n = n;
		this.a = a;
	}
	
	public NounPhrase(Article a, Noun n, Preposition p) {
		this.n = n;
		this.a = a;
		this.p = p;
		//this.np = np;
	}
	
	public String toString() {
		if (p == null) {
			return a + " " + n;
		}
		else {
			return a + " " + n + " " + p;
		}
	}
}
