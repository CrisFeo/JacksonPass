package blocks.financial;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class GSPC extends Financial implements IBlock {

	@Override
	public String getShortName() {
		return "S&P";
	}

	@Override
	public String getFullName() {
		return "S&P Change (ie '+' or '-')";
	}

	@Override
	public String getPattern() {
		return "%GSPC%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getChange("^GSPC");
	}
}