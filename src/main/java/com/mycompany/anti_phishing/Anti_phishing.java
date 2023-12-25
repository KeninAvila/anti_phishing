/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.anti_phishing;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Anti_phishing {

    public static void main(String[] args) {
        List<String> frasesABuscar = obtenerFrasesABuscar();
        String rutaArchivo = "frase.txt";

        // Variable para almacenar los puntos acumulados
        int puntosAcumulados = 0;

        // Mapa para almacenar el número de ocurrencias por frase
        Map<String, Integer> ocurrenciasPorFrase = new HashMap<>();

        // Explorar el archivo en busca de frases
        puntosAcumulados = explorarArchivo(rutaArchivo, frasesABuscar, puntosAcumulados, ocurrenciasPorFrase);

        // Imprimir estadísticas
        imprimirEstadisticas(ocurrenciasPorFrase);

    }

    private static List<String> obtenerFrasesABuscar() {
        List<String> frasesABuscar = new ArrayList<>();
        // Añade las frases que deseas buscar
        frasesABuscar.add("Verifique cuenta");
        frasesABuscar.add("Confirme su contraseña");
        frasesABuscar.add("Actualizar información");
        frasesABuscar.add("PayPal");
        frasesABuscar.add("Amazon");
        frasesABuscar.add("Urgente");
        frasesABuscar.add("Acción requerida");
        frasesABuscar.add("Contraseña expirada");
        frasesABuscar.add("eBay");
        frasesABuscar.add("Apple");
        frasesABuscar.add("DHL");
        frasesABuscar.add("FedEx");
        frasesABuscar.add("Transferencia bancaria");
        frasesABuscar.add("Impuestos");
        frasesABuscar.add("Tarjeta de crédito");
        frasesABuscar.add("Número de seguridad social");
        frasesABuscar.add("Cuenta suspendida");
        frasesABuscar.add("Cuenta bloqueada");
        frasesABuscar.add("Iniciar sesión");
        frasesABuscar.add("Datos personales");
        frasesABuscar.add("Información de pago");
        frasesABuscar.add("Correo electrónico no solicitado");
        frasesABuscar.add("Oferta limitada");
        frasesABuscar.add("Ganaste");
        frasesABuscar.add("Premio");
        frasesABuscar.add("Lotería");
        frasesABuscar.add("Herencia");
        frasesABuscar.add("Donación");
        frasesABuscar.add("Microsoft");
        frasesABuscar.add("Google");
        // Añade más frases según sea necesario
        return frasesABuscar;
    }

    private static int explorarArchivo(String rutaArchivo, List<String> frasesABuscar, int puntosAcumulados, Map<String, Integer> ocurrenciasPorFrase) {
       int i = 0;
        try {
            File archivo = new File(rutaArchivo);
            Scanner scanner = new Scanner(archivo);

            // Itera sobre cada línea del archivo
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();

                // Comprueba si la línea contiene alguna frase de búsqueda
                for (String frase : frasesABuscar) {
                    if (linea.toLowerCase().contains(frase.toLowerCase())) {
                        // Agrega los puntos asociados a la frase al total acumulado
                        puntosAcumulados += obtenerPuntosPorFrase(frase);

                        // Incrementa el número de ocurrencias para la frase
                        ocurrenciasPorFrase.put(frase, ocurrenciasPorFrase.getOrDefault(frase, 0) + 1);

                        // Imprime la línea del archivo con la frase encontrada
                        if(i == 0){
                          System.out.println("Coincidencia encontrada: " + linea + " (" + frase + ")");
                        }
                        i = 1;
                    }
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        }

        // Retorna los puntos acumulados después de explorar el archivo
        return puntosAcumulados;
    }

    private static int obtenerPuntosPorFrase(String frase) {
        // Definir la asignación de puntos para cada frase
        switch (frase) {
            case "Verificar cuenta":
            case "Confirmar contraseña":
            case "Actualizar información":
            case "PayPal":
            case "Amazon":
                return 3;
            case "Urgente":
            case "Acción requerida":
            case "Contraseña expirada":
            case "eBay":
            case "Apple":
            case "DHL":
            case "FedEx":
            case "Transferencia bancaria":
            case "Impuestos":
            case "Tarjeta de crédito":
            case "Número de seguridad social":
                return 2;
            case "Cuenta suspendida":
            case "Cuenta bloqueada":
            case "Iniciar sesión":
            case "Datos personales":
            case "Información de pago":
            case "Correo electrónico no solicitado":
            case "Oferta limitada":
            case "Ganaste":
            case "Premio":
            case "Lotería":
            case "Herencia":
            case "Donación":
            case "Microsoft":
            case "Google":
                return 1;
            default:
                return 0; // Por defecto, no asigna puntos
        }
    }

private static void imprimirEstadisticas(Map<String, Integer> ocurrenciasPorFrase) {
    System.out.println("\nEstadísticas:");
    for (Map.Entry<String, Integer> entry : ocurrenciasPorFrase.entrySet()) {
        String frase = entry.getKey();
        int ocurrencias = entry.getValue();
        int puntos = obtenerPuntosPorFrase(frase);

        System.out.println("Frase: " + frase + ", Ocurrencias: " + ocurrencias + ", Puntos por ocurrencia: " + puntos);
    }
}
}

