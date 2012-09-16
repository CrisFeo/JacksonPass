package blocks.time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import blocks.ICategory;

public class Time extends ICategory {

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
