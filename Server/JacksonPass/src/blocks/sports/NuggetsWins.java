package blocks.sports;

import javax.servlet.http.HttpServletRequest;

import blocks.IBlock;

public class NuggetsWins extends Sports implements IBlock {

	@Override
	public String getShortName() {
		return "Nuggets Wins";
	}

	@Override
	public String getFullName() {
		return "Denver Nuggets Wins";
	}

	@Override
	public String getPattern() {
		return "%NugNBAW%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getWins("NBA", "Nuggets");
	}
}