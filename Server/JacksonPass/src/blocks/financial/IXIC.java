package blocks.financial;

import javax.servlet.http.HttpServletRequest;
import blocks.IBlock;

public class IXIC extends Financial implements IBlock {

	@Override
	public String getShortName() {
		return "Nasdaq";
	}

	@Override
	public String getFullName() {
		return "Stock Change (ie '+' or '-')";
	}

	@Override
	public String getPattern() {
		return "%IXIC%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getChange("^IXIC");
	}
}