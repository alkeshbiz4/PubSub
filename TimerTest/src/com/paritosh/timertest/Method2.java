package com.paritosh.timertest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Method2 extends Activity {

	TextView txtTimer;
	long startTime = 0;

	Handler timerHandler = new Handler();
	Runnable timerRunnable = new Runnable() {

		@Override
		public void run() {
			long millis = System.currentTimeMillis();// - startTime;
			int seconds = (int) (millis / 1000);
			int minutes = seconds / 60;
			int hours = minutes / 60;
			seconds = seconds % 60;
			minutes = minutes % 60;
			hours = hours % 24;

			txtTimer.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

			timerHandler.postDelayed(this, 500);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_method2);

		txtTimer = (TextView) findViewById(R.id.txtTimer);
		timerHandler.postDelayed(timerRunnable, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.method2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPause() {
		super.onPause();
		timerHandler.removeCallbacks(timerRunnable);
	}

	@Override
	protected void onResume() {
		super.onResume();
		timerHandler.postDelayed(timerRunnable, 0);
	}
}
