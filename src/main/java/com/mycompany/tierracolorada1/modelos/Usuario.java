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
@Table(name = "Usuarios")
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
    
    public Usuario(String nombreUsuario, String nombre, String rol, String contrasenia) {
    this.nombreUsuario = nombreUsuario;
    this.nombreCompleto = nombre;
    this.rol = rol;
    this.contrasenia = contrasenia;
}

public boolean verificarContrasenia(String contrasenia) {
    return this.contrasenia.equals(contrasenia);
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
    if (nombreUsuario == null || nombreUsuario.trim().length() < 4) {
        throw new IllegalArgumentException("El nombre de usuario debe tener al menos 4 caracteres válidos (sin espacios vacíos).");
    }
    // Evita espacios intermedios o caracteres que rompan la consola
    if (!nombreUsuario.matches("^[a-zA-Z0-9_]+$")) {
        throw new IllegalArgumentException("El nombre de usuario solo puede contener letras, números y guiones bajos (_).");
    }
    this.nombreUsuario = nombreUsuario.trim();
}

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}

