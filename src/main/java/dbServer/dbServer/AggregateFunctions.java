package dbServer.dbServer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AggregateFunctions {
	//property
	private String query;
	private ArrayList<String> aggregateFunc = new ArrayList<String>();
	
	//constructor to initialize query
	public AggregateFunctions(String query) {
		super();
		this.query = query;
	}
	
	//getter and setter
	public ArrayList<String> getAggregateFunc() {
		return aggregateFunc;
	}

	public void setAggregateFunc() {
		
		if (query != null) {
			Pattern pattern = Pattern.compile("(avg|sum|min|max|count)(\\([a-zA-Z0-9_*]+)(\\))");
			Matcher matcher = pattern.matcher(query);
			
			System.out.println("Aggregate Functions:");
			while (matcher.find()) {
				aggregateFunc.add((matcher.group()));
			}
			System.out.println(aggregateFunc);
		}
	}
}