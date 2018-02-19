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
	
	public Restrictions(String query) {
		super();
		this.query = query;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter() {
		if(query != null) {
			int startPos = query.toLowerCase().indexOf("where");
			if (startPos > 0) {
				filter = (query.substring(startPos+6, query.length()));
			}
		}
	}
	public ArrayList<String> getConditions() {
		return conditions;
	}
	public void setConditions() {
		if(query != null) {
			Pattern pattern = Pattern.compile("(\\w+[ ]?)(<>|>=|<=|!=|>|<|=|like|in|not like|not in|between[ ]?\\d[ ]?and\\d)([ ]?['(]?\\w+[')]?)");
			Matcher matcher = pattern.matcher(query);
			while (matcher.find())
			{
				conditions.add(matcher.group());
			}
		}
	}
	public ArrayList<String> getOperators() {
		return operators;
	}
	public void setOperators() {
		if(query != null) {
			Pattern pattern = Pattern.compile("(and)( )|(or)( )|(not)( )");
			Matcher matcher = pattern.matcher(query);
			while (matcher.find()) {
				operators.add(matcher.group(1));
			}
		}
	}
	
	public String getOrderByField() {
		return orderByField;
	}
	public void setOrderByField() {
		if(query != null) {
			int startPos = query.toLowerCase().indexOf("order by");
			int endPos = query.toLowerCase().indexOf("asc");
			
			if (endPos == -1)
				endPos = query.toLowerCase().indexOf("desc");
			
			if (endPos == -1)
				endPos = query.length();
			
			if (startPos > 0) {
				orderByField = (query.substring((startPos+9), endPos));
			}
		}
	}
	public String getGroupByField() {
		return groupByField;
	}
	public void setGroupByField() {
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
				groupByField = (query.substring((startPos+9), endPos));
			}
		}
	}
}