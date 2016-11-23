<%@page import="java.nio.file.Files"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="Clases.Archivo"%>
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
        <form  style="position:absolute; left:50%;"  action="../generarArchivo" method="get">
            <select name="tipo">
                <option value="2">Quinielas</option>
                <option value="3">Survival</option>
            </select>
            Semana: 
            <select name="semana">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
                <option value="13">13</option>
                <option value="14">14</option>
                <option value="15">15</option>
                <option value="16">16</option>
                <option value="17">17</option>
            </select>
             <input type="submit" value="Descargar Archivo">
        </form>
        
        
        
	</body>
</html><%}%>
