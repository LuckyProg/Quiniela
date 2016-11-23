<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else if(sesion.getAttribute("rol").equals(1)){%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
        <link rel="stylesheet" type="text/css" href="../CSS/general.css" media="screen" />
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
        
        <form style="position:absolute; left:50%;"  action="../generarArchivo" method="get">
            <select name="tipo">
                <option value="1">Usuarios</option>
                <option value="2">Quiniela</option>
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
             <input type="submit" value="Generar Archivo">
        </form>
    </body>
</html>
<%}%>