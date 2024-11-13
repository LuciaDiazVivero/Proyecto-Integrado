/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ModeloUsuario;
import modelo.entidades.Rol;

/**
 *
 * @author msi
 */
@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {

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
        String vista = "/registro.jsp";
        
        String nombre = request.getParameter("nombre");
        if(nombre != null) {
            String error = null;
            String apellidos = request.getParameter("apellidos");
            String contra = request.getParameter("contra");
            String repetirContra = request.getParameter("repetirContra");
            if(!contra.equals(repetirContra)){
                error = "Las contraseñas no coinciden";
            }  
            String email = request.getParameter("email");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String telefono = request.getParameter("telefono");
            String localidad = request.getParameter("localidad");
            String domicilio = request.getParameter("domicilio");
            String tarjeta = request.getParameter("tarjeta");
            Double saldo = 0.0;
            Rol rol = Rol.CLIENTE;
            
            if(error == null) {
                error = ModeloUsuario.crearUsuario(nombre, apellidos, email, contra, fechaNacimiento, telefono, localidad, domicilio, tarjeta, saldo, rol);
            }
            if(error == null) {
                response.sendRedirect("Login");
                return;
            }
            
            // Si se ha producido algún error ...
            request.setAttribute("error", error);            
            
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
