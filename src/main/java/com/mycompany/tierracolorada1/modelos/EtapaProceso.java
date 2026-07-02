/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "etapa_proceso")
public class EtapaProceso implements Trazable {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id_etapa_proceso")
private Long id;

@Enumerated(EnumType.STRING)
@Column(name = "tipo_etapa", nullable = false)
private Etapa tipoEtapa;

@Column(name = "peso_entrada", nullable = false)
private double pesoEntrada;

@Column(nullable = false)
private double merma;

@Column(name = "peso_salida", nullable = false)
private double pesoSalida;


@ManyToOne
@JoinColumn(name = "id_lote", nullable = false)
private Lote lote;

public EtapaProceso() {
}


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

throw new IllegalArgumentException(

"La merma no puede ser un valor negativo.");

}

if (kilosMerma > this.pesoEntrada) {
    throw new IllegalArgumentException("Error operativo: La merma no puede ser mayor al peso de entrada disponible.");
}

this.merma = kilosMerma;

this.pesoSalida = this.pesoEntrada - kilosMerma;

}


@Override

public String obtenerResumen() {
return "PROCESO - Fase: " + this.tipoEtapa
+ " Entrada: " + this.pesoEntrada + " kg"
+ " Merma: " + this.merma + " kg"
+ " Salida Neta: " + this.pesoSalida + " kg";
}

public Long getId() {
return id;
}

public Etapa getTipoEtapa() {
return tipoEtapa;
}

public void setTipoEtapa(Etapa tipoEtapa) {
this.tipoEtapa = tipoEtapa;
}

public double getPesoEntrada() {
return pesoEntrada;

}

public void setPesoEntrada(double pesoEntrada) {
this.pesoEntrada = pesoEntrada;
}

public double getMerma() {
return merma;
}

public void setMerma(double merma) {
this.merma = merma;
}

public double getPesoSalida() {
return pesoSalida;
}

public void setPesoSalida(double pesoSalida) {
this.pesoSalida = pesoSalida;
}

public Lote getLote() {
return lote;
}

public void setLote(Lote lote) {
this.lote = lote;
}
}
