/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Clases.Archivo;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fernando
 */
public class generarArchivo extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet generarArchivo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet generarArchivo at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        int caso = Integer.parseInt(request.getParameter("tipo"));
        HttpSession cecyt = request.getSession();
        if(cecyt.getAttribute("rol")!=null){
        int rol = Integer.parseInt(String.valueOf(cecyt.getAttribute("rol")));
        
        int semana = Integer.parseInt(String.valueOf(request.getParameter("semana")));
        int tipo = Integer.parseInt(String.valueOf(request.getParameter("tipo")));
        
        Archivo a = new Archivo();
        
        
        String filename = "";
        byte [] pene = null;
        switch(tipo){
        
            case 1: 
                System.out.println("Caso 1");
                if(rol!=1){
                    response.sendRedirect("index.jsp");
                }else{
                    filename = "usuarios.csv";
                    pene = Files.readAllBytes(a.Danielavenami(1,semana).toPath());
                }
                break;
            case 2:
                System.out.println("caso 2");
                filename = "quiniela_"+semana+".csv";
                pene = Files.readAllBytes(a.Danielavenami(2,semana).toPath());
                break;
            case 3:
                System.out.println("caso 3");
                filename = "survival.csv";
                pene = Files.readAllBytes(a.Danielavenami(3,semana).toPath());
                break;
            default:break;
        
        }
        
        response.setContentType("text/csv");
        response.setHeader("Content-disposition", "attachment; filename=\""+filename+"\"");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "-1");
        response.getOutputStream().write(pene);
        
        }else{
        response.sendRedirect("index.jsp");
        }
        
        
        
       

   
    
       
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
