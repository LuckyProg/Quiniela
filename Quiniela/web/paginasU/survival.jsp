<%@page import="Clases.Usuario"%>
<%@page import="Clases.Survival"%>
<%@page import="Clases.Equipo"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else{
    String apodo = sesion.getAttribute("usuario").toString();
    String orden = request.getParameter("orden");
    if(orden==null){
        
        orden="5";

    }
    Survival sur = new Survival();
    int semana = 0;
   for(int i=1;i<=17;i++){
       if(sur.noseleccionado(apodo, i)){
           semana = i;
           break;
       }
   }
    %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
		<link rel="shortcut icon" type="image/x-icon" href="IMG/IC.ico">
        <title>SURVIVAL</title>
        <link href='https://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../CSS/estilos2.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../CSS/survival.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../fonts/style.css" media="screen"/>
    </head>
    <body id="bgsurv">
        <jsp:include page="navegador.jsp" flush="true">
            <jsp:param name="nu" value='<%=apodo%>'/>
        </jsp:include>   
        <br><br>
		<br>
		<div class="divid">
                    <%Survival wuera = new Survival();
                        if(wuera.participa(apodo) == false){%>
                            <center><h2 id="h11"><font color="white">Has perdido &nbsp;</h2> </font><br>
                    <%}else{%>
                                <center><h2 id="h11"><font color="white">Elige tu equipo: &nbsp;</h2> </font><br><br>
                        <table align="center" border="1">
                            <tr>
                                <td><center><font color="black">Semana: &nbsp; </font></td>
                                <td>
                                       <%=semana%>
                                </td>
                            </tr>
                            <tr>
                                <form name="f1" method="post" action="../modificarSurvival">
                                <td><%
                                    int uno = semana-1;
                                    if(wuera.noseleccionado(apodo, uno)==true && semana!=1){}
                                    else{
                                    %>
                                    <%if(wuera.noseleccionado(apodo, semana)==true){%>
                                    
                                        <select name="id_equipo">
                                            <%  
                                                boolean sel = false, aux = false;
                                                Vector<Equipo> ecuip=new Equipo().mostrarEquipos();                  
                                                for(Equipo e:ecuip){                  
                                            %>
                                                <%
                                                    Vector<Survival> selected=new Survival().verSurvival(apodo);                  
                                                    for(Survival s:selected){
                                                        if(e.getId_equipo()==s.getId_equipo()){
                                                            if(s.getSemana() == semana){
                                                                aux = true;
                                                            }else{
                                                                sel=true;
                                                            }
                                                        }
                                                    }
                                                    if(sel==false){
                                                %>
                                                <option value='<%=e.getId_equipo()%>' <%if(aux == true){%>selected<%}%>><%=e.getNombre()%></option>
                                            <%
                                                }
                                                sel = false;
                                                aux = false;
                                             }
                                            %>
                                        </select>
                                </td>
                                <td>
                                    <input type="hidden" name="apodo" value='<%=sesion.getAttribute("usuario")%>'>
                                    <input type="hidden" name="semana" value=<%=semana%>>
                                    <input align="center" id="bb" type="submit" value="Guardar">
                                    <%}}%>
                                </td>
                                </form>
                            </tr>
                        </table></center>
		<%}%></div>
		<div class="cajatb"><br><br>
			<center><img src="../IMG/surv.JPG">
                            <br><br><h1 id="h11"><font color="white">Usuario en sesion: <%=sesion.getAttribute("usuario")%>&nbsp; </font></h1>
                            <br><br>
                            <form method="Post" action="survival.jsp">
                                <select id="bb" name="orden">
                                    <option value="5">-------</option>
                                    <option value="1">nombre</option>
                                    <option value="5">ganadores</option>
                                </select>
                                <input type="submit" value="Ordenar">
                            </form> </center>
                            <br><br>
                            <center><table align=center name="ths" id="ths" border="0">
				<tr id="titulos">
					<td>JUGADOR</td><td>S 1</td><td>S 2</td><td>S 3</td><td>S 4</td><td>S 5</td><td>S 6</td><td>S 7</td><td>S 8</td><td>S 9</td><td>S 10</td><td>S 11</td><td>S 12</td>
					<td>S 13</td><td>S 14</td><td>S 15</td><td>S 16</td><td>S 17</td>
				</tr>
                                <%
                                Vector<Usuario> usuarios=new Usuario().mostrarUsuarios(orden);
                                for(Usuario usu:usuarios){
                                if(usu.getRol()==1){}
                                else{
                                
                                %>
				<tr>
					<td id="j1">
						<%=usu.getNombre()%>
					</td>
                                        <%Vector<Survival> survi=new Survival().verSurvival(usu.getApodo());                  
                                        for(Survival s:survi){
                                            if(wuera.noseleccionado(usu.getApodo(), s.getSemana())==false){
                                        %>
					<td <%if(s.isGana()==true){%>id="gana"<%}%><%else{%>id="pierde"<%}%>>				
						<%=s.getNombre_equipo()%>
					</td>
                                        <%}}%>
				</tr>
                                <%}}%>
                        </table></center>
                        <br><br><br><br>
		</div>
	</body>
</html><%}%>
