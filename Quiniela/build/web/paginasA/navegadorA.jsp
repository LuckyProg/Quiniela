<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%if(request.getParameter("nu")==null){
        
        response.sendRedirect("../index.jsp");
        }else{
            int pag = Integer.parseInt(request.getParameter("pag"));
%>
    <link rel="stylesheet" type="text/css" href="../CSS/general.css" media="screen" />
    <link href='https://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../fonts/style.css" media="screen"/>
    </head>
    <body>
        <ul class="sidenav">
            <li id="usuu"><a href="inicio_admin.jsp" <%if(pag==0){%>class="active"<%}%>>
                    <center><h3 style="padding-bottom: 5px; padding-top: 5px;">Administración</h3></center>
                </a>
            </li><br>
            <li id="kk">
                <a href="#" <%if(pag==1){%>class="active"<%}%>>
                    <span class = "icon-user"></span>&nbsp;&nbsp;Usuarios
                </a>
                <ul class="subsidenav">
                    <li><a href="agregarU.jsp">&nbsp;&nbsp;•&nbsp;&nbsp;Agregar Usuario</a></li>
                    <li><a href="modificarU.jsp">&nbsp;&nbsp;•&nbsp;&nbsp;Modificar Usuario</a></li>
                </ul>
            </li>
            <li id="kk">
                <a href="#" <%if(pag==2){%>class="active"<%}%>>
                    <span class="icon-list-numbered"></span>&nbsp;&nbsp;Partido
                </a>
                <ul class="subsidenav">
                    <li><a href="agregarP.jsp">&nbsp;&nbsp;•&nbsp;&nbsp;Agregar Partido</a></li>
                    <li><a href="modificarP.jsp">&nbsp;&nbsp;•&nbsp;&nbsp;Modificar Partido</a></li>
                    <li><a href="resultadosP.jsp">&nbsp;&nbsp;•&nbsp;&nbsp;Subir Resultados</a></li>
                </ul>
            </li>
            <li>
                <a href="modificarQ.jsp" <%if(pag==3){%>class="active"<%}%>>
                    <span class = "icon-clipboard"></span>&nbsp;&nbsp;Modificar Quiniela
                </a>
            </li>
            <li id="kk">
                <a href="#" <%if(pag==4){%>class="active"<%}%>>
                    <span class = "icon-tree"></span>&nbsp;&nbsp;Ligas
                </a>
                 <ul class="subsidenav">
                    <li><a href="mostrarL.jsp">&nbsp;&nbsp;•&nbsp;&nbsp;Ver Ligas</a></li>
                    <li><a href="enfrenL.jsp">&nbsp;&nbsp;•&nbsp;&nbsp;Enfrentamientos Playoff</a></li>
                </ul>
            </li>
            <li><a href="mostrarS.jsp" <%if(pag==5){%>class="active"<%}%>>
                    <span class = "icon-fire"></span>&nbsp;&nbsp;Consultar Survival
                </a>
            </li>
            <li><a href="expA.jsp" <%if(pag==5){%>class="active"<%}%>>
                    <span class = "icon-file-excel"></span>&nbsp;&nbsp;Exportar Archivos
                </a>
            </li>
            <li><a href="../cerrarSesion"><span class = "icon-undo2"></span>&nbsp;&nbsp;Salir</a></li>
        </ul>
    </body>
</html><%}%>
