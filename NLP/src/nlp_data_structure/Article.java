package nlp_data_structure;

public class Article extends Word {

	public Article(String contents) {
		super(contents);
	}

    @Override
    public String getType() {
        return "Article";
    }
}
