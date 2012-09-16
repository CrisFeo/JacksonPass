package blocks.sports;

import javax.servlet.http.HttpServletRequest;

import blocks.IBlock;

public class NinersRank extends Sports implements IBlock {

	@Override
	public String getShortName() {
		return "Niners Rank";
	}

	@Override
	public String getFullName() {
		return "San Francisco 49ers Rank";
	}

	@Override
	public String getPattern() {
		return "%49erNFLR%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getRanking("NFL", "49ers");
	}
}