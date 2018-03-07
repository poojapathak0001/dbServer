package dbServer.dbServer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.JSONArray;
public class Json {
 
 public void createJSON(int[] index, int ic, String[] header, ArrayList<String> data) {   
	  
	  //json object to get result row wise from data
	  JSONObject json = new JSONObject();
	  //json array to store resultant objects 
	  JSONArray jsonArray = new JSONArray();
	  
	  //looping through data and initializing jsonArray
	  for (String entry : data) {
		  String fieldValue[] = entry.split(",");
		  for (int i=0; i<ic; i++) {
			  json.put(header[index[i]], fieldValue[i]);
		  }
		  jsonArray.put(json);
	  }
	  
	  //resultant json object
	  JSONObject result = new JSONObject();
	  result.put("result", jsonArray);
	  
	  //Writing to result to json file
	  try {  
           
          FileWriter fileWriter = new FileWriter("./dbServerData");  
          System.out.println("Writing JSON object to file");  
          System.out.println("-----------------------");    

          fileWriter.write(result.toString());  
          fileWriter.flush();  
          fileWriter.close();  

      } catch (IOException e) {  
          e.printStackTrace();  
      } 
	  
	  System.out.println("JSON file created:\n" + result.toJSONString());
	 }
}
