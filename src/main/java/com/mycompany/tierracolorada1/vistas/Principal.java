/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tierracolorada1.vistas;

import com.mycompany.tierracolorada1.controladores.AuditoriaControlador;
import com.mycompany.tierracolorada1.modelos.*;
import com.mycompany.tierracolorada1.persistencia.*;
import java.util.Scanner;
import java.util.List;

public class Principal {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        AuditoriaControlador auditoriaCtrl = new AuditoriaControlador();
        ProductorJPAControlador productorDao = new ProductorJPAControlador();
        VehiculoJPAControlador vehiculoDao = new VehiculoJPAControlador();
        LoteJPAControlador loteDao = new LoteJPAControlador();
        AuditoriaJPAControlador auditoriaDao = new AuditoriaJPAControlador();
        ViajeJPAControlador viajeDao = new ViajeJPAControlador();

        System.out.println("====== SISTEMA TIERRA COLORADA ======");
        boolean sistemaActivo = true;

        while (sistemaActivo) {
            
            // FASE 1: CONTROL DE ACCESO OBLIGATORIO
            while (auditoriaCtrl.getUsuarioAutenticado() == null && sistemaActivo) {
                System.out.println("\n--- BIENVENIDO: INGRESO AL SISTEMA ---");
                System.out.println("1. Registrar un Nuevo Usuario (Sign Up)");
                System.out.println("2. Iniciar Sesión (Log In)");
                System.out.println("3. Salir del Programa");
                System.out.print("Seleccione una opción: ");
                
                int opcionAcceso = 0;
                try {
                    opcionAcceso = Integer.parseInt(teclado.nextLine());
                } catch (Exception e) {
                    System.out.println("❌ Ingrese un número válido.");
                    continue;
                }

                switch (opcionAcceso) {
                    case 1:
                        System.out.println("\n=== REGISTRO DE NUEVO USUARIO ===");
                        System.out.print("Nombre de Usuario (ID de texto): ");
                        String userReg = teclado.nextLine();
                        System.out.print("Nombre Completo: ");
                        String nombreReg = teclado.nextLine();
                        System.out.print("Rol (ej: Supervisor, Balancero): ");
                        String rolReg = teclado.nextLine();
                        System.out.print("Contraseña: ");
                        String passReg = teclado.nextLine();

                        boolean regOk = auditoriaCtrl.registrarNuevoUsuario(userReg, nombreReg, rolReg, passReg);
                        if (regOk) {
                            System.out.println("✅ ¡Usuario guardado en MySQL! Ya puede iniciar sesión.");
                        } else {
                            System.out.println("❌ No se pudo registrar (el usuario ya existe).");
                        }
                        break;

                    case 2:
                        System.out.println("\n=== INICIO DE SESIÓN EN BASE DE DATOS ===");
                        System.out.print("Usuario: ");
                        String userLog = teclado.nextLine();
                        System.out.print("Contraseña: ");
                        String passLog = teclado.nextLine();

                        if (auditoriaCtrl.iniciarSesion(userLog, passLog)) {
                            System.out.println("\n🔓 ¡Log In Exitoso! Bienvenido/a al sistema.");
                        } else {
                            System.out.println("❌ Credenciales incorrectas o usuario inexistente.");
                        }
                        break;

                    case 3:
                        System.out.println("👋 Saliendo del sistema de prueba.");
                        sistemaActivo = false;
                        break;

                    default:
                        System.out.println("❌ Opción inválida.");
                }
            }

            // FASE 2: MENÚ OPERATIVO DE PLANTA
            if (sistemaActivo) {
                System.out.println("\n--- MENÚ DE OPERACIONES REALES ---");
                System.out.println("3. Registrar un Productor");
                System.out.println("4. Registrar un Vehículo");
                System.out.println("5. Ingresar un Lote y Simular Merma (Auditoría Viva)");
                System.out.println("6. Cerrar Sesión");
                System.out.print("Seleccione una opción: ");

                int opcionOperativa = 0;
                try {
                    opcionOperativa = Integer.parseInt(teclado.nextLine());
                } catch (Exception e) {
                    System.out.println("❌ Ingrese un número válido.");
                    continue;
                }

                switch (opcionOperativa) {
                    case 3:
                        System.out.println("\n=== REGISTRO DE PRODUCTOR ===");
                        System.out.print("CUIT del Productor (ej: 30-12345678-9): ");
                        String cuitProd = teclado.nextLine();
                        System.out.print("Nombre / Razón Social: ");
                        String razonSocial = teclado.nextLine();
                        System.out.print("Teléfono de Contacto: ");
                        String telefono = teclado.nextLine();

                        Productor nuevoProd = new Productor(cuitProd, razonSocial, telefono);
                        productorDao.crear(nuevoProd);
                        System.out.println("✅ Productor guardado en la base de datos.");
                        break;

                    case 4:
                        System.out.println("\n=== REGISTRO DE VEHÍCULO ===");
                        System.out.print("Patente (Clave Primaria): ");
                        String patente = teclado.nextLine();
                        System.out.print("Marca del Vehículo: ");
                        String marca = teclado.nextLine();
                        System.out.print("Modelo del Vehículo: ");
                        String modelo = teclado.nextLine();
                        System.out.print("¿Qué tipo es? (1: Camión Propio / 2: Flete Tercerizado): ");
                        int tipoVehiculo = Integer.parseInt(teclado.nextLine());

                        if (tipoVehiculo == 1) {
                            System.out.print("Ingrese el Costo Fijo Mensual ($): ");
                            double costoFijo = Double.parseDouble(teclado.nextLine());
                            
                            // Instancia exacta respetando tu constructor: (patente, marca, costoFijoMensual, modelo)
                            CamionPropio cp = new CamionPropio(patente, marca, costoFijo, modelo);
                            vehiculoDao.crear(cp);
                        } else {
                            System.out.print("Ingrese el Costo por Kilómetro ($): ");
                            double costoKm = Double.parseDouble(teclado.nextLine());
                            
                            // Instancia exacta respetando tu constructor: (patente, marca, modelo, costoPorKilometro)
                            FleteTercerizado ft = new FleteTercerizado(patente, marca, modelo, costoKm);
                            vehiculoDao.crear(ft);
                        }
                        System.out.println("✅ Vehículo guardado polimórficamente en MySQL.");
                        break;

                    case 5:
                        System.out.println("\n=== CIRCUITO DE LOTE Y AUDITORÍA ===");
                        System.out.print("Kilos de Hoja Verde (Peso Inicial de Entrada): ");
                        double pesoIn = Double.parseDouble(teclado.nextLine());
                        System.out.print("Porcentaje de humedad (ej: 22.0): ");
                        double humedad = Double.parseDouble(teclado.nextLine());
                        System.out.print("CUIT del Productor (Debe existir en la BD): ");
                        String cuitProdLote = teclado.nextLine();
                        System.out.print("Patente del vehículo (Debe existir en la BD): ");
                        String patenteLote = teclado.nextLine();
                        System.out.print("Distancia recorrida por el camión (Km): ");
                        double kms = Double.parseDouble(teclado.nextLine());

                        // Recuperamos las relaciones desde MySQL
                        Productor prodAsociado = productorDao.buscarProductorPorId(cuitProdLote);
                        Vehiculo vehAsociado = vehiculoDao.buscarVehiculoPorPatente(patenteLote);

                        if (prodAsociado == null || vehAsociado == null) {
                            System.out.println("❌ ERROR: El productor o el vehículo no existen en la base de datos.");
                            break;
                        }

                        // Creamos el viaje asociando el vehículo 
                        Viaje viajeReal = new Viaje(vehAsociado, kms); 
                        
                        // 2. 🔥 AGREGAR ESTA LÍNEA: Guardamos el viaje para que MySQL le genere un ID
                        viajeDao.crear(viajeReal); 
                        
                        // Creamos el lote con el orden exacto de tu constructor 
                        Lote loteReal = new Lote(prodAsociado, viajeReal, pesoIn, humedad);
                        loteDao.crear(loteReal); // Guarda el lote y la etapa INGRESO automáticamente por cascada [cite: 31, 33]
                        System.out.println("✅ Lote N° " + loteReal.getIdLote() + " guardado con éxito en etapa de INGRESO.");

                        // Avanzamos el lote a Sapecado usando tus métodos nativos 
                        System.out.println("\n--- Procesando Lote: Avanzando a etapa de SAPECADO ---");
                        loteReal.avanzarNuevaEtapa(Etapa.SAPECADO); // Agrega la etapa a la lista 
                        
                        System.out.print("¿Cuántos kilos pesa el lote AL SALIR del Sapecado? (Peso de Salida): ");
                        double pesoPostSapecado = Double.parseDouble(teclado.nextLine());

                        // Seteamos el peso de salida en la etapa actual (Sapecado)
                        loteReal.getEtapaActual().setPesoSalida(pesoPostSapecado);
                        loteDao.editar(loteReal); // Guardamos la actualización en MySQL [cite: 31]
                        
                        // 🔥 Registramos la auditoría de cambio usando tu controlador de negocio
                        auditoriaCtrl.registrarAuditoriaDeCambio(loteReal, pesoIn, pesoPostSapecado);

                        // Consultamos MySQL para verificar
                        System.out.println("\n🔍 [VERIFICACIÓN] Leyendo rastro de auditoría desde MySQL...");
                        List<Auditoria> listaAuditorias = auditoriaDao.buscarTodasLasAuditorias();
                        if (listaAuditorias != null && !listaAuditorias.isEmpty()) {
                            System.out.println(listaAuditorias.get(0).obtenerDetalleAuditoria());
                        }
                        break;

                    case 6:
                        auditoriaCtrl.cerrarSesion();
                        System.out.println("🔒 Sesión cerrada.");
                        break;

                    default:
                        System.out.println("❌ Opción inválida.");
                }
            }
        }
        teclado.close();
    }
}

    

 


