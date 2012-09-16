package blocks.time;

import javax.servlet.http.HttpServletRequest;
import blocks.IBlock;

public class Day2 extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "Day 2";
	}

	@Override
	public String getFullName() {
		return "1st 2 Letters of Day of Week";
	}

	@Override
	public String getPattern() {
		return "%Day2%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("EEE").substring(0, 2);
	}
}
