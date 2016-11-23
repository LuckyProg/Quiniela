/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Clases.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author derda
 */
public class iniciarSesion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String user, pass, des_col = " ";
int id_rol = 0;
int id_usuario = 0;
boolean ses = false;
boolean admin = false;
try{
HttpSession sesion = request.getSession(); 
user = request.getParameter("user");
pass = request.getParameter("pass");
Vector<Usuario> usuarios=new Usuario().mostrarUsuarios("1");
String nombre = " ";
for(Usuario usu:usuarios){   
    if(user.equals(usu.getApodo())&&pass.equals(usu.getPass())){
        ses=true;
        id_rol=usu.getRol();
        id_usuario=usu.getId();
        nombre = usu.getNombre();
        if(id_rol==1){admin=true;}
    }
}
if(ses==true && sesion.getAttribute("usuario") == null && admin == false){
    //si coincide usuario y password y además no hay sesión iniciada
    sesion.setAttribute("usuario", user);
    sesion.setAttribute("rol", id_rol);
    sesion.setAttribute("id_usuario", id_usuario);
    sesion.setAttribute("nombre", nombre);
    //redirijo a página con información de login exitoso
    response.sendRedirect("paginasU/inicio.jsp");
}else if(ses==true && sesion.getAttribute("usuario") == null && admin == true){
    sesion.setAttribute("usuario", user);
    sesion.setAttribute("rol", id_rol);
    //redirijo a página con información de login exitoso
    response.sendRedirect("paginasA/inicio_admin.jsp");
}else{
    response.sendRedirect("index.jsp?m=a0Ñ9as8L7sd6K2d1");
}}
catch(Exception e){System.out.println(e);}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}