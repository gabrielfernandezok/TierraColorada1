/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.modelos;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lotes")
public class Lote implements Trazable {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id_lote")
private int idLote;

@Column(name = "porcentaje_humedad", nullable = false)
private double porcentajeHumedad;

@Column(name = "fecha_hora_ingreso", nullable = false)

private LocalDateTime fechaHoraIngreso;

@ManyToOne
@JoinColumn(name = "productor_cuit", nullable = false)
private Productor productor;

@ManyToOne
@JoinColumn(name = "id_viaje")
private Viaje viaje;

@OneToMany(
mappedBy = "lote",
cascade = CascadeType.ALL,
orphanRemoval = true
)
private List<EtapaProceso> historialEtapas;


public Lote() {

this.historialEtapas = new ArrayList<>();

}

public Lote(Productor productor, Viaje viaje, double kilosIniciales, double porcentajeHumedad) {
this.productor = productor;

this.viaje = viaje;

this.porcentajeHumedad = porcentajeHumedad;

this.fechaHoraIngreso = LocalDateTime.now();

this.historialEtapas = new ArrayList<>();

EtapaProceso etapaIngreso =

new EtapaProceso(Etapa.INGRESO, kilosIniciales);

etapaIngreso.setLote(this);

this.historialEtapas.add(etapaIngreso);

}

public void agregarEtapa(EtapaProceso etapa) {

etapa.setLote(this);

historialEtapas.add(etapa);

}

public void eliminarEtapa(EtapaProceso etapa) {

historialEtapas.remove(etapa);

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



EtapaProceso nuevaFase =

new EtapaProceso(nuevaEtapa, pesoEntradaSiguiente);
nuevaFase.setLote(this);

this.historialEtapas.add(nuevaFase);
}



public double calcularCostoFlete() {

return this.viaje.calcularCostoTransporte();

}



@Override
public String obtenerResumen() {
return "Ficha del Lote N° " + this.idLote
+ " Productor: " + this.productor.getnombre()
+ " Humedad: " + this.porcentajeHumedad + "%"
+ " Estado Actual: " + this.getEtapaActual().getTipoEtapa()
+ " Peso Remanente: " + this.getPesoActual() + " kg";
}



public int getIdLote() {
return idLote;
}

public double getPorcentajeHumedad() {
return porcentajeHumedad;
}

public void setPorcentajeHumedad(double porcentajeHumedad) {

this.porcentajeHumedad = porcentajeHumedad;

}

public LocalDateTime getFechaHoraIngreso() {

return fechaHoraIngreso;
}

public Productor getProductor() {
return productor;

}

public void setProductor(Productor productor) {
this.productor = productor;
}

public Viaje getViaje() {
return viaje;
}

public void setViaje(Viaje viaje) {
this.viaje = viaje;
}

public List<EtapaProceso> getHistorialEtapas() {
return historialEtapas;
}

public void setHistorialEtapas(List<EtapaProceso> historialEtapas) {
this.historialEtapas = historialEtapas;
}
}
