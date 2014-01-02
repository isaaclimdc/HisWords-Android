package net.isaacl.hiswords;

import android.util.Log;

import com.loopj.android.http.*;

public class ESVHttpClient {
	private static final String BASE_URL = "http://www.esvapi.org/v2/rest/";
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	public static void getChapter(Chapter chapt, TextHttpResponseHandler responseHandler) {
		RequestParams params = new RequestParams();
		params.put("key", "4d81dd93ce80c1e6");
		params.put("output-format", "plain-text");
		params.put("include-footnotes", "false");
		params.put("include-passage-references", "false");
		params.put("include-footnotes", "false");
		params.put("include-passage-horizontal-lines", "false");
		params.put("include-headings", "true");
		params.put("include-heading-horizontal-lines", "false");
		params.put("line-length", "0");
		params.put("passage", chapt.toString());
		
		client.get(getAbsoluteUrl("passageQuery"), params, responseHandler);
	}
	
	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}
	
	public static void log(String msg) {
		Log.v("net.isaacl.hiswords", msg);
	}
}
