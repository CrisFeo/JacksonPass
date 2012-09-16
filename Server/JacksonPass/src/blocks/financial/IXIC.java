package blocks.financial;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class IXIC extends Financial implements IBlock {

	@Override
	public String getShortName() {
		return "Nasdaq";
	}

	@Override
	public String getFullName() {
		return "Nasdaq Change (ie '+' or '-')";
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