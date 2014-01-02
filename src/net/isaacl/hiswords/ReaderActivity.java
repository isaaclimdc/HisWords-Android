package net.isaacl.hiswords;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.loopj.android.http.TextHttpResponseHandler;

public class ReaderActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reader);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Intent intent = getIntent();
        Chapter chapt = (Chapter)intent.getParcelableExtra(HomeActivity.READER_CHAPTER);
        fetchChapterText(chapt);
        
        setTitle(chapt.toString());
    }
    
    public void fetchChapterText(Chapter chapt) {
        ESVHttpClient.getChapter(chapt, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseBody) {
            	TextView textView = (TextView)findViewById(R.id.reader_textview);
                textView.setText(responseBody);
            }
            @Override
            public void onFailure(String responseBody, Throwable e) {
                log("Fail: " + responseBody);
            }
        });
    }

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reader, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public static void log(String msg) {
		Log.v("net.isaacl.hiswords", msg);
	}
}
