package blocks.sports;

import javax.servlet.http.HttpServletRequest;

import blocks.IBlock;

public class JetsRank extends Sports implements IBlock {

	@Override
	public String getShortName() {
		return "Jets Rank";
	}

	@Override
	public String getFullName() {
		return "New York Jets Rank";
	}

	@Override
	public String getPattern() {
		return "%JetsNFLR%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getRanking("NFL", "Jets");
	}
}