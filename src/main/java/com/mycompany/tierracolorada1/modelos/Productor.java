/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

/**
 * @author ryzen 5
 */
@Entity 
public class Productor implements Trazable {

    @Id 
    @Column(length = 15) 
    private String cuit;
    
    @Column(nullable = false) 
    private String nombre;
    
    private String telefono;

    public Productor() {
    }

    public Productor(String cuit, String nombre, String telefono) {
        setCuit(cuit);
        setNombre(nombre);
        setTelefono(telefono);
    }

    @Override
    public String obtenerResumen() {
        return "PRODUCTOR - CUIT: " + this.cuit +
               " Nombre: " + this.nombre +
               " Teléfono: " + this.telefono;
    }

    public String getcuit() {
        return cuit;
    }

    public String getnombre() {
        return nombre;
    }

    public String gettelefono() {
        return telefono;
    }

    public void setCuit(String cuit) {
    if (cuit == null || cuit.trim().isEmpty()) {
        throw new IllegalArgumentException(" El CUIT no puede estar vacío.");
    }
    
    // Expresión regular: busca exactamente 2 dígitos, guion, 8 dígitos, guion, 1 dígito.
    String regexCuit = "^\\d{2}-\\d{8}-\\d{1}$";
    
    if (!cuit.matches(regexCuit)) {
        throw new IllegalArgumentException(" CUIT inválido. Debe tener el formato estricto de números y guiones (ej: 30-12345678-9).");
    }
    this.cuit = cuit;
}

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
    if (telefono != null && !telefono.isEmpty()) {
        // Permite números, espacios, guiones y el signo +
        if (!telefono.matches("^[0-9+\\s-]+$")) {
            throw new IllegalArgumentException("El teléfono solo puede contener números, espacios o el signo '+'.");
        }
    }
    this.telefono = telefono;
}
}
