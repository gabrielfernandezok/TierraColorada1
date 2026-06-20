/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.controladores;

import com.mycompany.tierracolorada1.modelos.*;
import com.mycompany.tierracolorada1.persistencia.*;

public class AuditoriaControlador {

    private Usuario usuarioAutenticado;
    private final AuditoriaJPAControlador auditoriaDao;
    private final UsuarioJPAControlador usuarioDao;

    public AuditoriaControlador() {
        this.auditoriaDao = new AuditoriaJPAControlador();
        this.usuarioDao = new UsuarioJPAControlador();
    }

    public boolean iniciarSesion(String nombreUsuario, String contrasenia) {
        Usuario usuarioBuscado = usuarioDao.buscarUsuarioPorNombre(nombreUsuario);

        if (usuarioBuscado != null && usuarioBuscado.verificarContrasenia(contrasenia)) {
            this.usuarioAutenticado = usuarioBuscado;
            return true;
        }
        return false; 
    }

    public Auditoria registrarAuditoriaDeCambio(Lote loteModificado, double pesoAnterior, double pesoNuevo) {
        if (this.usuarioAutenticado == null) {
            throw new SecurityException("Acceso denegado: No existe un usuario autenticado.");
        }

        Auditoria nuevaAuditoria = new Auditoria(
            this.usuarioAutenticado,
            loteModificado,
            loteModificado.getEtapaActual().getTipoEtapa(),
            pesoAnterior,
            pesoNuevo
        );

        try {
            auditoriaDao.crear(nuevaAuditoria);
        } catch (Exception e) {
            throw new RuntimeException("Error crítico en la base de datos: No se pudo guardar la auditoría.", e);
        }

        return nuevaAuditoria;
    }

    public void cerrarSesion() {
        this.usuarioAutenticado = null;
    }

    public Usuario getUsuarioAutenticado() {
        return this.usuarioAutenticado;
    }
}
