package blocks.sports;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class NinersWins extends Sports implements IBlock {

	@Override
	public String getShortName() {
		return "49ers Wins";
	}

	@Override
	public String getFullName() {
		return "San Francisco 49ers Wins";
	}

	@Override
	public String getPattern() {
		return "%49erNFLW%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getWins("NFL", "49ers");
	}
}