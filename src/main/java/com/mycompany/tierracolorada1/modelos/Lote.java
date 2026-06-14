/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.modelos; 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Lote implements Trazable {
    
    private int idLote;
    private double porcentajeHumedad;
    private LocalDateTime fechaHoraIngreso;
    private Productor productor;
    private Viaje viaje;
    private List<EtapaProceso> historialEtapas;

    

    public Lote(int idLote, Productor productor, Viaje viaje, double kilosIniciales, double porcentajeHumedad) {
        this.idLote = idLote;
        this.productor = productor;
        this.viaje = viaje;
        this.porcentajeHumedad = porcentajeHumedad;
        this.fechaHoraIngreso = LocalDateTime.now(); 
        
       

        this.historialEtapas = new ArrayList<>();
        
        
        EtapaProceso etapaIngreso = new EtapaProceso(Etapa.INGRESO, kilosIniciales);
        this.historialEtapas.add(etapaIngreso);
    }

    

    public EtapaProceso getEtapaActual() {
        if (historialEtapas.isEmpty()) {
            return null;
        }
        return historialEtapas.get(historialEtapas.size() - 1);
    }

   

        public double getPesoActual() {
        EtapaProceso actual = getEtapaActual();
        return (actual != null) ? actual.getPesoSalida() : 0.0;
    }

    
        public void avanzarNuevaEtapa(Etapa nuevaEtapa) {
        double pesoEntradaSiguiente = this.getPesoActual();
        
        

        EtapaProceso nuevaFase = new EtapaProceso(nuevaEtapa, pesoEntradaSiguiente);
        this.historialEtapas.add(nuevaFase);
    }

    

        public double calcularCostoFlete() {
        return this.viaje.calcularCostoTransporte();
    }

    

    @Override
    public String obtenerResumen() {
        return "Ficha del Lote N° " + this.idLote + 
               " Productor: " + this.productor.getnombre() + 
               " Humedad: " + this.porcentajeHumedad + "%" +
               " Estado Actual: " + this.getEtapaActual().getTipoEtapa() +
               " Peso Remanente: " + this.getPesoActual() + " kg";
    }

    


    public int getIdLote() {
        return idLote;
    }

    public double getPorcentajeHumedad() {
        return porcentajeHumedad;
    }

    public LocalDateTime getFechaHoraIngreso() {
        return fechaHoraIngreso;
    }

    public Productor getProductor() {
        return productor;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public List<EtapaProceso> getHistorialEtapas() {
        return historialEtapas;
    }
    
   }
