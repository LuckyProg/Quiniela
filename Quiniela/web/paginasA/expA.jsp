<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else if(sesion.getAttribute("rol").equals(1)){
    int semana = 0;
    if(request.getParameter("semana")!=null){
        semana = Integer.parseInt(request.getParameter("semana"));
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
        <link rel="stylesheet" type="text/css" href="../CSS/general.css" media="screen" />
        <link href="../CSS/eA.css" rel="stylesheet" type="text/css"/>
        <title>EXPORTAR ARCHIVOS</title>

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
                            <li><a href="agregarU.jsp">• Agregar Usuario</a></li>
                            <li><a href="modificarU.jsp">• Modificar</a></li>  
                        </ul>
                </section>
                <section>
                        <br><br><div><h2>Partidos</h2></div><br>
                        <ul>
                            <li><a href="agregarP.jsp">• Agregar partido</a></li>
                            <li><a href="modificarP.jsp">• Modificar partidos</a></li>
                            <li><a href="resultadosP.jsp">• Subir Resultados</a></li>
                        </ul>
                </section>
                <section>
                        <br><br><div><h2>Quiniela</h2></div><br>
                        <ul>
                            <li><a href="modificarQ.jsp">• Modificar quiniela</a></li>
                        </ul>
                </section>
                <section>
                        <br><br><div><h2>Liga</h2></div><br>
                        <ul>
                            <li><a href="mostrarL.jsp">• Ver ligas</a></li>
                        </ul>
                </section>
                <section>
                        <br><br><div><h2>Survivor</h2></div><br>
                        <ul>
                            <li><a href="mostrarS.jsp">• Consultar Survivor</a></li>        
                        </ul>
                </section>
                <section>
                        <br><br><div><h2>Exportar</h2></div><br>
                        <ul>
                            <li><a href="expA.jsp">•Archivos </a></li>                                
                        </ul>
                </section>
                <section>
                        <br><br><div><a href="../cerrarSesion"><h2>Salir</h2></a></div>
                </section>
                <br>
            </center>
        </div>
        
        <div class="quinie"> 
        <div class="semanal">
                    <ul>
                        <%for(int x = 1; x<18; x++){%>
                        <a href="expA.jsp?semana=<%=x%>"><li <%if(x==semana){%>id="selecct"<%}%>><%=x%></li></a>
                        <%}%>
                    </ul>
                </div>
                        <div class="jugadores">
                            <center>
                                <form action="../generarArchivo" method="get">
                                Archivo: &nbsp;&nbsp;&nbsp;&nbsp;
                                <select id="selet" name="tipo">
                                    <optgroup>
                                        <option value="1">Usuarios</option>
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
</html>
<%}%>