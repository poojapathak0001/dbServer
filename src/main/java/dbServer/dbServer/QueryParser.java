package dbServer.dbServer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryParser {
	private String query = null;
	private ArrayList<String> selectData = new ArrayList<String>();
	private ArrayList<String> whereData = new ArrayList<String>();

	//getter and setter for query	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	//getter and setter for data based on select clause
	public ArrayList<String> getSelectData() {
		return selectData;
	}
	
	public void setSelectData(ArrayList<String> selectData) {
		this.selectData = selectData;
	}

	//getter and setter for data based on where clause
	public ArrayList<String> getWhereData() {
		return whereData;
	}

	public void setWhereData(ArrayList<String> whereData) {
		this.whereData = whereData;
	}
	
	//method to extract data based on where clause
	public void extractWhereData() {
		Restrictions rs = new Restrictions();
		rs.setQuery(query);
		rs.setConditions(rs.extractConditions());
		
		ArrayList<String> whereConditions = rs.getConditions();
		for (String i : whereConditions)
			System.out.println("Conditions:"+i);
		System.out.println();

		QuerySelector qs = new QuerySelector();
		qs.setQuery(query);
		qs.setFileName(qs.extractFileName());
		String fileName = qs.getFileName();

		ReadFile rf = new ReadFile();
		rf.setFileName(fileName);
		rf.readFile();
		ArrayList<String> data = rf.getData();
		String[] key = rf.getHeader();
				
		//getting field to compare
		int index = -1;
		String f = null, op = null, val = null;
		for (String i : whereConditions) {
			Pattern p = Pattern.compile("(\\w+)([ ]?)(<>|>=|<=|!=|>|<|=|like|in|not like|not in|between[ ]?\\d[ ]?and\\d)([ ]?['(]?)(\\w+)([')]?)");			
			Matcher m = p.matcher(i);
			if(m.find()) {
				 f = m.group(1);
				 op = m.group(3);
				 val = m.group(5);	
			}
		for (int j=0; j<key.length; j++) {
				if(f.equals(key[j]))
					{index = j;}
				}	
		
		//action according to the operator
		
		switch(op.charAt(0)) {
		case '>' : double dVal = Double.parseDouble(val);
					for(String ic : data) {
						String i1[] = ic.split(",");
						if((Double.parseDouble(i1[index]))>dVal)
						{
							whereData.add(ic);
						}
					}
					break;
		case '<' : double dVal1 = Double.parseDouble(val);
					for(String ic : data) {
						String i1[] = ic.split(",");
						if((Double.parseDouble(i1[index]))<dVal1)
						{
							whereData.add(ic);
						}
					}
					break;
		
		case '=' : 
					for(String ic : data) {
						String i1[] = ic.split(",");
						if(val.equalsIgnoreCase(i1[index]))
						{
							whereData.add(ic);
						}
					}
					break;
		}
		}
		
	}
	
	//method to obtain data based on select clause
	public void extractSelectData() {
		QuerySelector qs = new QuerySelector();
		qs.setQuery(query);
		qs.setFileName(qs.extractFileName());
		qs.setFields(qs.extractFields());
		String[] fields = qs.getFields();
		String fileName = qs.getFileName();
		
		ReadFile rf = new ReadFile();
		rf.setFileName(fileName);
		rf.readFile();
		ArrayList<String> data = getWhereData();
		data = (data.size() != 0)? data : rf.getData();
		String[] key = rf.getHeader();
		
		int[] index= new int[key.length];
		int ic = 0;
		if(fields[0].equals("*")) {
			fields = key;
			for (String i : fields) {
				System.out.print(i + " ");
				for (int j=0; j<key.length; j++) {
						{index[ic++] = j;}
					}			
				}
		}
		else {
		for (String i : fields) {
			System.out.print(i + " ");
			for (int j=0; j<key.length; j++) {
				if(i.equals(key[j]))
					{index[ic++] = j;}
				}			
			}
		}
		System.out.println("");
		for (String jc : data) {
			String[] j = jc.split(",");
			for (int i=0; i<ic; i++) {
				
				selectData.add(j[index[i]]);
				System.out.print(j[index[i]]+" ");
			}	
			System.out.println();
		
	}
  }
}
