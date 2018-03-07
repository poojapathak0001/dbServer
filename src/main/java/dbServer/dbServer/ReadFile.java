package dbServer.dbServer;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFile {
	private String[] header = null;
	private ArrayList<String> data = null;
	private LinkedHashMap<String, String> dataTypes = new LinkedHashMap<String,String>();
	
	//getter and setter for header
	public String[] getHeader() {
		return header;
	}
	public void setHeader(String[] header) {
		this.header = header;
	}
	
	//getter and setter for data
	public ArrayList<String> getData() {
		return data;
	}
	public void setData(ArrayList<String> data) {
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
	public void readFile(String file) {
		  BufferedReader br = null;  
		  String line = "";   
		  try {  
			  if(file == null)
				  throw new FileNotFoundException();

			   String csvFileToRead = "csvFiles/" + new QuerySelector().extractFileName(file);  
			   br = new BufferedReader(new FileReader(csvFileToRead)); 
			   setHeader(br.readLine().split(","));
			   ArrayList<String> data = new ArrayList<String>();
			   //storing data
			   while ((line = br.readLine()) != null) {  
			    data.add(line);
			   }  
			   setData(data);
		   } catch (FileNotFoundException e) {  
		    e.printStackTrace();  
		   } catch (IOException e) {  
		    e.printStackTrace();  
		   } catch (Exception e) {
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
		for (String ic : data) {
			String i[] = ic.split(",");
			 for (int j= 0; j<i.length ; j++) {
				 try {
					 Integer.parseInt(i[j]);
					 datatype.put(header[j],"int");
				 } catch (NumberFormatException e) {
					 try {
						 Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
						 Matcher m = p.matcher(i[j]);
						 if(m.matches())
							 datatype.put(header[j],"date");
						 else
							 datatype.put(header[j],"string");;
					 }catch (NullPointerException ex) {
					   ex.printStackTrace();
					}catch (Exception ex) {
						ex.printStackTrace();
					}
				 }
			 }
		 }
		 setDataTypes(datatype);
	}
}
