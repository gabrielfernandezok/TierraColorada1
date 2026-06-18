/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tierracolorada1.modelos;
import jakarta.persistence.*;
/**
 * @author ryzen 5
 */
@Entity
@Table(name = "Usuarios-gays")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    
    @Column(nullable = false)
    private String nombreCompleto; 
    
    @Column(nullable = false, unique = true)
    private String nombreUsuario;  
    private String contrasenia;  
    
    @Column(length = 30)
    private String rol;

    public Usuario() {
    }
    
    public Usuario(String nombreUsuario, String nombreCompleto, String rol, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
        this.contrasenia = contrasenia;
    }

    
    public boolean verificarContrasenia(String intentoContrasenia) {
        return this.contrasenia != null && this.contrasenia.equals(intentoContrasenia);
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}

