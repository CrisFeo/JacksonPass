package blocks.computerInfo;

import javax.servlet.http.HttpServletRequest;

import blocks.ICategory;

public class ComputerInfo extends ICategory {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBlockColor() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBrowser(HttpServletRequest req) {
		return req.getHeader("browser-name");
	}

	public String getOS(HttpServletRequest req) {
		String plat = req.getHeader("os-type").toLowerCase();
		if (plat.contains("mac"))
			return "macintosh";
		else if (plat.contains("win"))
			return "windows";
		else if (plat.contains("iphone"))
			return "iphone";
		else if (plat.contains("arm") || plat.contains("android"))
			return "android";
		else if (plat.contains("ipad"))
			return "ipad";
		else if (plat.contains("linux"))
			return "ipad";
		else
			return plat.split(" ")[0];

	}

}
