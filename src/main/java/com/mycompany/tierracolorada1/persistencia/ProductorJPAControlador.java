/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.persistencia;

import com.mycompany.tierracolorada1.modelos.Productor;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ProductorJPAControlador {

    private EntityManager getEntityManager() {
        return JPAUtil.getEntityManagerFactory().createEntityManager();
    }

    public void crear(Productor productor) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin(); 
            em.persist(productor); 
            em.getTransaction().commit();
            System.out.println("Productor con CUIT " + productor.getcuit() + " guardado en la Base de Datos.");
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR EN PERSISTENCIA [Crear Productor]: " + ex.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void editar(Productor productor) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(productor); 
            em.getTransaction().commit();
            System.out.println("Datos del productor CUIT " + productor.getcuit() + " actualizados con éxito.");
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR EN PERSISTENCIA [Editar Productor]: " + ex.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public Productor buscarProductorPorCuit(String cuit) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productor.class, cuit);
        } catch (Exception ex) {
            System.out.println("ERROR EN PERSISTENCIA [Buscar Productor por CUIT]: " + ex.getMessage());
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Productor> buscarTodosLosProductores() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Productor p", Productor.class).getResultList();
        } catch (Exception ex) {
            System.out.println("ERROR EN PERSISTENCIA [Buscar Todos los Productores]: " + ex.getMessage());
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
