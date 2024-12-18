
# 📘 **Documentación Completa de la Estructura de Carpetas para Arquitectura Hexagonal (Ports & Adapters) en Java Spring Boot**

Esta documentación define la **estructura de carpetas** ideal para una aplicación **Java Spring Boot** basada en la **arquitectura hexagonal (Ports & Adapters)**. Se aplica **DDD (Domain-Driven Design)** y los principios de **SOLID**, con una clara separación entre las capas de **entrada, núcleo y salida**.

El objetivo principal es mantener la lógica de negocio independiente de la infraestructura, facilitando la **escalabilidad**, **mantenibilidad** y **testabilidad** de la aplicación.

---

## 🏗️ **Estructura General de la Aplicación**
```
src
 └── main
     └── java
         └── com.example.miapp
             ├── application
             │    ├── bussiness
             │    │     ├── impl
             │    │     │     ├── ProductImpl.java
             │    │     └── interface
             │    │           ├── ProductBussiness.java
             │    ├── usecase
             │    │     ├── CreateProductUseCaseImpl.java
             │    ├── dto
             │    │     ├── CreateProductDto.java
             │    └── exception
             │          ├── ProductNotFoundException.java
             │
             ├── domain
             │    ├── model
             │    │     ├── Product.java
             │    ├── port
             │    │     ├── inbound
             │    │     │     ├── CreateProductUseCase.java
             │    │     └── outbound
             │    │           ├── ProductRepositoryPort.java
             │    └── exception
             │          ├── ProductNotFoundException.java
             │
             └── infrastructure
                  ├── inadapter
                  │    ├── controller
                  │    │       ├── ProductController.java
                  │    ├── websocket
                  │    │       ├── ProductWebSocketHandler.java
                  │    └── scheduled
                  │          ├── ProductScheduledTask.java
                  │
                  ├── outadapter
                  │    ├── persistence
                  │    │    ├── adapter
                  │    │    │     ├── ProductJpaAdapter.java
                  │    │    ├── repository
                  │    │    │     ├── ProductJpaRepository.java
                  │    │    ├── entity
                  │    │    │     ├── ProductEntity.java
                  │    │    └── mapper
                  │    │          ├── ProductMapper.java
                  │    │
                  │    ├── api
                  │    │    ├── providerA
                  │    │         ├── adapter
                  │    │         │     ├── ProductClientAdapter.java
                  │    │         ├── config
                  │    │         │     ├── RetrofitConfig.java
                  │    │         ├── definition
                  │    │         │     ├── ServiceCallProductApiSoap.java
                  │    │         ├── call
                  │    │         │     ├── CallProductServicesHttp.java
                  ├── configuration
                  │    └── SwaggerConfig.java
                  │
                  │
                  └── shared
                      └── exception
```

---

## 📂 **1. application**
> **Propósito**: Orquestar la lógica de negocio sin lógica técnica.

**Subcarpetas:**
- **usecase**: Contiene los casos de uso que orquestan la lógica de negocio.
- **bussiness**: Contiene los casos de uso y la funcionalidades adicionales para mejor reutilizacion de casos de uso y tema de seguridad.
  - **impl**: Logica de negocio de la aplicación.
  - **interface**:  Definición de interfaces de negocio.
- **dto**: Objetos de transferencia de datos que se utilizan para enviar y recibir información.
- **exception**: Excepciones de la **lógica de aplicación**, como validaciones de entrada o errores de acceso.
**Ejemplos de Excepciones:**
- **InvalidInputException**
- **UnauthorizedAccessException**

**Ejemplo de Estructura:**
```
application
 ├── bussiness
 │    ├── impl
 │    │     ├── ProductImpl.java
 │    └── interface
 │          ├── ProductBussiness.java
 ├── usecase
      ├── CreateProductUseCase.java
 ├── dto
      ├── CreateProductDto.java
 └── exception
      ├── ProductNotFoundException.java
```
---

## 📂 **2. domain**
> **Propósito**: Contiene la **lógica de negocio pura** e independiente de la infraestructura.

