/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.modelos;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditorias")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Long idAuditoria;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_lote", nullable = false)
    private Lote lote;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "etapa_procesada", nullable = false)
    private Etapa etapaProcesada;
    
    @Column(name = "peso_anterior", nullable = false)
    private double pesoAnterior;
    
    @Column(name = "peso_posterior", nullable = false)
    private double pesoPosterior;
    
    @Column(name = "humedad_ingreso_fija", nullable = false)
    private double humedadIngresoFija;
    
    public Auditoria() {
    }
    
    public Auditoria(Usuario usuario, Lote lote, Etapa etapaProcesada, 
                     double pesoAnterior, double pesoPosterior) {
        this.fechaHora = LocalDateTime.now();
        this.usuario = usuario;
        this.lote = lote;
        this.etapaProcesada = etapaProcesada;
        this.pesoAnterior = pesoAnterior;
        this.pesoPosterior = pesoPosterior;
        
        this.humedadIngresoFija = lote.getPorcentajeHumedad();
    }
    

    public String obtenerDetalleAuditoria() {
        return "[AUDITORÍA] Fecha/Hora: " + this.fechaHora
                + " | Usuario: " + this.usuario.getNombreUsuario()
                + " | Lote N°: " + this.lote.getIdLote()
                + " | Etapa: " + this.etapaProcesada
                + " | Peso Anterior: " + this.pesoAnterior + " kg"
                + " | Peso Posterior: " + this.pesoPosterior + " kg"
                + " | Humedad Inicial (Fija): " + this.humedadIngresoFija + "%";
    }
    
    public Long getIdAuditoria() {
        return idAuditoria;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Etapa getEtapaProcesada() {
        return etapaProcesada;
    }

    public void setEtapaProcesada(Etapa etapaProcesada) {
        this.etapaProcesada = etapaProcesada;
    }

    public double getPesoAnterior() {
        return pesoAnterior;
    }

    public void setPesoAnterior(double pesoAnterior) {
        this.pesoAnterior = pesoAnterior;
    }

    public double getPesoPosterior() {
        return pesoPosterior;
    }

    public void setPesoPosterior(double pesoPosterior) {
        this.pesoPosterior = pesoPosterior;
    }

    public double getHumedadIngresoFija() {
        return humedadIngresoFija;
    }

    public void setHumedadIngresoFija(double humedadIngresoFija) {
        this.humedadIngresoFija = humedadIngresoFija;
    }
}