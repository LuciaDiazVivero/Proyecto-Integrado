/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.admin;

import static controlador.admin.CrearLugar.TAM_BUFFER;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.ModeloLugar;
import modelo.entidades.Lugar;

/**
 *
 * @author msi
 */
@WebServlet(name = "EditarLugar", urlPatterns = {"/admin/EditarLugar"})
public class EditarLugar extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        String vista = "/admin/editarLugar.jsp";
        String error = null;
        String idStr = request.getParameter("id");
        int id;

        try {
            id = Integer.parseInt(idStr);
            if (id < 0) {
                throw new NumberFormatException("El ID no puede ser negativo.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "El ID proporcionado no es válido: " + e.getMessage());
            getServletContext().getRequestDispatcher(vista).forward(request, response);
            return;
        }

        Lugar lu;
        try {
            lu = ModeloLugar.consultarLugar(id);
            if (lu == null) {
                request.setAttribute("error", "No se encontró un lugar con el ID proporcionado.");
                getServletContext().getRequestDispatcher(vista).forward(request, response);
                return;
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error al consultar el lugar: " + e.getMessage());
            getServletContext().getRequestDispatcher(vista).forward(request, response);
            return;
        }

        String pais = request.getParameter("pais");
        String localidad = request.getParameter("localidad");
        if(id > 0 && pais != null && localidad != null 
                && !idStr.trim().isEmpty() && !pais.trim().isEmpty() && !localidad.trim().isEmpty()) {
            //String error = null;
            
            /* Imagen Lugar */
            Part imagen = request.getPart("imagen");
            if(imagen != null) {
                String nombreFichero = localidad;
                InputStream entrada = imagen.getInputStream();
                String ruta = getServletContext().getRealPath("/imgLugares/") + nombreFichero;
                FileOutputStream salida = new FileOutputStream(ruta);
                byte[] buffer = new byte[TAM_BUFFER];
                while (entrada.available() > 0) {
                    int tam = entrada.read(buffer);
                    salida.write(buffer, 0, tam);
                }
                salida.close();
                entrada.close();                
            } else {}
            /* END Imagen Lugar */
            
            if(request.getParameter("eliminar") != null) {
                error = ModeloLugar.eliminarLugar(lu);
            } else {
                error = ModeloLugar.actualizarLugar(lu, id, pais, localidad, " ");  
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
        request.setAttribute("lugar", lu);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EditarLugar.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EditarLugar.class.getName()).log(Level.SEVERE, null, ex);
        }
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
