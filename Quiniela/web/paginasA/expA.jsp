<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else if(sesion.getAttribute("rol").equals(1)){
    int semana = 0;
    if(request.getParameter("semana")!=null){
        semana = Integer.parseInt(request.getParameter("semana"));
    }
    String apodo = String.valueOf(sesion.getAttribute("usuario"));
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
         <jsp:include page="navegadorA.jsp" flush="true">
            <jsp:param name="nu" value='<%=apodo%>'/>
            <jsp:param name="pag" value='5'/>
        </jsp:include> 
        
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