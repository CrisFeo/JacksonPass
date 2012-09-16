package blocks.time;

import javax.servlet.http.HttpServletRequest;
import blocks.IBlock;

public class Day3 extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "Day 3";
	}

	@Override
	public String getFullName() {
		return "1st 3 Letters of Day of Week";
	}

	@Override
	public String getPattern() {
		return "%Day3%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("EEE");
	}
}
