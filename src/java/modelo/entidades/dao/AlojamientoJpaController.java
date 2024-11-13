/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.entidades.Alojamiento;
import modelo.entidades.Viajes;
import modelo.entidades.dao.exceptions.NonexistentEntityException;

/**
 *
 * @author msi
 */
public class AlojamientoJpaController implements Serializable {

    public AlojamientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alojamiento alojamiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Viajes viaje = alojamiento.getViaje();
            if (viaje != null) {
                viaje = em.getReference(viaje.getClass(), viaje.getIdViaje());
                alojamiento.setViaje(viaje);
            }
            em.persist(alojamiento);
            if (viaje != null) {
                Alojamiento oldAlojamientoOfViaje = viaje.getAlojamiento();
                if (oldAlojamientoOfViaje != null) {
                    oldAlojamientoOfViaje.setViaje(null);
                    oldAlojamientoOfViaje = em.merge(oldAlojamientoOfViaje);
                }
                viaje.setAlojamiento(alojamiento);
                viaje = em.merge(viaje);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alojamiento alojamiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alojamiento persistentAlojamiento = em.find(Alojamiento.class, alojamiento.getIdAlojamiento());
            Viajes viajeOld = persistentAlojamiento.getViaje();
            Viajes viajeNew = alojamiento.getViaje();
            if (viajeNew != null) {
                viajeNew = em.getReference(viajeNew.getClass(), viajeNew.getIdViaje());
                alojamiento.setViaje(viajeNew);
            }
            alojamiento = em.merge(alojamiento);
            if (viajeOld != null && !viajeOld.equals(viajeNew)) {
                viajeOld.setAlojamiento(null);
                viajeOld = em.merge(viajeOld);
            }
            if (viajeNew != null && !viajeNew.equals(viajeOld)) {
                Alojamiento oldAlojamientoOfViaje = viajeNew.getAlojamiento();
                if (oldAlojamientoOfViaje != null) {
                    oldAlojamientoOfViaje.setViaje(null);
                    oldAlojamientoOfViaje = em.merge(oldAlojamientoOfViaje);
                }
                viajeNew.setAlojamiento(alojamiento);
                viajeNew = em.merge(viajeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = alojamiento.getIdAlojamiento();
                if (findAlojamiento(id) == null) {
                    throw new NonexistentEntityException("The alojamiento with id " + id + " no longer exists.");
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
            Alojamiento alojamiento;
            try {
                alojamiento = em.getReference(Alojamiento.class, id);
                alojamiento.getIdAlojamiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alojamiento with id " + id + " no longer exists.", enfe);
            }
            Viajes viaje = alojamiento.getViaje();
            if (viaje != null) {
                viaje.setAlojamiento(null);
                viaje = em.merge(viaje);
            }
            em.remove(alojamiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alojamiento> findAlojamientoEntities() {
        return findAlojamientoEntities(true, -1, -1);
    }

    public List<Alojamiento> findAlojamientoEntities(int maxResults, int firstResult) {
        return findAlojamientoEntities(false, maxResults, firstResult);
    }

    private List<Alojamiento> findAlojamientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alojamiento.class));
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

    public Alojamiento findAlojamiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alojamiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlojamientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alojamiento> rt = cq.from(Alojamiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
