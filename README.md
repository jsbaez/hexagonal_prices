# Hexagonal Prices API

Prueba tecnica de arquitectura hexagonal modular con Spring Boot 3 y Java 21.

## Arquitectura
El proyecto sigue los principios de la **Arquitectura Hexagonal Multi-modulo**:
- `prices-core-domain`: Logica pura de negocio y contratos (Puertos).
- `prices-core-app`: Implementacion de Casos de Uso (Servicios).
- `prices-infra-rest`: Adaptador de entrada para el API REST.
- `prices-infra-jpa`: Adaptador de salida para persistencia en H2.
- `prices-app-launcher`: Ensamblador de la aplicacion y configuracion de Spring.
- `prices-test-blackbox`: Pruebas de caja negra con Apache JMeter.

## Requisitos
- Java 21+ (Compatible con Java 25)
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
`GET http://localhost:8080/prices?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1`

## Pruebas de Caja Negra (JMeter)
El modulo `prices-test-blackbox` permite ejecutar los 5 escenarios solicitados externamente.
Para ejecutarlos (con la aplicacion levantada):
```bash
mvn verify -pl prices-test-blackbox
```
Los resultados se encontraran en `prices-test-blackbox/target/jmeter/results`.

