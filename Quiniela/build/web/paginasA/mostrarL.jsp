<%@page import="Clases.Liga"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else if(sesion.getAttribute("rol").equals(1)){
    int liga;
    if(request.getParameter("liga")!=null){
        liga = Integer.parseInt(request.getParameter("liga"));
    }else {liga=1;}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
        <link href="../CSS/posiciones.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="../CSS/general.css" media="screen" />
        <title>LIGAS</title>
        <link href='https://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../CSS/estilos2.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../fonts/style.css" media="screen"/>
        <link href="../CSS/posiciones.css" rel="stylesheet" type="text/css"/>
        <script src="../JS/tableS.js" type="text/javascript"></script>
        <script src="../JS/cv.js" type="text/javascript"></script>
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
        <%
            Vector<Liga> lig = new Liga().mostrarLigas(1);
            if(lig.isEmpty()){
        %>
        <form method="post" action="../generarL">
            <input id="generarL" type="submit" value="Crear Ligas">
        </form>
        <%}else{%>
        <form method="post" action="../borrarL">
            <input id="generarL" type="submit" value="Borrar Ligas">
        </form>
        <%}%>
        <%Vector<Liga> ligas = new Liga().mostrarLigas(liga);
            if(ligas.isEmpty()){
             %>
             <div id="noL" style="background: transparent;">NO HAY LIGAS</div>
            <%}else{
            
            
            Vector<Liga> ligasT = new Liga().mostrarTop(liga,"AMERICANA");%>
            
            <div id="seleccionar_liga" style="position: absolute;top:0;right: 5%;background: transparent;">
                <ul>
                    <%
                    int numL = new Liga().numL();
                    for(int i = 1; i <= numL; i++){
                    %>
                    
                    <a href="?liga=<%=i%>"><li style="width: <%=80/numL%>%; <%if(i==liga){%>background:#4caf50;<%}%>">LIGA <%=i%></li></a>
                    <%}%>
  
                </ul>
                    
            </div>
                    <form method="post" action="../cerrarS">
            <input id="generarL2" type="submit" value="Cerrar Semana">
            <p id="asdasd">Semana:</p><select name="semana" id="generalL3">
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
            </form>
            <div id="ligas" style="position:absolute;top: 17%;right: 5%;background: transparent;">
                <table class="sortable" id="ligaL">
                    
                    <thead>
                        <tr>
                            <th>CONFERENCIA</th>   <th>DIVISIÓN</th>    <th>USUARIO</th> <th>JG</th>  <th>JP</th> <th>JE</th> <th>DIV</th>    <th>AFC</th>    <th>NFC</th>    <th>PF</th> <th>PC</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Liga l:ligas){%>
                        <tr>
                            <td><%=l.getConferencia()%></td>
                            <td><%=l.getRegion()%></td>
                            <td><%=l.getNombreU()%></td>
                            <td><%=l.getJg()%></td>
                            <td><%=l.getJp()%></td>
                            <td><%=l.getJe()%></td>
                            <td><%=l.getDivi()%></td>
                            <td><%=l.getAfc()%></td>
                            <td><%=l.getNfc()%></td>
                            <td><%=l.getPf()%></td>
                            <td><%=l.getPc()%></td>      
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <table class="sortable" id="ligaR">
                    <thead>
                        <tr>
                            <th colspan="2" style="font-size: 20px;"><span class="icon-trophy"></span>&nbsp;&nbsp;AMERICANA</th>
                        </tr>
                        <tr>
                            <th >USUARIO</th>   <th>DIVISIÓN</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Liga daniela:ligasT){%>
                        <tr>
                            <td><%=daniela.getNombreU()%></td>
                            <td><%=daniela.getRegion()%></td>    
                        </tr>
                        <%}%>
                        
                    </tbody>
                </table>
                <table class="sortable" id="ligaR2">
                    <thead>
                        <tr>
                            <th colspan="2" style="font-size: 20px;"><span class="icon-trophy"></span>&nbsp;&nbsp;NACIONAL</th>
                        </tr>
                        <tr>
                            <th >USUARIO</th>   <th>DIVISIÓN</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%ligasT = new Liga().mostrarTop(liga,"NACIONAL");
                        
                       for (Liga daniela:ligasT){%>
                        <tr>
                            <td><%=daniela.getNombreU()%></td>
                            <td><%=daniela.getRegion()%></td>    
                        </tr>
                        <%}%>
                         
                    </tbody>
                </table>
            </div>
                        <%}%>
    </body>
</html>
<%}%>