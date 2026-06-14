/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.modelos;

/**
 * @author ryzen 5
 */
public class Viaje {

    private double distanciaRecorridaKm;
    private Vehiculo vehiculo;

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

