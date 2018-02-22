package dbServer.dbServer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AggregateFunctions {
	//property
	private String query;
	private ArrayList<String> aggregateFunc = new ArrayList<String>();
	
	//getter and setter for query
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	//getter and setter for aggregate functions
	public ArrayList<String> getAggregateFunc() {
		return aggregateFunc;
	}
	
	public void setAggregateFunc(ArrayList<String> aggregateFunc) {
		this.aggregateFunc = aggregateFunc;
	}
	
	//method to find aggregate function in the query
	public ArrayList<String> extractAggregateFunc() {
		
		if (query != null) {
			Pattern pattern = Pattern.compile("(avg|sum|min|max|count)(\\([a-zA-Z0-9_*]+)(\\))");
			Matcher matcher = pattern.matcher(query);
			ArrayList<String> agg = new ArrayList<String>();
			System.out.println("Aggregate Functions:");
			while (matcher.find()) {
				agg.add((matcher.group()));
			}
			return agg;
		}
		else
			return null;
	}
}
