package nlp_data_structure;

public class Verb extends Word {

	public Verb(String contents) {
		super(contents);
	}

    @Override
    public String getType() {
        return "Verb";
    }
}
