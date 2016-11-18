/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Clases.Partido;
import Clases.Quinela;
import Clases.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fernando
 */
public class registrarUsuario extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String nombre = request.getParameter("nombre");
       double monto = Double.parseDouble(request.getParameter("monto"));
       int rol = Integer.parseInt(request.getParameter("rol"));
       int id_usuario = 0;        
       
       Usuario user = new Usuario();
       String pass = user.generarPass();
       user = user.registrarUsuario(nombre, pass, monto, rol);
       
       Vector<Usuario> usuarios=new Usuario().mostrarUsuarios("0");
       
       if(user!=null){
            for(Usuario usu:usuarios){
                id_usuario = usu.getId();
            }
            Quinela wuera = new Quinela();
            Vector<Partido> partidos;
            for(int i=1; i<=17; i++){
                partidos = new Partido().mostrarPartidos(String.valueOf(i));
                for(Partido par:partidos){
                    wuera.crearPronostico(id_usuario, par.getId_partido(), par.isPr_mar());
                }
            }
            response.sendRedirect("paginasA/agregarU.jsp?nombre="+nombre+"&pass="+pass+"");
         }else{
             response.sendRedirect("error.jsp");
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
