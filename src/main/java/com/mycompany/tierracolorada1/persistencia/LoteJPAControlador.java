/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.persistencia;

import com.mycompany.tierracolorada1.modelos.Lote;
import jakarta.persistence.EntityManager;
import java.util.List;
/**
 *
 * @author ryzen 5
 */
public class LoteJPAControlador {
    private EntityManager getEntityManager() {
        return JPAUtil.getEntityManagerFactory().createEntityManager();
    }
    
    public void crear(Lote lote) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(lote);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("ERROR LoteJpa - Crear: " + ex.getMessage());
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    public void editar(Lote lote) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(lote);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("ERROR LoteJpa - Editar: " + ex.getMessage());
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    public Lote buscarLotePorId(int idLote) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lote.class, idLote);
        } catch (Exception ex) {
            System.err.println("ERROR LoteJpa - Buscar por ID: " + ex.getMessage());
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    public List<Lote> buscarTodosLosLotes() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT l FROM Lote l", Lote.class).getResultList();
        } catch (Exception ex) {
            System.err.println("ERROR LoteJpa - Buscar Todos: " + ex.getMessage());
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
