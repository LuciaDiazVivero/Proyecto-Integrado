/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.dao.LugarJpaController;

/**
 *
 * @author msi
 */
@WebServlet(name = "EliminarLugar", urlPatterns = {"/admin/EliminarLugar"})
public class EliminarLugar extends HttpServlet {

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
        String idString = request.getParameter("idLugar");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Proyecto_Agencia_ViajesPU");
        LugarJpaController ljc = new LugarJpaController(emf);   
        
        try {
            // Convertir el ID de String a Integer
            Integer id = Integer.parseInt(idString);

            // Eliminar el fichero asociado de la carpeta imgLugar
            File eliminarArchivo = new File(getServletContext().getRealPath("./../imgLugar/") + id);
            eliminarArchivo.delete();
            /*
            if (eliminarArchivo.exists()) {
                eliminarArchivo.delete();
            }
            */

            // Llamar al método destroy para eliminar la entidad de la base de datos
            ljc.destroy(id);
            response.sendRedirect("./GestionLugares");
        } catch(Exception e) {
                System.out.println("******************************************");
                System.out.println(e.getMessage());
                System.out.println("******************************************");            
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
