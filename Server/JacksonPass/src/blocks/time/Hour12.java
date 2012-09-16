package blocks.time;

import javax.servlet.http.HttpServletRequest;
import blocks.IBlock;

public class Hour12 extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "12 Hour";
	}

	@Override
	public String getFullName() {
		return "Hour in 12hr Format";
	}

	@Override
	public String getPattern() {
		return "%Hour12%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("h");
	}
}
