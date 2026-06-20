/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.controladores;

import com.mycompany.tierracolorada1.modelos.Usuario;
import com.mycompany.tierracolorada1.modelos.Auditoria;
import com.mycompany.tierracolorada1.modelos.Lote;
import com.mycompany.tierracolorada1.persistencia.AuditoriaJPAControlador;
import com.mycompany.tierracolorada1.persistencia.UsuarioJPAControlador;

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
            System.out.println("Sesión iniciada para el usuario: " + usuarioBuscado.getNombreUsuario());
            return true;
        }
        
        return false;
    }

    public boolean registrarNuevoUsuario(String nombreUsuario, String nombreCompleto, String rol, String contrasenia) {
    try {
        if (usuarioDao.buscarUsuarioPorNombre(nombreUsuario) != null) {
            System.out.println("El nombre de usuario [" + nombreUsuario + "] ya está registrado.");
            return false;
        }


        Usuario nuevoUsuario = new Usuario(nombreUsuario, nombreCompleto, rol, contrasenia);


        usuarioDao.crear(nuevoUsuario);
        System.out.println("Usuario [" + nombreUsuario + "] registrado con éxito en el sistema.");
        return true;
        
    } catch (Exception e) {
        System.out.println("Error al registrar usuario: " + e.getMessage());
        return false;
    }
}

    public Auditoria registrarAuditoriaDeCambio(Lote loteModificado, double pesoAnterior, double pesoNuevo) {

        if (this.usuarioAutenticado == null) {
            throw new SecurityException("Acceso denegado: No existe un usuario autenticado para auditar esta acción.");
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
            
            System.out.println(nuevaAuditoria.obtenerDetalleAuditoria());
            
        } catch (Exception e) {
            System.out.println("ERROR [AuditoriaControlador]: No se pudo persistir el registro de auditoría. " + e.getMessage());
        }

        return nuevaAuditoria;
    }

    
    public void cerrarSesion() {
        this.usuarioAutenticado = null;
        System.out.println("Sesión cerrada correctamente.");
    }

    public Usuario getUsuarioAutenticado() {
        return this.usuarioAutenticado;
    }
}
