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
	
	//getter and setter for filename	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	//getter and setter for header
	public String[] getHeader() {
		return header;
	}
	public void setHeader(String[] header) {
		this.header = header;
	}
	
	//getter and setter for data
	public ArrayList<String[]> getData() {
		return data;
	}
	public void setData(ArrayList<String[]> data) {
		this.data = data;
	}
	
	//getter and setter for data type
	public LinkedHashMap<String, String> getDataTypes() {
		return dataTypes;
	}
	public void setDataTypes(LinkedHashMap<String, String> dataTypes) {
		this.dataTypes = dataTypes;
	}
	
	///method to read file
	public void readFile() {
		String csvFileToRead = getFileName();  
		  BufferedReader br = null;  
		  String line = "";   
		  try {  
			   br = new BufferedReader(new FileReader(csvFileToRead)); 
			   setHeader(br.readLine().split(","));
			   ArrayList<String[]> data = new ArrayList<String[]>();
			   while ((line = br.readLine()) != null) {  
			    data.add(line.split(","));
			   }  
			   setData(data);
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
	
	//method to identify data type
	public void extractDataType() {
		LinkedHashMap<String, String> datatype = new LinkedHashMap<String, String>();
		for (String[] i : data) {
			 for (int j= 0; j<i.length ; j++) {
				 try {
					 Integer.parseInt(i[j]);
					 datatype.put(header[j],"int");
				 } catch (Exception e) {
					 try {
						 Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
						 Matcher m = p.matcher(i[j]);
						 if(m.matches())
							 datatype.put(header[j],"date");
						 else
							 datatype.put(header[j],"string");;
					 }catch (Exception ex) {
					   
					}
				 }
			 }
		 }
		 setDataTypes(datatype);
	}
}
