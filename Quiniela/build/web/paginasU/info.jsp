<%-- 
    Document   : info
    Created on : 14/05/2016, 04:30:06 AM
    Author     : ADMIN
--%>

<%@page import="java.util.Vector"%>
<%@page import="Clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else{
String apodo = String.valueOf(sesion.getAttribute("usuario"));
%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
		<link rel="shortcut icon" type="image/x-icon" href="IMG/IC.ico">
        <title>PERFIL</title>
        <link href='https://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../CSS/estilos2.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../fonts/style.css" media="screen"/>
        <script type="text/javascript" src="JS/val.js"></script>
    </head>
    <body id="brody" onpaste="return false;">
        <jsp:include page="navegador.jsp" flush="true">
            <jsp:param name="nu" value='<%=apodo%>'/>
            <jsp:param name="pag" value='0'/>
        </jsp:include>   
        <br><br>
		<br>
		<div class="divv">
			<div class="divvv">
                            <%
                                Vector<Usuario> usuarios=new Usuario().mostrarUsuarios("1");
                                for(Usuario usu:usuarios){
                                    if(sesion.getAttribute("usuario").equals(usu.getApodo())){                                
                                %>
                                <center>
                                <form name="f1" method="post" action="../modificarUsuario">
                                    <h1>PERFIL</h1><br>
					<table align="center" class="tb_info" border="0">
						<tr class="tr_info">
							<td width="300">
								Nombre de Usuario:
							</td>
							<td width="300">
								<input class="cajas1" type="text" name="apodo" value="<%=usu.getApodo()%>" onkeypress="return soloLetras(event)" required>
							</td>
						</tr>
						<tr class="tr_info">
							<td>
								Contraseña:
							</td>
							<td>
								<input class="cajas1" type="text" name="pass" value="<%=usu.getPass()%>" onkeypress="return soloLetras(event)" required>
							</td>
						</tr>
                                                <tr class="tr_info">
							<td>
								Nombre:
							</td>
							<td>
								<input class="cajas1" type="text" name="nombre" value="<%=usu.getNombre()%>" onkeypress="return soloLetras(event)" required>
							</td>
						</tr>
						<tr class="tr_info">
							<td>
								E-mail:
							</td>
							<td>
								<input class="cajas1" type="text" name="correo" value="<%=usu.getCorreo()%>" onkeypress="return soloLetras(event)" required>
							</td>
						</tr>
                                                <tr class="tr_info">
							<td>
								Monto pagado:
							</td>
                                                        <td>
								$ <%=usu.getMonto()%> (MXN)
							</td>
						</tr>
                                                <tr>
                                                    <td> </td>
                                                    <td>
                                                        <input type="hidden" name="monto" value="<%=usu.getMonto()%>">
                                                        <input type="hidden" name="id_usuario" value="<%=usu.getId()%>">
                                                        <input type="hidden" name="rol" value="<%=usu.getRol()%>">
                                                        <input type="submit" id="login" value="Guardar">
                                                    </td>
                                                </tr>
					</table></form></center><%}}%>
			</div>
		</div>
	</body>
        <%if(request.getParameter("d")!=null){%>
        <script type="text/javascript">
            alert("Datos actualizados correctamente");
        </script>
        <%}%>
</html><%}%>
