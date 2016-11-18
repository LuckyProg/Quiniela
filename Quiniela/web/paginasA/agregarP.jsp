<%@page import="Clases.Equipo"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else if(sesion.getAttribute("rol").equals(1)){%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
        <link rel="stylesheet" type="text/css" href="../CSS/general.css" media="screen" />
        <title>AGREGAR PARTIDO</title>
        <script type="text/javascript" src="../JS/val.js"></script>

       
    </head>
    <body onpaste="return false;">
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
        <div id="in"><center><h1>AGREGAR PARTIDO</h1><br><br>
       
        <form method="post" action="../agregarPartido">
            <table border="1">
                <tr>
                    <td>
                        Visitante:
                        <select name="visitante">
                            <%
                                Vector<Equipo> ecuip=new Equipo().mostrarEquipos();                  
                                for(Equipo e:ecuip){                  
                            %>
                            <option value='<%=e.getId_equipo()%>'><%=e.getNombre()%></option>                           
                            <%
                                
                             }
                            %>
                            
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        Local:
                        <select name="local">
                           <%for(Equipo e:ecuip){                  
                            %>
                            <option value='<%=e.getId_equipo()%>'><%=e.getNombre()%></option>                           
                            <%
                             }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Semana:<input type="number" name="semana" onkeypress="return soloNum(event)" maxlength=2></td>
                </tr>
                <tr>
                    <td>Fecha:<input type="datetime-local" name="fecha" onsubmit="return soloNum(this.value)"></td>
                </tr>
                <tr>
                    <td>Primer marcador:<input type="checkbox" value=true name="primer_marcador"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Guardar"></td>
                </tr>
            </table>
        </form></div></center>
    </body>
</html>
<%}%>
