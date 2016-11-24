<%@page import="Clases.Puntaje"%>
<%@page import="Clases.Enfrentamiento"%>
<%@page import="java.util.Vector"%>
<%@page import="Clases.Liga"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else{
    String apodo = sesion.getAttribute("usuario").toString();
    int semana  = (request.getParameter("semana") != null) ? Integer.parseInt(request.getParameter("semana")):1;
    int liga  = (request.getParameter("liga") != null) ? Integer.parseInt(request.getParameter("liga")):1;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
		<link rel="shortcut icon" type="image/x-icon" href="IMG/IC.ico">
        <title>CALENDARIO</title>
        <link href='https://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
        <link href="../CSS/enfrentamientos.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="../CSS/estilos2.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../fonts/style.css" media="screen"/>
               
    </head>
    <body>
        <jsp:include page="navegador.jsp" flush="true">
            <jsp:param name="nu" value='<%=apodo%>'/>
            <jsp:param name="pag" value='4'/>
        </jsp:include>        
            
            <div class="semanal">
                <ul>
                    <%for(int x = 1; x<15; x++){%>
                    <a href="?semana=<%=x%>&liga=<%=liga%>"><li <%if(x==semana){%>id="selecct"<%}%>>    <%=(x>13)? "14-17":x%>   </li></a>
                    <%}%>
                </ul>
            </div>
            <div id="seleccionar_liga" style="">
                <ul>
                    <%
                    int numL = new Liga().numL();
                    for(int i = 1; i <= numL; i++){
                    %>
                    
                    <a href="?semana=<%=semana%>&liga=<%=i%>"><li style="width: <%=80/numL%>%; <%if(i==liga){%>background:rgba(195, 52, 52, 1);<%}%>">LIGA <%=i%></li></a>
                    <%}%>
  
                </ul>
            </div>
            <%if(semana==14){%>
            
            <div class="eliminatorias">
                
               <%
                   Vector<Enfrentamiento> sem14 = new Enfrentamiento().mostrarEnfrentamientos(14, liga);
                   boolean catorce = sem14.size()==8 ? true:false;
                   System.out.println(sem14.size());
                   Vector<Enfrentamiento> sem15 = new Enfrentamiento().mostrarEnfrentamientos(15, liga);
                   boolean quince = sem15.size()==4 ? true:false;
                   Vector<Enfrentamiento> sem16 = new Enfrentamiento().mostrarEnfrentamientos(16, liga);
                   boolean diesciseis = sem16.size()==2 ? true:false;
                   Vector<Enfrentamiento> sem17 = new Enfrentamiento().mostrarEnfrentamientos(17, liga);
                   boolean diescisiete = sem17.size()==1 ? true:false;
                   
                   
               %>
                    <table>
                        <tr>
                            <td style="font-size: 40px;">AMERICANA</td>
                        </tr>
                        <tr>
                            <%if(catorce){%><td class="jugador"><%=sem14.get(0).getVisitante()%></td><td><div class="punt">P:<%=sem14.get(0).getVp()%><br>DL:<%=sem14.get(0).getVdl()%><br>DS:<%=sem14.get(0).getVds()%></div></td><%}else{%><td class="jugador"></td><%}%>
                        </tr>
                        
                        <tr>
                            <td></td>   <td class="arriba"></td>   <%if(quince){%><td class="jugador"><%=sem15.get(0).getVisitante()%></td><td><div class="punt">P:<%=sem15.get(0).getVp()%><br>DL:<%=sem15.get(0).getVdl()%><br>DS:<%=sem15.get(0).getVds()%></div></td><%}else{%><td class="jugador"></td><%}%>
                        </tr>
                        
                        <tr>
                            <td></td>   <td class="izquierda"></td>   <%if(quince){%><td class="jugador"><%=sem15.get(0).getLocal()%></td><td><div class="punt">P:<%=sem15.get(0).getLp()%><br>DL:<%=sem15.get(0).getLdl()%><br>DS:<%=sem15.get(0).getLds()%></div></td><%}else{%><td class="jugador"></td><%}%>
                        </tr>
                        
                        <tr>
                            <%if(catorce){%><td class="jugador"><%=sem14.get(1).getVisitante()%></td><td><div class="punt">P:<%=sem14.get(1).getVp()%><br>DL:<%=sem14.get(1).getVdl()%><br>DS:<%=sem14.get(1).getVds()%></div></td><%}else{%><td class="jugador"></td><td></td><%}%>  <td></td>   <td class="izquierda"></td>
                        </tr>
                        
                        <tr>
                            <%if(catorce){%><td class="jugador"><%=sem14.get(1).getLocal()%></td><td><div class="punt">P:<%=sem14.get(1).getLp()%><br>DL:<%=sem14.get(1).getLdl()%><br>DS:<%=sem14.get(1).getLds()%></div></td><%}else{%><td class="jugador"></td><td></td><%}%>  <td></td>   <td class="izquierda"></td>
                        </tr>
                        
                        <tr>
                            <td></td>   <td></td>   <td></td>   <td class="arriba"></td>   <%if(diesciseis){%><td class="jugador"><%=sem16.get(0).getVisitante()%></td><td><div class="punt">P:<%=sem16.get(0).getVp()%><br>DL:<%=sem16.get(0).getVdl()%><br>DS:<%=sem16.get(0).getVds()%></div></td><%}else{%><td class="jugador"></td><%}%>
                        </tr>
                        
                        <tr>
                            <td></td>   <td></td>   <td></td>   <td class="izquierda"></td>   <%if(diesciseis){%><td class="jugador"><%=sem16.get(0).getLocal()%></td><td><div class="punt">P:<%=sem16.get(0).getLp()%><br>DL:<%=sem16.get(0).getLdl()%><br>DS:<%=sem16.get(0).getLds()%></div></td><%}else{%><td class="jugador"></td><%}%>
                        </tr>
                        
                        <tr>
                            <%if(catorce){%><td class="jugador"><%=sem14.get(2).getVisitante()%></td>  <td><div class="punt">P:<%=sem14.get(2).getVp()%><br>DL:<%=sem14.get(2).getVdl()%><br>DS:<%=sem14.get(2).getVds()%></div></td>  <td></td>   <td class="izquierda"></td>   <td></td>   <td class="izquierda"></td><%}else{%><td class="jugador"></td>  <td></td>  <td></td>   <td class="izquierda"></td>   <td></td>   <td class="izquierda"></td><%}%>
                        </tr>
                        
                        <tr>
                            <%if(quince){%><td></td>   <td class="arriba"></td>   <td class="jugador"><%=sem15.get(1).getVisitante()%></td>  <td><div class="punt">P:<%=sem15.get(1).getVp()%><br>DL:<%=sem15.get(1).getVdl()%><br>DS:<%=sem15.get(1).getVds()%></div></td> <td></td>   <td class="izquierda"></td>  <%}else{%><td></td>   <td class="arriba"></td>   <td class="jugador"></td>  <td></td> <td></td>   <td class="izquierda"></td>  <%}%>
                        </tr>
                        
                        <tr>
                            <%if(quince){%><td></td>   <td class="izquierda"></td>   <td class="jugador"><%=sem15.get(1).getLocal()%></td>  <td><div class="punt">P:<%=sem15.get(1).getLp()%><br>DL:<%=sem15.get(1).getLdl()%><br>DS:<%=sem15.get(1).getLds()%></div></td> <td></td>   <td class="izquierda"></td>  <%}else{%><td></td>   <td class="izquierda"></td>   <td class="jugador"></td>  <td></td> <td></td>   <td class="izquierda"></td>  <%}%>
                        </tr>
                        
                        <tr>
                            <%if(catorce){%><td class="jugador"><%=sem14.get(3).getVisitante()%></td>   <td><div class="punt">P:<%=sem14.get(3).getVp()%><br>DL:<%=sem14.get(3).getVdl()%><br>DS:<%=sem14.get(3).getVds()%></div></td>   <td></td>   <td></td>   <td></td>   <td class="izquierda"></td><%}else{%><td class="jugador"></td>   <td></td>   <td></td>   <td></td>   <td></td>   <td class="izquierda"></td><%}%>
                        </tr>
                        
                        <tr>
                            <%if(catorce){%><td class="jugador"><%=sem14.get(3).getLocal()%></td>   <td><div class="punt">P:<%=sem14.get(3).getLp()%><br>DL:<%=sem14.get(3).getLdl()%><br>DS:<%=sem14.get(3).getLds()%></div></td>   <td></td>   <td></td>   <td></td>   <td class="izquierda"></td><%}else{%><td class="jugador"></td>   <td></td>   <td></td>   <td></td>   <td></td>   <td class="izquierda"></td><%}%>
                        </tr>
                        
                        <tr>
                            <%if(diescisiete){%><td></td>   <td></td>   <td></td>   <td></td>   <td></td>   <td class="arriba"></td>   <td class="jugador"><%=sem17.get(0).getVisitante()%></td>  <td><div class="punt">P:<%=sem17.get(0).getVp()%><br>DL:<%=sem17.get(0).getVdl()%><br>DS:<%=sem17.get(0).getVds()%></div></td><%}else{%><td></td>   <td></td>   <td></td>   <td></td>   <td></td>   <td class="arriba"></td>   <td class="jugador"></td> <td></td><%}%>
                        </tr>
                        
                        <tr>
                             <td style="font-size:40px;">NACIONAL</td>  <%if(diescisiete){%>  <td></td>   <td></td>   <td></td>   <td></td>   <td class="izquierda"></td>   <td class="jugador"><%=sem17.get(0).getLocal()%></td><td><div class="punt">P:<%=sem17.get(0).getLp()%><br>DL:<%=sem17.get(0).getLdl()%><br>DS:<%=sem17.get(0).getLds()%></div></td><%}else{%><td></td>   <td></td>   <td></td>   <td></td>   <td class="izquierda"></td> <td class="jugador"></td><%}%>
                        </tr>
                        
                        
                        <%/*Conferencia 2*/%>
                        
                        
                       <tr>
                           <%if(catorce){%><td class="jugador"><%=sem14.get(4).getVisitante()%></td><td><div class="punt">P:<%=sem14.get(4).getVp()%><br>DL:<%=sem14.get(4).getVdl()%><br>DS:<%=sem14.get(4).getVds()%></div></td><td></td><td></td><td></td><td class="izquierda"></td><%}else{%><td class="jugador"></td><td></td><td></td><td></td><td></td><td class="izquierda"></td>   <%}%>
                        </tr>
                        
                        <tr>
                            <td></td>   <td class="arriba"></td>   <%if(quince){%><td class="jugador"><%=sem15.get(2).getVisitante()%></td><td><div class="punt">P:<%=sem15.get(2).getVp()%><br>DL:<%=sem15.get(2).getVdl()%><br>DS:<%=sem15.get(2).getVds()%></div></td><td></td><td class="izquierda"></td><%}else{%><td class="jugador"></td><td></td><td></td><td class="izquierda"</td><%}%>
                        </tr>
                        
                        <tr>
                            <td></td>   <td class="izquierda"></td>   <%if(quince){%><td class="jugador"><%=sem15.get(2).getLocal()%></td><td><div class="punt">P:<%=sem15.get(2).getLp()%><br>DL:<%=sem15.get(2).getLdl()%><br>DS:<%=sem15.get(2).getLds()%></div></td><td></td><td class="izquierda"></td><%}else{%><td class="jugador"></td><td></td><td></td><td class="izquierda"></td><%}%>
                        </tr>
                        
                        <tr>
                            <%if(catorce){%><td class="jugador"><%=sem14.get(5).getVisitante()%></td><td><div class="punt">P:<%=sem14.get(5).getVp()%><br>DL:<%=sem14.get(5).getVdl()%><br>DS:<%=sem14.get(5).getVds()%></div></td><td></td><td class="izquierda"></td><td></td><td class="izquierda"></td><%}else{%><td class="jugador"></td><td></td><td></td><td class="izquierda"></td><td></td><td class="izquierda"></td><%}%>  <td></td> 
                        </tr>
                        
                        <tr>
                            <%if(catorce){%><td class="jugador"><%=sem14.get(5).getLocal()%></td><td><div class="punt">P:<%=sem14.get(5).getLp()%><br>DL:<%=sem14.get(5).getLdl()%><br>DS:<%=sem14.get(5).getLds()%></div></td><td></td><td class="izquierda"></td><td></td><td class="izquierda"></td><%}else{%><td class="jugador"></td><td></td><td></td><td class="izquierda"></td><td class="izquierda>"></td><td class="izquierda"></td><%}%>   
                        </tr>
                        
                        <tr>
                            <td></td>   <td></td>   <td></td>   <td class="arriba"></td>   <%if(diesciseis){%><td class="jugador"><%=sem16.get(0).getVisitante()%></td><td><div class="punt">P:<%=sem16.get(0).getVp()%><br>DL:<%=sem16.get(0).getVdl()%><br>DS:<%=sem16.get(0).getVds()%></div></td><%}else{%><td class="jugador"></td><%}%>
                        </tr>
                        
                        <tr>
                            <td></td>   <td></td>   <td></td>   <td class="izquierda"></td>   <%if(diesciseis){%><td class="jugador"><%=sem16.get(0).getLocal()%></td><td><div class="punt">P:<%=sem16.get(0).getLp()%><br>DL:<%=sem16.get(0).getLdl()%><br>DS:<%=sem16.get(0).getLds()%></div></td><%}else{%><td class="jugador"></td><%}%>
                        </tr>
                        
                        <tr>
                            <%if(catorce){%><td class="jugador"><%=sem14.get(2).getVisitante()%></td>  <td><div class="punt">P:<%=sem14.get(2).getVp()%><br>DL:<%=sem14.get(2).getVdl()%><br>DS:<%=sem14.get(2).getVds()%></div></td>  <td></td>   <td class="izquierda"></td>  <%}else{%><td class="jugador"></td>  <td></td>  <td></td>   <td class="izquierda"></td>   <td></td>   <%}%>
                        </tr>
                        
                        <tr>
                            <%if(quince){%><td></td>   <td class="arriba"></td>   <td class="jugador"><%=sem15.get(1).getVisitante()%></td>  <td><div class="punt">P:<%=sem15.get(1).getVp()%><br>DL:<%=sem15.get(1).getVdl()%><br>DS:<%=sem15.get(1).getVds()%></div></td>   <%}else{%><td></td>   <td class="arriba"></td>   <td class="jugador"></td>  <td></td> <td></td>   <%}%>
                        </tr>
                        
                        <tr>
                            <%if(quince){%><td></td>   <td class="izquierda"></td>   <td class="jugador"><%=sem15.get(1).getLocal()%></td>  <td><div class="punt">P:<%=sem15.get(1).getLp()%><br>DL:<%=sem15.get(1).getLdl()%><br>DS:<%=sem15.get(1).getLds()%></div></td> <%}else{%><td></td>   <td class="izquierda"></td>   <td class="jugador"></td>  <td></td> <td></td>    <%}%>
                        </tr>
                        
                        <tr>
                            <%if(catorce){%><td class="jugador"><%=sem14.get(3).getVisitante()%></td>   <td><div class="punt">P:<%=sem14.get(3).getVp()%><br>DL:<%=sem14.get(3).getVdl()%><br>DS:<%=sem14.get(3).getVds()%></div></td><%}else{%><td class="jugador"></td>   <td></td>   <td></td>   <td></td>   <td></td>   <%}%>
                        </tr>
                        
                        <tr>
                            <%if(catorce){%><td class="jugador"><%=sem14.get(3).getLocal()%></td>   <td><div class="punt">P:<%=sem14.get(3).getLp()%><br>DL:<%=sem14.get(3).getLdl()%><br>DS:<%=sem14.get(3).getLds()%></div></td>   <%}else{%><td class="jugador"></td>   <td></td>   <td></td>   <td></td>   <td></td>   <%}%>
                        </tr>
                        
                        
                        
                    </table>
               
                
            </div>
            
            <%}else{%>
            <div class="enfren">
                <%
                    Vector<Enfrentamiento> enfrents = new Enfrentamiento().mostrarEnfrentamientos(semana, liga);
                    for(Enfrentamiento E:enfrents){
                    boolean mio = false;
                    if(E.getVisitante().equalsIgnoreCase(sesion.getAttribute("nombre").toString()) || E.getLocal().equalsIgnoreCase(sesion.getAttribute("nombre").toString())){
                        mio = true;
                    }
                    else{
                        mio = false;
                    }
                %>
                <div class="enfrentamiento" <%if(mio){%>style = "background:darkorange;"<%}%>>
                    
                    <div class="V">
                        
                        <div class="nombre">
                            <%=E.getVisitante()%><br>
                            <%if(E.getGanador() == E.getVid()){%><span class="icon-checkmark"></span><%}%>
                            <%if(E.getGanador() == E.getLid()){%><span class="icon-cross"></span><%}%>
                        </div>
                        
                        
                        
                    </div>
                    
                    <div class="marcador">
                        <div class="mv">
                            <i>P&nbsp;</i> <b><%=E.getVid()!=1000 ? E.getVp() : new Puntaje().promedio(semana)%></b> -<br>
                            <i>DL&nbsp;</i> <b><%=E.getVid()!=1000 ? E.getVdl() : new Puntaje().peordl(semana)%></b> -<br>
                            <i>DS&nbsp;</i> <b><%=E.getVid()!=1000 ? E.getVds() : new Puntaje().peords(semana)%></b> -
                        </div>
                        <div class="ml">
                            -&nbsp;<b><%=E.getLid()!=1000 ? E.getLp() : new Puntaje().promedio(semana)%></b> <i>P</i><br>
                            -&nbsp;<b><%=E.getLid()!=1000 ? E.getLdl() : new Puntaje().peordl(semana)%></b> <i>DL</i><br>
                            -&nbsp;<b><%=E.getLid()!=1000 ? E.getLds() : new Puntaje().peords(semana)%></b> <i>DS</i>
                        </div>
                    </div>
                    
                    <div class="L">
                        
                        <div class="nombre">     
                            <%=E.getLocal()%><br>
                            <%if(E.getGanador() == E.getLid()){%><span class="icon-checkmark"></span><%}%>
                            <%if(E.getGanador() == E.getVid()){%><span class="icon-cross"></span><%}%>
                        </div>
                        
                    </div>
                    </div>
                    <%}%>
            
            </div>
                    <%}%>
    
            </body>
</html>
<%}%>