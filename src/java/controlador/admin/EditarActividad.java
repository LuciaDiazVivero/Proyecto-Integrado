/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ModeloActividad;
import modelo.entidades.Actividad;

/**
 *
 * @author msi
 */
@WebServlet(name = "EditarActividad", urlPatterns = {"/admin/EditarActividad"})
    public class EditarActividad extends HttpServlet {

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
        String vista = "/admin/editarActividad.jsp";
        Integer id = Integer.parseInt(request.getParameter("id"));
        Actividad a = ModeloActividad.consultarActividad(id);
        String nombre = request.getParameter("nombre");

        if (nombre != null && !nombre.trim().isEmpty()) {
            String error = null;

            if (request.getParameter("eliminar") != null) {
                // Manejar eliminación de la actividad
                error = ModeloActividad.eliminarActividad(a);
            } else {
                // Procesar actualización de la actividad
                String fechaActStr = request.getParameter("fechaAct");
                Date fechaAct = null;

                // Si el campo de fecha está vacío, usar la fecha actual de la actividad
                if (fechaActStr == null || fechaActStr.trim().isEmpty()) {
                    fechaAct = a.getFechaAct();
                } else {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        fechaAct = dateFormat.parse(fechaActStr);
                    } catch (ParseException e) {
                        error = "Formato de fecha inválido";
                    }
                }

                // Actualizar actividad solo si no hay errores previos
                if (error == null) {
                    error = ModeloActividad.actualizarActividad(a, nombre, fechaAct);
                }
            }

            // Manejo de errores o redirección
            if (error != null) {
                request.setAttribute("error", error);
            } else {
                response.sendRedirect("./GestionActividades");
                return;
            }
        }

        // Enviar datos a la vista para edición
        request.setAttribute("actividad", a);
        getServletContext().getRequestDispatcher(vista).forward(request, response);
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
