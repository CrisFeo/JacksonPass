package blocks.financial;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class XOM extends Financial implements IBlock {

	@Override
	public String getShortName() {
		return "XOM";
	}

	@Override
	public String getFullName() {
		return "Exxon Change (ie '+' or '-')";
	}

	@Override
	public String getPattern() {
		return "%XOM%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getChange("XOM");
	}
}