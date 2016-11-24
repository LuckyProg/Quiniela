<%@page import="Clases.Usuario"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%
    HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else if(sesion.getAttribute("rol").equals(1)){
    
    String orden = request.getParameter("orden");
    if(orden==null){
        
        orden="0";

    }
    
    String apodo = String.valueOf(sesion.getAttribute("usuario"));
%>
<html>
    <head>
        <script type="text/javascript" src="../SA/sweetalert-dev.js"></script>
        <link rel="stylesheet" type="text/css" href="../SA/sweetalert.css" media="screen"/>   
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
        <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="../CSS/general.css" media="screen" />
        <title>MODIFICAR USUARIO</title>
        <script type="text/javascript" src="../JS/val.js"></script>

    </head>
    <body onpaste="return false;">
        <%if(request.getParameter("cambio")!=null){%>
   <script>swal("Cambio Exitoso!", "Datos del usuario han sido modificados!", "success")</script>
    <%}%>
         <jsp:include page="navegadorA.jsp" flush="true">
            <jsp:param name="nu" value='<%=apodo%>'/>
            <jsp:param name="pag" value='1'/>
        </jsp:include> 
        <div id="in">
            <center>
                
                <h1>MODIFICAR</h1><br>
                <form method="Post" action="modificarU.jsp">
                    Orden: <select name="orden" onchange="this.form.submit()">
                        <option value="0">-------</option>
                        <option value="0">creación</option>
                        <option value="1">nombre</option>
                        <option value="2">apodo</option>
                        <option value="3">monto</option>
                        <option value="4">rol</option>
                    </select>
                    
                </form> 
                <br>
                <%
                Vector<Usuario> usuarios=new Usuario().mostrarUsuarios(orden);
                %>
                <table >
                    <tr>
                        <td>NOMBRE</td>
                        <td>APODO</td>
                        <td>CONTRASEÑA</td>
                        <td>CORREO</td>
                        <td>MONTO</td>
                        <td>ROL</td>
                        <td></td>
                    </tr>
                    <%
                    for(Usuario usu:usuarios){
                    %>
                            <tr>
                                <form method="Post" action="../modificarUsuario"> 
                                <td><input type="text" name="nombre" onkeypress="return soloLetras(event)" value='<%=usu.getNombre()%>'></td>
                                <td><input type="text" name="apodo" onkeypress="return soloLetras(event)" value='<%=usu.getApodo()%>'></td>
                                <td><input type="password" name="pass" onkeypress="return soloLetras(event)" value='<%=usu.getPass()%>'></td>
                                <td><input type="text" name="correo" onkeypress="return soloLetras(event)" value='<%=usu.getCorreo()%>'></td>
                                <td><input type="text" name="monto" onkeypress="return soloNum(event)" value='<%=usu.getMonto()%>'></td>
                                <td><input type="text" name="rol" onkeypress="return soloNum(event)" value='<%=usu.getRol()%>'></td>                                 
                                <td><input type="hidden" name = "id_usuario" value='<%=usu.getId()%>'></td>
                                <td><input type="submit" value="Guardar"></td>
                                </form>
                                <form method="post" action="../borrarUsuario">                                   
                                <td><input type="hidden" name = "id_usuario" value='<%=usu.getId()%>'></td>
                                <td><input type="submit" value="Eliminar"></td>
                                </form>
                            </tr>
                     <%}%>
                </table>
        
            </center>
        </div>
    </body>
</html><%}%>