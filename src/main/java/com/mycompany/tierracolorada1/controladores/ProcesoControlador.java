/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.controladores;

import com.mycompany.tierracolorada1.modelos.Lote;
import com.mycompany.tierracolorada1.modelos.EtapaProceso; 
import com.mycompany.tierracolorada1.modelos.Etapa;
import java.util.List;

public class ProcesoControlador {

    public void iniciarNuevaEtapa(Lote loteActual, Etapa tipoEtapa) {
    
        loteActual.avanzarNuevaEtapa(tipoEtapa);
    }

    public void registrarMermaEnEtapaActual(Lote loteActual, double kilosMerma) {
    
        EtapaProceso etapaActiva = loteActual.getEtapaActual();
        
        if (etapaActiva == null) {
            throw new IllegalStateException("El lote no tiene ninguna etapa de procesamiento iniciada.");
        }
     
        etapaActiva.registrarMerma(kilosMerma);
    }

    public double consultarPesoActualEnPlanta(Lote loteActual) {

        return loteActual.getPesoActual();
    }

    public String obtenerReporteTrazabilidad(Lote loteActual) {

        return loteActual.obtenerResumen();
    }

    public List<EtapaProceso> obtenerHistorialCompleto(Lote loteActual) {
        return loteActual.getHistorialEtapas();
    }
}
