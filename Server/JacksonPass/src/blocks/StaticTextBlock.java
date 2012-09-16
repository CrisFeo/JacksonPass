package blocks;

import javax.servlet.http.HttpServletRequest;

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

	@Override
	public String getRepresentation(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
