<%@page import="Clases.Equipo"%>
<%@page import="Clases.Partido"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else if(sesion.getAttribute("rol").equals(1)){
    
    String sem = request.getParameter("sem");
    if(sem==null){
        
        sem="1";

    }
    String apodo = String.valueOf(sesion.getAttribute("usuario"));
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
        <link rel="stylesheet" type="text/css" href="../CSS/general.css" media="screen" />
        <script type="text/javascript" src="../JS/val.js"></script>
        <title>RESULTADOS</title>

    </head>
    <body onpaste="return false;">
         <jsp:include page="navegadorA.jsp" flush="true">
            <jsp:param name="nu" value='<%=apodo%>'/>
            <jsp:param name="pag" value='2'/>
        </jsp:include> 
        <div id="in">
            <center>
                
                <h1>Resultados</h1><br>
                <form method="Post" action="resultadosP.jsp">
                    Semana: <input type="number" name="sem" >
                    <input type="submit" value="Buscar">
                </form> 
                <br>
                <%
                    
                Vector<Partido> partidos=new Partido().mostrarPartidos(sem);
                Vector<Equipo> ecuip=new Equipo().mostrarEquipos(); 
                %>
                <table border="1">
                    <tr>
                        <td>SEMANA</td>
                        <td>VISITANTE</td>
                        <td>LOCAL</td>
                        <td>MAYOR</td>
                        <td>MENOR</td>
                        <td></td>
                        
                    </tr>
                    <%
                    for(Partido par:partidos){
                    %>
                             <tr>
                               <form method="Post" action="../subirResultados"> 
                                    <td>
                                        <input name="id_partido" type="hidden" value="<%=par.getId_partido()%>">
                                        <input name="fecha" type="hidden" value="<%=par.getFecha()%>">
                                        <input name="pr_mar" type="hidden" value="<%=par.isPr_mar()%>">
                                        <input name="semana" type="hidden" value="<%=par.getSemana()%>">
                                        <%=par.getSemana()%>
                                    </td>
                                    <td>
                                        <%=par.getVisitante()%>
                                        <input name="visitante" type="hidden" value="<%=par.getId_visitante()%>">
                                        <br>
                                        <br>
                                        <center><input type="radio" name="ganadoreal" value="<%=par.getId_visitante()%>" <%if(par.getId_visitante() == par.getGanadoreal()){%>checked<%}%>></center>
                                    </td>
                                    <td>
                                        <%=par.getLocal()%>
                                        <input name="local" type="hidden" value="<%=par.getId_local()%>">
                                        <br>
                                        <br>
                                        <center><input type="radio" name="ganadoreal" value="<%=par.getId_local()%>" <%if(par.getId_local() == par.getGanadoreal()){%>checked<%}%>></center>
                                    </td>
                                    <td>
                                        <input name="mayoreal" type="number" onkeypress="return soloNum(event)" value="<%=par.getMayoreal()%>">
                                    </td>
                                    <td>
                                        <input name="menoreal" type="number" onkeypress="return soloNum(event)" value="<%=par.getMenoreal()%>">
                                    </td>
                                    <td><input type="submit" value="Guardar"></td>
                                </form>
                            </tr>
                     <%}%>
                </table>
        
            </center>
        </div>
    </body>
</html>
<%}%>
