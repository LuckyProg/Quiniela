<%@page import="Clases.Usuario"%>
<%@page import="Clases.Survival"%>
<%@page import="Clases.Equipo"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else{
    String apodo = sesion.getAttribute("usuario").toString();
    String orden = request.getParameter("orden");
    if(orden==null){
        
        orden="5";

    }
    Survival sur = new Survival();
    int semana = 0;
   for(int i=1;i<=17;i++){
       if(sur.noseleccionado(apodo, i)){
           semana = i;
           break;
       }
   }
    %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
		<link rel="shortcut icon" type="image/x-icon" href="IMG/IC.ico">
        <title>SURVIVAL</title>
        <link href='https://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../CSS/estilos2.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../CSS/survival.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../fonts/style.css" media="screen"/>
    </head>
    <body id="bgsurv">
       <jsp:include page="navegador.jsp" flush="true">
            <jsp:param name="nu" value='<%=apodo%>'/>
        </jsp:include>   
        
	</body>
</html><%}%>
