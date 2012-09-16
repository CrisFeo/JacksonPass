package blocks;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public abstract class ICategory {
	public abstract String getName();

	public abstract String getColor();

	public abstract String getBlockColor();

	/**
	 * 
	 * @return A list of key value pairs to send over to mobile
	 */
	public abstract ArrayList<JsonElement> mobileFormat();

	/**
	 * Performs a get request to the api server
	 * 
	 * @param apiUrl
	 *            The url to contact
	 * @return A json representation of the response, or null if there is an
	 *         error
	 */
	public JsonElement getJson(String apiUrl) {
		String response = getResponse(apiUrl);
		if (response != null) {
			JsonParser p = new JsonParser();
			JsonElement head = p.parse(response);
			return head;
		} else {
			return null;
		}

	}

	public String getResponse(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			URLConnection conn = url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuilder b = new StringBuilder();

			while ((inputLine = in.readLine()) != null)
				b.append(inputLine);
			in.close();

			return b.toString();
		} catch (IOException e) {
			return null;
		}
	}

}