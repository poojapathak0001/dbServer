package dbServer.dbServer;

import java.util.regex.*;
import java.util.*;


public class QuerySelector {
	
	private String query = null;
	private String[] tokens = null;
	private ArrayList<String> fileName = new ArrayList<String>();
	private String base = null;
	private String[] fields = null;
	
	
	//getter and setter for Query
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	//getter and setter for tokens
	public String[] getTokens() {
		return tokens;
	}

	public void setTokens(String[] tokens) {
		this.tokens = tokens;
	}

	//getter and setter for filename
	public ArrayList<String> getFileName() {
		return fileName;
	}

	public void setFileName(ArrayList<String> fileName) {
		this.fileName = fileName;
	}

	//getter and setter for base
	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	//getter and setter for fields
	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	//method to extract tokens
	public String[] extractTokens() {
		if (!query.equals("")) {
			return query.split("[ ,]");
		} else {
			return null;
		}
	}
	
	//methods to extract file name from query
	public ArrayList<String> extractFileName() {
		
		if (query != null) {
			Pattern pattern = Pattern.compile("[a-zA-Z0-9]+\\.(csv)|(txt)");
			Matcher matcher = pattern.matcher(query);
			
			ArrayList<String> fn = new ArrayList<String>();
			while (matcher.find())
			{
			    fn.add(matcher.group());
			}
			return fn;
		}
		else
			return null;
	}
	
	//method to extract base part from query
	public String extractBase() {
		
		if(query != null) {
			int endPos = query.indexOf("where");
			if (endPos > 0) {
				return (query.substring(0, endPos-1));
			}
			else if (endPos == -1) {
				return (query.substring(0, query.length()));
			}
			else
				return null;
		}
		return null;
	}
	
	//methods to extract field values
	public String[] extractFields() {
		if(query != null) {
			int startPos = query.toLowerCase().indexOf("select");
			int endPos = query.toLowerCase().indexOf("from");
			if(startPos >= 0 && endPos < query.length()) {
				 return (query.substring((startPos+7), endPos-1)).split(",");
			}
			return null;
		}
		return null;
	}
}