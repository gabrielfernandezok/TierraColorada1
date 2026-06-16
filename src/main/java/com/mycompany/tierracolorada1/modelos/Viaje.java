/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "viaje")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_viaje")
    private Long idViaje;

    @Column(name = "distancia_recorrida_km", nullable = false)
    private double distanciaRecorridaKm;

    @ManyToOne
    @JoinColumn(name = "vehiculo_patente", nullable = false)
    private Vehiculo vehiculo;

    public Viaje() {
    }

    public Viaje(Vehiculo vehiculo, double distanciaRecorridaKm) {
        if (distanciaRecorridaKm < 0) {
            throw new IllegalArgumentException("La distancia recorrida no puede ser negativa.");
        }

        this.vehiculo = vehiculo;
        this.distanciaRecorridaKm = distanciaRecorridaKm;
    }

    public double calcularCostoTransporte() {
        return this.vehiculo.calcularCostoTransporte(this.distanciaRecorridaKm);
    }

    public Long getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(Long idViaje) {
        this.idViaje = idViaje;
    }

    public double getDistanciaRecorridaKm() {
        return distanciaRecorridaKm;
    }

    public void setDistanciaRecorridaKm(double distanciaRecorridaKm) {
        if (distanciaRecorridaKm < 0) {
            throw new IllegalArgumentException("La distancia recorrida no puede ser negativa.");
        }

        this.distanciaRecorridaKm = distanciaRecorridaKm;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}

