package net.isaacl.hiswords;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		BookParser parser = new BookParser();
		Book booksList[] = parser.getBooks(this);
		printBooksList(booksList);
		
		BookAdapter adapter = new BookAdapter(this, R.layout.books_listview_row, booksList);

	    // We get the ListView component from the layout
	    ListView listView = (ListView)findViewById(R.id.booksListView);
	    listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	private void printBooksList(Book booksList[]) {
		String res = "";
		for (Book book : booksList) {
			res += book.toString() + "\n";
		}
		log(res);
	}
	
	public static void log(String msg) {
		Log.v("net.isaacl.hiswords", msg);
	}

}
