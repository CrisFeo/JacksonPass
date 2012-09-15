package blocktypes;
public class StaticTextBlock implements IBlock{
	private String text;
	
	public StaticTextBlock(String text)
	{
		this.text = text;
	}
	
	public String getCategory() {
		return "";
	}

	public String getRepresentation() {
		return text;
	}

	public String getShortName() {
		return null;
	}

	public String getFullName() {
		return null;
	}

	public String getPattern() {
		return null;
	}
	
}
