package com.conversor;

import com.conversor.servises.ApiService;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
/**
 * ConversorApp es la clase principal que permite al usuario convertir entre diferentes monedas.
 * Utiliza la clase ApiService para obtener tasas de cambio desde una API externa.
 */
public class ConversorApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ApiService apiService = new ApiService();
        System.out.println("BIENVENIDO AL CONVERSOR DE MONEDAS");

        boolean continuar = true;

        while (continuar){

            Conversor.mostrarMonedasDisponibles();

            System.out.print("\nIngrese la moneda de ORIGEN (ej: USD): ");
            String monedaOrigen = scanner.nextLine().toUpperCase();

            // Validar la moneda de origen
            if (!Conversor.esMonedaValida(monedaOrigen)) {
                System.out.println("Moneda de origen no válida. Inténtelo nuevamente.\n");
                continue;
            }

            System.out.print("\nIngrese la moneda de DESTINO (ej: PEN): ");
            String monedaDestino = scanner.nextLine().toUpperCase();

            // Validar la moneda de destino
            if(!Conversor.esMonedaValida(monedaDestino)){
                System.out.println("Moneda de destino no válida. Inténtelo nuevamente.\n");
                continue;
            }

            System.out.print("Ingrese la cantidad a convertir: ");
            double cantidad;

            // Validar la entrada de cantidad
            try {
                cantidad = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Cantidad inválida. Intente nuevamente.\n");
                continue;
            }

            try {
                // Obtener tasas de cambio desde la API
                JsonObject json = apiService.getRates(monedaOrigen);
                JsonObject tasasJson = json.getAsJsonObject("conversion_rates");

                // Filtrar las monedas permitidas
                Map<String, Double> tasas = Conversor.obtenerTasasFiltradas(tasasJson);

                // Realizar conversión
                double resultado = Conversor.convertir(cantidad, monedaOrigen, monedaDestino, tasasJson);

                System.out.printf("%.2f %s equivale a %.2f %s%n", cantidad, monedaOrigen, resultado, monedaDestino);
            }catch (IOException | InterruptedException e) {
                System.out.println("Error al obtener tasas de cambio. Inténtelo nuevamente más tarde.");
            }

            System.out.print("\n¿Desea realizar otra conversión? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (!respuesta.equals("s")) {
                continuar = false;
                System.out.println("Gracias por usar el conversor. ¡Hasta pronto! 👋");
            }
        }
        scanner.close();
    }
}