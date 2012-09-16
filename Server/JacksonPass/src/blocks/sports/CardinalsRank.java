package blocks.sports;

import javax.servlet.http.HttpServletRequest;

import blocks.IBlock;

public class CardinalsRank extends Sports implements IBlock {

	@Override
	public String getShortName() {
		return "Cardinals Rank";
	}

	@Override
	public String getFullName() {
		return "St. Louis Cardinals Rank";
	}

	@Override
	public String getPattern() {
		return "%CardMLBR%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getRanking("MLB", "Cardinals");
	}
}