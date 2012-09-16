package blocks.sports;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class JetsWins extends Sports implements IBlock {

	@Override
	public String getShortName() {
		return "Jets Wins";
	}

	@Override
	public String getFullName() {
		return "New York Jets Wins";
	}

	@Override
	public String getPattern() {
		return "%JetsNFLW%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getWins("NFL", "Jets");
	}
}