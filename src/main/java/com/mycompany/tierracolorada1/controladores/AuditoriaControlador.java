/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.controladores;

// Importamos tus modelos exactos
import com.mycompany.tierracolorada1.modelos.Usuario;
import com.mycompany.tierracolorada1.modelos.Auditoria;
import com.mycompany.tierracolorada1.modelos.Lote;

public class AuditoriaControlador {

    private Usuario usuarioAutenticado;
    private int contadorIdAuditoria = 1; 

    public boolean iniciarSesion(String nombreUsuario, String contrasenia) {

        Usuario adminSistema = new Usuario("admin123", "Juan Pérez", "Supervisor_Planta", "yerba2026");


        if (adminSistema.getNombreUsuario().equals(nombreUsuario) && adminSistema.verificarContrasenia(contrasenia)) {
            this.usuarioAutenticado = adminSistema;
            return true;
        }
        
        return false;
    }

    public Auditoria registrarAuditoriaDeCambio(Lote loteModificado, double valorAnterior, double valorNuevo) {
        if (this.usuarioAutenticado == null) {
            throw new SecurityException("Acceso denegado: No existe un usuario autenticado para auditar esta acción.");
        }

        Auditoria nuevaAuditoria = new Auditoria(
            this.contadorIdAuditoria++, 
            loteModificado.getIdLote(),
            this.usuarioAutenticado, 
            valorAnterior, 
            valorNuevo
        );

        return nuevaAuditoria;
    }


    public Usuario getUsuarioAutenticado() {
        return this.usuarioAutenticado;
    }
}
