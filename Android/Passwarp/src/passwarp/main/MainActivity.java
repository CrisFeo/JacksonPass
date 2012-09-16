package passwarp.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.example.passadapt.R;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnGenericMotionListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.TableLayout;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.SlidingDrawer.OnDrawerScrollListener;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TableLayout table;
	private int colorDark = Color.rgb(69, 45, 0);
	private int colorLight = Color.rgb(69, 45, 0);

	private void setText() {
		TableRow loadingRow = new TableRow(getApplicationContext());
		loadingRow.setGravity(Gravity.CENTER);
		TextView text = new TextView(getApplicationContext());
		text.setText("Loading...");
		text.setTextColor(colorDark);
		text.setTextSize(30);
		text.setPadding(0, 450, 0, 0);
		text.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
		loadingRow.addView(text);
		table.addView(loadingRow);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		table = (TableLayout) findViewById(R.id.tablelayout);
		ServerTask task = new ServerTask();
		task.execute("http://ec2-23-20-105-103.compute-1.amazonaws.com:8080/JacksonServer/Verify");
		final MediaPlayer close = MediaPlayer.create(getApplicationContext(),
				R.raw.open2);
		final MediaPlayer open = MediaPlayer.create(getApplicationContext(),
				R.raw.close2);

		final SlidingDrawer handle = (SlidingDrawer) findViewById(R.id.slidingDrawer1);

		final CheckBox sound = (CheckBox) findViewById(R.id.checkBox1);

		setText();

		handle.setOnDrawerCloseListener(new OnDrawerCloseListener() {

			public void onDrawerClosed() {
				if (sound.isChecked()) {
					close.start();
				}

			}
		});
		handle.setOnDrawerOpenListener(new OnDrawerOpenListener() {

			public void onDrawerOpened() {
				if (sound.isChecked()) {
					open.start();

				}

			}
		});
	}

	HashMap<String, Integer> sportsWins = new HashMap<String, Integer>();
	HashMap<String, Integer> sportsRankings = new HashMap<String, Integer>();
	HashMap<String, Double> stocks = new HashMap<String, Double>();

	public void parse(String json) {
		// json =
		// "{\"results\":[{\"type\":\"sport\",\"sport\":\"MLB\",\"team\":\"Braves\",\"wins\":\"12\",\"ranking\":\"6\"},{\"type\":\"sport\",\"sport\":\"MLB\",\"team\":\"Giants\",\"wins\":\"46\",\"ranking\":\"88\"},{\"type\":\"sport\",\"sport\":\"MLB\",\"team\":\"Cowboys\",\"wins\":\"0\",\"ranking\":\"55\"},{\"type\":\"sport\",\"sport\":\"MLB\",\"team\":\"Trailblazers\",\"wins\":\"99\",\"ranking\":\"1\"},{\"type\":\"sport\",\"sport\":\"MLB\",\"team\":\"Astros\",\"wins\":\"31\",\"ranking\":\"5\"},{\"type\":\"sport\",\"sport\":\"NBA\",\"team\":\"Rockets\",\"wins\":\"61\",\"ranking\":\"15\"},{\"type\":\"stock\",\"ticker\":\"GOOG\",\"value\":\"6.15\"},{\"type\":\"stock\",\"ticker\":\"AAPL\",\"value\":\"-51.12\"},{\"type\":\"stock\",\"ticker\":\"PFE\",\"value\":\"1.12\"},{\"type\":\"stock\",\"ticker\":\"T\",\"value\":\"2.1\"},{\"type\":\"stock\",\"ticker\":\"XOM\",\"value\":\"5\"},{\"type\":\"stock\",\"ticker\":\"S&P\",\"value\":\"10.99\"},{\"type\":\"stock\",\"ticker\":\"NASDAQ\",\"value\":\"24.56\"}]}";
		try {
			JSONArray arr = new JSONObject(json).getJSONArray("results");
			for (int i = 0; i < arr.length(); i++) {
				JSONObject obj = (JSONObject) arr.get(i);
				if (obj.getString("type").equals("sport")) {
					sportsWins.put(obj.getString("team"), obj.getInt("wins"));
					sportsRankings.put(obj.getString("team"),
							obj.getInt("ranking"));
				} else if (obj.getString("type").equals("stock")) {
					stocks.put(obj.getString("ticker"), obj.getDouble("value"));
				}

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void createRows(String json) {
		table.removeAllViews();
		parse(json);
		TableRow sportsHead = new TableRow(getApplicationContext());
		TextView sh = new TextView(getApplicationContext());
		sh.setText("Sports");
		sh.setTextColor(colorDark);
		sh.setTextSize(50);
		sh.setPadding(0, 10, 0, 0);
		sh.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
		TextView sh2 = new TextView(getApplicationContext());
		sh2.setText("   Wins");
		sh2.setTextColor(colorDark);
		sh2.setTextSize(23);
		sh2.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
		TextView sh3 = new TextView(getApplicationContext());
		sh3.setText("Ranking");
		sh3.setTextColor(colorDark);
		sh3.setTextSize(23);
		sh3.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);

		sportsHead.addView(sh);
		sportsHead.addView(sh2);
		sportsHead.addView(sh3);
		table.addView(sportsHead);

		for (String key : sportsWins.keySet()) {
			TableRow row = new TableRow(getApplicationContext());
			TextView text1 = new TextView(getApplicationContext());
			text1.setText(key);
			text1.setTextColor(colorDark);
			text1.setTextSize(23);
			text1.setPadding(0, 10, 0, 0);
			text1.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);

			TextView text2 = new TextView(getApplicationContext());
			text2.setText("   " + sportsWins.get(key).toString());
			text2.setTextColor(colorLight);
			text2.setTextSize(23);
			text2.setPadding(0, 10, 0, 0);
			text2.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);

			TextView text3 = new TextView(getApplicationContext());
			text3.setText("" + sportsRankings.get(key).toString());
			text3.setTextColor(colorLight);
			text3.setTextSize(23);
			text3.setPadding(0, 10, 0, 0);
			text3.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);

			row.addView(text1);
			row.addView(text2);
			row.addView(text3);
			table.addView(row);
		}

		TableRow stocksHead = new TableRow(getApplicationContext());
		TextView ssh = new TextView(getApplicationContext());
		ssh.setText("Stocks");
		ssh.setTextColor(colorDark);
		ssh.setTextSize(50);
		ssh.setPadding(0, 10, 0, 0);
		ssh.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);

		TextView ssh2 = new TextView(getApplicationContext());
		ssh2.setText("   Change");
		ssh2.setTextColor(colorDark);
		ssh2.setTextSize(23);
		ssh2.setPadding(0, 10, 0, 0);
		ssh2.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);

		stocksHead.addView(ssh);
		stocksHead.addView(ssh2);
		table.addView(stocksHead);

		for (String key : stocks.keySet()) {
			TableRow row = new TableRow(getApplicationContext());
			TextView text = new TextView(getApplicationContext());
			text.setText(key);
			text.setTextColor(colorDark);
			text.setTextSize(23);
			text.setPadding(0, 10, 0, 0);
			text.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);

			TextView text2 = new TextView(getApplicationContext());
			String sign = stocks.get(key) > 0 ? "+" : "";
			text2.setText("   " + sign + stocks.get(key).toString());
			text2.setTextColor(colorLight);
			text2.setTextSize(23);
			text2.setPadding(0, 10, 0, 0);
			text2.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);

			row.addView(text);
			row.addView(text2);
			table.addView(row);
		}

		TableRow time = new TableRow(getApplicationContext());
		TextView t = new TextView(getApplicationContext());
		t.setText("Time");
		t.setTextColor(colorDark);
		t.setTextSize(50);
		t.setPadding(0, 10, 0, 0);
		t.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
		time.addView(t);
		table.addView(time);

		TableRow det = new TableRow(getApplicationContext());
		TextView tdet = new TextView(getApplicationContext());
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, MM/dd/yy");
		tdet.setText(formatter.format(new Date()));
		tdet.setTextColor(colorLight);
		tdet.setTextSize(23);
		tdet.setPadding(0, 10, 0, 0);
		tdet.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
		det.addView(tdet);
		table.addView(det);

		Chronometer stopWatch = (Chronometer) findViewById(R.id.chrono);
		TableRow timeRow = new TableRow(getApplicationContext());
		final TextView timeText = new TextView(getApplicationContext());
		timeText.setText("poop");
		stopWatch.setOnChronometerTickListener(new OnChronometerTickListener() {
			public void onChronometerTick(Chronometer arg0) {
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
				timeText.setText(formatter.format(new Date()));
			}
		});
		stopWatch.start();
		timeText.setTextColor(colorLight);
		timeText.setTextSize(23);
		timeText.setPadding(0, 10, 0, 0);
		timeText.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
		timeRow.addView(timeText);
		table.addView(timeRow);

	}

	private class ServerTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... urls) {
			Log.v("poop", "loooool");
			String line = "";
			for (String url : urls) {
				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(url);
				try {
					HttpResponse execute = client.execute(httpGet);
					InputStream content = execute.getEntity().getContent();

					BufferedReader buffer = new BufferedReader(
							new InputStreamReader(content));

					line = buffer.readLine();

					/*
					 * char c = (char) buffer.read(); while (c != -1) {
					 * buf.append(c); c = (char) buffer.read(); }
					 */

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//for (int i = 0; i < Integer.MAX_VALUE/2; i++){}
			publishProgress(line);
			return line;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			createRows(values[0]);
		}
	}

}
