package blocks.time;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class Year2 extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "Year 2";
	}

	@Override
	public String getFullName() {
		return "Last 2 Digits of Year";
	}

	@Override
	public String getPattern() {
		return "%Year2%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("yy");
	}
}
