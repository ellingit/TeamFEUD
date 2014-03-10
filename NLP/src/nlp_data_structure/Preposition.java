package nlp_data_structure;

public class Preposition extends Word {
	
	Preposition p = null;
	NounPhrase np = null;

	public Preposition(String contents) {
		super(contents);
	}

    public Preposition(Preposition x, NounPhrase y) {
    	super(x.word);
		this.p = x;
		this.np = y;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContentsByObject() {
		if (np == null) {
			return "Preposition(\"" + this.word + "\")";
		}
		else {
			return p.getContentsByObject() + " " + np.getContentsByObject();
		}
		
	}
}
