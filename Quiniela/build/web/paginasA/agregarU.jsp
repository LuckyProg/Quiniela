<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%HttpSession sesion = request.getSession();
    if(sesion.getAttribute("usuario") == null){
        response.sendRedirect("../index.jsp");
    }else if(sesion.getAttribute("rol").equals(1)){
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
        <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="../CSS/general.css" media="screen" />
        <title>INSERTAR</title>
        <script type="text/javascript" src="../JS/val.js"></script>

    </head>
    <body >
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
        <%
        String nombre = request.getParameter("nombre");
        String pass = request.getParameter("pass");
        if(nombre!=null && pass!=null){
        %>    
        <h3>
            Usuario registrado exitosamente.
            <br>
            <br>
             Nombre: <%out.println(nombre);%>
            <br>
             Contraseña: <%out.println(pass);%>
        </h3>
        <br>
        <%}%> 
        <center>
        <h1>INSERTAR</h1><br>
        <form name="usuario" method="post" action="../registrarUsuario">
            <table border="1">
                <tr>
                    <td>Nombre(s): <input type="text" name="nombre" onkeypress="return soloLetras(event)" maxlength="49"  ondrop='return false' required></td>
                </tr>
                <tr>
                    <td>Monto pagado: <input type="text" name="monto" onkeypress="return soloNum(event)" maxlength="49" ondrop='return false' onpaste=return false required></td>
                </tr>
                    <td>
                        Privilegio:
                        <select name="rol">
                            <option value="2">Jugador</option>
                            <option value="1">Administrador</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Enviar"></td>
                </tr>
            </table>
        </form>
        </div>
</center>
        <%}%>
    </body>
</html>