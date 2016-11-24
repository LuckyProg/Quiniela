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
    </head>
    <body>
        <ul class="sidenav">
            <li id="usuu"><a href="info.jsp" <%if(pag==0){%>class="active"<%}%>>
                    <span class = "icon-user"></span>&nbsp;&nbsp;<%=request.getParameter("nu")%>
                </a>
            </li>
            <li><a href="inicio.jsp" <%if(pag==1){%>class="active"<%}%>>
                    <span class = "icon-home3"></span>&nbsp;&nbsp;Inicio
                </a>
            </li>
            <li id="kk">
                <a href="#" <%if(pag==2){%>class="active"<%}%>>
                    <span class = "icon-clipboard"></span>&nbsp;&nbsp;Quiniela
                </a>
                <ul class="subsidenav">
                    <li><a href="llenarQ.jsp">&nbsp;&nbsp;•&nbsp;&nbsp;Llena tu quiniela</a></li>
                    <li><a href="compararQ.jsp">&nbsp;&nbsp;•&nbsp;&nbsp;Compara tu quiniela</a></li>
                </ul>
            </li>
            <li><a href="survival.jsp" <%if(pag==3){%>class="active"<%}%>>
                    <span class = "icon-fire"></span>&nbsp;&nbsp;Survival
                </a>
            </li>
            <li id="kk">
                <a href="#" <%if(pag==4){%>class="active"<%}%>>
                    <span class = "icon-tree"></span>&nbsp;&nbsp;Liga
                </a>
                <ul class="subsidenav">
                    <li><a href="posiciones.jsp">&nbsp;&nbsp;•&nbsp;&nbsp;Posiciones</a></li>
                    <li><a href="enfrentamientos.jsp">&nbsp;&nbsp;•&nbsp;&nbsp;Enfrentamientos</a></li>
                </ul>
            </li>
            <li id="kk">
                <a href="#" <%if(pag==5){%>class="active"<%}%>>
                    <span class = "icon-stats-dots"></span>&nbsp;&nbsp;Resultados
                </a>
                 <ul class="subsidenav">
                    <li><a href="resultados.jsp">&nbsp;&nbsp;•&nbsp;&nbsp;Semanales</a></li>
                    <li><a href="resultados_gen.jsp">&nbsp;&nbsp;•&nbsp;&nbsp;Generales</a></li>
                </ul>
            </li>
            <li><a href="exportarA.jsp" <%if(pag==6){%>class="active"<%}%>>
                    <span class = "icon-file-excel"></span>&nbsp;&nbsp;Reportes
                </a>
            </li>
            <li><a href="../cerrarSesion"><span class = "icon-undo2"></span>&nbsp;&nbsp;Salir</a></li>
        </ul>
    </body>
</html><%}%>
