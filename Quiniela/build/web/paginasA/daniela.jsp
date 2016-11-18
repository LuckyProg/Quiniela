<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            StringBuilder result = new StringBuilder();
        URL url = new URL("http://api.suredbits.com/nfl/v0/games");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = rd.readLine()) != null) {
         result.append(line);
      }
      rd.close();
      
      String a = "{\"games\":"+result.toString()+"}";
      System.out.println(a);
      
        %>
        <script>
            //org.json.simple.JSONObject; tutorial
            //https://www.mkyong.com/java/json-simple-example-read-and-write-json/
            //API : https://www.suredbits.com/api/nfl/games/
        var data = '<%=a%>';

	var json = JSON.parse(data);

	
	alert(json.games[15].gsisId); //mkyong

        
    </script>
    
        <h1><%%></h1>
    </body>
    
</html>
