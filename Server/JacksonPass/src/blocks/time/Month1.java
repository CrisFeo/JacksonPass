package blocks.time;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class Month1 extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "Month 1";
	}

	@Override
	public String getFullName() {
		return "1st Letter of Month";
	}

	@Override
	public String getPattern() {
		return "%Month1%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("MMM").substring(0, 1);
	}
}
