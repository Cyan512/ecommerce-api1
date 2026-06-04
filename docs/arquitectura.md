# Arquitectura del Proyecto — Ecommerce API

## Visión General

API RESTful para e-commerce construida con **Spring Boot 4.0.6** y **Java 21**.  
Arquitectura **Hexagonal (Ports & Adapters)** combinada con **Vertical Slicing** — cada funcionalidad de negocio es un slice independiente con sus propias capas de dominio, aplicación e infraestructura.

---

## Stack Tecnológico

| Tecnología | Versión | Propósito |
|---|---|---|
| Java | 21 | Lenguaje de programación |
| Spring Boot | 4.0.6 | Framework principal |
| Spring MVC | — | Capa REST |
| Spring Data JPA + Hibernate | — | Persistencia y ORM |
| PostgreSQL | — | Base de datos relacional |
| Spring Security OAuth2 Client | — | Autenticación delegada (OAuth2) |
| Springdoc OpenAPI | 3.0.2 | Documentación de API (Swagger UI) |
| Lombok | — | Reducción de boilerplate |
| Maven | 3.9.16 | Build y dependencias |
| Jakarta Validation | — | Validación de datos de entrada |

---

## Estructura de Paquetes

```
com.ecommerce
├── EcommerceApiApplication.java          ← Punto de entrada
│
├── shared/                                ← Código compartido entre slices
│   └── domain/model/                      ← BaseEntity, value objects genéricos
│
├── product/                               ← Vertical slice: Productos
│   ├── domain/                            ← ★ Núcleo de negocio (sin dependencias Spring)
│   │   ├── model/                         ←   Entidades de dominio, value objects
│   │   ├── port/                          ←   Interfaces de puerto (input/output)
│   │   └── service/                       ←   Servicios de dominio (lógica pura)
│   ├── application/                       ← Casos de uso
│   │   ├── usecase/                       ←   Implementación de input ports
│   │   └── dto/                           ←   Objetos de transferencia (request/response)
│   └── infrastructure/                    ← Adaptadores (Spring, JPA, Web)
│       ├── persistence/                   ←   Adaptador JPA (entidad, repositorio, mapper)
│       ├── web/                           ←   Controlador REST
│       └── config/                        ←   Configuración Spring del slice
│
├── category/                              ← Vertical slice: Categorías
│   └── (misma estructura que product)
│
├── user/                                  ← Vertical slice: Usuarios
│   └── (misma estructura)
│
├── cart/                                  ← Vertical slice: Carrito de compras
│   └── (misma estructura)
│
├── order/                                 ← Vertical slice: Órdenes/Pedidos
│   └── (misma estructura)
│
└── auth/                                  ← Vertical slice: Autenticación
    └── (misma estructura)
```

### Patrón por Slice

Cada slice vertical contiene **tres capas** siguiendo el hexágono:

| Capa | Dependencias | Responsabilidad |
|---|---|---|
| **domain/** | Ninguna (POJO puro) | Entidades, value objects, interfaces de puerto, reglas de negocio |
| **application/** | Domain | Orquestación de casos de uso, DTOs de entrada/salida |
| **infrastructure/** | Application | Adaptadores concretos (JPA, REST controllers, beans Spring) |

### Reglas de Dependencia

- **Domain** → sin dependencias externas (ni siquiera Spring)
- **Application** → depende solo de Domain
- **Infrastructure** → depende de Application y Domain

La flecha de dependencia siempre apunta **hacia adentro** del hexágono.

---

## Flujo de una Petición

```
Cliente HTTP
    ↓
[SecurityFilterChain (OAuth2)]
    ↓
DispatcherServlet
    ↓
Controller (infrastructure/web)    ← Adaptador de entrada (REST)
    ↓
UseCase (application/usecase)      ← Puerto de entrada
    ↓
Domain Service (domain/service)    ← Lógica de negocio pura
    ↓
Port interface (domain/port)       ← Puerto de salida
    ↓
RepositoryAdapter (infrastructure/persistence)  ← Adaptador de salida (JPA)
    ↓
PostgreSQL
```

---

## Vertical Slices Definidos

| Slice | Responsabilidad |
|---|---|
| **product** | Catálogo de productos, stock, precios |
| **category** | Categorización y agrupación de productos |
| **user** | Gestión de usuarios, perfiles, direcciones |
| **cart** | Carrito de compras, items, cálculos |
| **order** | Órdenes de compra, estados, historial |
| **auth** | Autenticación OAuth2, registro, login |

---

## Dependencias Principales (pom.xml)

- **spring-boot-starter-webmvc** — API REST con Spring MVC.
- **spring-boot-starter-data-jpa** — ORM con Hibernate + JPA.
- **spring-boot-starter-security-oauth2-client** — Autenticación mediante OAuth2.
- **spring-boot-starter-validation** — Validación con Jakarta Bean Validation.
- **springdoc-openapi-starter-webmvc-ui** — Swagger UI + OpenAPI 3.
- **postgresql** — Driver de PostgreSQL.
- **lombok** — Generación automática de getters, setters, constructores, etc.

---

## Estado del Proyecto

Fase inicial — esqueleto arquitectónico creado con las carpetas de cada slice.  
Pendiente: configuración de base de datos, implementación de entidades, repositorios, servicios, controladores y seguridad.
