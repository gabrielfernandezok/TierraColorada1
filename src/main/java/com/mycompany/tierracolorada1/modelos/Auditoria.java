/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.modelos;

/**
 *
 * @author ryzen 5
 */


import java.time.LocalDateTime;

public class Auditoria {

    private int idAuditoria;
    private int idLote; 
    private double valorAnterior;
    private double valorNuevo;
    private LocalDateTime fechaHora;
    
    private Usuario usuario;

    public Auditoria(int idAuditoria, int idLote, Usuario usuario, double valorAnterior, double valorNuevo) {
        this.idAuditoria = idAuditoria;
        this.idLote = idLote;
        this.usuario = usuario;
        this.valorAnterior = valorAnterior;
        this.valorNuevo = valorNuevo;
        this.fechaHora = LocalDateTime.now(); 
    }

    public int getIdAuditoria() {
        return idAuditoria;
    }

    public int getIdLote() {
        return idLote;
    }

    public double getValorAnterior() {
        return valorAnterior;
    }

    public double getValorNuevo() {
        return valorNuevo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
