# 💱 Conversor de Monedas

Este proyecto es un conversor de monedas hecho en Java usando Maven, la API [ExchangeRate-API](https://www.exchangerate-api.com/) y la biblioteca Gson.

## 🛠 Tecnologías usadas

- Java 21
- Gson
- ExchangeRate API

## 🚀 ¿Qué hace?

Permite convertir entre diferentes monedas latinoamericanas (y USD), mostrando tasas actualizadas desde una API externa.

## 🧩 Estructura del proyecto

- `ApiService`: se conecta a la API y obtiene las tasas de cambio.
- `Conversor`: maneja la lógica de conversión y filtrado de monedas.
- `ConversorApp`: clase principal, maneja la interacción con el usuario por consola.

## 💡 Monedas soportadas

- PEN - Sol peruano
- USD - Dólar estadounidense
- BOB - Boliviano boliviano
- BRL - Real brasileño
- ARS - Peso argentino

## 🖥️ ¿Cómo usarlo?

1. Clona el repositorio:
   ```
   git clone https://github.com/tuUsuario/ConversorMonedas.git
   ```
2. Abre el proyecto en IntelliJ
3. Ejecuta la clase ConversorApp desde src/main/java/com/conversor/ConversorApp.java
