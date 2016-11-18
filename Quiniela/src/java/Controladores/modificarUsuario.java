/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Clases.Conexion;
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
 * @author Alumno
 */
public class modificarUsuario extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            HttpSession sesion = request.getSession();
       if(sesion.getAttribute("usuario") == null){
            response.sendRedirect("index.jsp");
       }
       else{
           String nombre = request.getParameter("nombre");
           String apodo = request.getParameter("apodo");
           String pass = request.getParameter("pass");
           String correo = request.getParameter("correo");
           Double monto = Double.parseDouble(String.valueOf(request.getParameter("monto")));
           int rol = Integer.parseInt(request.getParameter("rol"));
           int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
           
           Usuario uera = new Usuario();
           Vector <Usuario> temp = new Vector<Usuario>();
           temp =  uera.validarUsuario(apodo);
            if(temp.isEmpty()){
               
                uera = uera.editarUsuario(nombre, apodo, pass, correo, monto, rol, id_usuario);

                if(uera!=null){

                    if(sesion.getAttribute("rol").equals(1)){
                        response.sendRedirect("paginasA/modificarU.jsp?cambio=TCgh6gaj778GF8");
                    }else if(sesion.getAttribute("rol").equals(2)){
                            sesion.setAttribute("usuario", apodo);
                            response.sendRedirect("paginasU/info.jsp?d=TCgh6gaj778GF8");
                    }

                }else{

                 response.sendRedirect("error.jsp");

                }
            
            }else{
                for(Usuario a:temp){
                    
                    if(a.getId()==id_usuario){
                    
                        uera = uera.editarUsuario(nombre, apodo, pass, correo, monto, rol, id_usuario);

                        if(uera!=null){

                            if(sesion.getAttribute("rol").equals(1)){
                                response.sendRedirect("paginasA/modificarU.jsp?cambio=TCgh6gaj778GF8");
                            }else if(sesion.getAttribute("rol").equals(2)){
                                    sesion.setAttribute("usuario", apodo);
                                    System.out.println(sesion.getAttribute("usuario"));
                                    response.sendRedirect("paginasU/info.jsp?d=TCgh6gaj778GF8");
                            }

                        }else{
                         response.sendRedirect("error.jsp");

                        }
                    }else{
                    response.sendRedirect("error.jsp");
                    break;
                    
                    }
                
                
                }
               
                
            }
          

           
       }
           
           
        }
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
