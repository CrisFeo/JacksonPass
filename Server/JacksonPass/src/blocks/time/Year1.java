package blocks.time;

import javax.servlet.http.HttpServletRequest;
import blocks.IBlock;

public class Year1 extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "Year 1";
	}

	@Override
	public String getFullName() {
		return "Last Digit of Year";
	}

	@Override
	public String getPattern() {
		return "%Year1%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("yy").substring(1);
	}
}
