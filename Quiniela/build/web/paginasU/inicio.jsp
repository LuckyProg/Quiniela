<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
    response.sendRedirect("../index.jsp");
}else{

String apodo = String.valueOf(sesion.getAttribute("usuario"));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
		<link rel="shortcut icon" type="image/x-icon" href="IMG/IC.ico">
        <title>QUINIELA</title>
        <link href='https://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="../CSS/estilos2.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="../fonts/style.css" media="screen"/>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
        <script src="JS/slideshow.js"></script>
    </head>
    <body>
        <jsp:include page="navegador.jsp" flush="true">
            <jsp:param name="nu" value='<%=apodo%>'/>
            <jsp:param name="pag" value='1'/>
        </jsp:include>   
        <div id="slideShowImages">
                 <img src="../IMG/i1.jpg" alt="Slide 1" />
                 <img src="../IMG/i3.jpg" alt="Slide 3" />
        </div>
    </body>      
</html><%}%>