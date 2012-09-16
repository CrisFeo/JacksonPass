package blocks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public abstract class ICategory {
	public abstract String getName();
	public abstract String getColor();
	public abstract String getBlockColor();
	/**
	 * Performs a get request to the api server
	 * 
	 * @param apiUrl
	 *            The url to contact
	 * @return A json representation of the response, or null if there is an
	 *         error
	 */
	public JsonElement getJson(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			URLConnection conn = url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuilder b = new StringBuilder();

			while ((inputLine = in.readLine()) != null)
				b.append(inputLine);
			in.close();

			JsonParser p = new JsonParser();
			JsonElement head = p.parse(b.toString());

			return head;
		} catch (IOException e) {
			return null;
		}

	}

}