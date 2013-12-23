package net.isaacl.hiswords;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class HomeActivity extends Activity {
	
	public final static String READER_CHAPTER = "net.isaacl.hiswords.READER_CHAPTER";

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
	    
	    listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            	BookAdapter adpt = (BookAdapter)parent.getAdapter();
            	Book booksList[] = adpt.objects;
            	Book selectedBook = booksList[position];
            	Chapter chapt = new Chapter(selectedBook, 1); //TODO: Fix when you have chapter selection!
                openReaderForChapter(chapt);
            }
        });
	    
	    listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            	BookAdapter adpt = (BookAdapter)parent.getAdapter();
            	Book booksList[] = adpt.objects;
            	Book selectedBook = booksList[position];
                showAlertForBook(selectedBook);
				return true;
            }
        });
	}
	
	public void openReaderForChapter(Chapter chapt) {
		Intent intent = new Intent(this, ReaderActivity.class);
	    intent.putExtra(READER_CHAPTER, chapt);
	    startActivity(intent);
	}
	
	public void showAlertForBook(Book book) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Synopsis: " + book.name);
        builder.setMessage(book.synopsis);
        AlertDialog dialog = builder.create();
        dialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	/*** Helpers ***/
	
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
