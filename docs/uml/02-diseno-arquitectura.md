\subsubsection{Diseño de Arquitectura}

\begin{enumerate}[label=\alph*)]

\item \textbf{Objetivo}

Definir la arquitectura del sistema, estableciendo la estructura de capas, la separación de responsabilidades y las tecnologías a utilizar.

\item \textbf{Limitaciones}

El proyecto debía implementarse con Spring Boot y Java 21, sin uso de Lombok, y cumplir con una arquitectura hexagonal combinada con vertical slicing.

\item \textbf{Planificación}

Esta actividad se realizó de forma paralela al análisis de requisitos, con una duración de 2 días hábiles.

\item \textbf{Justificación}

Una arquitectura bien definida desde el inicio garantiza que el sistema sea mantenible, escalable y fácil de extender. La combinación de hexagonal (ports \& adapters) con vertical slicing permite aislar cada funcionalidad de negocio en módulos independientes.

\item \textbf{Descripción}

Se diseñó una arquitectura hexagonal (Ports \& Adapters) combinada con Vertical Slicing. Cada funcionalidad de negocio es un slice independiente con sus propias capas de dominio, aplicación e infraestructura.

\textbf{Tecnologías seleccionadas:}

\begin{itemize}
  \item Java 21 — Lenguaje de programación
  \item Spring Boot 4.0.6 — Framework principal
  \item Spring MVC — API REST
  \item Spring Data JPA + Hibernate — ORM
  \item PostgreSQL — Base de datos
  \item Spring Security OAuth2 Client — Autenticación Google
  \item JJWT 0.12.6 — Generación y validación de JWT
  \item Cloudinary — Almacenamiento de imágenes
  \item Springdoc OpenAPI 3.0.2 — Documentación Swagger
\end{itemize}

\textbf{Estructura de paquetes:}

\begin{verbatim}
com.ecommerce
├── config/                  ← Configuración global
├── exception/               ← Manejador global de errores
├── auth/                    ← Autenticación (JWT, OAuth2)
├── user/                    ← Usuarios y direcciones
├── category/                ← Categorías
├── product/                 ← Productos, reseñas, Cloudinary
├── cart/                    ← Carrito y wishlist
└── order/                   ← Pedidos, pagos, envíos, cupones
\end{verbatim}

\textbf{Capas por slice:}

\begin{itemize}
  \item \textbf{domain/model/} — Entidades puras del dominio, sin dependencias externas
  \item \textbf{domain/port/} — Interfaces de puerto (repositorios)
  \item \textbf{application/usecase/} — Casos de uso con lógica de negocio
  \item \textbf{application/dto/} — Objetos de transferencia de datos
  \item \textbf{infrastructure/persistence/} — Entidades JPA y adaptadores de repositorio
  \item \textbf{infrastructure/web/} — Controladores REST
\end{itemize}

El diagrama de componentes se muestra en el archivo \texttt{diagrams/arquitectura.puml}.

\item \textbf{Supervisor responsable}

Ingeniero Eloy Edison De la Sota Carazas.

\end{enumerate}
