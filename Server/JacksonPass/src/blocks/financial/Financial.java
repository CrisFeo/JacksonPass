package blocks.financial;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import blocks.ICategory;

public class Financial extends ICategory {

	@Override
	public String getName() {
		return "Stocks";
	}

	@Override
	public String getColor() {
		return "Stankin";
	}

	@Override
	public String getBlockColor() {
		return "Tall mother fucker";
	}

	public String getChange(String stock) {
		String change = getStock(stock);
		return String.valueOf(change.charAt(0));
	}

	protected String getStock(String stock) {
		stock = stock.replaceAll("\\^", "%5E");
		String apiQuery = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quoteslist%20where%20symbol%3D'" + stock
				+ "'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		JsonObject json = getJson(apiQuery).getAsJsonObject();
		String change = json.get("query").getAsJsonObject().get("results").getAsJsonObject().get("quote").getAsJsonObject().get("Change")
				.getAsString();
		return change;
	}

	public static void main(String args[]) {
		Financial f = new Financial();
		f.getChange("AAPL");
		f.getChange("GOOG");
		f.getChange("PFE");
		f.getChange("T");
		f.getChange("XOM");
		f.getChange("^IXIC");
		f.getChange("^GSPC");
	}

	@Override
	public ArrayList<JsonElement> mobileFormat() {
		Financial f = new Financial();
		
		String[] stocks = { "AAPL", "GOOG", "PFE", "T", "XOM", "^IXIC", "^GSPC" };
		
		ArrayList<JsonElement> results = new ArrayList<JsonElement>();
		JsonObject result = new JsonObject();
		for (String stock : stocks) {
			result.addProperty("type", "stock");
			result.addProperty("ticker", stock);
			result.addProperty("value", f.getStock(stock).replaceAll("+", ""));
			results.add(result);
		}

		return results;
	}

}
