# Hexagonal Prices API

Prueba tecnica de arquitectura hexagonal modular con Spring Boot 3 y Java 21.

## Arquitectura
El proyecto sigue los principios de la **Arquitectura Hexagonal Multi-modulo**:
- `prices-core-domain`: Logica pura de negocio y contratos (Puertos).
- `prices-core-app`: Implementacion de Casos de Uso (Servicios).
- `prices-infra-rest`: Adaptador de entrada para el API REST.
- `prices-infra-jpa`: Adaptador de salida para persistencia en H2.
- `prices-app-launcher`: Ensamblador de la aplicacion y configuracion de Spring.

## Requisitos
- Java 21+
- Maven 3.9+

## Ejecucion
Para compilar y ejecutar los tests de integracion (los 5 escenarios solicitados):
```bash
mvn clean install
```

Para lanzar la aplicacion:
```bash
mvn spring-boot:run -pl prices-app-launcher
```

El endpoint estara disponible en:
`GET http://localhost:8080/brands/1/products/35455/prices?applicationDate=2020-06-14T10:00:00`

## Notas de Implementacion
- **Arquitectura Hexagonal:** Desacoplamiento total entre dominio, aplicacion e infraestructura.
- **Logica de Prioridad:** Implementada en la capa de aplicacion (`PriceService`) para mayor robustez.
- **REST Semantico:** Rutas basadas en recursos y manejo global de errores con Problem Details (RFC 7807).
- **Optimizacion:** Indices compuestos en base de datos para consultas eficientes.
