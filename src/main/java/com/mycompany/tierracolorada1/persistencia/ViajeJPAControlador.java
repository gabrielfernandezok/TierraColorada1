/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.persistencia;

import com.mycompany.tierracolorada1.modelos.Viaje;
import jakarta.persistence.EntityManager;
import java.util.List;
/**
 *
 * @author ryzen 5
 */
public class ViajeJPAControlador {
    private EntityManager getEntityManager() {
        return JPAUtil.getEntityManagerFactory().createEntityManager();
    }
    
    public void crear(Viaje viaje) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(viaje);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("ERROR [ViajeJpa - Crear]: " + ex.getMessage());
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    public Viaje buscarViajePorId(Long idViaje) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Viaje.class, idViaje);
        } catch (Exception ex) {
            System.err.println(" ERROR [ViajeJpa - Buscar por ID]: " + ex.getMessage());
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    public List<Viaje> buscarTodosLosViajes() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT v FROM Viaje v", Viaje.class).getResultList();
        } catch (Exception ex) {
            System.err.println("ERROR [ViajeJpa - Buscar Todos]: " + ex.getMessage());
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    public void editar(Viaje viaje) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(viaje);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("ERROR [ViajeJpa - Editar]: " + ex.getMessage());
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
