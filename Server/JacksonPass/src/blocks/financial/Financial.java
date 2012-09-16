package blocks.financial;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import blocks.ICategory;

public class Financial extends ICategory {

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

	public String getChange(String stock) {
		stock = stock.replaceAll("\\^", "%5E");
		String apiQuery = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quoteslist%20where%20symbol%3D'" + stock
				+ "'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		JsonObject json = getJson(apiQuery).getAsJsonObject();
		String change = json.get("query").getAsJsonObject().get("results").getAsJsonObject().get("quote").getAsJsonObject().get("Change")
				.getAsString();

		System.out.println(stock + ":" + change);
		return String.valueOf(change.charAt(0));
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

}