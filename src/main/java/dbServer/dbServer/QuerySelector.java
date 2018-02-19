package dbServer.dbServer;

import java.util.regex.*;
import java.util.*;


public class QuerySelector {
	
	private String query = null;
	private String[] tokens = null;
	private ArrayList<String> fileName = new ArrayList<String>();
	private String base = null;
	private String[] fields = null;
	
	public QuerySelector(String query) {
		super();
		this.query = query;
	}
	
	public String[] getTokens() {
		return tokens;
	}
	public void setTokens() {
		if (!query.equals("")) {
			tokens = query.split("[ ,]");
		} else {
			tokens = null;
		}
	}
	public ArrayList<String> getFileName() {
		
			return fileName;
	}
	public void setFileName() {
		
		if (query != null) {
			Pattern pattern = Pattern.compile("[a-zA-Z0-9]+\\.(csv)|(txt)");
			Matcher matcher = pattern.matcher(query);
			
			while (matcher.find())
			{
			    fileName.add(matcher.group());
			}
		}
	}
	public String getBase() {
		return base;
	}

	public void setBase() {
		
		if(query != null) {
			int endPos = query.indexOf("where");
			if (endPos > 0) {
				base = (query.substring(0, endPos-1));
			}
			else if (endPos == -1) {
				base = (query.substring(0, query.length()));
			}
		}
	}
	public String[] getFields() {
		return fields;
	}
	public void setFields() {
		if(query != null) {
			int startPos = query.toLowerCase().indexOf("select");
			int endPos = query.toLowerCase().indexOf("from");
			if(startPos >= 0 && endPos < query.length()) {
				 String sub[] = (query.substring((startPos+7), endPos-1)).split(",");
				fields = sub;
			}
		}
	}
}