<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else if(sesion.getAttribute("rol").equals(1)){
    
    String orden = request.getParameter("orden");
    if(orden==null){
        
        orden="0";

    }

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
        <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="../CSS/general.css" media="screen" />
        <title>CERRAR SEMANA</title>
        <script type="text/javascript" src="JS/val.js"></script>

    </head>
    <body>
        <div id="main-right">
            <center>
                <section>
                    <br>
                    <a href="inicio_admin.jsp"><h1>ADMIN</h1></a>
                    <br>
                    <br>
                    <div><h2>Usuarios</h2></div>
                    <br>
                        <ul>
                            <li><a href="agregarU.jsp"> Agregar Usuario</a></li>
                            <li><a href="modificarU.jsp"> Modificar</a></li>  
                        </ul>
                </section>
                <section>
                        <br><br><div><h2>Partidos</h2></div><br>
                        <ul>
                            <li><a href="agregarP.jsp"> Agregar partido</a></li>
                            <li><a href="modificarP.jsp"> Modificar partidos</a></li>
                            <li><a href="resultadosP.jsp"> Subir Resultados</a></li>
                        </ul>
                </section>
                <section>
                        <br><br><div><h2>Quiniela</h2></div><br>
                        <ul>
                            <li><a href="modificarQ.jsp"> Modificar quiniela</a></li>
                        </ul>
                </section>
                <section>
                        <br><br><div><h2>Liga</h2></div><br>
                        <ul>
                            <li><a href="mostrarL.jsp"> Ver ligas</a></li>
                        </ul>
                </section>
                <section>
                        <br><br><div><h2>Survivor</h2></div><br>
                        <ul>
                            <li><a href="mostrarS.jsp"> Consultar Survivor</a></li>        
                        </ul>
                </section>
                <section>
                        <br><br><div><h2>Exportar</h2></div><br>
                        <ul>
                            <li><a href="expA.jsp"> Archivos </a></li>                                
                        </ul>
                </section>
                <section>
                        <br><br><div><a href="../cerrarSesion"><h2>Salir</h2></a></div>
                </section>
                <br>
            </center>
        </div>
        <div id="in">
            <center>
                <h1>CERRAR SEMANA</h1>
                <br><br>
                <form method="Post" action="../CerrarSurvival">
                    <select name="semana">
                        <%for(int x = 1; x<18 ; x++){%>
                        <option value="<%=x%>"><%=x%></option>
                        <%}%>
                    </select>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="submit" value="Cerrar">
                </form> 
        </div>
    </body>
</html><%}%>
