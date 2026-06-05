\subsubsection{Diseño de Base de Datos}

\begin{enumerate}[label=\alph*)]

\item \textbf{Objetivo}

Diseñar el modelo de datos del sistema, definiendo las tablas, columnas, tipos de datos, relaciones y restricciones de integridad referencial.

\item \textbf{Limitaciones}

La base de datos debía ser PostgreSQL, con UUID como tipo de dato para las llaves primarias. No se permitió el uso de secuencias autoincrementales.

\item \textbf{Planificación}

Esta actividad se realizó durante la fase de diseño, con una duración de 2 días hábiles.

\item \textbf{Justificación}

Un modelo de datos bien diseñado es fundamental para garantizar la integridad y consistencia de la información. El uso de UUID como PK permite la distribución y escalabilidad horizontal sin conflictos de identidad.

\item \textbf{Descripción}

Se diseñó un modelo de datos relacional con 14 tablas:

\begin{itemize}
  \item \textbf{users} — Almacena los usuarios del sistema con herencia SINGLE\_TABLE. El discriminador \texttt{tipo} define si es CLIENTE, ADMINISTRADOR o VENDEDOR.
  \item \textbf{direcciones} — Direcciones de envío asociadas a usuarios.
  \item \textbf{categorias} — Categorías de productos con jerarquía padre-hijo (autorreferenciada).
  \item \textbf{productos} — Catálogo de productos con precio, stock e imagen.
  \item \textbf{resenias} — Reseñas de productos escritas por usuarios.
  \item \textbf{carritos} — Carrito de compras (uno por usuario).
  \item \textbf{carrito\_items} — Productos agregados al carrito.
  \item \textbf{wishlists} — Lista de deseos (una por usuario).
  \item \textbf{wishlist\_items} — Productos guardados en la wishlist.
  \item \textbf{pedidos} — Pedidos realizados por usuarios.
  \item \textbf{pedido\_items} — Productos incluidos en cada pedido.
  \item \textbf{pagos} — Pagos asociados a pedidos.
  \item \textbf{envios} — Envíos asociados a pedidos.
  \item \textbf{cupones} — Códigos de descuento.
\end{itemize}

Se implementaron 19 restricciones de llave foránea (FK) para garantizar la integridad referencial entre las tablas. Por ejemplo, la tabla \texttt{pedidos} tiene FK a \texttt{users} (usuario que realiza el pedido), \texttt{direcciones} (dirección de envío) y \texttt{cupones} (código de descuento aplicado).

El diagrama entidad-relación se muestra en el archivo \texttt{diagrams/entidad-relacion.puml}.

\item \textbf{Supervisor responsable}

Ingeniero Eloy Edison De la Sota Carazas.

\end{enumerate}
