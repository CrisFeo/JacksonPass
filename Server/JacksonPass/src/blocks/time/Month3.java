package blocks.time;

import javax.servlet.http.HttpServletRequest;
import blocks.IBlock;

public class Month3 extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "Month 3";
	}

	@Override
	public String getFullName() {
		return "1st 3 Letters of Month";
	}

	@Override
	public String getPattern() {
		return "%Month3%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("MMM");
	}
}
