package blocks.computerInfo;

import javax.servlet.http.HttpServletRequest;
import blocks.IBlock;

public class Platform extends ComputerInfo implements IBlock {

	@Override
	public String getShortName() {
		return "Platform";
	}

	@Override
	public String getFullName() {
		return "Computer Platform (ie mac, windows, linux)";
	}

	@Override
	public String getPattern() {
		return "%Plat%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getOS(req);
	}
}
