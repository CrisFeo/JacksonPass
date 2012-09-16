package blocks.time;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class MonthNum extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "Month Num";
	}

	@Override
	public String getFullName() {
		return "Number of Month";
	}

	@Override
	public String getPattern() {
		return "%MonthNum%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("M");
	}
}
