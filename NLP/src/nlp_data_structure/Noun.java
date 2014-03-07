package nlp_data_structure;

public class Noun extends Word {

	public Noun(String contents) {
		super(contents);
	}

    @Override
    public String getType() {
        return "Noun";
    }

	@Override
	public String getContentsByObject() {
		// TODO Auto-generated method stub
		return "Noun(\"" + this.word + "\")";
	}
}
