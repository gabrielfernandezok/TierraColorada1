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
@Table(name = "camion_propio")
@PrimaryKeyJoinColumn(name = "patente")
public class CamionPropio extends Vehiculo {

@Column(nullable = false)
private double costoFijoMensual;

    public CamionPropio() {
        super();
    }
    
    public CamionPropio(String patente, String marca, String modelo, double costoFijoMensual) {
        super(patente, marca, modelo);
        this.costoFijoMensual = costoFijoMensual;
    }

    @Override
    public double calcularCostoTransporte(double distanciaKm) {
        if (distanciaKm < 0) {
            throw new IllegalArgumentException("La distancia no puede ser negativa.");
        }
        
        double tasaDesgaste = this.costoFijoMensual / 2000.0;
        return distanciaKm * tasaDesgaste;
    }

    
    public double getCostoFijoMensual() {
        return this.costoFijoMensual;
    }

    
    public void setCostoFijoMensual(double nuevoCostoFijoMensual) {
        if (nuevoCostoFijoMensual < 0) {
            throw new IllegalArgumentException("El costo de mantenimiento no puede ser negativo.");
        }
        this.costoFijoMensual = nuevoCostoFijoMensual;
    }
}
