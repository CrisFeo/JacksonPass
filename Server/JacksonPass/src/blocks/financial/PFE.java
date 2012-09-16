package blocks.financial;

import javax.servlet.http.HttpServletRequest;
import blocks.IBlock;

public class PFE extends Financial implements IBlock {

	@Override
	public String getShortName() {
		return "PFE";
	}

	@Override
	public String getFullName() {
		return "Pfizer Change (ie '+' or '-')";
	}

	@Override
	public String getPattern() {
		return "%PFE%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getChange("PFE");
	}
}