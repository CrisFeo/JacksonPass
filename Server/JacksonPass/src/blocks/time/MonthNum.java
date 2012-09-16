package blocks.time;

import javax.servlet.http.HttpServletRequest;
import blocks.IBlock;

public class MonthNum extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "Month Num";
	}

	@Override
	public String getFullName() {
		return "Number of Month";
	}

	@Override
	public String getPattern() {
		return "%MonthNum%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("M");
	}
}
