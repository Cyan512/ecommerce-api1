# Backend E-Commerce API

## Stack Tecnológico

| Tecnología | Versión |
|---|---|
| Java | 21 (LTS) |
| Spring Boot | 3.5.14 |
| Spring Web | MVC (no reactive) |
| Spring Data JPA | Hibernate + PostgreSQL |
| Spring Security OAuth2 Client | Google, Facebook, GitHub |
| PostgreSQL | 16+ |
| Lombok | Reducción de boilerplate |
| Validation | Jakarta Bean Validation |
| SpringDoc OpenAPI | Swagger UI + OpenAPI 3 |
| JJWT (opcional) | 0.12.x para emisión de JWT propio |

## Dependencias (Maven)

```xml
<dependencies>
    <!-- Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- JPA + PostgreSQL -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Seguridad OAuth 2.0 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-oauth2-client</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Utilidades -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- Documentación -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.8.6</version>
    </dependency>

    <!-- JWT (opcional) -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.12.6</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.12.6</version>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.12.6</version>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

## application.yml

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ecommerce
    username: ${DB_USER:postgres}
    password: ${DB_PASSWORD:postgres}
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect

  security:
    oauth2:
      client:
        registration:
          google:
            client-id: ${GOOGLE_CLIENT_ID}
            client-secret: ${GOOGLE_CLIENT_SECRET}
            scope: openid, email, profile
          facebook:
            client-id: ${FACEBOOK_CLIENT_ID}
            client-secret: ${FACEBOOK_CLIENT_SECRET}
            scope: email, public_profile
          github:
            client-id: ${GITHUB_CLIENT_ID}
            client-secret: ${GITHUB_CLIENT_SECRET}
            scope: user:email, read:user

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    operations-sorter: method
```

## Modelo de Datos (16 tablas)

Ver `docs/uml/diagrama-relacional-bd.puml` para el diagrama completo.

### Tabla: users

| Columna | Tipo | Restricciones |
|---|---|---|
| id | UUID | PK |
| nombre | VARCHAR(100) | NOT NULL |
| email | VARCHAR(150) | UNIQUE, NOT NULL |
| password | VARCHAR(255) | nullable (solo email/password) |
| telefono | VARCHAR(20) | nullable |
| oauth_provider | VARCHAR(50) | nullable |
| oauth_id | VARCHAR(255) | nullable |
| tipo | ENUM('cliente','admin','staff') | NOT NULL |
| fecha_registro | TIMESTAMP | NOT NULL |
| activo | BOOLEAN | DEFAULT true |

UK compuesto: `(oauth_provider, oauth_id)`

### Tablas adicionales

- **direcciones** — FK → users
- **categorias** — independiente
- **productos** — FK → categorias
- **carritos** — FK → users (UK usuario_id)
- **carrito_items** — FK → carritos, productos
- **pedidos** — FK → users, direcciones, staff (users), cupones
- **pedido_items** — FK → pedidos, productos
- **pagos** — FK → pedidos (UK)
- **envios** — FK → pedidos (UK)
- **resenias** — FK → users, productos. UK(usuario_id, producto_id)
- **wishlists** — FK → users
- **wishlist_items** — FK → wishlists, productos. UK(wishlist_id, producto_id)
- **cupones** — UK codigo

## Estructura de paquetes

