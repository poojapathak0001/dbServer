package dbServer.dbServer;
public class Extract {

	public static void main(String[] args) {
		
		 String query = null;
		 System.out.println("Enter the query:");
		 query = "select city from ipl.csv where id = 200";
		 //calling functions QuerySelector
		 QuerySelector qs = new QuerySelector();
		 qs.setQuery(query);
		 qs.setTokens(qs.extractTokens());
		 System.out.println("\nTokens: ");
		 for (String i : qs.getTokens()) {
			 System.out.print(i+", ");
		 }
		 
		 qs.setFileName(qs.extractFileName());
		 System.out.println("\nFile name: ");
		 System.out.print(qs.getFileName());
		 
		 qs.setBase(qs.extractBase());
		 System.out.println("\nBase: "+ qs.getBase());
		 
		 qs.setFields(qs.extractFields());
		 System.out.println("\nFields: ");
		 for (String i : qs.getFields()) {
			 System.out.print(i + " ");
		 }
		 
		 //calling functions of Restriction 
		 Restrictions rs = new Restrictions();
		 rs.setQuery(query);
		 rs.setFilter(rs.extractFilter());
		 System.out.println("\nFilter: " + rs.getFilter());
		 
		 rs.setConditions(rs.extractConditions());
		 System.out.println("\nConditions: ");
		 for (String i : rs.getConditions()) {
			 System.out.println(i);
		 }
		 
		 rs.setOperators(rs.extractOperators());
		 System.out.println("\nOperators: ");
		 for (String i : rs.getOperators()) {
			 System.out.println(i);
		 }
		 
		 rs.setOrderByField(rs.extractOrderByField());
		 System.out.println("\nOrder by field: " + rs.getOrderByField());
		 
		 rs.setGroupByField(rs.extractGroupByField());
		 System.out.println("\nGroup by field: " + rs.getGroupByField());
		 
		 //calling function of AggregateFunctions
		 AggregateFunctions af =new AggregateFunctions();
		 af.setQuery(query);
		 af.setAggregateFunc(af.extractAggregateFunc());
		 System.out.println("\nAggregate Function: ");
		 for (String i : af.getAggregateFunc()) {
			 System.out.println(i);
		 }
		 
		 //calling method of ReadFile
		 ReadFile rf = new ReadFile();
	     rf.setFileName(qs.getFileName());
		 rf.readFile();
		 rf.extractDataType();
		 
		 //calling methods of QueryParser
		 QueryParser qp = new QueryParser();
		 qp.setQuery(query);
		 
		 qp.extractWhereData();
		 qp.extractSelectData();
		 
		
	}

}
