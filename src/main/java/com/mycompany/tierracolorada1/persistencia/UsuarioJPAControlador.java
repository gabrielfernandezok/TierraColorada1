/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.persistencia;

import com.mycompany.tierracolorada1.modelos.Usuario;
import jakarta.persistence.EntityManager;
import java.util.List;

public class UsuarioJPAControlador {


    private EntityManager getEntityManager() {
        return JPAUtil.getEntityManagerFactory().createEntityManager();
    }

    public void crear(Usuario usuario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            System.out.println("Usuario guardado exitosamente en la Base de Datos.");
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR EN PERSISTENCIA - Crear Usuario: " + ex.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

 
    public List<Usuario> buscarTodosLosUsuarios() {
        EntityManager em = getEntityManager();
        try {
 
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } catch (Exception ex) {
            System.out.println("ERROR EN PERSISTENCIA - Buscar Usuarios: " + ex.getMessage());
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    public Usuario buscarUsuarioPorId(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } catch (Exception ex) {
            System.out.println("ERROR EN PERSISTENCIA - Buscar Usuario ID: " + ex.getMessage());
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public Usuario buscarUsuarioPorNombre(String nombreUsuario) {
    EntityManager em = getEntityManager();
    try {
        // En lugar de em.find, hacemos una consulta JPQL que busca por el atributo de texto
        return em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :userText", Usuario.class)
                 .setParameter("userText", nombreUsuario.trim())
                 .getSingleResult();
    } catch (jakarta.persistence.NoResultException e) {
        // Si no encuentra ningún usuario con ese texto, devolvemos null de forma segura
        return null;
    } catch (Exception ex) {
        System.err.println("ERROR [UsuarioJpa - Buscar por Nombre de Usuario]: " + ex.getMessage());
        return null;
    } finally {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
}

