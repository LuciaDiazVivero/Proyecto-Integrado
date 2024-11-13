/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.entidades.Reservas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.entidades.Usuario;
import modelo.entidades.dao.exceptions.NonexistentEntityException;

/**
 *
 * @author msi
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getReservasCollection() == null) {
            usuario.setReservasCollection(new ArrayList<Reservas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Reservas> attachedReservasCollection = new ArrayList<Reservas>();
            for (Reservas reservasCollectionReservasToAttach : usuario.getReservasCollection()) {
                reservasCollectionReservasToAttach = em.getReference(reservasCollectionReservasToAttach.getClass(), reservasCollectionReservasToAttach.getIdReservas());
                attachedReservasCollection.add(reservasCollectionReservasToAttach);
            }
            usuario.setReservasCollection(attachedReservasCollection);
            em.persist(usuario);
            for (Reservas reservasCollectionReservas : usuario.getReservasCollection()) {
                Usuario oldUsuarioOfReservasCollectionReservas = reservasCollectionReservas.getUsuario();
                reservasCollectionReservas.setUsuario(usuario);
                reservasCollectionReservas = em.merge(reservasCollectionReservas);
                if (oldUsuarioOfReservasCollectionReservas != null) {
                    oldUsuarioOfReservasCollectionReservas.getReservasCollection().remove(reservasCollectionReservas);
                    oldUsuarioOfReservasCollectionReservas = em.merge(oldUsuarioOfReservasCollectionReservas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            Collection<Reservas> reservasCollectionOld = persistentUsuario.getReservasCollection();
            Collection<Reservas> reservasCollectionNew = usuario.getReservasCollection();
            Collection<Reservas> attachedReservasCollectionNew = new ArrayList<Reservas>();
            for (Reservas reservasCollectionNewReservasToAttach : reservasCollectionNew) {
                reservasCollectionNewReservasToAttach = em.getReference(reservasCollectionNewReservasToAttach.getClass(), reservasCollectionNewReservasToAttach.getIdReservas());
                attachedReservasCollectionNew.add(reservasCollectionNewReservasToAttach);
            }
            reservasCollectionNew = attachedReservasCollectionNew;
            usuario.setReservasCollection(reservasCollectionNew);
            usuario = em.merge(usuario);
            for (Reservas reservasCollectionOldReservas : reservasCollectionOld) {
                if (!reservasCollectionNew.contains(reservasCollectionOldReservas)) {
                    reservasCollectionOldReservas.setUsuario(null);
                    reservasCollectionOldReservas = em.merge(reservasCollectionOldReservas);
                }
            }
            for (Reservas reservasCollectionNewReservas : reservasCollectionNew) {
                if (!reservasCollectionOld.contains(reservasCollectionNewReservas)) {
                    Usuario oldUsuarioOfReservasCollectionNewReservas = reservasCollectionNewReservas.getUsuario();
                    reservasCollectionNewReservas.setUsuario(usuario);
                    reservasCollectionNewReservas = em.merge(reservasCollectionNewReservas);
                    if (oldUsuarioOfReservasCollectionNewReservas != null && !oldUsuarioOfReservasCollectionNewReservas.equals(usuario)) {
                        oldUsuarioOfReservasCollectionNewReservas.getReservasCollection().remove(reservasCollectionNewReservas);
                        oldUsuarioOfReservasCollectionNewReservas = em.merge(oldUsuarioOfReservasCollectionNewReservas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Collection<Reservas> reservasCollection = usuario.getReservasCollection();
            for (Reservas reservasCollectionReservas : reservasCollection) {
                reservasCollectionReservas.setUsuario(null);
                reservasCollectionReservas = em.merge(reservasCollectionReservas);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
