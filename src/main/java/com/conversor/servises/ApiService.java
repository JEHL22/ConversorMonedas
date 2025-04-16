package com.conversor.servises;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {

    //CONSTANTE PARA URL DE LA API SIN MONEDA
    private static final String API_URL_BASE = "https://v6.exchangerate-api.com/v6/98f44d839be6ee27fb2284a5/latest/";
    //instanciar HttpClient
    private final HttpClient client;

    //constructor, inicializa el cliente
    public ApiService() {
        this.client = HttpClient.newHttpClient();
    }

    //metodo que devuelve el objeto Json con tasas de conversion
    public JsonObject getRates(String basecurrency) throws IOException, InterruptedException {
        //crear la url
        String url = API_URL_BASE + basecurrency;
        //crear el objeto HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        //enviar la peticion y recibir la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //convertir la respuesta a JsonObject
        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        return json;
    }
}
