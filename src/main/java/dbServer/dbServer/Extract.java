package dbServer.dbServer;

import java.util.*;

public class Extract {

	public static void main(String[] args) {
		
		 Scanner sc = new Scanner(System.in);
		 String query = null;
		 
		 System.out.println("Enter the query:");
		 query = sc.nextLine();
		 
		 //calling Query Selector
		 QuerySelector qs = new QuerySelector(query);
		 qs.setTokens();
		 for (String i : qs.getTokens()) {
			 System.out.println(i);
		 }
		 qs.setFileName();
		 for (String i : qs.getFileName()) {
			 System.out.println(i);
			 Restrictions rs = new Restrictions(query);
			 rs.setConditions();
			 ReadFile rf = new ReadFile(i);
			 rf.readFile();
			 QueryParser qp = new QueryParser(query);
			 qp.setSelectData();
			 qp.setWhereData();
			 
			 
		 }
		 AggregateFunctions af =new AggregateFunctions(query);
		 af.setAggregateFunc();

		 for (String i : af.getAggregateFunc()) {
			 System.out.println(i);
		 }
		 
		 sc.close();
	}

}