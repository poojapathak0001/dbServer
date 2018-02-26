package dbServer.dbServer;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
public class Json {
 
 public void createJSON(int[] index, int ic, String[] header, ArrayList<String> data) {   
  
	  LinkedHashMap<String, Object> tom = new LinkedHashMap<String, Object>();
	  
	  int i = 0;
	  while (i<ic) {
		  ArrayList<String> datanew = new ArrayList<String>() ;
		  int x = 0;
		  for (String j : data) {
			  String js[] = j.split(",");
			  datanew.add(js[index[i]]);
		  }
		  tom.put(header[index[i]], datanew);
		  i++;
	  }
	     /*
	     for (String jc : data) {
	   String[] j = jc.split(",");
	   if(i<ic)
	   tom.put(header[index[i++]], j);//(header[index[i++]], j);
	   System.out.println();
	  
	     }*/
	     JSONObject tomJsonObj = new JSONObject(tom);
	         System.out.println(tomJsonObj.toString());
	 }
}
