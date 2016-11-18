/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Clases.Quinela;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fernando
 */
public class guardarQuiniela extends HttpServlet {

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
       HttpSession sesion = request.getSession();
        int ganador = 0;
        int mayor = 0;
        int menor = 0;
        int id_partido = 0;
        int id_usuario = Integer.parseInt(sesion.getAttribute("id_usuario").toString());
        int sm = 0;
        if(request.getParameter("Sm")!=null){
            sm = Integer.parseInt(request.getParameter("Sm"));
        }
        int no_marcador = 0;
        Quinela qui = new Quinela();
        for(int i = 1; i<=Integer.parseInt(request.getParameter("numpar"));i++){
            
           try {
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
               Date servidor = sdf.parse(qui.obtenerFecha());
               Date partido = sdf.parse(request.getParameter("fecha"+i) + ":00.0");
               if(servidor.before(partido)){
                            if(request.getParameter("ganador"+String.valueOf(i))!=null){
                            ganador = Integer.parseInt(request.getParameter("ganador"+String.valueOf(i)));
                            }else{ganador = 0;}
                            if(request.getParameter("mayor"+String.valueOf(i)).equals("")){

                            }else{
                                mayor = Integer.parseInt(request.getParameter("mayor"+String.valueOf(i)));
                            }
                            if(request.getParameter("menor"+String.valueOf(i)).equals("")){

                            }else{
                                menor = Integer.parseInt(request.getParameter("menor"+String.valueOf(i)));
                            }
                            if(sm == i){
                                no_marcador = 2;
                            }
                            if(request.getParameter("Pm")!=null){
                                no_marcador = 1;
                            }
                            id_partido = Integer.parseInt(request.getParameter("id_partido"+String.valueOf(i)));
                            
                            qui.guardarPronostico(ganador, mayor, menor, id_partido, id_usuario, no_marcador);
                   
               }
               
               no_marcador = 0;
               
           } catch (ParseException ex) {
               Logger.getLogger(guardarQuiniela.class.getName()).log(Level.SEVERE, null, ex);
           }
            
        }
        
        response.sendRedirect("paginasU/llenarQ.jsp?semana="+request.getParameter("s"));
        
        
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