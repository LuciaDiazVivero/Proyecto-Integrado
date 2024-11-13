/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.entidades.Usuario;
import modelo.entidades.dao.UsuarioJpaController;

/**
 *
 * @author msi
 */
public class ModeloLogin {
 
    public static Usuario validarUsuario(String email, String contra){
        Usuario u = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Proyecto_Agencia_ViajesPU");
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        List<Usuario> usuario = ujc.findUsuarioEntities();
        boolean encontrado = false;
        for(int i = 0; i < usuario.size() && !encontrado; i++){
            Usuario actual = usuario.get(i);
            if(actual.getEmail().equals(email)){
                if(actual.getContra().equals(contra)){
                    u = actual;
                }
            }
        }
        emf.close();
        return u;
    }    
    
}
