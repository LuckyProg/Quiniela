<%@page import="Clases.Usuario"%>
<%@page import="Clases.Liga"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else if(sesion.getAttribute("rol").equals(1)){
String apodo = String.valueOf(sesion.getAttribute("usuario"));
int liga;
String conf;
    if(request.getParameter("liga")!=null){
        liga = Integer.parseInt(request.getParameter("liga"));
    }else {liga=1;}
    if(request.getParameter("conf")!=null){
        conf = request.getParameter("conf");
    }else {conf="AMERICANA";}%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
        <link rel="stylesheet" type="text/css" href="../CSS/general.css" media="screen" />
        <link href="../CSS/posiciones.css" rel="stylesheet" type="text/css"/>
        <title>AGREGAR PARTIDO</title>
        <script type="text/javascript" src="../JS/val.js"></script>

       
    </head>
    <body onpaste="return false;">
        <jsp:include page="navegadorA.jsp" flush="true">
            <jsp:param name="nu" value='<%=apodo%>'/>
            <jsp:param name="pag" value='4'/>
        </jsp:include> 
        <div id="seleccionar_liga" style="position: absolute;top:0;right: 5%;background: transparent;">
                <ul>
                    <%
                    int numL = new Liga().numL();
                    for(int i = 1; i <= numL; i++){
                    %>
                    
                    <a href="?liga=<%=i%>&conf=<%=conf%>"><li style="width: <%=80/numL%>%; <%if(i==liga){%>background:#4caf50;<%}%>">LIGA <%=i%></li></a>
                    <%}%>
  
                </ul>
                    
            </div>
        <div id="seleccionar_conf" style="position: absolute;top:0;right: 5%;background: transparent;">
                <ul>
                    
                    <a href="?liga=<%=liga%>&conf=AMERICANA"><li style="width: <%=80/2%>%; <%if(conf.equals("AMERICANA")){%>background:#4caf50;<%}%>">AMERICANA</li></a>
                    <a href="?liga=<%=liga%>&conf=NACIONAL"><li style="width: <%=80/2%>%; <%if(conf.equals("NACIONAL")){%>background:#4caf50;<%}%>">NACIONAL</li></a>
  
                </ul>
                    
            </div>
        <div id="in2"><center><h1>TOP</h1><br><br>
       
            <table border="1">
                <tr>
                    <td>
                        Posicion
                    </td>
                    <td>
                        Usuario
                    </td>
                    <td>
                        Jg
                    </td>
                    <td>
                        Jp
                    </td>
                    <td>
                        Je
                    </td>
                    <td>
                        Divi
                    </td>
                    <td>
                        Afc
                    </td>
                    <td>
                        Nfc
                    </td>
                </tr>
                        <%
                        
                        Vector<Liga> ligasT = new Liga().mostrarTop(liga,conf, 20); 
                        int cont = 1;
                        for(Liga l:ligasT){
                        %>
                <tr>
                    <td>
                        <%=cont%>
                    </td>
                    <td>
                        <%=l.getNombreU()%>
                    </td>
                    <td>
                        <%=l.getJg()%>
                    </td>
                    <td>
                        <%=l.getJp()%>
                    </td>
                    <td>
                        <%=l.getJe()%>
                    </td>
                    <td>
                        <%=l.getDivi()%>
                    </td>
                    <td>
                        <%=l.getAfc()%>
                    </td>
                    <td>
                        <%=l.getNfc()%>
                    </td>
                </tr>
                    <%
                    cont++; }%>
            </table></div></center>
        <div id="in0"><center><h1>SELECCIONAR PLAYOFFS</h1><br><br>
       
        <form method="post" action="../CrearEnfrentamiento">
            <input type='hidden' name='liga' value='<%=liga%>'>
            <input type='hidden' name='conf' value='<%=conf%>'>
            <table border="1">
                <tr>
                    <td>
                        Posicion
                    </td>
                    <td>
                        Usuario
                    </td>
                </tr>
                        <%
                        ligasT = new Liga().mostrarTop(liga,conf, 6); 
                        cont = 1;
                        for(Liga l:ligasT){
                        %>
                <tr>
                    <td>
                        <%=cont%>
                    </td>
                    <td>
                        <select name='id<%=cont%>'>
                            <%                
                                Vector<Liga> ligasT2 = new Liga().mostrarTop(liga,conf, 20);                  
                                for(Liga u:ligasT2){                  
                            %>
                            <option value='<%=u.getId_usuario()%>' <%if(u.getId_usuario()==l.getId_usuario()){%>selected<%}%>><%=u.getNombreU()%></option>                           
                            <%
                             }
                            %>
                        </select>
                </tr>
                    <%
                    cont++; }%>
                <tr>
                    <td><input type="submit" value="Guardar"></td>
                </tr>
            </table>
        </form></div></center>
    </body>
</html>
<%}%>

