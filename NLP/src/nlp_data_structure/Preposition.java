package nlp_data_structure;

public class Preposition extends Word {
	
	Preposition p = null;
	NounPhrase np = null;

	public Preposition(String contents) {
		super(contents);
	}

    public Preposition(NounPhrase y, Preposition x) {
    	super(x.word);
		this.p = x;
		this.np = y;
	}

	@Override
    public String getType() {
        return "Preposition";
    }
}
