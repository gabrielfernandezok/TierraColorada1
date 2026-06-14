/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.modelos;

/**
 *
 * @author ryzen 5
 */
public class EtapaProceso implements Trazable {

    private Etapa tipoEtapa; 
    private double pesoEntrada;
    private double merma;
    private double pesoSalida;

   
    public EtapaProceso(Etapa tipoEtapa, double pesoEntrada) {
        this.tipoEtapa = tipoEtapa;
        this.pesoEntrada = pesoEntrada;
        this.merma = 0.0;
        this.pesoSalida = pesoEntrada;
    }

    
    public EtapaProceso(Etapa tipoEtapa, double pesoEntrada, double merma) {
        this.tipoEtapa = tipoEtapa;
        this.pesoEntrada = pesoEntrada;
        this.merma = merma;
        this.pesoSalida = pesoEntrada - merma;
    }

    public void registrarMerma(double kilosMerma) {
    
        if (kilosMerma < 0) {
            throw new IllegalArgumentException("La merma no puede ser un valor negativo.");
        }
        if (kilosMerma > this.pesoEntrada) {
            throw new IllegalArgumentException("Error operativo: La merma (" + kilosMerma 
                    + " kg) no puede ser mayor al peso de entrada disponible (" + this.pesoEntrada + " kg).");
        }
        
        this.merma = kilosMerma;
        this.pesoSalida = this.pesoEntrada - kilosMerma;
    }

    
    @Override
    public String obtenerResumen() {
        return "PROCESO - Fase: " + this.tipoEtapa + 
               "Entrada: " + this.pesoEntrada + " kg" +
               "Merma: " + this.merma + " kg" +
               "Salida Neta: " + this.pesoSalida + " kg";
    }

    public Etapa getTipoEtapa() {
        return tipoEtapa;
    }

    public double getPesoEntrada() {
        return pesoEntrada;
    }

    public double getMerma() {
        return merma;
    }

    public double getPesoSalida() {
        return pesoSalida;
    }
    
}
