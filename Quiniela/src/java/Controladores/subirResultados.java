/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Clases.Enfrentamiento;
import Clases.Partido;
import Clases.Quinela;
import Clases.Survival;
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
 * @author derda
 */
public class subirResultados extends HttpServlet {

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
            String e1 = request.getParameter("e1");
            String e2 = request.getParameter("e2");
            int semana =  Integer.parseInt(request.getParameter("semana"));
            int local =  Integer.parseInt(request.getParameter("local"));
            int visitante =  Integer.parseInt(request.getParameter("visitante"));
            String fecha =  request.getParameter("fecha");
            String mayoreal = request.getParameter("mayoreal");
            String menoreal = request.getParameter("menoreal");
            String ganadoreal = request.getParameter("ganadoreal");
            int id_partido =  Integer.parseInt(request.getParameter("id_partido"));
            boolean pr_mar =  Boolean.parseBoolean(request.getParameter("pr_mar"));
            Partido par = new Partido();
            par = par.subirResultado(semana, local, visitante, fecha, mayoreal, menoreal, ganadoreal, id_partido);
            
            if(par!=null){
                
                Survival wuera = new Survival();
                wuera.actualizar(semana, Integer.parseInt(ganadoreal), local, visitante);
                
                Quinela wuiris = new Quinela();
                wuiris.guardarAcierto(pr_mar, mayoreal, menoreal, id_partido, semana, Integer.parseInt(ganadoreal));
                
                Enfrentamiento e = new Enfrentamiento();
                if(pr_mar){
                    
                }
                if(semana==14 && pr_mar==true){
                    e.crearS15();
                }
                if(semana==15 && pr_mar==true){
                    e.crearS16();
                }
                if(semana==16 && pr_mar==true){
                    e.crearS17();
                }
                
                response.sendRedirect("paginasA/resultadosP.jsp?sem="+semana+"&e1="+e1+"&e2="+e2);
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
