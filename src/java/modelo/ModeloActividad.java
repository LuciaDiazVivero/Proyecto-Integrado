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
import modelo.entidades.Actividad;
import modelo.entidades.dao.ActividadJpaController;
import modelo.entidades.dao.exceptions.NonexistentEntityException;

/**
 *
 * @author msi
 */
public class ModeloActividad {
    public static final String PU = "Proyecto_Agencia_ViajesPU"; 

    public static String crearActividad(String nombre, String fechaAct) {
        String error = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        ActividadJpaController ajc = new ActividadJpaController(emf);
        Actividad a = new Actividad();
        a.setNombre(nombre);
       
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaA = null;
        try {
            fechaA = df.parse(fechaAct);
        } catch(ParseException e) {
            System.err.println("----------- crearActividad: Error al convertir fecha: " + fechaAct);
        }        
        a.setFechaAct(fechaA);
        
        try {
            ajc.create(a);
        } catch (RollbackException ex) {
            if(ex.getMessage().contains("Duplicate")) {
                error = "Ya existe esa actividad";
            } else {
                error = "Se ha producido un error al crear la actividad";
            }
        }
        emf.close();
        return error;
    }

    public static Actividad consultarActividad(Integer id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);        
        ActividadJpaController ajc = new ActividadJpaController(emf);
        Actividad a = ajc.findActividad(id);
        emf.close();
        return a;
    }
    
    public static String actualizarActividad(Actividad a, String nombre, Date fechaAct){
        String error = null;
        a.setNombre(nombre);
        a.setFechaAct(fechaAct);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        ActividadJpaController ajc = new ActividadJpaController(emf);
        try{
            ajc.edit(a);
        } catch (NonexistentEntityException ex) {
            error = "La actividad " + a + " ha sido eliminada";
        } catch (Exception ex) {
            if(ex.getMessage().contains("Duplicate")) {
                error = "Ya existe la actividad " + a;
            } else {
                error = "Se ha producido un error al actualizar la actividad " + a;
            }
        }
        emf.close();
        return error;
    }  
    
    public static String eliminarActividad(Actividad a) {
        String error = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        ActividadJpaController ajc = new ActividadJpaController(emf);
        try {
            ajc.destroy(a.getId());
        } catch (modelo.entidades.dao.exceptions.NonexistentEntityException ex) {
            error = "La actividad " + a + " ya ha sido eliminada";
        } catch (Exception ex) {
            error = "Se ha producido un error al eliminar la actividad " + a;
        }
        emf.close();
        return error;    
    }    

}
