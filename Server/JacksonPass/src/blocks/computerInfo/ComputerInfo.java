package blocks.computerInfo;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sleepycat.persist.model.Persistent;

import blocks.ICategory;
import blocks.financial.Financial;

@Persistent
public class ComputerInfo extends ICategory {

	@Override
	public String getName() {
		return "Computer Info";
	}

	@Override
	public String getColor() {
		return "#EEEEEE";
	}

	@Override
	public String getBlockColor() {
		return "blue";
	}

	public String getBrowser(HttpServletRequest req) {
		return req.getHeader("browser_name").toLowerCase();
	}

	public String getOS(HttpServletRequest req) {
		String plat = req.getHeader("platform").toLowerCase();
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
		return new ArrayList<JsonElement>();
	}

}
