package dbServer.dbServer;

import java.util.ArrayList;
import java.util.Scanner;

public class Extract {
	
	public static void callQuerySelector(String query) {
		//calling functions QuerySelector
		 QuerySelector qs = new QuerySelector();
		 //Extracting tokens
		 qs.setTokens(qs.extractTokens(query));
		 System.out.println("\nTokens: ");
		 for (String token : qs.getTokens()) {
			 System.out.print(token+", ");
		 }
		 //Extracting filename 
		 qs.setFileName(qs.extractFileName(query));
		 System.out.println("\nFile name: ");
		 System.out.print(qs.getFileName());
		 //Extracting Base
		 qs.setBase(qs.extractBase(query));
		 System.out.println("\nBase:\n "+ qs.getBase());
		 //Extracting fields
		 qs.setFields(qs.extractFields(query));
		 System.out.println("\nFields: ");
		 for (String field : qs.getFields()) {
			 System.out.print(field + " ");
		 }
	}
	
	public static void callRestrictions(String query) {
		
		 Restrictions rs = new Restrictions();
		 //Extracting filter
		 rs.setFilter(rs.extractFilter(query));
		 System.out.println("\nFilter:\n " + rs.getFilter());
		 //Extracting conditions
		 rs.setConditions(rs.extractConditions(query));
		 System.out.println("\nConditions: ");
		 for (String condition : rs.getConditions()) {
			 System.out.println(condition);
		 }
		 //Extracting logical operators
		 rs.setOperators(rs.extractOperators(query));
		 System.out.println("\nOperators: ");
		 for (String operator : rs.getOperators()) {
			 System.out.println(operator);
		 }
		 //Extracting order by fields
		 rs.setOrderByField(rs.extractOrderByField(query));
		 System.out.println("\nOrder by field:\n " + rs.getOrderByField());
		 //Extracting group by fields
		 rs.setGroupByField(rs.extractGroupByField(query));
		 System.out.println("\nGroup by field:\n " + rs.getGroupByField());
	}

	public static void callAggregateFunctions(String query) {
		
		 AggregateFunctions af =new AggregateFunctions();
		 //Extracting aggregate functions
		 af.setAggregateFunc(af.extractAggregateFunc(query));
		 System.out.println("\nAggregate Function: ");
		 for (String aggregate : af.getAggregateFunc()) {
			 System.out.println(aggregate);
		 }
		 System.out.println();
	}
	
	public static void callReadFile(String query) {
		
		ReadFile rf = new ReadFile();
		rf.readFile(new QuerySelector().extractFileName(query));
		rf.extractDataType();
	}
	
	public static void callQueryParser(String query) {
		
		 QueryParser qp = new QueryParser();
		 //calling method to extract data on basis of where clause
		 qp.extractWhereData(query);
		 //calling method to extract data
		 qp.extractSelectData(query);
		 
		 //calling callOrderByParser
		 callOrderByParser(query, qp.getSelectData());
	}
	
	public static void callOrderByParser(String query, ArrayList<String> selectData) {
		
		new OrderByParser().extractOrderByData(query, selectData);
	}
	public static void main(String[] args) {
		
		 String query = null;
		 System.out.println("Enter the query:");
		 query = new Scanner(System.in).nextLine();
		 //query = "select city,id from ipl.csv where id < 10";
		 //query = "select city from ipl.csv where id < 5 order by city"
		 
		 //calling methods of QuerySelector
		 callQuerySelector(query);
		 //calling functions of Restriction 
		 callRestrictions(query);
		 //calling function of AggregateFunctions
		 callAggregateFunctions(query);
		 //calling method of ReadFile
		 callReadFile(query);
		 //calling methods of QueryParser
		 callQueryParser(query);
		 
		 
		 
	}

}
