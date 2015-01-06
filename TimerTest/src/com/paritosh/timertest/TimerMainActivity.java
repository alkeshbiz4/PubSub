package com.paritosh.timertest;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TimerMainActivity extends Activity {

	Timer timer;
	TextView txtTimer;
	int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtTimer = (TextView) findViewById(R.id.txtTimer);
		Button btnAnotherMethod = (Button) findViewById(R.id.btnAnotherMethod);

		btnAnotherMethod.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intentMethod2 = new Intent(getApplicationContext(), Method2.class);
				startActivity(intentMethod2);
			}
		});

		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				timerMethod();
			}
		}, 0, 1000);
	}

	private void timerMethod() {
		// This method is called directly by the timer
		// and runs in the same thread as the timer.

		// We call the method that will work with the UI
		// through the runOnUiThread method.
		i++;
		this.runOnUiThread(Timer_Tick);
	}

	private Runnable Timer_Tick = new Runnable() {
		public void run() {
			// This method runs in the same thread as the UI.
			// Do something to the UI thread here
			txtTimer.setText("Changing Time " + i);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}
