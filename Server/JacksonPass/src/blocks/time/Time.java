package blocks.time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.JsonElement;
import com.sleepycat.persist.model.Persistent;

import blocks.ICategory;

@Persistent
public class Time extends ICategory {

	@Override
	public String getName() {
		return "Time";
	}

	@Override
	public String getColor() {
		return "Stolabitch";
	}

	@Override
	public String getBlockColor() {
		return "BIRD!";
	}

	protected String format(String format) {
		Date d = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(new Date());
	}

	protected String getDOW(String day) {
		ArrayList<String> days = new ArrayList<String>();
		days.add("sunday");
		days.add("monday");
		days.add("tuesday");
		days.add("wednesday");
		days.add("thursday");
		days.add("friday");
		days.add("satuday");

		int index = days.indexOf(day.toLowerCase());
		assert index > -1;

		return String.valueOf(index + 1);
	}

	@Override
	public ArrayList<JsonElement> mobileFormat() {
		return new ArrayList<JsonElement>();
	}

}
