package blocks.financial;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class AAPL extends Financial implements IBlock {

	@Override
	public String getShortName() {
		return "AAPL";
	}

	@Override
	public String getFullName() {
		return "Apple Change (ie '+' or '-')";
	}

	@Override
	public String getPattern() {
		return "%AAPL%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getChange("AAPL");
	}
}
