<%@page import="Clases.Usuario"%>
<%@page import="Clases.Equipo"%>
<%@page import="java.util.Vector"%>
<%@page import="Clases.Survival"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else if(sesion.getAttribute("rol").equals(1)){
    String orden = request.getParameter("orden");
    if(orden==null){
        
        orden="5";

    }
    Survival sur = new Survival();

   %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
        <link rel="stylesheet" type="text/css" href="../CSS/general.css" media="screen" />
        <title>MOSTRAR SURVIVAL</title>
        <link href='https://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../fonts/style.css" media="screen"/>
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
        
        <div class="divid"  >
                    <%Survival wuera = new Survival();%>
                                <center><h2 id="h11"><font color="white">Modificar Survival: &nbsp;</h2> </font><br><br>
                        <form name="f1" method="post" action="../modificarSurvival">
                                    <table align="center" border="1">
                            
                            <tr>
                                <td><center><font color="black">Semana: &nbsp; </font></td>
                                <td>
                                    
                                
                                   <select name="semana">
                                    <option value=1>1</option>
                                    <option value=2>2</option>
                                    <option value=3>3</option>
                                    <option value=4>4</option>
                                    <option value=5>5</option>
                                    <option value=6>6</option>
                                    <option value=7>7</option>
                                    <option value=8>8</option>
                                    <option value=9>9</option>
                                    <option value=10>10</option>
                                    <option value=11>11</option>
                                    <option value=12>12</option>
                                    <option value=13>13</option>
                                    <option value=14>14</option>
                                    <option value=15>15</option>
                                    <option value=16>16</option>
                                    <option value=17>17</option>
                                </select>
                                </td>
                            </tr>
                            <tr>
                                <td><center><font color="black">Usuario: &nbsp; </font></td>
                                <td>
                                <select name="apodo">
                                <%Vector<Usuario> usuarios=new Usuario().mostrarUsuarios(orden);
                                for(Usuario usu:usuarios){%>
                                    <option value="<%=usu.getApodo()%>">
                                       <%=usu.getNombre()%>
                                    </option>
                                
                                <%}%>
                                </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                        <select name="id_equipo">
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
                                <td>
                                    <input align="center" id="bb" type="submit" value="Guardar">
                                </td>
                                
                            </tr>
                            
                        </table>
                                            </form></center>
		</div>
    </body>
</html>
<%}%>
