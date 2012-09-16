package blocks.time;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class Hour24 extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "24 Hour";
	}

	@Override
	public String getFullName() {
		return "Hour in 24hr Format";
	}

	@Override
	public String getPattern() {
		return "%Hour24%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("k");
	}
}
