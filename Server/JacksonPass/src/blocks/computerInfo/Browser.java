package blocks.computerInfo;

import javax.servlet.http.HttpServletRequest;
import blocks.IBlock;

public class Browser extends ComputerInfo implements IBlock {

	@Override
	public String getShortName() {
		return "Browser";
	}

	@Override
	public String getFullName() {
		return "Current Browser (ie chrome, safari)";
	}

	@Override
	public String getPattern() {
		return "%Browser%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getBrowser(req);
	}
}
