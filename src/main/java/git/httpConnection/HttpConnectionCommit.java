package git.httpConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import git.dto.Commit;
import git.initials.Initials;
import git.models.Connection;

public class HttpConnectionCommit {
	
	public static final String USER_AGENT = "Mozilla/5.0";
	
	public static String sendGetCommit(Connection connection) throws Exception {
	 	 
		 String url = "https://api.github.com/repos/"+connection.getOwner()+"/"+connection.getProject()+"/commits?base="+connection.getBranchName();
		 String token=connection.getToken();
		 String tokenAuth = "Bearer "+ token;
		 
		 URL obj = new URL(url);
		 
		 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		 
		 // optional default is GET
	     con.setRequestMethod("GET");
	     
	     //add request header
	     con.setRequestProperty("User-Agent", USER_AGENT);
	     con.setRequestProperty("Authorization", tokenAuth);
	     
	     int responseCode = con.getResponseCode();
	     
	     System.out.println("\nSending 'GET' request to URL : " + url);
	     System.out.println("Response Code : " + responseCode);
	     BufferedReader in = new BufferedReader(
		          new InputStreamReader(con.getInputStream()));
		     String inputLine;
		     
		     StringBuffer response = new StringBuffer();
		     while ((inputLine = in.readLine()) != null) {
		            response.append(inputLine+ "\n");
		        }
		     
		     in.close();
		     
		     return response.toString();		       	
	}
	
	public static List<Commit> getCommits(String response) {
		 JSONArray jsonArr = new JSONArray(response.toString());
	     
	     List<Commit> list = new ArrayList<>();

	        for (int i = 0; i < jsonArr.length(); i++)
	        {
	        	JSONObject object = jsonArr.getJSONObject(i);
		         
	            JSONObject temp=(JSONObject) object.get("commit");
	            
	            JSONObject tempAuthor=(JSONObject) temp.get("author");
	            String author=tempAuthor.getString("name");
	            
	            String sha= object.getString("sha");
	            
	            String message= temp.getString("message");
	           
	            Commit request = new Commit();
	            request.setAuthor(Initials.getInitials(author));
	            request.setSha(sha);
	            request.setCommit_message(message);
	            
	            list.add(request);		            
	        }
	        
	        if(list.size()<=10){
	        	return list;
	        }
	        else {
	        	return list.subList( list.size()-11, list.size()-1);
	        }
	        
	}

}
