package net.isaacl.hiswords;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class HomeActivity extends Activity {
	
	public final static String XML_BOOKS_FILE = "books.xml";
	public final static String ADAPTER_BOOK_NAME_KEY = "bookName";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		List<Map<String, Book>> booksList = null;
		
		try {
			XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = pullParserFactory.newPullParser();

			InputStream in_s = getApplicationContext().getAssets().open(XML_BOOKS_FILE);
		    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	        parser.setInput(in_s, null);
	        
	        booksList = parseXML(parser);
		}
		catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	    // We get the ListView component from the layout
	    ListView lv = (ListView)findViewById(R.id.booksListView);

	    // This is a simple adapter that accepts as parameter
	    // Context
	    // Data list
	    // The row layout that is used during the row creation
	    // The keys used to retrieve the data
	    // The View id used to show the data. The key number and the view id must match
	    SimpleAdapter simpleAdpt = new SimpleAdapter(this,
	    											 booksList,
	    											 android.R.layout.simple_list_item_1,
	    											 new String[] {ADAPTER_BOOK_NAME_KEY},
	    											 new int[] {android.R.id.text1});
	    lv.setAdapter(simpleAdpt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	/* Populating the ListView by parsing the XML */

	class Book {
		public String name;
		public String numChapts;
		public String synopsis;
		public Boolean isOldTestament;
	}
	
	private List<Map<String, Book>> parseXML(XmlPullParser parser) throws XmlPullParserException,IOException {
		// This is what we're returning
		List<Map<String, Book>> books = new ArrayList<Map<String, Book>>();
		
        int eventType = parser.getEventType();
        Book currentBook = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String xmlTag = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    if (xmlTag == "book") {
                        currentBook = new Book();
                    }
                    else if (currentBook != null) {
                        if (xmlTag == "name"){
                            currentBook.name = parser.nextText();
                        }
                        else if (xmlTag == "numChapts") {
                        	currentBook.numChapts = parser.nextText();
                        }
                        else if (xmlTag == "synopsis") {
                            currentBook.synopsis = parser.nextText();
                        }
                        else if (xmlTag == "isOldTestament") {
                            currentBook.isOldTestament = Boolean.valueOf(parser.nextText());
                        }  
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (xmlTag.equalsIgnoreCase("book") && currentBook != null) {
                    	HashMap<String, Book> bookMap = new HashMap<String, Book>();
                	    bookMap.put(ADAPTER_BOOK_NAME_KEY, currentBook);
                	    books.add(bookMap);
                    }
                    break;
            }
            eventType = parser.next();
        }
        
        return books;
	}

}