**Subcarpetas:**
- **model**: Entidades y objetos de valor que representan el núcleo de la lógica de negocio.
- **port**: 
  - **inbound**: Define los **puertos de entrada**.
  - **outbound**: Define los **puertos de salida**.
- **exception**: Excepciones relacionadas con la **lógica de negocio**.

**Ejemplos de Excepciones:**
- **OrderNotFoundException**
- **InsufficientBalanceException**

**Ejemplo de Estructura:**
```
domain
 ├── model
      ├── Product.java
 ├── port
 │    ├── inbound
            ├── CreateProductUseCase.java
 │    └── outbound
            ├── ProductRepositoryPort.java
 └── exception
      ├── ProductNotFoundException.java
```

---

## 📂 **3. infrastructure**
> **Propósito**: Contiene los adaptadores de entrada y salida, configuración, seguridad y elementos compartidos.

### 📂 **3.1 inadapter**
> **Responsabilidad**: Puntos de entrada al sistema (API REST, eventos, CLI, gRPC, etc.).

**Subcarpetas:**
- **controller**: API REST.
- **websocket**: Maneja la comunicación **en tiempo real**.
- **scheduled**: Define tareas **cron programadas**.

**Ejemplo de Estructura:**
```
inadapter
 ├── controller
      ├── ProductController.java
 ├── websocket
      ├── ProductWebSocketHandler.java
 └── scheduled
      ├── ProductScheduledTask.java
```

---

### 📂 **3.2 outadapter**
> **Responsabilidad**: Adaptadores para persistencia, API externas, colas de mensajes y sistemas de archivos.

**Subcarpetas:**
- **persistence**: Interactúa con la base de datos.
  - **adapter**: Implementa los puertos de salida de persistencia.
  - **repository**: Interfaz JPA para acceso a la base de datos.
  - **entity**: Entidades JPA.
  - **mapper**: Convierte entidades JPA a objetos de dominio.
- **api**: Interactúa con APIs externas.
  - **providerA**: Nombre Proveedor de WS externo.
    - **adapter**: pasar de un Dto externo a un modelo de dominio o DTO de la aplicación.
    - **config**: Configuración de la librería Retrofit para la llamada a la API
    - **definition**: Definición del llamado a la API de entrada y salida.
    - **call**: Ejecución de la api.
- **messaging**: Comunicación con colas de mensajes.
- **file**: Almacenamiento de archivos (S3, Azure Blob).

**Ejemplo de Estructura:**
```
outadapter
 ├── api
      ├── ProductController.java
 ├── persistence
      ├── adapter
            ├── ProductJpaAdapter.java
      ├── repository
            ├── ProductJpaRepository.java
      ├── entity
            ├── ProductEntity.java
      └── mapper
            ├── ProductMapper.java  
 ├── api
      ├── providerA
            ├── adapter
                  ├── ProductClientAdapter.java
            ├── config
                  ├── RetrofitConfig.java
            ├── definition
                  ├── ServiceCallProductApiSoap.java
            ├── call
                  ├── CallProductServicesHttp.java
 ├── messaging
      ├── adapter
            ├── ProductMessageAdapter.java
      ├── producer
            ├── ProductMessageProducer.java
      └── mapper
            ├── ProductMessageMapper.java
 ├── file
      ├── adapter
            ├── ProductFileAdapter.java
      ├── storage
            ├── ProductFileStorage.java
      └── mapper
            ├── ProductFileMapper.java
```

---

## 📂 **3.3 configuration**
> **Responsabilidad**: Configuración general de la aplicación.

**Archivos:**
- **SwaggerConfig.java**: Configura Swagger.

**Ejemplo de Estructura:**
```
configuration
 └── SwaggerConfig.java
 └── LoggingConfig.java
 └── Etc...
```

---

## 🚀 **Conclusión**
- La **arquitectura hexagonal** permite la **separación de la lógica de negocio y la infraestructura**.
- Se definen excepciones personalizadas en **application, domain, infrastructure y shared**.
- La estructura permite la **escalabilidad, reutilización y facilidad de prueba**.