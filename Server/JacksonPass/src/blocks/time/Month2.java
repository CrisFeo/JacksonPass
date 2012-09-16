package blocks.time;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class Month2 extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "Month 2";
	}

	@Override
	public String getFullName() {
		return "1st 2 Letters of Month";
	}

	@Override
	public String getPattern() {
		return "%Month2%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("MMM").substring(0, 2);
	}
}
