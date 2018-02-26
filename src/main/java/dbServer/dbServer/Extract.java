package dbServer.dbServer;

import java.util.Scanner;

public class Extract {

	public static void main(String[] args) {
		
		 String query = null;
		 System.out.println("Enter the query:");
		 query = new Scanner(System.in).nextLine();
		 //query = "select city,id from ipl.csv where id < 10";
		 
		 //calling functions QuerySelector
		 QuerySelector qs = new QuerySelector();
		 //Extracting tokens
		 qs.setTokens(qs.extractTokens(query));
		 System.out.println("\nTokens: ");
		 for (String i : qs.getTokens()) {
			 System.out.print(i+", ");
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
		 for (String i : qs.getFields()) {
			 System.out.print(i + " ");
		 }
		 
		 //calling functions of Restriction 
		 Restrictions rs = new Restrictions();
		 //Extracting filter
		 rs.setFilter(rs.extractFilter(query));
		 System.out.println("\nFilter:\n " + rs.getFilter());
		 //Extracting conditions
		 rs.setConditions(rs.extractConditions(query));
		 System.out.println("\nConditions: ");
		 for (String i : rs.getConditions()) {
			 System.out.println(i);
		 }
		 //Extracting logical operators
		 rs.setOperators(rs.extractOperators(query));
		 System.out.println("\nOperators: ");
		 for (String i : rs.getOperators()) {
			 System.out.println(i);
		 }
		 //Extracting order by fields
		 rs.setOrderByField(rs.extractOrderByField(query));
		 System.out.println("\nOrder by field:\n " + rs.getOrderByField());
		 //Extracting group by fields
		 rs.setGroupByField(rs.extractGroupByField(query));
		 System.out.println("\nGroup by field:\n " + rs.getGroupByField());
		 
		 //calling function of AggregateFunctions
		 AggregateFunctions af =new AggregateFunctions();
		 //Extracting aggregate fnctions
		 af.setAggregateFunc(af.extractAggregateFunc(query));
		 System.out.println("\nAggregate Function: ");
		 for (String i : af.getAggregateFunc()) {
			 System.out.println(i);
		 }
		 System.out.println();
		 
		 //calling method of ReadFile
		 ReadFile rf = new ReadFile();
		 rf.readFile(query);
		 rf.extractDataType();
		 
		 //calling methods of QueryParser
		 QueryParser qp = new QueryParser();
		 //calling method to extract data on basis of where clause
		 qp.extractWhereData(query);
		 //calling method to extract data
		 qp.extractSelectData(query);
		 
	}

}
