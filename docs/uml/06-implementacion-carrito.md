\subsubsection{Implementación: Carrito, Wishlist y Pedidos}

\begin{enumerate}[label=\alph*)]

\item \textbf{Objetivo}

Implementar los módulos de carrito de compras, lista de deseos (wishlist) y gestión de pedidos con soporte para cupones de descuento.

\item \textbf{Limitaciones}

El carrito y la wishlist debían crearse de forma automática (lazy creation) la primera vez que el usuario los consulta. Los pedidos debían reducir el stock de los productos automáticamente y soportar cupones de descuento.

\item \textbf{Planificación}

Esta actividad se realizó durante la tercera semana de desarrollo, con una duración de 4 días hábiles.

\item \textbf{Justificación}

El carrito de compras y los pedidos son funcionalidades esenciales para cualquier sistema de comercio electrónico. Los cupones de descuento permiten implementar campañas promocionales.

\item \textbf{Descripción}

Se implementaron los siguientes componentes:

\textbf{Carrito de compras:}
\begin{itemize}
  \item \texttt{GET /api/cart} — Obtener el carrito del usuario (se crea automáticamente si no existe).
  \item \texttt{POST /api/cart/items} — Agregar un producto al carrito.
  \item \texttt{DELETE /api/cart/items/\{id\}} — Eliminar un item del carrito.
  \item \texttt{DELETE /api/cart} — Vaciar el carrito por completo.
\end{itemize}

El carrito calcula automáticamente el precio unitario y subtotal de cada item en el backend, evitando manipulaciones del cliente.

\textbf{Wishlist (lista de deseos):}
\begin{itemize}
  \item \texttt{GET /api/wishlist} — Obtener la wishlist del usuario (se crea automáticamente si no existe).
  \item \texttt{POST /api/wishlist/items} — Agregar un producto a la wishlist.
  \item \texttt{DELETE /api/wishlist/items/\{itemId\}} — Eliminar un item de la wishlist.
\end{itemize}

\textbf{Pedidos:}
\begin{itemize}
  \item \texttt{POST /api/orders} — Crear un pedido. Reduce el stock de los productos, calcula el descuento si se aplica un cupón y registra los items del pedido.
  \item \texttt{GET /api/orders} — Obtener los pedidos del usuario autenticado.
  \item \texttt{GET /api/orders/\{id\}} — Obtener detalle de un pedido.
\end{itemize}

\textbf{Admin pedidos:}
\begin{itemize}
  \item \texttt{GET /api/admin/orders} — Listar todos los pedidos del sistema (incluye el email del cliente).
  \item \texttt{PUT /api/admin/orders/\{id\}/status} — Cambiar el estado de un pedido (PENDIENTE, CONFIRMADO, ENVIADO, ENTREGADO, CANCELADO).
\end{itemize}

\textbf{Cupones:}
\begin{itemize}
  \item \texttt{GET /api/coupons/\{codigo\}} — Consultar un cupón por código.
\end{itemize}
Los cupones soportan descuentos porcentuales o montos fijos, con configuraciones de monto mínimo, usos máximos y fecha de expiración.

\item \textbf{Supervisor responsable}

Ingeniero Eloy Edison De la Sota Carazas.

\end{enumerate}
