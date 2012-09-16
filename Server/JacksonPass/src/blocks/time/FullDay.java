package blocks.time;

import javax.servlet.http.HttpServletRequest;
import blocks.IBlock;

public class FullDay extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "Full Day";
	}

	@Override
	public String getFullName() {
		return "Full Day of Week";
	}

	@Override
	public String getPattern() {
		return "%FullDay%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("EEEE");
	}
}
