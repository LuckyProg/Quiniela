/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

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
                Usuario morena = new Usuario();
                
                Vector<Usuario> usuarios=new Usuario().mostrarUsuarios("1");
                Vector<Survival> selected;
                /*for(Usuario usu:usuarios){
                    
                    selected=new Survival().verSurvival(usu.getApodo()); 
                    for(Survival s:selected){
                        if(semana == s.getSemana()){
                            if(visitante == s.getId_equipo()){
                                if(visitante == Integer.parseInt(ganadoreal)){
                                    morena.survi(usu.getId(), true);
                                    wuera.actualizar(usu.getId(), semana, true);
                                }
                                else{
                                    morena.survi(usu.getId(), false);
                                    wuera.actualizar(usu.getId(), semana, false);
                                }
                            }
                            else if(local == s.getId_equipo()){
                                if(local== Integer.parseInt(ganadoreal)){
                                    morena.survi(usu.getId(), true);
                                    wuera.actualizar(usu.getId(), semana, true);
                                }
                                else{
                                    morena.survi(usu.getId(), false);
                                    wuera.actualizar(usu.getId(), semana, false);
                                }
                            }
                            else if(s.getId_equipo() == 100){
                                morena.survi(usu.getId(), false);
                                wuera.actualizar(usu.getId(), semana, false);
                            }
                        }
                    }
                }*/
                Quinela wuiris = new Quinela();
                wuiris.guardarAcierto(pr_mar, mayoreal, menoreal, id_partido, semana, Integer.parseInt(ganadoreal));
                response.sendRedirect("paginasA/resultadosP.jsp?sem="+semana);
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
