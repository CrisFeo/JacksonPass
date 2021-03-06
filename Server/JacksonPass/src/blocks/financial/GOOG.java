package blocks.financial;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class GOOG extends Financial implements IBlock {

	@Override
	public String getShortName() {
		return "GOOG";
	}

	@Override
	public String getFullName() {
		return "Google Change (ie '+' or '-')";
	}

	@Override
	public String getPattern() {
		return "%GOOG%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getChange("GOOG");
	}
}