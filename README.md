# Navium BFF Sucursal

API Gateway / Backend-for-Frontend para la sucursal de Navium.

## Descripción

Este proyecto es un servicio Spring Boot que expone un conjunto de endpoints REST bajo `/api/bff` y actúa como un agregador de servicios externos mediante OpenFeign.

Incluye:
- Spring Boot 4.0.6
- Java 21
- Spring Cloud OpenFeign
- Spring Security
- Librería de seguridad `navium-security-lib`
- Construcción con Maven

## Estructura del proyecto

- `src/main/java/com/navium/bff_sucursal` - código principal
- `src/main/java/com/navium/bff_sucursal/controller` - controlador REST
- `src/main/java/com/navium/bff_sucursal/client` - clientes Feign para servicios externos
- `src/main/java/com/navium/bff_sucursal/dto` - DTOs de entrada/salida
- `src/main/java/com/navium/bff_sucursal/config` - configuración de Spring y seguridad
- `src/main/resources/application.properties` - propiedades de aplicación

## Requisitos

- JDK 21
- Maven (o usar los wrappers incluidos)

## Ejecución

En Windows:

```powershell
mvnw.cmd spring-boot:run
```

En Linux/macOS:

```bash
./mvnw spring-boot:run
```

También se puede compilar con:

```bash
./mvnw clean package
```

## Configuración

Propiedades principales definidas en `src/main/resources/application.properties`:

- `spring.application.name=bff-sucursal`
- `server.port=8082`
- `spring.mvc.cors.enabled=true`
- `jwt.secret` - clave secreta para JWT
- `spring.main.allow-bean-definition-overriding=true`

## Endpoints disponibles

Base path: `http://localhost:8082/api/bff`

### Agendamientos
- `GET /agendamientos` - listar todos los agendamientos
- `GET /consulta-rapida/{patente}` - buscar agendamientos por patente
- `GET /agendamiento/{id}` - obtener agendamiento por ID
- `GET /agendamiento/estado/{estado}` - listar agendamientos por estado
- `POST /agendamiento` - crear un nuevo agendamiento
- `GET /agendamiento/rut/{rut}` - buscar agendamientos por RUT del chofer
- `PUT /agendamiento/{id}/cancelar` - cancelar agendamiento
- `GET /agendamientos/fechas?inicio={fecha}&fin={fecha}` - listar por rango de fechas
- `GET /agendamiento/consulta?id={id}&patente={patente}&momento={momento}` - consulta flexible de agendamiento

### Usuario
- `GET /usuario/{id}` - obtener usuario por ID

### Andenes
- `GET /andenes` - listar andenes
- `GET /andenes/{id}` - obtener andén por ID
- `GET /andenes/disponibles` - listar andenes disponibles
- `GET /andenes/ocupados` - listar andenes ocupados
- `GET /andenes/zona/{zona}` - listar andenes por zona
- `GET /andenes/codigo/{codigo}` - obtener andén por código

### Contenedores
- `GET /contenedores` - listar contenedores
- `GET /contenedor/{id}` - obtener contenedor por ID
- `GET /contenedores/patio` - listar contenedores en patio

### Consultas combinadas
- `GET /consulta-completa/{patente}` - devuelve información de agendamiento y contenedor relacionada con la patente

## Notas importantes

- El proyecto usa el paquete `com.navium.bff_sucursal` porque el nombre con guión (`com.navium.bff-sucursal`) no es válido en Java.
- El origen CORS permitido en el controlador es `http:localhost:5173`.

## Referencias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
- [Maven](https://maven.apache.org/)

## Tests

Para ejecutar pruebas unitarias:

```bash
./mvnw test
```
