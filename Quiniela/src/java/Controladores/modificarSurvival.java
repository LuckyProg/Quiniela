/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Clases.Partido;
import Clases.Survival;
import Clases.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class modificarSurvival extends HttpServlet {

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
            int id_equipo = Integer.parseInt(request.getParameter("id_equipo"));
            int semana = Integer.parseInt(String.valueOf(request.getParameter("semana")));
            String apodo = request.getParameter("apodo");
            int id_usuario=0;
            Date hoy; 
            SimpleDateFormat sdf; 
            String fecha = ""; 
            hoy = new Date(); 
            sdf = new SimpleDateFormat("dd-MM-yyyy"); 
            fecha = sdf.format(hoy); 
            Vector<Partido> partidos=new Partido().mostrarPartidos(String.valueOf(semana));
            for(Partido par:partidos){
                System.out.println(par.getFecha());
            }
            Survival sur = new Survival();
            Usuario usu = new Usuario();
            Vector<Usuario> usuarios=new Usuario().mostrarUsuarios("1");
            for(Usuario usua:usuarios){
                if(apodo.equals(String.valueOf(usua.getApodo()))){
                    id_usuario = usua.getId();
                }
            }
            sur = sur.editarSurvival(id_equipo, id_usuario, semana);
            if(sur!=null){
                if(sesion.getAttribute("rol").equals(1)){
                    response.sendRedirect("paginasA/mostrarS.jsp");
                }
                else if(sesion.getAttribute("rol").equals(2)){
                    response.sendRedirect("paginasU/survival.jsp?semana="+semana);
                }
            
         }else{
             response.sendRedirect("error.jsp");
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