```
com.ecommerce
├── config/
│   ├── SecurityConfig.java
│   ├── OAuth2LoginSuccessHandler.java
│   ├── JwtConfig.java (opcional)
│   └── OpenApiConfig.java
├── controller/
│   ├── AuthController.java
│   ├── ProductController.java
│   ├── CategoryController.java
│   ├── CartController.java
│   ├── OrderController.java
│   ├── PaymentController.java
│   ├── ReviewController.java
│   ├── WishlistController.java
│   ├── CouponController.java
│   └── AdminController.java
├── dto/
│   ├── request/
│   │   ├── LoginRequest.java
│   │   ├── RegisterRequest.java
│   │   ├── ProductRequest.java
│   │   └── ...
│   └── response/
│       ├── AuthResponse.java
│       ├── ProductResponse.java
│       └── ...
├── model/
│   ├── User.java (abstract)
│   ├── Cliente.java
│   ├── Administrador.java
│   ├── Vendedor.java
│   ├── Producto.java
│   ├── Categoria.java
│   ├── Carrito.java
│   ├── CarritoItem.java
│   ├── Pedido.java
│   ├── PedidoItem.java
│   ├── Pago.java
│   ├── Envio.java
│   ├── Resenia.java
│   ├── Wishlist.java
│   ├── WishlistItem.java
│   └── Cupon.java
├── enums/
│   ├── EstadoPedido.java
│   ├── EstadoPago.java
│   ├── EstadoEnvio.java
│   ├── TipoDescuento.java
│   └── TipoUsuario.java
├── repository/
│   ├── UserRepository.java
│   ├── ProductoRepository.java
│   └── ...
├── service/
│   ├── AuthService.java
│   ├── ProductService.java
│   └── ...
├── exception/
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   └── BusinessException.java
└── EcommerceApplication.java
```

## Roles y Autorización

| Rol | Endpoints acceso |
|---|---|
| `ROLE_CLIENT` | `/api/cart/**`, `/api/orders/**`, `/api/profile/**`, `/api/reviews/**`, `/api/wishlist/**` |
| `ROLE_STAFF` | `/api/admin/orders/**`, `/api/admin/assigned-orders/**` |
| `ROLE_ADMIN` | `/api/admin/**` (full CRUD) |
| Sin autenticar | `/api/products`, `/api/categories`, `/auth/**`, `/oauth2/**`, `/swagger-ui/**` |

## Flujo de Autenticación OAuth 2.0

1. Usuario → Click "Login con Google"
2. Cliente → Redirige a `GET /oauth2/authorization/google`
3. Spring Security → Redirige al proveedor OAuth
4. Usuario → Autoriza la app
5. Proveedor → Callback a `login/oauth2/code/google`
6. `OAuth2LoginSuccessHandler` → Busca/crea usuario en BD
7. Backend → Emite JWT propio
8. Cliente → Almacena JWT y lo envía en cada petición como `Authorization: Bearer <token>`

## API Endpoints Principales

| Método | Endpoint | Auth | Descripción |
|---|---|---|---|
| POST | `/auth/register` | No | Registro email/password |
| POST | `/auth/login` | No | Login email/password |
| GET | `/oauth2/authorization/{provider}` | No | Login OAuth |
| GET | `/api/products` | No | Listar productos |
| GET | `/api/products/{id}` | No | Detalle producto |
| GET | `/api/categories` | No | Listar categorías |
| POST | `/api/cart/items` | CLIENT | Agregar al carrito |
| POST | `/api/orders` | CLIENT | Crear pedido |
| GET | `/api/orders` | CLIENT | Mis pedidos |
| PUT | `/api/admin/products/{id}` | ADMIN | Editar producto |
| GET | `/api/admin/reports` | ADMIN | Reportes |

## Diagramas de Referencia

Todos los diagramas están en `docs/uml/`:

| Archivo | Diagrama |
|---|---|
| `casos-de-uso-actores.puml` | Casos de uso |
| `clases-entidades-relaciones.puml` | Clases |
| `diagrama-relacional-bd.puml` | Base de datos |
| `secuencia-registro-usuario.puml` | Secuencia - Registro |
| `secuencia-login-oauth.puml` | Secuencia - OAuth |
| `secuencia-proceso-compra.puml` | Secuencia - Compra |
| `secuencia-gestion-pedidos-staff.puml` | Secuencia - Staff |
| `actividad-proceso-compra.puml` | Actividad - Compra |
| `actividad-gestion-productos.puml` | Actividad - Productos |
| `estados-pedido.puml` | Estados - Pedido |
| `estados-pago.puml` | Estados - Pago |
