package blocks.sports;

import javax.servlet.http.HttpServletRequest;

import blocks.IBlock;

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