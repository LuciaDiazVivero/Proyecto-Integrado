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
public class ViajesJpaController implements Serializable {

    public ViajesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Viajes viajes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alojamiento alojamiento = viajes.getAlojamiento();
            if (alojamiento != null) {
                alojamiento = em.getReference(alojamiento.getClass(), alojamiento.getIdAlojamiento());
                viajes.setAlojamiento(alojamiento);
            }
            em.persist(viajes);
            if (alojamiento != null) {
                Viajes oldViajeOfAlojamiento = alojamiento.getViaje();
                if (oldViajeOfAlojamiento != null) {
                    oldViajeOfAlojamiento.setAlojamiento(null);
                    oldViajeOfAlojamiento = em.merge(oldViajeOfAlojamiento);
                }
                alojamiento.setViaje(viajes);
                alojamiento = em.merge(alojamiento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Viajes viajes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Viajes persistentViajes = em.find(Viajes.class, viajes.getIdViaje());
            Alojamiento alojamientoOld = persistentViajes.getAlojamiento();
            Alojamiento alojamientoNew = viajes.getAlojamiento();
            if (alojamientoNew != null) {
                alojamientoNew = em.getReference(alojamientoNew.getClass(), alojamientoNew.getIdAlojamiento());
                viajes.setAlojamiento(alojamientoNew);
            }
            viajes = em.merge(viajes);
            if (alojamientoOld != null && !alojamientoOld.equals(alojamientoNew)) {
                alojamientoOld.setViaje(null);
                alojamientoOld = em.merge(alojamientoOld);
            }
            if (alojamientoNew != null && !alojamientoNew.equals(alojamientoOld)) {
                Viajes oldViajeOfAlojamiento = alojamientoNew.getViaje();
                if (oldViajeOfAlojamiento != null) {
                    oldViajeOfAlojamiento.setAlojamiento(null);
                    oldViajeOfAlojamiento = em.merge(oldViajeOfAlojamiento);
                }
                alojamientoNew.setViaje(viajes);
                alojamientoNew = em.merge(alojamientoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = viajes.getIdViaje();
                if (findViajes(id) == null) {
                    throw new NonexistentEntityException("The viajes with id " + id + " no longer exists.");
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
            Viajes viajes;
            try {
                viajes = em.getReference(Viajes.class, id);
                viajes.getIdViaje();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The viajes with id " + id + " no longer exists.", enfe);
            }
            Alojamiento alojamiento = viajes.getAlojamiento();
            if (alojamiento != null) {
                alojamiento.setViaje(null);
                alojamiento = em.merge(alojamiento);
            }
            em.remove(viajes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Viajes> findViajesEntities() {
        return findViajesEntities(true, -1, -1);
    }

    public List<Viajes> findViajesEntities(int maxResults, int firstResult) {
        return findViajesEntities(false, maxResults, firstResult);
    }

    private List<Viajes> findViajesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Viajes.class));
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

    public Viajes findViajes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Viajes.class, id);
        } finally {
            em.close();
        }
    }

    public int getViajesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Viajes> rt = cq.from(Viajes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
