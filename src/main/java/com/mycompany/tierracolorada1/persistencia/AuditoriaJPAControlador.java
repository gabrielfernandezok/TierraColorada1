/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.persistencia;

import com.mycompany.tierracolorada1.modelos.Auditoria;
import jakarta.persistence.EntityManager;
import java.util.List;


public class AuditoriaJPAControlador {

    private EntityManager getEntityManager() {
        return JPAUtil.getEntityManagerFactory().createEntityManager();
    }

    public void crear(Auditoria auditoria) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(auditoria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("ERROR [AuditoriaJpa - Crear]: " + ex.getMessage());
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Auditoria> buscarTodasLasAuditorias() {
        EntityManager em = getEntityManager();
        try {
        
            return em.createQuery("SELECT a FROM Auditoria a ORDER BY a.fechaHora DESC", Auditoria.class)
                     .getResultList();
        } catch (Exception ex) {
            System.err.println("ERROR [AuditoriaJpa - Buscar Todas]: " + ex.getMessage());
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Auditoria> buscarAuditoriasPorLote(int idLote) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Auditoria a WHERE a.lote.idLote = :idLote ORDER BY a.fechaHora DESC", Auditoria.class)
                     .setParameter("idLote", idLote)
                     .getResultList();
        } catch (Exception ex) {
            System.err.println("ERROR [AuditoriaJpa - Buscar por Lote]: " + ex.getMessage());
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
