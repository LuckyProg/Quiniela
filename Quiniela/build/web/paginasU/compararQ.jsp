<%@page import="Clases.Quinela"%>
<%@page import="Clases.Usuario"%>
<%@page import="java.util.Vector"%>
<%HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else{
    String apodo = sesion.getAttribute("usuario").toString();
    int id_usuario = Integer.parseInt(sesion.getAttribute("id_usuario").toString());
    int id2 = request.getParameter("id2")!=null ? Integer.parseInt(request.getParameter("id2")) : 0;
    String contrincante = id2!=0 ? new Usuario().mostrarNombre(id2): " ";
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
        <link rel="stylesheet" type="text/css" href="../fonts/style.css" media="screen"/>
        <link href="../CSS/compararQui.css" rel="stylesheet" type="text/css"/>
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
                        <a href="compararQ.jsp?semana=<%=x%>&id2=<%=id2%>"><li <%if(x==semana){%>id="selecct"<%}%>><%=x%></li></a>
                        <%}%>
                    </ul>
                </div>
                        <div class="jugadores">
                            <center>
                                <form method = "post" action = "compararQ.jsp">
                                <span class = "icon-user"></span> <%=sesion.getAttribute("usuario")%> &nbsp;&nbsp;&nbsp;&nbsp;
                                VS&nbsp;&nbsp;&nbsp;&nbsp;
                                <span class = "icon-user"></span> 
                                <select id="selet" name="id2">
                                    <optgroup>
                                    <%Vector<Usuario> usuarios=new Usuario().mostrarUsuarios("1");
                                    for(Usuario usu:usuarios){%>
                                    <option value="<%=usu.getId()%>"><%=usu.getNombre()%></option>
                                    <%}%>
                                    </optgroup>
                                </select>
                                    &nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="Comparar">
                                </form>
                        </center>
                        </div>
                <div class="partido3">
                    <%
                    if(id2!=0){
                    Vector <Quinela> q =new Quinela().mostrarPronosticos(id_usuario,semana);
                    Vector <Quinela> q2 =new Quinela().mostrarPronosticos(id2,semana);
                    int pos = 0;
                    for(Quinela qui:q){%>
                    <center><h2><%=qui.getVisitante()+ " " + " vs " + " " + qui.getLocal()%></h2></center>
                    <div class="enfrentamiento">
                    
                    <div class="V">
                        
                        <div class="nombre">
                            <%=sesion.getAttribute("usuario")%>
                        </div>
                    </div>
                    
                    <div class="marcador">
                        <div class="mv">
                            <i>&nbsp;</i> <b><%=qui.getGanador() == qui.getId_local() ? qui.getLocal().substring(0, 3): " "%>
                                <%=qui.getGanador() == qui.getId_visitante() ? qui.getVisitante().substring(0, 3): " "%></b> -<br>
                            <i>M&nbsp;</i> <b><%=qui.getMayor()%></b> -<br>
                            <i>m&nbsp;</i> <b><%=qui.getMenor()%></b> -
                        </div>
                        <div class="ml">
                            -&nbsp;<b><%=q2.get(pos).getGanador() == q2.get(pos).getId_local() ? q2.get(pos).getLocal().substring(0, 3): " "%>
                                <%=q2.get(pos).getGanador() == q2.get(pos).getId_visitante() ? q2.get(pos).getVisitante().substring(0, 3): " "%></b> <i></i><br>
                            -&nbsp;<b><%=q2.get(pos).getMayor()%></b> <i>M</i><br>
                            -&nbsp;<b><%=q2.get(pos).getMenor()%></b> <i>m</i>
                        </div>
                    </div>
                    
                    <div class="L">
                        
                        <div class="nombre">     
                            <%=contrincante%>
                        </div>
                        
                    </div>
                    </div>
                    <%pos++; }}%>
                </div>
            </div>
    </body>
</html>
<%}%>
