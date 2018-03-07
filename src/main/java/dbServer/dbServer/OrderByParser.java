package dbServer.dbServer;

import java.util.ArrayList;

public class OrderByParser {
	private ArrayList<String> orderByData = new ArrayList<String>();

	//getter setter methods for order by
	public ArrayList<String> getOrderByData() {
		return orderByData;
	}

	public void setOrderByData(ArrayList<String> orderByData) {
		this.orderByData = orderByData;
	}
	
	
	//method to get order by data
	 public void extractOrderByData(String query, ArrayList<String> selectData) {
		  try {
		   if(query == null)
		    throw new NullPointerException();
		   
		   //getting order by fields
		   Restrictions rs = new Restrictions();
		   String orderBy = rs.extractOrderByField(query);
		   
		   int flipOrder = -1;
		   flipOrder = query.toLowerCase().indexOf("desc");
		   
		   
		  
		 } catch(NullPointerException e) {
		  System.out.println("Invalid query type");
		  e.printStackTrace();
		 } catch(Exception e) {
		  e.printStackTrace();
		 }
	 }

}
