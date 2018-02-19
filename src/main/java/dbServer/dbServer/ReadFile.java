 package dbServer.dbServer;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFile {
	private String fileName = null;
	private String[] header = null;
	private ArrayList<String[]> data = null;
	private LinkedHashMap<String, String> dataTypes = new LinkedHashMap<String,String>();
	
	
	public ReadFile(String fileName) {
		super();
		this.fileName = fileName;
	}
	public String[] getHeader() {
		return header;
	}
	public ArrayList<String[]> getData() {
		return data;
	}
	public LinkedHashMap<String, String> getDataTypes() {
		return dataTypes;
	}
	public void readFile() {
		String csvFileToRead = "csvFiles/"+fileName;  
		  BufferedReader br = null;  
		  String line = "";   
		  try {  
			   br = new BufferedReader(new FileReader(csvFileToRead)); 
			   header = br.readLine().split(",");
			   while ((line = br.readLine()) != null) {  
			    data.add(line.split(","));
			   }  
		   } catch (FileNotFoundException e) {  
		    e.printStackTrace();  
		   } catch (IOException e) {  
		    e.printStackTrace();  
		   } finally {  
			   if (br != null) {  
			    try {  
			     br.close();  
			    } catch (IOException e) {  
			     e.printStackTrace();  
			    }  
			  }  
		  }  
	}
	public void setDataType() {
		for (String[] i : data) {
			 for (int j= 0; j<i.length ; j++) {
				 try {
					 Integer.parseInt(i[j]);
					 dataTypes.put(header[j],"int");
				 } catch (Exception e) {
					 try {
						 Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
						 Matcher m = p.matcher(i[j]);
						 if(m.matches())
							 dataTypes.put(header[j],"date");
						 else
							 dataTypes.put(header[j],"string");;
					 }catch (Exception ex) {
					   
					}
				 }
			 }
		 }

		 System.out.println(dataTypes);
	}
}