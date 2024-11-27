/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import modelo.entidades.Lugar;
import modelo.entidades.dao.LugarJpaController;
import modelo.entidades.dao.exceptions.NonexistentEntityException;
import modelo.entidades.dao.exceptions.PreexistingEntityException;

/**
 *
 * @author msi
 */
public class ModeloLugar {
    public static final String PU = "Proyecto_Agencia_ViajesPU"; 
  
    public static String crearLugar(String pais, String localidad, String imagen){
        String error = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        LugarJpaController ljc = new LugarJpaController(emf);
        Lugar lu = new Lugar();
        lu.setPais(pais);
        lu.getLocalidad();
        lu.setImagen(imagen);
        try {
            ljc.create(lu);
        } catch (RollbackException e) {
            if(e.getMessage().contains("Duplicate")) {
                error = "Ya existe el lugar " + pais;
            } else {
                error = "Se ha producido un error al crear el lugar";
            }
        }
        emf.close();
        return error;
    }
   
    public static Lugar consultarLugar(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        LugarJpaController ljc = new LugarJpaController(emf);
        Lugar lu = ljc.findLugar(id);
        emf.close();
        return lu;
    }    
    
    public static String actualizarLugar(Lugar lu, Integer id, String pais, String localidad, String imagen) throws Exception {
        String error = null;
        lu.setIdLugar(id);
        lu.setPais(pais);
        lu.setLocalidad(localidad);
        lu.setImagen(imagen);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        LugarJpaController ljc = new LugarJpaController(emf);
        try {
            ljc.edit(lu);
        } catch (NonexistentEntityException ex) {
            error = "El lugar ha sido eliminado";
        } catch (Exception e) {
            if(e.getMessage().contains("Duplicate")) {
                error = "Ya existe el lugar " + pais;
            } else {
                error = "Se ha producido un error al actualizar el lugar " + pais;
            }
        }
        emf.close();
        return error;
    }
    
    public static String eliminarLugar(Lugar lu) {
        String error = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        LugarJpaController ljc = new LugarJpaController(emf);
        try {
            ljc.destroy(lu.getIdLugar());
        } catch (NonexistentEntityException e) {
            error = "El lugar " + lu.getPais() + " ya ha sido eliminado";
        } catch (Exception ex) {
            error = "Se ha producido un error al eliminar el lugar " + lu.getPais();
        }
        emf.close();
        return error;
    }
    
}
