package blocks.sports;

import javax.servlet.http.HttpServletRequest;

import blocks.IBlock;

public class BlazersWins extends Sports implements IBlock {

	@Override
	public String getShortName() {
		return "Blazers Wins";
	}

	@Override
	public String getFullName() {
		return "Portland Trail Blazers Wins";
	}

	@Override
	public String getPattern() {
		return "%BlazeNBAW%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getWins("NBA", "Blazers");
	}
}