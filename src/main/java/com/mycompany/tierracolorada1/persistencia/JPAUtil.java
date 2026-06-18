/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.persistencia;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory emf;

    private JPAUtil() {
    }

    public static synchronized EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            try {
                System.out.println("[JPA] Inicializando fabrica de conexiones y generando tablas...");

        emf = Persistence.createEntityManagerFactory("Persistencia1");
            System.out.println("[JPA] Fabrica de conexiones levantada");
            } catch (Exception ex) {
                System.err.println("ERROR GRAVE: No se pudo levantar la fabrica de conexiones JPA.");
                ex.printStackTrace();
                throw new RuntimeException("Error al inicializar la base de datos", ex);
            }
        }
        return emf;
    }

    public static void shutdown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("[JPA] Fabrica de conexiones cerrada de forma segura.");
        }
    }
}


