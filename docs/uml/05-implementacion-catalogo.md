\subsubsection{Implementación: Catálogo (Productos, Categorías, Reseñas)}

\begin{enumerate}[label=\alph*)]

\item \textbf{Objetivo}

Implementar los módulos de catálogo de productos, categorías con jerarquía y reseñas de usuarios.

\item \textbf{Limitaciones}

Los productos debían tener borrado lógico (campo \texttt{activo}) en lugar de borrado físico. Las reseñas debían tener una restricción de unicidad por usuario y producto.

\item \textbf{Planificación}

Esta actividad se realizó durante la segunda semana de desarrollo, con una duración de 3 días hábiles.

\item \textbf{Justificación}

El catálogo de productos es el núcleo del sistema de comercio electrónico. Los clientes deben poder navegar los productos, filtrar por categoría y leer reseñas de otros compradores. Los administradores necesitan herramientas para gestionar el inventario.

\item \textbf{Descripción}

Se implementaron los siguientes componentes:

\textbf{Categorías:}
\begin{itemize}
  \item \texttt{GET /api/categories} — Listar todas las categorías.
  \item \texttt{GET /api/categories/\{id\}} — Obtener detalle de una categoría.
  \item \texttt{POST /api/admin/categories} — Crear categoría (admin).
  \item \texttt{PUT /api/admin/categories/\{id\}} — Actualizar categoría (admin).
  \item \texttt{DELETE /api/admin/categories/\{id\}} — Eliminar categoría (admin).
\end{itemize}

Las categorías soportan jerarquía padre-hijo mediante el campo \texttt{padreId}, permitiendo crear subcategorías.

\textbf{Productos:}
\begin{itemize}
  \item \texttt{GET /api/products} — Listar productos activos.
  \item \texttt{GET /api/products/\{id\}} — Obtener detalle de un producto.
  \item \texttt{GET /api/products/by-category/\{categoriaId\}} — Filtrar productos por categoría.
  \item \texttt{POST /api/admin/products} — Crear producto (admin).
  \item \texttt{PUT /api/admin/products/\{id\}} — Actualizar producto (admin).
  \item \texttt{DELETE /api/admin/products/\{id\}} — Desactivar producto (borrado lógico, admin).
\end{itemize}

Los productos incluyen campos como nombre, descripción, precio, stock, categoría y URL de imagen.

\textbf{Reseñas:}
\begin{itemize}
  \item \texttt{GET /api/reviews/product/\{productoId\}} — Obtener reseñas de un producto (público).
  \item \texttt{POST /api/reviews} — Crear reseña (cliente).
  \item \texttt{DELETE /api/reviews/\{id\}} — Eliminar reseña propia (cliente).
\end{itemize}

Las reseñas tienen una calificación del 1 al 5 y un comentario opcional. Se validó que un usuario no pueda reseñar el mismo producto más de una vez.

El diagrama de clases del modelo de dominio se muestra en el archivo \texttt{diagrams/clases-dominio.puml}.

\item \textbf{Supervisor responsable}

Ingeniero Eloy Edison De la Sota Carazas.

\end{enumerate}
