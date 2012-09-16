package blocks.time;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class DayWeek extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "Day in Week";
	}

	@Override
	public String getFullName() {
		return "Number of Day in Week, Sunday as 1";
	}

	@Override
	public String getPattern() {
		return "%DayWeek%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getDOW(format("EEEE"));
	}
}
