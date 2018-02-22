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
		int index = 0;
		String f = null, op = null, val = null;
		for (String i : whereConditions) {
			System.out.println(i);
			Pattern p = Pattern.compile("(\\w+)([ ]?)(<>|>=|<=|!=|>|<|=|like|in|not like|not in|between[ ]?\\d[ ]?and\\d)([ ]?['(]?)(\\w+)([')]?)");			
			Matcher m = p.matcher(i);
			if(m.find()) {
				 f = m.group(1);
				 op = m.group(3);
				System.out.println(op);
				 val = m.group(5);
				System.out.println(f+op+val);	
			}
		}
		for (int j=0; j<key.length; j++) {
				if(f.equals(key[j]))
					{index = j;}
				}					
		//action according to the operator

		switch(op.charAt(0)) {
		case '>' : double dVal = Double.parseDouble(val);
					for(String ic : data) {
						String i[] = ic.split(",");
						if((Double.parseDouble(i[index]))>dVal)
						{
							whereData.add(i[index]);
						}
					}
					break;
		case '=' : System.out.println(val);
					for(String ic : data) {
						String i[] = ic.split(",");
						System.out.println("data"+i);
						if(val.equals(i))
						{
							whereData.add(i[index]);
							System.out.println(whereData);
						}
					}
					break;
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
		ArrayList<String> data = rf.getData();
		String[] key = rf.getHeader();
		
		int[] index= new int[key.length];
		int ic = 0;
		System.out.println(fields);
		if(fields[0].equals("*")) {
			fields = key;
		}
		else {
		for (String i : fields) {
			System.out.println(i);
			for (int j=0; j<key.length; j++) {
				if(i.equals(key[j]))
					{index[ic++] = j;}
				}			
			}
		}
		for (int i=0; i<ic; i++) {
			for (String jc : data) {
				String[] j = jc.split(",");
				selectData.add(j[index[i]]);
System.out.print(j[index[i]]+", ");
			}	
		
	}
	
}}
