/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.controladores;

import com.mycompany.tierracolorada1.modelos.Lote;
import com.mycompany.tierracolorada1.modelos.Productor;
import com.mycompany.tierracolorada1.modelos.Vehiculo;
import com.mycompany.tierracolorada1.modelos.CamionPropio;
import com.mycompany.tierracolorada1.modelos.FleteTercerizado;
import com.mycompany.tierracolorada1.modelos.Viaje;

public class BalanzaControlador {

    public Lote registrarIngresoCamionPropio(int idLote, String cuitProd, String nombreProd, String nroProductor,
                                             String patente, String marca, String modelo, double costoFijoMensual, 
                                             double distanciaKm, double pesoInicial, double humedadInicial) {
        
        Productor productor = new Productor(cuitProd, nombreProd, nroProductor);
        Vehiculo vehiculo = new CamionPropio(patente, marca, modelo, costoFijoMensual);
        Viaje viaje = new Viaje(vehiculo, distanciaKm);
        
        Lote nuevoLote = new Lote(idLote, productor, viaje, pesoInicial, humedadInicial);
        
        return nuevoLote;
    }

    public Lote registrarIngresoFleteTercerizado(int idLote, String cuitProd, String nombreProd, String nroProductor,
                                                 String patente, String marca, String modelo, double costoPorKilometro, 
                                                 double distanciaKm, double pesoInicial, double humedadInicial) {
        

        Productor productor = new Productor(cuitProd, nombreProd, nroProductor);
        Vehiculo vehiculo = new FleteTercerizado(patente, marca, modelo, costoPorKilometro);
        Viaje viaje = new Viaje(vehiculo, distanciaKm);
        

        Lote nuevoLote = new Lote(idLote, productor, viaje, pesoInicial, humedadInicial);
        
        return nuevoLote;
    }


    public double obtenerCostoFleteTotal(Lote lote) {

        return lote.getViaje().calcularCostoTransporte();
    }
}
