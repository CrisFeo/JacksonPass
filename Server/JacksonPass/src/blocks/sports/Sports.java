package blocks.sports;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import blocks.ICategory;
import blocks.financial.Financial;

public class Sports extends ICategory {

	@Override
	public String getName() {
		return "Sports";
	}

	@Override
	public String getColor() {
		return "Semen";
	}

	@Override
	public String getBlockColor() {
		return "ASSHOLE";
	}

	@Override
	public ArrayList<JsonElement> mobileFormat() {
		String[] teams = { "Cardinals", "Astros", "Jets", "49ers", "Nuggets", "Blazers" };
		String[] sports = { "MLB", "MLB", "NFL", "NFL", "NBA", "NBA" };
		ArrayList<JsonElement> results = new ArrayList<JsonElement>();
		for (int i = 0; i < teams.length; i++) {
			JsonObject result = new JsonObject();
			result.addProperty("type", "sport");
			result.addProperty("sport", sports[i]);
			result.addProperty("team", teams[i]);

			result.addProperty("wins", getWins(sports[i], teams[i]));
			result.addProperty("ranking", getRanking(sports[i], teams[i]));
			results.add(result);
		}

		return results;
	}

	private String getApiUrl(String sport) {
		if (sport.equals("NFL"))
			return "http://api.sportsdatallc.org/nfl-t1/teams/2012/REG/standings.xml?api_key=4mkjfxzatpzepsrua4yt9ayp";
		else if (sport.equals("MLB"))
			return "http://api.sportsdatallc.org/mlb-t2/standings/2012.xml?api_key=kx35tsetvddha5e29z44bxqv";
		else if (sport.equals("NBA"))
			return "http://api.sportsdatallc.org/nba-t1/standings?season_id=2011&api_key=g8wcs6r87f6mqfwnmfbu646n";
		else
			return null;
	}

	/**
	 * 
	 * @param sport
	 * @param team
	 * @return
	 */
	protected String getRanking(String sport, String team) {
		try {

			String apiUrl = getApiUrl(sport);
			SAXReader reader = new SAXReader();
			Document d = reader.read(new URL(apiUrl));

			String xpath = null;
			if (sport.equals("NBA"))
				xpath = "*/*/*/*/*/*";
			else
				xpath = "*/*/*/*";

			List<? extends Node> nodes = d.selectNodes(xpath);

			if (sport.equals("NBA")) {
				for (Node node : nodes) {
					if (node.asXML().contains(team)) {
						String[] parentStructure = node.getParent().asXML().split("(\\s+)");
						for (int i = 0; i < parentStructure.length; i++) {
							if (parentStructure[i].contains("position"))
								return parentStructure[i].split("=")[1].replaceAll("\"", "");
						}
					}
				}
				return "-1";
			} else {
				for (Node node : nodes) {
					if (node.asXML().contains(team)) {
						String[] parentStructure = node.getParent().asXML().split("\n");
						for (int i = 0; i < parentStructure.length; i++) {
							if (parentStructure[i].contains(team))
								return String.valueOf(i);
						}
					}
				}
				return "-1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "-1";
	}

	/**
	 * 
	 * @param sport
	 * @param team
	 * @return
	 */
	protected String getWins(String sport, String team) {
		try {

			String apiUrl = getApiUrl(sport);
			SAXReader reader = new SAXReader();
			Document d = reader.read(new URL(apiUrl));

			String xpath = null;
			if (sport.equals("NBA"))
				xpath = "*/*/*/*/*/*";
			else
				xpath = "*/*/*/*";

			List<? extends Node> nodes = d.selectNodes(xpath);

			if (sport.equals("NBA")) {
				for (Node node : nodes) {
					if (node.asXML().contains(team)) {
						String[] xml = node.asXML().split("\n");
						for(String shit:xml){
							shit = shit.trim().toLowerCase();
							if(shit.contains("won"))
								return shit.split("[<>]")[2];
						}
					}
				}
				return "-1";
			} else {
				for (Node node : nodes) {
					if (node.asXML().contains(team)) {
						String[] structure = node.asXML().split("[\\s+=]");
						for (int i = 0; i < structure.length; i++) {
							if (structure[i].contains("wins"))
								return structure[i+1].replaceAll("\"", "");
						}
					}
				}
				return "-1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "-1";
	}

	public static void main(String args[]) {
		Sports s = new Sports();
		System.out.println(s.getWins("NFL", "Rams"));
		System.out.println(s.getWins("NBA", "76ers"));
		System.out.println(s.getWins("MLB", "Astros"));
		for(int i = 0; i<10000;i++);
		System.out.println(s.getRanking("NFL", "Rams"));
		System.out.println(s.getRanking("NBA", "76ers"));
		System.out.println(s.getRanking("MLB", "Astros"));
		
	}
}
