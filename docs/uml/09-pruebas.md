\subsubsection{Pruebas Funcionales}

\begin{enumerate}[label=\alph*)]

\item \textbf{Objetivo}

Verificar el correcto funcionamiento de todos los endpoints de la API mediante pruebas funcionales, asegurando que los flujos principales y alternativos operen según lo esperado.

\item \textbf{Limitaciones}

Las pruebas se realizaron de forma manual utilizando herramientas de línea de comandos (curl). No se implementaron pruebas automatizadas unitarias ni de integración debido a restricciones de tiempo.

\item \textbf{Planificación}

Esta actividad se realizó de forma continua durante todo el desarrollo, con una sesión de pruebas integral al finalizar cada módulo.

\item \textbf{Justificación}

Las pruebas funcionales permiten detectar errores tempranamente y garantizar que la API cumple con los requisitos especificados. Probar cada endpoint después de su implementación reduce el riesgo de bugs acumulados.

\item \textbf{Descripción}

Se realizaron pruebas manuales de todos los endpoints utilizando curl, verificando los siguientes escenarios:

\textbf{Autenticación:}
\begin{itemize}
  \item Registro de nuevo usuario → 201 Created con JWT
  \item Registro con email duplicado → 409 Conflict
  \item Login con credenciales correctas → 200 OK con JWT
  \item Login con credenciales incorrectas → 400 Bad Request
  \item Login de usuario bloqueado → 400 Bad Request
\end{itemize}

\textbf{Usuarios y perfil:}
\begin{itemize}
  \item Obtener perfil con token válido → 200 OK
  \item Cambiar contraseña con contraseña actual correcta → 200 OK
  \item Cambiar contraseña con contraseña actual incorrecta → 400 Bad Request
  \item Cambiar nombre → 200 OK con datos actualizados
\end{itemize}

\textbf{Admin usuarios:}
\begin{itemize}
  \item Listar usuarios → 200 OK
  \item Crear usuario con rol específico → 201 Created
  \item Bloquear usuario → 200 OK (activo = false)
  \item Desbloquear usuario → 200 OK (activo = true)
  \item Eliminar usuario sin pedidos → 204 No Content
  \item Eliminar admin por defecto → 409 Conflict
  \item Eliminar usuario con pedidos → 409 Conflict
\end{itemize}

\textbf{Productos y categorías:}
\begin{itemize}
  \item CRUD completo de categorías (admin)
  \item CRUD completo de productos (admin)
  \item Listar productos públicos → solo productos activos
  \item Filtrar productos por categoría
\end{itemize}

\textbf{Carrito y pedidos:}
\begin{itemize}
  \item Agregar items al carrito → cálculo correcto de subtotales
  \item Crear pedido → reducción de stock
  \item Aplicar cupón de descuento → cálculo correcto del descuento
  \item Cambiar estado de pedido (admin)
  \item Listar pedidos del cliente
  \item Listar todos los pedidos (admin) con email del cliente
\end{itemize}

\textbf{Reseñas:}
\begin{itemize}
  \item Crear reseña → 200 OK
  \item Reseña duplicada → 409 Conflict
  \item Eliminar reseña propia → 204 No Content
\end{itemize}

\textbf{Imágenes:}
\begin{itemize}
  \item Subir imagen a Cloudinary → URL almacenada en producto
\end{itemize}

\textbf{Wishlist:}
\begin{itemize}
  \item Agregar producto a wishlist
  \item Eliminar producto de wishlist
  \item Ver wishlist
\end{itemize}

\textbf{Direcciones:}
\begin{itemize}
  \item CRUD de direcciones del usuario autenticado
\end{itemize}

Todas las pruebas fueron exitosas, confirmando que la API funciona correctamente en todos los escenarios contemplados.

\item \textbf{Supervisor responsable}

Ingeniero Eloy Edison De la Sota Carazas.

\end{enumerate}
