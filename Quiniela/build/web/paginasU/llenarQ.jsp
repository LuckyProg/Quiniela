<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Vector"%>
<%@page import="Clases.Quinela"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
        <link href='https://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../CSS/estilos2.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../CSS/llenarQ.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../fonts/style.css" media="screen"/>
        <script type="text/javascript" src="SA/sweetalert-dev.js"></script>
        <script src="../JS/cv.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="SA/sweetalert.css" media="screen"/>
        <script type="text/javascript">
           sweetAlert("Oops...", "Usuario no existente!", "error");
        </script>
        <title>LLENA TU QUINIELA</title>
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
                <form method="post" name="daniela" action="../guardarQuiniela">   
                    <input type="hidden" value="<%=semana%>" name="semana">
                <%
                    Quinela wuera = new Quinela();
                    int cont=0;
                    boolean pasa = false;
                    Vector <Quinela> q =new Quinela().mostrarPronosticos(id_usuario,semana);
                    for(Quinela qui:q){
                    cont++;
                    String idvisitante = "e"+qui.getId_visitante();
                    String idlocal = "e"+qui.getId_local();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                    Date servidor = sdf.parse(wuera.obtenerFecha());
                    Date partido = sdf.parse(qui.getFecha()+" "+qui.getHora() + ":00.0");
                    if(servidor.before(partido)){ 
                        pasa = true;
                    }
                    int idP = qui.getId_partido();
                    String fecha = qui.getFecha();
                    String hora = qui.getHora();
                    String aC = qui.getAcierto();
                    int idL = qui.getId_local();
                    int idV = qui.getId_visitante();
                    int idG = qui.getGanador();
                    String visitante = qui.getVisitante();
                    String local = qui.getLocal();
                %>
                <input type="hidden" name="numpar" value="<%=q.size()%>">
                <input type="hidden" name="fecha<%=cont%>" value="<%=fecha%> <%=hora%>">
                <div class="partido" id="<%=idP%>">
                    
                    <div class="status" <%if(aC!=null){if(aC.equals("1")){%>id="bien"<%}%> <%if(aC.equals("0")){%>id="mal"<%}}%>></div>
                    
                    <div class="equipos">
                        
                        <div class="fecha">Fecha: <%=fecha%><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hora: <%=hora%></div>
                        <label>
                            <div class="visitante" id="<%=idvisitante%>" <%if(pasa){%> onclick="cambiarColor('<%=idvisitante%>','<%=idlocal%>')" <%} if(idG==idV){%>style="background-color: #1a648a"<%}%>>
                                <img src="../IMG/equipo/<%=qui.getImagenvis()%>"/>
                                <span>V: <%=qui.getVisitante()%><br><input type="radio" name="ganador<%=cont%>"value="<%=idV%>" <%if(idG==idV){%>checked<%}%>>
                                    <%if(!pasa){%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    % <%//=wuera.obtenerPorcentajevisitante(idP,semana)%><%}%>
                                </span>
                            </div>
                        </label>
                        
                        <span class="vs">VS</span>
                        
                        <label>
                            <div class="local" id="<%=idlocal%>" <%if(pasa){%> onclick="cambiarColor('<%=idlocal%>','<%=idvisitante%>')" <%} if(idG==idL){%>style="background-color: #1a648a"<%}%>>
                                <img src="../IMG/equipo/<%=qui.getImagenloc()%>" alt=""/>
                                <span>L: <%=qui.getLocal()%><br><input type="radio" name="ganador<%=cont%>" value="<%=qui.getId_local()%>" <%if(idG==idL){%>checked<%}%>>
                                    <%if(!pasa){%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    % <%//=wuera.obtenerPorcentajelocal(idP,semana)%><%}%>
                                </span> 
                            </div>
                        </label>
                        
                    </div>
                    <div class="marcadores"> 
                        <%
                            int noM = qui.getNo_marcador();
                            int M = qui.getMayor();
                            int m = qui.getMenor();
                        %>
                        <%if(noM==1){%>
                        <p>Primer marcador:</p>
                        <br>
                        <p>Mayor: <input type="number" name="mayor<%=cont%>" value="<%=M%>"></p>
                        <p>Menor: <input type="number" name="menor<%=cont%>" value="<%=m%>"></p>
                        <input type="hidden" name="Pm" value="1">
                        <%}else{%>
                        <p>Segundo marcador: <input type="radio" name="Sm" id="S<%=cont%>" value="<%=cont%>" onchange="mostrarMarcador('<%=cont%>')" <%if(noM==2){%>checked<%}%>></p>
                        <br>
                        <p id="M<%=cont%>" <%if(noM!=2){%>style="display:none;"<%}%>>Mayor: <input type="number" name="mayor<%=cont%>" <%if(noM==2){%>value="<%=M%>"<%}%>></p>
                        <p id="m<%=cont%>" <%if(noM!=2){%>style="display:none;"<%}%>>Menor: <input type="number" name="menor<%=cont%>" <%if(noM==2){%>value="<%=m%>"<%}%>></p>
                        <%}%>
                    </div>  
                </div>
                    <input type="hidden" name="id_partido<%=cont%>" value="<%=idP%>">
                <%}%>
                <br>
                
                <%if(cont!=0){%><center><input type="submit" id="submite" value="Guardar"></center><%}else{%>
                
                <center><h2>No hay partidos registrados en la semana <%=s%></h2></center>
                
                <%}%>
                <input type="hidden" name="s" value="<%=s%>">
            </form>
            </div>
    </body>
</html>
<%}%>