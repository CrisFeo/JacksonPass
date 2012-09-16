package blocks;

import javax.servlet.http.HttpServletRequest;
import com.sleepycat.persist.model.Persistent;

@Persistent
public class StaticTextBlock implements IBlock{
	private String text;
	
	public StaticTextBlock() {}
	
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
		return "";
	}

	public String getFullName() {
		return "StaticTextBlock";
	}

	public String getPattern() {
		return "";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return text;
	}
	
}
