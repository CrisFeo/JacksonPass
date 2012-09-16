package blocks.computerInfo;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class Platform extends ComputerInfo implements IBlock {

	@Override
	public String getShortName() {
		return "Platform";
	}

	@Override
	public String getFullName() {
		return "Computer Platform (ie mac, windows, linux)";
	}

	@Override
	public String getPattern() {
		return "%Plat%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return getOS(req);
	}
}
