/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.admin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.ModeloLugar;

/**
 *
 * @author msi
 */
@WebServlet(name = "CrearLugar", urlPatterns = {"/admin/CrearLugar"})
public class CrearLugar extends HttpServlet {
    public static final int TAM_BUFFER = 4 * 1024;
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
        String vista = "/admin/crearLugar.jsp";
        String pais = request.getParameter("pais");
        String localidad = request.getParameter("localidad");
        if(pais != null && localidad != null
                && !pais.trim().isEmpty() && !localidad.trim().isEmpty()) {
            String error = ModeloLugar.crearLugar(pais, localidad, " ");
            
            Part imagen = request.getPart("imagen");
            if(error == null) {
                /* Imagen */
                String nombreFichero = localidad;
                InputStream entrada = imagen.getInputStream();
                String ruta = getServletContext().getRealPath("/imgLugar/") + nombreFichero;
                FileOutputStream salida = new FileOutputStream(ruta);
                byte[] buffer = new byte[TAM_BUFFER];
                while (entrada.available() > 0) {
                    int tam = entrada.read(buffer);
                    salida.write(buffer, 0, tam);
                }     
                salida.close();
                entrada.close();
                /* END Imagen */
            }
            
            if(error != null) {
                request.setAttribute("error", error);
                request.setAttribute("pais", pais);
                request.setAttribute("localidad", localidad);
                request.setAttribute("imagen", imagen);
            } else {
                response.sendRedirect("./GestionLugares");
                return; 
            }
        }
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
