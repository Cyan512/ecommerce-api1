\subsubsection{Análisis de Requisitos}

\begin{enumerate}[label=\alph*)]

\item \textbf{Objetivo}

Definir los requisitos funcionales y no funcionales del sistema de comercio electrónico, identificando los actores involucrados y las funcionalidades que debe soportar la API.

\item \textbf{Limitaciones}

Ninguna.

\item \textbf{Planificación}

Esta actividad fue programada al inicio del proyecto, antes de cualquier tarea de desarrollo, con una duración estimada de 3 días hábiles.

\item \textbf{Justificación}

Es esencial contar con una especificación clara de requisitos para guiar el diseño e implementación del sistema, evitando retrabajos y asegurando que todas las funcionalidades necesarias sean cubiertas.

\item \textbf{Descripción}

Se realizó el levantamiento de requisitos del sistema, identificando los siguientes actores:

\begin{itemize}
  \item \textbf{Cliente} — Usuario registrado que puede navegar productos, gestionar carrito, realizar pedidos, escribir reseñas y administrar su wishlist y direcciones.
  \item \textbf{Administrador} — Usuario con permisos totales para gestionar productos, categorías, pedidos y usuarios del sistema.
  \item \textbf{Vendedor} — Usuario con acceso limitado a pedidos asignados.
  \item \textbf{Google OAuth2} — Servicio externo de autenticación.
\end{itemize}

Se definieron los siguientes módulos funcionales:

\begin{itemize}
  \item \textbf{Auth} — Registro, inicio de sesión local (email/password) y mediante Google OAuth2, generación de tokens JWT.
  \item \textbf{Usuarios} — Gestión de perfiles, cambio de contraseña y nombre, direcciones de envío.
  \item \textbf{Admin Usuarios} — CRUD de usuarios del sistema con roles (Cliente, Administrador, Vendedor), bloqueo y desbloqueo.
  \item \textbf{Categorías} — Catálogo de categorías con jerarquía padre-hijo.
  \item \textbf{Productos} — Catálogo de productos con filtro por categoría, imagen opcional y borrado lógico.
  \item \textbf{Reseñas} — Calificación y comentarios de productos por parte de los clientes.
  \item \textbf{Carrito} — Carrito de compras por usuario con cálculo automático de subtotales.
  \item \textbf{Wishlist} — Lista de deseos por usuario.
  \item \textbf{Pedidos} — Creación de pedidos con descuentos por cupón, control de stock y gestión de estados.
  \item \textbf{Pagos y Envíos} — Registro de pagos y envíos asociados a pedidos.
  \item \textbf{Cupones} — Códigos de descuento con configuración de monto mínimo, usos máximos y porcentaje o monto fijo.
\end{itemize}

Los requisitos no funcionales incluyen: uso de PostgreSQL como base de datos, autenticación mediante JWT, arquitectura hexagonal con vertical slicing, y subida de imágenes a Cloudinary.

El diagrama de casos de uso se muestra en el archivo \texttt{diagrams/casos-de-uso.puml}.

\item \textbf{Supervisor responsable}

Ingeniero Eloy Edison De la Sota Carazas.

\end{enumerate}
