package blocks.sports;

import javax.servlet.http.HttpServletRequest;

import blocks.IBlock;

public class NuggetsRank extends Sports implements IBlock {

	@Override
	public String getShortName() {
		return "Nuggets Rank";
	}

	@Override
	public String getFullName() {
		return "Denver Nuggets Rank";
	}

	@Override
	public String getPattern() {
		return "%NugNBAR%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getRanking("NBA", "Nuggets");
	}
}