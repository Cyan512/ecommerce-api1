\subsubsection{Implementación: Relaciones en Base de Datos}

\begin{enumerate}[label=\alph*)]

\item \textbf{Objetivo}

Agregar las restricciones de llave foránea (foreign keys) en la base de datos para garantizar la integridad referencial entre las tablas del sistema.

\item \textbf{Limitaciones}

Las entidades JPA utilizan UUID como tipo de dato para las relaciones, sin \texttt{@ManyToOne} directo. Se debía mantener la compatibilidad con el código existente sin modificar los adaptadores.

\item \textbf{Planificación}

Esta actividad se realizó después de la implementación de todos los módulos funcionales, con una duración de 1 día hábil.

\item \textbf{Justificación}

Inicialmente, el modelo de datos no contaba con restricciones FK en la base de datos, lo que permitía que quedaran registros huérfanos (por ejemplo, pedidos sin usuario). Agregar las FK garantiza la integridad de los datos a nivel de base de datos, complementando las validaciones realizadas en la capa de aplicación.

\item \textbf{Descripción}

Se agregaron anotaciones \texttt{@ManyToOne} y \texttt{@OneToOne} en las entidades JPA para que Hibernate genere las restricciones FK automáticamente. Se utilizó la siguiente estrategia:

\begin{itemize}
  \item Los campos UUID existentes (\texttt{usuarioId}, \texttt{productoId}, etc.) se mantuvieron sin cambios para no modificar los adaptadores.
  \item Se agregaron nuevos campos de tipo entidad (\texttt{usuario}, \texttt{producto}, etc.) anotados con \texttt{@ManyToOne(fetch = FetchType.LAZY)} y \texttt{@JoinColumn(insertable = false, updatable = false)}.
  \item Los nuevos campos son de solo lectura (\texttt{insertable=false, updatable=false}) para que Hibernate ignore sus valores en operaciones de escritura.
  \item El parámetro \texttt{fetch = FetchType.LAZY} evita problemas de rendimiento por carga temprana de relaciones.
\end{itemize}

Se crearon 19 restricciones FK en total:

\begin{itemize}
  \item \texttt{direcciones.usuario\_id} → \texttt{users.id}
  \item \texttt{categorias.padre\_id} → \texttt{categorias.id} (autorreferenciada)
  \item \texttt{productos.categoria\_id} → \texttt{categorias.id}
  \item \texttt{resenias.usuario\_id} → \texttt{users.id}
  \item \texttt{resenias.producto\_id} → \texttt{productos.id}
  \item \texttt{carritos.usuario\_id} → \texttt{users.id}
  \item \texttt{carrito\_items.carrito\_id} → \texttt{carritos.id}
  \item \texttt{carrito\_items.producto\_id} → \texttt{productos.id}
  \item \texttt{wishlists.usuario\_id} → \texttt{users.id}
  \item \texttt{wishlist\_items.wishlist\_id} → \texttt{wishlists.id}
  \item \texttt{wishlist\_items.producto\_id} → \texttt{productos.id}
  \item \texttt{pedidos.usuario\_id} → \texttt{users.id}
  \item \texttt{pedidos.direccion\_id} → \texttt{direcciones.id}
  \item \texttt{pedidos.staff\_id} → \texttt{users.id}
  \item \texttt{pedidos.cupon\_id} → \texttt{cupones.id}
  \item \texttt{pedido\_items.pedido\_id} → \texttt{pedidos.id}
  \item \texttt{pedido\_items.producto\_id} → \texttt{productos.id}
  \item \texttt{pagos.pedido\_id} → \texttt{pedidos.id}
  \item \texttt{envios.pedido\_id} → \texttt{pedidos.id}
\end{itemize}

El diagrama entidad-relación completo se muestra en el archivo \texttt{diagrams/entidad-relacion.puml}.

\item \textbf{Supervisor responsable}

Ingeniero Eloy Edison De la Sota Carazas.

\end{enumerate}
