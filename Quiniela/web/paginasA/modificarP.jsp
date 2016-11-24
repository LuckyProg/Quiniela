<%@page import="Clases.Equipo"%>
<%@page import="Clases.Partido"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else if(sesion.getAttribute("rol").equals(1)){
    
    String sem = request.getParameter("sem");
    if(sem==null){
        
        sem="1";
        

    }
    
    String apodo = String.valueOf(sesion.getAttribute("usuario"));
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
        <link rel="stylesheet" type="text/css" href="../CSS/general.css" media="screen" />
        <script type="text/javascript" src="../JS/val.js"></script>
        <title>MODIFICAR PARTIDO</title>
    </head>
    <body onpaste="return false;">
         <jsp:include page="navegadorA.jsp" flush="true">
            <jsp:param name="nu" value='<%=apodo%>'/>
            <jsp:param name="pag" value='2'/>
        </jsp:include> 
        <div id="in">
            <center>
                
                <h1>MODIFICAR</h1><br><br>
                <h2>Semana: <%=sem%></h2><br>
                <form method="Post" action="modificarP.jsp">
                    Semana: <select name="sem" onchange="this.form.submit()">
                        <option value="0">----</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                    </select>
                </form> 
                <br>
                <%
                    
                Vector<Partido> partidos=new Partido().mostrarPartidos(sem);
                Vector<Equipo> ecuip=new Equipo().mostrarEquipos(); 
                %>
                <table >
                    <tr>
                        <td></td>
                        <td>SEMANA</td>
                        <td>VISITANTE</td>
                        <td>LOCAL</td>
                        <td>FECHA</td>
                        <td>P-M</td>
                        <td></td>
                        <td></td>
                        
                    </tr>
                    <%
                    for(Partido par:partidos){
                    %>
                            <tr>
                                <form method="Post" action="../modificarPartido"> 
                                    <td>
                                        <input name="id_partido" type="hidden" value="<%=par.getId_partido()%>">
                                    </td>
                                    <td>
                                        <input name="semana" type="number" onkeypress="return soloNum(event)" value="<%=par.getSemana()%>">
                                    </td>
                                    <td>
                                        <select name="visitante">
                                            <%
                 
                                                for(Equipo e:ecuip){   
                                                
                                            %>
                                            <option value='<%=e.getId_equipo()%>' <%if(par.getId_visitante()==e.getId_equipo()){%>selected<%}%> > <%=e.getNombre()%></option>                           
                                            <%
                                             }
                                            %>
                                        </select>
                                    </td>
                                    <td>
                                        <select name="local">
                                            <%
                 
                                                for(Equipo e:ecuip){   
                                                
                                            %>
                                            <option value='<%=e.getId_equipo()%>' <%if(par.getId_local()==e.getId_equipo()){%>selected<%}%> > <%=e.getNombre()%></option>                           
                                            <%
                                             }
                                            %>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="datetime-local" name="fecha" value="<%=par.getFecha()%>">
                                    </td>
                                    <td>
                                        <input type="checkbox" name="pr_mar" value=true <%if(par.isPr_mar()){%>checked<%}%>>
                                    </td>
                                    <td><input type="submit" value="Guardar"></td>
                                </form>
                                    
                                <form method="post" action="../borrarPartido">                                   
                                    <td><input type="hidden" name = "id_partido" value='<%=par.getId_partido()%>'></td>
                                    <td><input type="submit" value="Eliminar"></td>
                                </form>
                            </tr>
                     <%}%>
                </table>
        
            </center>
        </div>
    </body>
</html>
<%}%>
