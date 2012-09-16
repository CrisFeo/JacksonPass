package blocks.sports;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class AstrosWins extends Sports implements IBlock {

	@Override
	public String getShortName() {
		return "Astros Wins";
	}

	@Override
	public String getFullName() {
		return "Houston Astros Wins";
	}

	@Override
	public String getPattern() {
		return "%AstrMLBW%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getWins("MLB", "Astros");
	}
}