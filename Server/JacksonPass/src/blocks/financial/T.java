package blocks.financial;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class T extends Financial implements IBlock {

	@Override
	public String getShortName() {
		return "T";
	}

	@Override
	public String getFullName() {
		return "AT&T Change (ie '+' or '-')";
	}

	@Override
	public String getPattern() {
		return "%TStock%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getChange("T");
	}
	
	
}