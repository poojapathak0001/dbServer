package dbServer.dbServer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Restrictions {
	private String query = null;
	private String filter = null;
	private ArrayList<String> conditions = new ArrayList<String>();
	private ArrayList<String> operators = new ArrayList<String>();
	private String orderByField = null;
	private String groupByField = null;
	
	//getter and setter for query
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	//getter and setter for filter
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	//getter and setter for conditions
	public ArrayList<String> getConditions() {
		return conditions;
	}

	public void setConditions(ArrayList<String> conditions) {
		this.conditions = conditions;
	}

	//getter and setter for operators
	public ArrayList<String> getOperators() {
		return operators;
	}

	public void setOperators(ArrayList<String> operators) {
		this.operators = operators;
	}

	//getter and setter for order by fields
	public String getOrderByField() {
		return orderByField;
	}

	public void setOrderByField(String orderByField) {
		this.orderByField = orderByField;
	}

	//getter and setter for group by fields
	public String getGroupByField() {
		return groupByField;
	}

	public void setGroupByField(String groupByField) {
		this.groupByField = groupByField;
	}

	//method to extract filter part from query
	public String extractFilter() {
		if(query != null) {
			int startPos = query.toLowerCase().indexOf("where");
			if (startPos > 0) {
				return (query.substring(startPos+6, query.length()));
			}
			return null;
		}
		return null;
	}

	//method to extract conditions from query
	public ArrayList<String> extractConditions() {
		if(query != null) {
			Pattern pattern = Pattern.compile("(\\w+[ ]?)(<>|>=|<=|!=|>|<|=|like|in|not like|not in|between[ ]?\\d[ ]?and\\d)([ ]?['(]?\\w+[')]?)");
			Matcher matcher = pattern.matcher(query);
			
			ArrayList<String> con = new ArrayList<String>();
			while (matcher.find())
			{
				con.add(matcher.group());
			}
			return con;
		}
		return null;
	}
	
	///method to extract operators from query
	public ArrayList<String> extractOperators() {
		if(query != null) {
			Pattern pattern = Pattern.compile("(and)( )|(or)( )|(not)( )");
			Matcher matcher = pattern.matcher(query);
			
			ArrayList<String> op = new ArrayList<String>();
			while (matcher.find()) {
				op.add(matcher.group(1));
			}
			return op;
		}
		return null;
	}
	
	//method to extract order by fields
	public String extractOrderByField() {
		if(query != null) {
			int startPos = query.toLowerCase().indexOf("order by");
			int endPos = query.toLowerCase().indexOf("asc");
			
			if (endPos == -1)
				endPos = query.toLowerCase().indexOf("desc");
			
			if (endPos == -1)
				endPos = query.length();
			
			if (startPos > 0) {
				return (query.substring((startPos+9), endPos));
			}
			return null;
		}
		return null;
	}
	
	//method to extract group by fields
	public String extractGroupByField() {
		if(query != null) {
			int startPos = query.toLowerCase().indexOf("group by");
			int endPos = query.toLowerCase().indexOf("having") -1 ;
			
			if (endPos <=-1) {
				endPos = query.toLowerCase().indexOf("order by");
				endPos--;
			}
			if (endPos <= -1)
				endPos = query.length();
			
			if (startPos > 0) {
				return (query.substring((startPos+9), endPos));
			}
			return null;
		}
		return null;
	}
}
