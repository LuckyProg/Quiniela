<%@page import="Clases.Puntaje"%>
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
        <title>Resultados</title>
        <link href='https://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../CSS/estilos2.css" media="screen" />
        <link href="../CSS/resulta.css" rel="stylesheet" type="text/css"/>
        <script src="../JS/tableS.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="../fonts/style.css" media="screen"/>
    </head>
    <body id="bgsurv">
       <jsp:include page="navegador.jsp" flush="true">
            <jsp:param name="nu" value='<%=apodo%>'/>
            <jsp:param name="pag" value='5'/>
        </jsp:include>   
        
        <div id="ligas">
            <div class="semanal">
                <ul>
                    <%for(int x = 1; x<18; x++){%>
                    <a href="resultados.jsp?semana=<%=x%>"><li <%if(x==semana){%>id="selecct"<%}%>><%=x%></li></a>
                    <%}%>
                </ul>
            </div>
            <center><h1>RESULTADOS SEMANALES</h1></center>
            <table class="sortable" id="ligaL">

                <thead>
                    <tr>
                        <th>USUARIO</th>     
                        <th>P</th>    
                        <th>DL</th> 
                        <th>DS</th>
                    </tr>
                </thead>
                <tbody>
                    <%Vector<Puntaje> puntazo = new Puntaje().mostrarPuntajes(semana);
                    for(Puntaje puño:puntazo){%>
                    <tr>  
                        <td><%=puño.getNombre()%></td>
                        <td><%=puño.getP()%></td>
                        <td><%=puño.getDl()%></td>
                        <td><%=puño.getDs()%></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
    </body>
</html><%}%>
