package blocks.computerInfo;

import javax.servlet.http.HttpServletRequest;

import blocks.ICategory;

public class ComputerInfo extends ICategory {

	public ComputerInfo() {}
	
	@Override
	public String getName() {
		return "Computer Info";
	}

	@Override
	public String getColor() {
		return "0x000000";
	}

	@Override
	public String getBlockColor() {
		return "0xFFFFFF";
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
