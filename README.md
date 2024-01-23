# Mercantil API - Documentación

## Descripción

Mercantil App es una aplicación Java Spring que gestiona facturas, vehículos y cargas de gasolina. Utiliza MySQL como base de datos y tiene una interfaz de cliente construida con Swing.

## Estructura del Proyecto

- **Entity:** Clases de entidad para las tablas de la base de datos.
- **Repository:** Interfaces de repositorio para interactuar con la base de datos.
- **Service:** Clases de servicio que manejan la lógica de negocio.
- **Controller:** Clases controladoras que gestionan las solicitudes HTTP.

## Endpoints de la API REST

- **Facturas:**
  - Obtener todas: `GET /facturas`
  - Obtener por ID: `GET /facturas/{id}`
  - Crear nueva: `POST /facturas`

- **Facturas de Vehículos:**
  - Obtener todas: `GET /facturas-vehiculo`
  - Obtener por ID: `GET /facturas-vehiculo/{id}`
  - Crear nueva: `POST /facturas-vehiculo`

- **Facturas de Otro Tipo:**
  - Obtener todas: `GET /facturas-og`
  - Obtener por ID: `GET /facturas-og/{id}`
  - Crear nueva: `POST /facturas-og`

- **Cargas de Gasolina:**
  - Obtener todas: `GET /gasolina-v`
  - Obtener por ID: `GET /gasolina-v/{id}`
  - Crear nueva: `POST /gasolina-v`

## Cliente (Swing)

La interfaz gráfica permite visualizar y filtrar facturas, facturas de vehículos, facturas de otro tipo y cargas de gasolina.

## Configuración de la Base de Datos

Configura la base de datos en `application.properties`.

## Ejecución del Proyecto

1. Clona el repositorio.
2. Configura la base de datos.
3. Ejecuta la aplicación Spring Boot.
4. Ejecuta la aplicación cliente.

## Notas Importantes

- Requiere conexión a Internet para acceder a la API REST.
- La aplicación cliente se conecta a la API para obtener y enviar datos.
