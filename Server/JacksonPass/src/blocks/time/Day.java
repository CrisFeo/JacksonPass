package blocks.time;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class Day extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "Day";
	}

	@Override
	public String getFullName() {
		return "Day of Month";
	}

	@Override
	public String getPattern() {
		return "%Day%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("d");
	}
}
