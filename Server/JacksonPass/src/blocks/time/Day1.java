package blocks.time;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class Day1 extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "Day 1";
	}

	@Override
	public String getFullName() {
		return "1st Letter of Day of Week";
	}

	@Override
	public String getPattern() {
		return "%Day1%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("EEE").substring(0, 1);
	}
}
