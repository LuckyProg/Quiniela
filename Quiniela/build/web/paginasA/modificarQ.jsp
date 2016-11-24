<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else if(sesion.getAttribute("rol").equals(1)){
String apodo = String.valueOf(sesion.getAttribute("usuario"));%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
        <link rel="stylesheet" type="text/css" href="../CSS/general.css" media="screen" />
        <title>MODIFCAR QUINIELA</title>
    </head>
    <body>
         <jsp:include page="navegadorA.jsp" flush="true">
            <jsp:param name="nu" value='<%=apodo%>'/>
            <jsp:param name="pag" value='3'/>
        </jsp:include> 
        <div id="in">
            
            <form>
                
            </form>
            
        </div>
    </body>
</html>
<%}%>