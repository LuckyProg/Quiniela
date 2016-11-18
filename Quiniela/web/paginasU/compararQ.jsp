<%@page import="Clases.Usuario"%>
<%@page import="java.util.Vector"%>
<%HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else{
    String apodo = sesion.getAttribute("usuario").toString();
    int id_usuario = Integer.parseInt(sesion.getAttribute("id_usuario").toString());
    String s = request.getParameter("semana");
    if(s == null){
        s = "1";
    }
    int semana = Integer.parseInt(s);
    %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
        <link href='https://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../CSS/estilos2.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../CSS/llenarQ.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../fonts/style.css" media="screen"/>
        <title>Compara tu quiniela</title>
    </head>
    <body>
        <jsp:include page="navegador.jsp" flush="true">
            <jsp:param name="nu" value='<%=apodo%>'/>
        </jsp:include>   
        
            <div class="quinie">  
                <div class="semanal">
                    <ul>
                        <%for(int x = 1; x<18; x++){%>
                        <a href="llenarQ.jsp?semana=<%=x%>"><li <%if(x==semana){%>id="selecct"<%}%>><%=x%></li></a>
                        <%}%>
                    </ul>
                </div>
                        <div class="jugadores"><center><span class = "icon-user"></span> <%=sesion.getAttribute("usuario")%> &nbsp;&nbsp;&nbsp;&nbsp;
                                VS&nbsp;&nbsp;&nbsp;&nbsp;
                                <span class = "icon-user"></span> <select id="selet">
                                    <optgroup>
                                    <%Vector<Usuario> usuarios=new Usuario().mostrarUsuarios("1");
                                    for(Usuario usu:usuarios){%>
                                    <option value="<%=usu.getApodo()%>"><%=usu.getNombre()%></option>
                                    <%}%>
                                    </optgroup>
                                </select>
                        </center>
                        </div>
                <div class="partido3">
                    <table class="">
                        
                    </table>
                </div>
            </div>
    </body>
</html>
<%}%>
