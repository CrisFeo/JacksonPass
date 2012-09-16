package blocks.sports;

import javax.servlet.http.HttpServletRequest;

import blocks.IBlock;

public class CardinalWins extends Sports implements IBlock {

	@Override
	public String getShortName() {
		return "Cardninals Wins";
	}

	@Override
	public String getFullName() {
		return "St. Louis Cardninals Wins";
	}

	@Override
	public String getPattern() {
		return "%CardMLBW%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getWins("MLB", "Cardinals");
	}

}
