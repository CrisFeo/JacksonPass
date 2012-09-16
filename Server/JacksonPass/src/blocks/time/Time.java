package blocks.time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import blocks.ICategory;

public class Time extends ICategory {


	public Time() {}
	
	@Override
	public String getName() {
		return "Time";
	}

	@Override
	public String getColor() {
		return "0x000000";
	}

	@Override
	public String getBlockColor() {
		return "0xFFFFFF";
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

}
