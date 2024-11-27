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
import modelo.ModeloUsuario;
import modelo.entidades.Rol;
import modelo.entidades.Usuario;

/**
 *
 * @author msi
 */
@WebServlet(name = "EditarUsuario", urlPatterns = {"/admin/EditarUsuario"})
public class EditarUsuario extends HttpServlet {

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
        String vista = "/admin/editarUsuario.jsp";
        Integer id = Integer.parseInt(request.getParameter("id"));
        Usuario u = ModeloUsuario.consultarUsuario(id);
        String nombre = request.getParameter("nombre");

        if (nombre != null && !nombre.trim().isEmpty()) {
            String error = null;

            if (request.getParameter("eliminar") != null) {
                // Manejar eliminación del usuario
                error = ModeloUsuario.eliminarUsuario(u);
            } else {
                // Procesar actualización del usuario
                String apellidos = request.getParameter("apellidos");
                String email = request.getParameter("email");
                String contra = request.getParameter("contra");

                String fechaNacimientoStr = request.getParameter("fechaNacimiento");
                Date fechaNacimiento = null;

                // Si el campo de fecha está vacío, usar la fecha actual del usuario
                if (fechaNacimientoStr == null || fechaNacimientoStr.trim().isEmpty()) {
                    fechaNacimiento = u.getFechaNacimiento();
                } else {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
                    } catch (ParseException e) {
                        error = "Formato de fecha inválido";
                    }
                }

                String telefono = request.getParameter("telefono");
                String localidad = request.getParameter("localidad");
                String domicilio = request.getParameter("domicilio");
                String tarjeta = request.getParameter("tarjeta");
                Rol rol = Rol.valueOf(request.getParameter("rol"));
                String saldoStr = request.getParameter("saldo");

                try {
                    // Convertir saldo a double
                    double saldo = Double.parseDouble(saldoStr);

                    // Actualizar usuario si no hay errores
                    if (error == null) {
                        error = ModeloUsuario.actualizarUsuario(u, nombre, apellidos, email, contra, fechaNacimiento, telefono, localidad, domicilio, tarjeta, saldo, rol);
                    }
                } catch (NumberFormatException e) {
                    // Manejar error de conversión de saldo
                    error = "El saldo introducido no es válido";
                }
            }

            // Manejo de errores o redirección
            if (error != null) {
                request.setAttribute("error", error);
                request.setAttribute("contra", request.getParameter("contra"));
                request.setAttribute("repetirContra", request.getParameter("repetirContra"));
            } else {
                response.sendRedirect("./GestionUsuarios");
                return;
            }
        }

        // Enviar datos a la vista para edición
        request.setAttribute("usuario", u);
        request.setAttribute("roles", Rol.values());
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
