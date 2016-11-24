<%@page import="Clases.Liga"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else{
    String apodo = sesion.getAttribute("usuario").toString();
    int liga;
    if(request.getParameter("liga")!=null){
        liga = Integer.parseInt(request.getParameter("liga"));
    }else {liga=1;}
%>
    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
		<link rel="shortcut icon" type="image/x-icon" href="IMG/IC.ico">
        <title>POSICIONES</title>
        <link href='https://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../CSS/estilos2.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../fonts/style.css" media="screen"/>
        <link href="../CSS/posiciones.css" rel="stylesheet" type="text/css"/>
        <script src="../JS/tableS.js" type="text/javascript"></script>
        <script src="../JS/cv.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="navegador.jsp" flush="true">
            <jsp:param name="nu" value='<%=apodo%>'/>
            <jsp:param name="pag" value='4'/>
        </jsp:include>   
            
            <%Vector<Liga> ligas = new Liga().mostrarLigas(liga);
            if(ligas.isEmpty()){
             %>
             <div id="noL">LIGAS NO DISPONIBLES</div>
            <%}else{
            Vector<Liga> ligasT = new Liga().mostrarTop(liga,"AMERICANA");%>
            
            <div id="seleccionar_liga" style="">
                <ul>
                    <%
                    int numL = new Liga().numL();
                    for(int i = 1; i <= numL; i++){
                    %>
                    
                    <a href="?liga=<%=i%>"><li style="width: <%=80/numL%>%; <%if(i==liga){%>background:#4caf50;<%}%>">LIGA <%=i%></li></a>
                    <%}%>
  
                </ul>
            </div>
            <div id="ligas">
                <table class="sortable" id="ligaL">
                    
                    <thead>
                        <tr>
                            <th>CONFERENCIA</th>   <th>DIVISIÓN</th>    <th>USUARIO</th> <th>JG</th>  <th>JP</th> <th>JE</th> <th>DIV</th>    <th>AFC</th>    <th>NFC</th>    <th>PF</th> <th>PC</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Liga l:ligas){
                        if(l.getConferencia().equalsIgnoreCase("AMERICANA") && l.getRegion().equalsIgnoreCase("N")){%>
                        <tr>
                            <td><%=l.getConferencia()%></td>
                            <td><%=l.getRegion()%></td>
                            <td><%=l.getNombreU()%></td>
                            <td><%=l.getJg()%></td>
                            <td><%=l.getJp()%></td>
                            <td><%=l.getJe()%></td>
                            <td><%=l.getDivi()%></td>
                            <td><%=l.getAfc()%></td>
                            <td><%=l.getNfc()%></td>
                            <td><%=l.getPf()%></td>
                            <td><%=l.getPc()%></td>      
                        </tr>
                        <%}}%>
                    </tbody>
                </table>
                    <br>
                <table class="sortable" id="ligaL">
                    
                    <thead>
                        <tr>
                            <th>CONFERENCIA</th>   <th>DIVISIÓN</th>    <th>USUARIO</th> <th>JG</th>  <th>JP</th> <th>JE</th> <th>DIV</th>    <th>AFC</th>    <th>NFC</th>    <th>PF</th> <th>PC</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Liga l:ligas){
                        if(l.getConferencia().equalsIgnoreCase("AMERICANA") && l.getRegion().equalsIgnoreCase("E")){%>
                        <tr>
                            <td><%=l.getConferencia()%></td>
                            <td><%=l.getRegion()%></td>
                            <td><%=l.getNombreU()%></td>
                            <td><%=l.getJg()%></td>
                            <td><%=l.getJp()%></td>
                            <td><%=l.getJe()%></td>
                            <td><%=l.getDivi()%></td>
                            <td><%=l.getAfc()%></td>
                            <td><%=l.getNfc()%></td>
                            <td><%=l.getPf()%></td>
                            <td><%=l.getPc()%></td>      
                        </tr>
                        <%}}%>
                    </tbody>
                </table>
                    <br>
                <table class="sortable" id="ligaL">
                    
                    <thead>
                        <tr>
                            <th>CONFERENCIA</th>   <th>DIVISIÓN</th>    <th>USUARIO</th> <th>JG</th>  <th>JP</th> <th>JE</th> <th>DIV</th>    <th>AFC</th>    <th>NFC</th>    <th>PF</th> <th>PC</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Liga l:ligas){
                        if(l.getConferencia().equalsIgnoreCase("AMERICANA") && l.getRegion().equalsIgnoreCase("O")){%>
                        <tr>
                            <td><%=l.getConferencia()%></td>
                            <td><%=l.getRegion()%></td>
                            <td><%=l.getNombreU()%></td>
                            <td><%=l.getJg()%></td>
                            <td><%=l.getJp()%></td>
                            <td><%=l.getJe()%></td>
                            <td><%=l.getDivi()%></td>
                            <td><%=l.getAfc()%></td>
                            <td><%=l.getNfc()%></td>
                            <td><%=l.getPf()%></td>
                            <td><%=l.getPc()%></td>      
                        </tr>
                        <%}}%>
                    </tbody>
                </table>
                    <br>
                <table class="sortable" id="ligaL">
                    
                    <thead>
                        <tr>
                            <th>CONFERENCIA</th>   <th>DIVISIÓN</th>    <th>USUARIO</th> <th>JG</th>  <th>JP</th> <th>JE</th> <th>DIV</th>    <th>AFC</th>    <th>NFC</th>    <th>PF</th> <th>PC</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Liga l:ligas){
                        if(l.getConferencia().equalsIgnoreCase("AMERICANA") && l.getRegion().equalsIgnoreCase("S")){%>
                        <tr>
                            <td><%=l.getConferencia()%></td>
                            <td><%=l.getRegion()%></td>
                            <td><%=l.getNombreU()%></td>
                            <td><%=l.getJg()%></td>
                            <td><%=l.getJp()%></td>
                            <td><%=l.getJe()%></td>
                            <td><%=l.getDivi()%></td>
                            <td><%=l.getAfc()%></td>
                            <td><%=l.getNfc()%></td>
                            <td><%=l.getPf()%></td>
                            <td><%=l.getPc()%></td>      
                        </tr>
                        <%}}%>
                    </tbody>
                </table>
                    <br>
                <table class="sortable" id="ligaL">
                    
                    <thead>
                        <tr>
                            <th>CONFERENCIA</th>   <th>DIVISIÓN</th>    <th>USUARIO</th> <th>JG</th>  <th>JP</th> <th>JE</th> <th>DIV</th>    <th>AFC</th>    <th>NFC</th>    <th>PF</th> <th>PC</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Liga l:ligas){
                        if(l.getConferencia().equalsIgnoreCase("NACIONAL") && l.getRegion().equalsIgnoreCase("N")){%>
                        <tr>
                            <td><%=l.getConferencia()%></td>
                            <td><%=l.getRegion()%></td>
                            <td><%=l.getNombreU()%></td>
                            <td><%=l.getJg()%></td>
                            <td><%=l.getJp()%></td>
                            <td><%=l.getJe()%></td>
                            <td><%=l.getDivi()%></td>
                            <td><%=l.getAfc()%></td>
                            <td><%=l.getNfc()%></td>
                            <td><%=l.getPf()%></td>
                            <td><%=l.getPc()%></td>      
                        </tr>
                        <%}}%>
                    </tbody>
                </table>
                <table class="sortable" id="ligaR">
                    <thead>
                        <tr>
                            <th colspan="2" style="font-size: 20px;"><span class="icon-trophy"></span>&nbsp;&nbsp;AMERICANA</th>
                        </tr>
                        <tr>
                            <th >USUARIO</th>   <th>DIVISIÓN</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Liga daniela:ligasT){%>
                        <tr>
                            <td><%=daniela.getNombreU()%></td>
                            <td><%=daniela.getRegion()%></td>    
                        </tr>
                        <%}%>
                        
                    </tbody>
                </table>
                        <br>
                <table class="sortable" id="ligaL">
                    
                    <thead>
                        <tr>
                            <th>CONFERENCIA</th>   <th>DIVISIÓN</th>    <th>USUARIO</th> <th>JG</th>  <th>JP</th> <th>JE</th> <th>DIV</th>    <th>AFC</th>    <th>NFC</th>    <th>PF</th> <th>PC</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Liga l:ligas){
                        if(l.getConferencia().equalsIgnoreCase("NACIONAL") && l.getRegion().equalsIgnoreCase("E")){%>
                        <tr>
                            <td><%=l.getConferencia()%></td>
                            <td><%=l.getRegion()%></td>
                            <td><%=l.getNombreU()%></td>
                            <td><%=l.getJg()%></td>
                            <td><%=l.getJp()%></td>
                            <td><%=l.getJe()%></td>
                            <td><%=l.getDivi()%></td>
                            <td><%=l.getAfc()%></td>
                            <td><%=l.getNfc()%></td>
                            <td><%=l.getPf()%></td>
                            <td><%=l.getPc()%></td>      
                        </tr>
                        <%}}%>
                    </tbody>
                </table>
                    <br>
                <table class="sortable" id="ligaL">
                    
                    <thead>
                        <tr>
                            <th>CONFERENCIA</th>   <th>DIVISIÓN</th>    <th>USUARIO</th> <th>JG</th>  <th>JP</th> <th>JE</th> <th>DIV</th>    <th>AFC</th>    <th>NFC</th>    <th>PF</th> <th>PC</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Liga l:ligas){
                        if(l.getConferencia().equalsIgnoreCase("NACIONAL") && l.getRegion().equalsIgnoreCase("O")){%>
                        <tr>
                            <td><%=l.getConferencia()%></td>
                            <td><%=l.getRegion()%></td>
                            <td><%=l.getNombreU()%></td>
                            <td><%=l.getJg()%></td>
                            <td><%=l.getJp()%></td>
                            <td><%=l.getJe()%></td>
                            <td><%=l.getDivi()%></td>
                            <td><%=l.getAfc()%></td>
                            <td><%=l.getNfc()%></td>
                            <td><%=l.getPf()%></td>
                            <td><%=l.getPc()%></td>      
                        </tr>
                        <%}}%>
                    </tbody>
                </table>
                    <br>
                <table class="sortable" id="ligaL">
                    
                    <thead>
                        <tr>
                            <th>CONFERENCIA</th>   <th>DIVISIÓN</th>    <th>USUARIO</th> <th>JG</th>  <th>JP</th> <th>JE</th> <th>DIV</th>    <th>AFC</th>    <th>NFC</th>    <th>PF</th> <th>PC</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Liga l:ligas){
                        if(l.getConferencia().equalsIgnoreCase("NACIONAL") && l.getRegion().equalsIgnoreCase("S")){%>
                        <tr>
                            <td><%=l.getConferencia()%></td>
                            <td><%=l.getRegion()%></td>
                            <td><%=l.getNombreU()%></td>
                            <td><%=l.getJg()%></td>
                            <td><%=l.getJp()%></td>
                            <td><%=l.getJe()%></td>
                            <td><%=l.getDivi()%></td>
                            <td><%=l.getAfc()%></td>
                            <td><%=l.getNfc()%></td>
                            <td><%=l.getPf()%></td>
                            <td><%=l.getPc()%></td>      
                        </tr>
                        <%}}%>
                    </tbody>
                </table>
                
                <table class="sortable" id="ligaR">
                    <thead>
                        <tr>
                            <th colspan="2" style="font-size: 20px;"><span class="icon-trophy"></span>&nbsp;&nbsp;AMERICANA</th>
                        </tr>
                        <tr>
                            <th >USUARIO</th>   <th>DIVISIÓN</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%ligasT = new Liga().mostrarTop(liga,"AMERICANA");
                            for (Liga daniela:ligasT){%>
                        <tr>
                            <td><%=daniela.getNombreU()%></td>
                            <td><%=daniela.getRegion()%></td>    
                        </tr>
                        <%}%>
                        
                    </tbody>
                </table>
                <table class="sortable" id="ligaR2">
                    <thead>
                        <tr>
                            <th colspan="2" style="font-size: 20px;"><span class="icon-trophy"></span>&nbsp;&nbsp;NACIONAL</th>
                        </tr>
                        <tr>
                            <th >USUARIO</th>   <th>DIVISIÓN</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%ligasT = new Liga().mostrarTop(liga,"NACIONAL");
                        
                       for (Liga daniela:ligasT){%>
                        <tr>
                            <td><%=daniela.getNombreU()%></td>
                            <td><%=daniela.getRegion()%></td>    
                        </tr>
                        <%}%>
                         
                    </tbody>
                </table>
            </div>
                        <%}%>
    </body>
</html>
<%}%>