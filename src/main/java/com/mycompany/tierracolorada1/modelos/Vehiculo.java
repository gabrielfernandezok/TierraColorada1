/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.modelos;
import jakarta.persistence.*;
/**
 *
 * @author ryzen 5
 */
@Entity
@Table(name = "vehiculos")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehiculo {
@Id
    private String patente; 
@Column(nullable = false)
    private String marca;
@Column(nullable = false)
    private String modelo;

    public Vehiculo() {
    }

    public Vehiculo(String patente, String marca, String modelo) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
    }

    public abstract double calcularCostoTransporte(double distanciaKm);

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
    if (patente == null || patente.trim().isEmpty()) {
        throw new IllegalArgumentException("La patente no puede estar vacía.");
    }
    
    // Limpiamos espacios y pasamos a mayúsculas por si el usuario tipeó en minúsculas
    String patenteLimpia = patente.trim().toUpperCase();
    
    // Formato Viejo (AAA123) o Formato Mercosur (AA123AA)
    String regexVieja = "^[A-Z]{3}\\d{3}$";
    String regexMercosur = "^[A-Z]{2}\\d{3}[A-Z]{2}$";
    
    if (!patenteLimpia.matches(regexVieja) && !patenteLimpia.matches(regexMercosur)) {
        throw new IllegalArgumentException("Formato de patente inválido. Debe ser estándar (ej: ABC123 o AA123AA) sin espacios ni símbolos.");
    }
    this.patente = patenteLimpia;
}

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}


