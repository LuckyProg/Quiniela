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
    int semana = 1;
    if(request.getParameter("semana")!=null){
        semana = Integer.parseInt(request.getParameter("semana"));
    }
    %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
		<link rel="shortcut icon" type="image/x-icon" href="IMG/IC.ico">
        <title>EXPORTAR</title>
        <link href='https://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../CSS/estilos2.css" media="screen" />
        <link href="../CSS/eA.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="../fonts/style.css" media="screen"/>
    </head>
    <body id="bgsurv">
       <jsp:include page="navegador.jsp" flush="true">
            <jsp:param name="nu" value='<%=apodo%>'/>
            <jsp:param name="pag" value='6'/>
        </jsp:include>   
        <div class="quinie"> 
        <div class="semanal">
                    <ul>
                        <%for(int x = 1; x<18; x++){%>
                        <a href="exportarA.jsp?semana=<%=x%>"><li <%if(x==semana){%>id="selecct"<%}%>><%=x%></li></a>
                        <%}%>
                    </ul>
                </div>
                        <div class="jugadores">
                            <center>
                                <form action="../generarArchivo" method="get">
                                Archivo: &nbsp;&nbsp;&nbsp;&nbsp;
                                <select id="selet" name="tipo">
                                    <optgroup>
                                        <option value="2">Quinielas</option>
                                        <option value="3">Survival</option>
                                    </optgroup>
                                </select>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="hidden" name="semana" value="<%=semana%>">
                                <input type="submit" value="Descargar Archivo">
                            </form>
                        </center>
                        </div>
        </div>
	</body>
</html><%}%>

