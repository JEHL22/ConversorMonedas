package com.conversor;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Conversor {

    private static final List<String> monedasPermitidas = List.of("PEN", "USD", "BOB", "BRL", "ARS");

    public static void mostrarMonedasDisponibles(){
        System.out.println("Monedas Disponibles:");
        for (String moneda : monedasPermitidas) {
            System.out.println("- " + moneda);
        }
    }

    public static boolean esMonedaValida(String moneda) {
        return monedasPermitidas.contains(moneda);
    }

    public static double convertir(double cantidad, String monedaOrigen, String monedaDestino, JsonObject tasas) {
        double tasaOrigen = tasas.get(monedaOrigen).getAsDouble();
        double tasaDestino = tasas.get(monedaDestino).getAsDouble();
        return (cantidad / tasaOrigen) * tasaDestino;
    }
    // este metodo filtra las tasas de cambio para incluir solo las monedas permitidas
    public static Map<String, Double>obtenerTasasFiltradas(JsonObject json) {
        Map<String, Double> tasasFiltradas = new HashMap<>();
        for (String moneda : monedasPermitidas) {
            if (json.has(moneda)) {
                tasasFiltradas.put(moneda, json.get(moneda).getAsDouble());
            }
        }
        return tasasFiltradas;
    }
}
