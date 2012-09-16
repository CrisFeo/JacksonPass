package blocks.sports;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class AstrosRank extends Sports implements IBlock {

	@Override
	public String getShortName() {
		return "Astros Rank";
	}

	@Override
	public String getFullName() {
		return "Houston Astros Rank";
	}

	@Override
	public String getPattern() {
		return "%AstrMLBR%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getRanking("MLB", "Astros");
	}
}