package blocks.computerInfo;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import blocks.ICategory;
import blocks.financial.Financial;

public class ComputerInfo extends ICategory {

	@Override
	public String getName() {
		return "Computer Info";
	}

	@Override
	public String getColor() {
		return "Fuck Ben";
	}

	@Override
	public String getBlockColor() {
		return "Suck my Dick";
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

	@Override
	public ArrayList<JsonElement> mobileFormat() {
		return null;
	}

}
