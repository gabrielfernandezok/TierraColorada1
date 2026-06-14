/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.modelos;
import jakarta.persistence.*;

/**
 * @author ryzen 5
 */
@Entity
@Table(name = "flete_tercerizado")
@PrimaryKeyJoinColumn(name = "patente")
public class FleteTercerizado extends Vehiculo {

@Column(nullable = false)
    private double costoPorKilometro;

    public FleteTercerizado(String patente, String marca, String modelo, double costoPorKilometro) {
        super(patente, marca, modelo);
        if (costoPorKilometro < 0) {
            throw new IllegalArgumentException("La tarifa por kilómetro no puede ser negativa.");
        }
        this.costoPorKilometro = costoPorKilometro;
    }


    @Override
    public double calcularCostoTransporte(double distanciaKm) {
        if (distanciaKm < 0) {
            throw new IllegalArgumentException("La distancia recorrida no puede ser negativa.");
        }

        return distanciaKm * this.costoPorKilometro;
    }

    // GETTER
    public double getCostoPorKilometro() {
        return this.costoPorKilometro;
    }

   
    public void setCostoPorKilometro(double costoPorKilometro) {
        if (costoPorKilometro < 0) {
            throw new IllegalArgumentException("La tarifa por kilómetro no puede ser negativa.");
        }
        this.costoPorKilometro = costoPorKilometro;
    }
}