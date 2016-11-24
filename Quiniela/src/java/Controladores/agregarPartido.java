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
public class agregarPartido extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            int visitante = Integer.parseInt(request.getParameter("visitante"));
            int local = Integer.parseInt(request.getParameter("local"));
            int semana = Integer.parseInt(request.getParameter("semana"));
            int id_par = 0;
            boolean primer_marcador = Boolean.parseBoolean(request.getParameter("primer_marcador"));
            String fecha = request.getParameter("fecha");
            
            Partido party = new Partido();
            party = party.registrarPartido(semana, local, visitante,  fecha, primer_marcador);
            if(party!=null){
                Vector<Partido> partidos=new Partido().mostrarPartidos(String.valueOf(semana));
                for(Partido par:partidos){
                    id_par = par.getId_partido();
                }
                
                Quinela qui = new Quinela();     
                qui.crearPronostico2(id_par, primer_marcador);

                response.sendRedirect("paginasA/agregarP.jsp");
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
