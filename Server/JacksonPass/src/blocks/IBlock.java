package blocks;

import javax.servlet.http.HttpServletRequest;

public interface IBlock {
	public String getShortName();
	public String getFullName();
	public String getPattern();
	public String getRepresentation(HttpServletRequest req);
}
