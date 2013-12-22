package net.isaacl.hiswords;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;

public class BookParser {
	
	public final static String XML_BOOKS_FILE = "books.xml";
	
	public Book[] getBooks(Context context) {
		Book books[] = null;
		
		try {
			XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = pullParserFactory.newPullParser();

			InputStream in_s = context.getAssets().open(XML_BOOKS_FILE);
		    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	        parser.setInput(in_s, null);
	        
	        books = parseXML(parser);
		}
		catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return books;
	}
	
	private Book[] parseXML(XmlPullParser parser) throws XmlPullParserException,IOException {
		List<Book> booksList = new ArrayList<Book>();
		
        int eventType = parser.getEventType();
        Book currentBook = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String xmlTag = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    if (xmlTag.equals("book")) {
                        currentBook = new Book();
                    }
                    else if (currentBook != null) {
                        if (xmlTag.equals("name")) {
                            currentBook.name = parser.nextText();
                        }
                        else if (xmlTag.equals("numChapts")) {
                        	currentBook.numChapts = Integer.parseInt(parser.nextText());
                        }
                        else if (xmlTag.equals("synopsis")) {
                            currentBook.synopsis = parser.nextText();
                        }
                        else if (xmlTag.equals("isOldTestament")) {
                            currentBook.isOldTestament = Boolean.valueOf(parser.nextText());
                        }  
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (xmlTag.equalsIgnoreCase("book") && currentBook != null) {
                	    booksList.add(currentBook);
                    }
                    break;
            }
            
            eventType = parser.next();
        }
        
        Book[] booksArr = new Book[booksList.size()];
        booksArr = booksList.toArray(booksArr);
        return booksArr;
	}
}
