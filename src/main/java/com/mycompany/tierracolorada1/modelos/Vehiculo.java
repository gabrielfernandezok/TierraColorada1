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
        this.patente = patente;
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


