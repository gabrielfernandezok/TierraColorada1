/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.persistencia;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.IOException;
import java.util.Properties;

public class JPAUtil {

    private static EntityManagerFactory emf;

    private JPAUtil() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            try {
                Properties props = new Properties();
                props.load(JPAUtil.class.getClassLoader().getResourceAsStream("config.properties"));

                emf = Persistence.createEntityManagerFactory("Persistencia1", props);
            } catch (IOException e) {
                System.out.println("ERROR: No se pudo leer el archivo config.properties.");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("ERROR GRAVE: No se pudo levantar la fábrica de conexiones JPA.");
                e.printStackTrace();
            }
        }
        return emf;
    }

    public static void shutdown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}


