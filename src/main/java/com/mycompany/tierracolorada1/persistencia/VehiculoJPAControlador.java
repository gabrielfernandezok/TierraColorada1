/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.persistencia;

import com.mycompany.tierracolorada1.modelos.Vehiculo;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author ryzen 5
 */
public class VehiculoJPAControlador {
    
    private EntityManager getEntityManager() {
        return JPAUtil.getEntityManagerFactory().createEntityManager();
    }

    public void crear(Vehiculo vehiculo) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(vehiculo); 
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("ERROR [VehiculoJpa - Crear]: " + ex.getMessage());
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public Vehiculo buscarVehiculoPorPatente(String patente) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vehiculo.class, patente);
        } catch (Exception ex) {
            System.err.println("ERROR [VehiculoJpa - Buscar por Patente]: " + ex.getMessage());
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Vehiculo> buscarTodosLosVehiculos() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT v FROM Vehiculo v", Vehiculo.class).getResultList();
        } catch (Exception ex) {
            System.err.println("ERROR [VehiculoJpa - Buscar Todos]: " + ex.getMessage());
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    public void eliminar(String patente) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Vehiculo vehiculo = em.find(Vehiculo.class, patente);
            if (vehiculo != null) {
                em.remove(vehiculo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("ERROR [VehiculoJpa - Eliminar]: " + ex.getMessage());
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}