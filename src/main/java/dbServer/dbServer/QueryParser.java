package dbServer.dbServer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryParser {
	private String query = null;
	private String[] fields = null;
	private ArrayList<String> selectData = new ArrayList<String>();
	private ArrayList<String> whereData = new ArrayList<String>();

	public QueryParser(String query) {
		super();
		this.query = query;
	}
		
	public String[] getField() {
		return fields;
	}

	public void setField() {
		if(query != null)
		{	
			QuerySelector qs = new QuerySelector(query);
			qs.setFields();
			this.fields = qs.getFields();
		}
		else 
			System.out.println("errr");
	}
	
	public ArrayList<String> getSelectData() {
		return selectData;
	}
	
	public ArrayList<String> getWhereData() {
		return whereData;
	}

	public void setWhereData() {
		Restrictions rs = new Restrictions(query);
		rs.setConditions();
		ArrayList<String> whereConditions = rs.getConditions();
		for (String i : whereConditions)
			System.out.println(i);
		Pattern p = Pattern.compile("(\\w+)([ ]?)(<>|>=|<=|!=|>|<|=|like|in|not like|not in|)([ ][']?)(\\w+)(['])");

		QuerySelector qs = new QuerySelector(query);
		qs.setFileName();
		ArrayList<String> fileName = qs.getFileName();
		ReadFile rf = new ReadFile(fileName.get(0));
		rf.readFile();
		ArrayList<String[]> data = rf.getData();
		String[] key = rf.getHeader();
				
		//getting field to compare
		int[] index= new int[key.length];
		int ic = 0;
		String f = null, op = null, val = null;
		for (String i : whereConditions) {
			System.out.println(i);
			Matcher m = p.matcher(i);
			if(m.find()) {
				 f = m.group(1);
				 op = m.group(3);
				 val = m.group(5);
				System.out.println(f+op+val);
				for (int j=0; j<key.length; j++) {
					if(f.equals(key[j]))
						{index[ic++] = j;
					System.out.println(index[ic-1]);}
				}
			}
		}
		
		//action according to the operator
		System.out.println("Switch");
		switch(op) {
		case ">" : double dVal = Double.parseDouble(val);
					for(String[] i : data) {
						if((Integer.parseInt(i[index[0]]))>dVal)
						{
							whereData.add(i[index[0]]);
						}
					}
					break;
		case "=" : 
			System.out.println(val);
					for(String[] i : data) {
						System.out.println(i);
						if(val.equals(i[index[0]]))
						{
							whereData.add(i[2]);
							//System.out.println(whereData);
						}
					}
					break;
		}
		
	}
	public void setSelectData() {
		QuerySelector qs = new QuerySelector(query);
		qs.setFileName();
		ArrayList<String> fileName = qs.getFileName();
		ReadFile rf = new ReadFile(fileName.get(0));
		rf.readFile();
		ArrayList<String[]> data = rf.getData();
		String[] key = rf.getHeader();
		setField();
		
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
			for (String[] j : data) {
				selectData.add(j[index[i]]);
			}	
		
	}
	
}}