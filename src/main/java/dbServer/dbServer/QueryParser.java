package dbServer.dbServer;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class QueryParser {
 private ArrayList<String> selectData = new ArrayList<String>();
 private ArrayList<String> whereData = new ArrayList<String>();
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
 public void extractWhereData(String query) {
  
  try {
   //extracting conditions
   ArrayList<String> whereConditions = new Restrictions().extractConditions(query);
   //terminating if no where clause
   if(whereConditions == null || whereConditions.size() == 0)
    throw new NullPointerException();
 
   String fileName = new QuerySelector().extractFileName(query);
   // terminate if no filename found
   if(fileName == null)
    throw new FileNotFoundException();
   
   //reading file to get data and header information
   ReadFile rf = new ReadFile();
   rf.readFile("ipl.csv");
   ArrayList<String> data = rf.getData();
   String[] key = rf.getHeader();
   ArrayList<String> logicalOps = new Restrictions().extractOperators(query);
   //getting field to compare
   int index = -1;
   int lo = 0;
   String f = null, op = null, val = null;
   for (String i : whereConditions) {
	   if(index > -1 && logicalOps.size() != 0) {
		   if(lo < logicalOps.size()) {
			   if(logicalOps.get(lo).equals("or")) {
				   
			   }
			   else if(logicalOps.get(lo).equals("and")) {
				   data = whereData;
			   }
		   }
		   lo++;
	   }
    //pattern to match field, operator and value of the conditions
    Pattern p = Pattern.compile("(\\w+)([ ]?)(<>|>=|<=|!=|>|<|=|like|in|not like|not in|between[ ]?\\d[ ]?and\\d)([ ]?['(]?)(\\w+)([')]?)");   
    Matcher m = p.matcher(i);
    if(m.find()) {
      f = m.group(1); //field
      op = m.group(3);  //operator
      val = m.group(5); //value
    }
    
    //terminating if no conditions found or if wrong syntax
    if (f == null || op == null || val == null) {
     throw new NullPointerException();
    }
   
   //getting the index of the fields in file
   for (int j=0; j<key.length; j++) {
     if(f.equals(key[j]))
      {index = j;}
     } 
   
   //performing operations according to the operator
   switch(op) {
   //smaller than operator logic
   case ">" : double dVal = Double.parseDouble(val);
		      for(String ic : data) {
		       String i1[] = ic.split(",");
		       if((Double.parseDouble(i1[index]))>dVal)
		       {
		        whereData.add(ic);
		       }
		      }
		      break;
      
   //greater than operator logic
   case "<" : double dVal1 = Double.parseDouble(val);
		      for(String ic : data) {
		       String i1[] = ic.split(",");
		       if((Double.parseDouble(i1[index]))<dVal1)
		       {
		        whereData.add(ic);
		       }
		      }
		      break;
   case "<>" :
   case "not like" :
			   for(String ic : data) {
			    String i1[] = ic.split(",");
			    if(!(i1[index]).equalsIgnoreCase(val))
			    {
			     whereData.add(ic);
			    }
			   }
			   break;
   case ">=" : double dVal2 = Double.parseDouble(val);
			   for(String ic : data) {
			    String i1[] = ic.split(",");
			    if((Double.parseDouble(i1[index]))>=dVal2)
			    {
			     whereData.add(ic);
			    }
			   }
			   break;
   case "<=" : double dVal3 = Double.parseDouble(val);
			   for(String ic : data) {
			    String i1[] = ic.split(",");
			    if((Double.parseDouble(i1[index]))<=dVal3)
			    {
			     whereData.add(ic);
			    }
			   }
			   break;
   
   //equals operator logic
   case "=" :
   case "like" :
		      for(String ic : data) {
		       String i1[] = ic.split(",");
		       if(val.equalsIgnoreCase(i1[index]))
		       {
		        whereData.add(ic);
		       }
		      }
		      break;
   
   //default values
   default : System.out.println("Invalid option");
       throw new NullPointerException();
   }
    }
  } catch(NullPointerException e) {
   System.out.println("Invalid query syntax");
   e.printStackTrace();
  } catch(FileNotFoundException e) {
   System.out.println("File doesn't exists");
   e.printStackTrace();
  } catch(Exception e) {
   e.printStackTrace();
  }
  
 }
 
 //method to obtain data based on select clause
 public void extractSelectData(String query) {
  try {
   if(query == null)
    throw new NullPointerException();
   QuerySelector qs = new QuerySelector();
   String[] fields = qs.extractFields(query);
   String fileName = qs.extractFileName(query);
   
   //terminating if no filename
   if(fileName == null)
    throw new FileNotFoundException();
   ReadFile rf = new ReadFile();
   rf.readFile(fileName);
   ArrayList<String> data = getWhereData();
   
   //setting data as normal data if no where clause
   data = (data.size() != 0)? data : rf.getData();
   String[] key = rf.getHeader();
   
   int[] index= new int[key.length];
   int ic = 0;
   // for queries to print all the columns like 'Select * from ipl.csv' 
   if(fields[0].equals("*")) {
    fields = key;
    for (String i : fields) {
     for (int j=0; j<key.length; j++) {
       {index[ic++] = j;}
      }   
     }
   }
   else if(fields != null){
   for (String i : fields) {
    for (int j=0; j<key.length; j++) {
     if(i.equals(key[j]))
      {index[ic++] = j;}
     }   
    }
   }
   else {
    //terminating if no fields found
    throw new NullPointerException();
   }
   
   for (String jc : data) {
    String[] j = jc.split(",");
    for (int i=0; i<ic; i++) {
     
     selectData.add(j[index[i]]);
    } 
   
  }
  //calling method to create JSON object
  new Json().createJSON(index, ic, key, data);
 } catch(NullPointerException e) {
  System.out.println("Invalid query type");
  e.printStackTrace();
 } catch(FileNotFoundException e) {
  System.out.println("File doesn't exist");
  e.printStackTrace();
 } catch(Exception e) {
  e.printStackTrace();
 }
  }
}

