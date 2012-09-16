package blocks.sports;

import javax.servlet.http.HttpServletRequest;

import blocks.IBlock;

public class BlazersRank extends Sports implements IBlock {

	@Override
	public String getShortName() {
		return "Blazers Rank";
	}

	@Override
	public String getFullName() {
		return "Portland Trail Blazers Rank";
	}

	@Override
	public String getPattern() {
		return "%BlazeNBAR%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getRanking("NBA", "Blazers");
	}
}