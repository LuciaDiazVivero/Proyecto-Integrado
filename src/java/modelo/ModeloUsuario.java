/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import modelo.entidades.Rol;
import modelo.entidades.Usuario;
import modelo.entidades.dao.UsuarioJpaController;
import modelo.entidades.dao.exceptions.NonexistentEntityException;

/**
 *
 * @author msi
 */
public class ModeloUsuario {
    public static final String PU = "Proyecto_Agencia_ViajesPU";    
    
    public static String crearUsuario(String nombre, String apellidos, String email, String contra, String fechaNacimiento, String telefono, String localidad, String domicilio, String tarjeta, Double saldo, Rol rol) {
        String error = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setApellidos(apellidos);
        u.setEmail(email);        
        u.setContra(contra);
        u.setTelefono(telefono);
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaE = null;
        try {
            fechaE = df.parse(fechaNacimiento);
        } catch(ParseException e) {
            System.err.println("----------- crearUsuario: Error al convertir fecha: " + fechaNacimiento);
        }        
        u.setFechaNacimiento(fechaE);
        
        u.setLocalidad(localidad);
        u.setDomicilio(domicilio);
        u.setTarjeta(tarjeta);
        u.setSaldo(saldo);
        u.setRol(rol);
        
        try {
            ujc.create(u);
        } catch (RollbackException ex) {
            if(ex.getMessage().contains("Duplicate")) {
                error = "Ya existe un usuario con el email " + email;
            } else {
                error = "Se ha producido un error al crear el Usuario";
            }
        }
        emf.close();
        return error;
    }
    
    public static Usuario consultarUsuario(Integer id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);        
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        Usuario u = ujc.findUsuario(id);
        System.out.println("Fecha de nacimiento desde la base de datos: " + u.getFechaNacimiento());

        emf.close();
        return u;
    }
    
    public static String actualizarUsuario(Usuario u, String nombre, String apellidos, String email, String contra, Date fechaNacimiento, String telefono, String localidad, String domicilio, String tarjeta, Double saldo, Rol rol){
        String error = null;
        u.setNombre(nombre);
        u.setApellidos(apellidos);
        u.setEmail(email);
        u.setContra(contra);
        u.setFechaNacimiento(fechaNacimiento);
        u.setTelefono(telefono);
        u.setLocalidad(localidad);
        u.setDomicilio(domicilio);
        u.setTarjeta(tarjeta);
        u.setSaldo(saldo);
        u.setRol(rol);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        try{
            ujc.edit(u);
        } catch (NonexistentEntityException ex) {
            error = "El usuario ha sido eliminado";
        } catch (Exception ex) {
            if(ex.getMessage().contains("Duplicate")) {
                error = "Ya existe un usuario con email " + email;
            } else {
                error = "Se ha producido un error al actualizar el usuario " + u;
            }
        }
        emf.close();
        return error;
    }
    
    public static String eliminarUsuario(Usuario u) {
        String error = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        try {
            ujc.destroy(u.getId());
        } catch (modelo.entidades.dao.exceptions.NonexistentEntityException ex) {
            error = "El usuario " + u + " ya ha sido eliminado";
        } catch (Exception ex) {
            error = "Se ha producido un error al eliminar el usuario " + u;
        }
        emf.close();
        return error;    
    }
    
}
