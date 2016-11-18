

<%@page import="java.util.Vector"%>
<%@page import="Clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session='true'%>
<%
  HttpSession sesion = request.getSession();
  if(sesion!=null){
      sesion.invalidate();
  }
  String niu = request.getParameter("niu");
  String m = request.getParameter("m");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
        <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
		<link rel="shortcut icon" type="image/x-icon" href="IMG/IC.ico">
        <title>QUINIELA</title>
        <link href='https://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="CSS/estilos.css" media="screen"/>
        <link rel="stylesheet" type="text/css" href="fonts/style.css" media="screen"/>
        <script type="text/javascript" src="SA/sweetalert-dev.js"></script>
        <link rel="stylesheet" type="text/css" href="SA/sweetalert.css" media="screen"/>
    </head>
    <body>
        <center>
        <div class="mine">
            <form name="form1" method="POST" action="iniciarSesion">
                <h2><img src="IMG/logo.png" id="logo">&nbsp;&nbsp;INFERENCIA</h2><br>
                <%if(niu==null){%>
                <span class = "icon-user"></span>&nbsp;&nbsp;<input type="text" name="user" placeholder="Alias" autofocus><br><br>
                <%}%>
                <%if(niu!=null){%>
                <span class = "icon-user"></span>&nbsp;&nbsp;<select name="user">
                    <%Vector<Usuario> usuarios=new Usuario().mostrarUsuarios("1");
                    for(Usuario usu:usuarios){%>
                    <option value="<%=usu.getApodo()%>"><%=usu.getNombre()%></option>
                    <%}%>
                </select><br><br>
                <%}%>
                <span class = "icon-key"></span>&nbsp;&nbsp;<input id="password" type="password" name="pass" placeholder="Contraseña"><br>
                <a href="index.jsp?niu=1">¿Eres nuevo?</a><br><br>
                <center><input id="login" type="submit" value="Login"></center>
                
            </form>
        </div>
        </center>
    </body>
    <%if(m!=null){%>
        <script>
            sweetAlert("Oops...", "Usuario no existente!", "error");
        </script>
    <%}%>
</html>

