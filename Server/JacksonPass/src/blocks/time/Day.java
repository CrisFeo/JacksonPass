package blocks.time;

import javax.servlet.http.HttpServletRequest;
import blocks.IBlock;

public class Day extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "Day";
	}

	@Override
	public String getFullName() {
		return "Day of Month";
	}

	@Override
	public String getPattern() {
		return "%Day%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("d");
	}
}
