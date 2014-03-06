package nlp_data_structure;

public class NounPhrase {

	private Article a = null;
	private Noun n = null;
	private Preposition p = null;
	private NounPhrase np = null;
	
	public NounPhrase(Noun n, Article a) {
		this.n = n;
		this.a = a;
	}
	
	public NounPhrase(Noun n, Article a, Preposition p, NounPhrase np) {
		this.n = n;
		this.a = a;
		this.p = p;
		this.np = np;
	}
}
