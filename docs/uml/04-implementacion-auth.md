\subsubsection{Implementación: Autenticación y Usuarios}

\begin{enumerate}[label=\alph*)]

\item \textbf{Objetivo}

Implementar el módulo de autenticación local (email/password) y mediante Google OAuth2, así como la gestión de usuarios, roles y perfiles.

\item \textbf{Limitaciones}

El sistema debía utilizar JWT generado por el backend con la librería JJWT. No se permitió el uso de sesiones server-side ni Spring Session. Las contraseñas debían almacenarse usando BCrypt.

\item \textbf{Planificación}

Esta actividad se realizó durante la primera semana de desarrollo, con una duración de 4 días hábiles.

\item \textbf{Justificación}

La autenticación es la puerta de entrada al sistema. Implementarla correctamente desde el inicio garantiza la seguridad de los datos y permite controlar el acceso a los diferentes recursos según el rol del usuario.

\item \textbf{Descripción}

Se implementaron los siguientes componentes:

\textbf{Autenticación local:}
\begin{itemize}
  \item \texttt{POST /api/auth/register} — Registro de nuevos usuarios como CLIENTE. Valida que el email no esté duplicado, encripta la contraseña con BCrypt y devuelve un JWT.
  \item \texttt{POST /api/auth/login} — Inicio de sesión con email y contraseña. Verifica credenciales, estado del usuario (activo/inactivo) y genera un JWT con los datos del usuario.
\end{itemize}

\textbf{Autenticación Google OAuth2:}
\begin{itemize}
  \item \texttt{GET /oauth2/authorization/google} — Redirección al flujo OAuth2 de Google.
  \item \texttt{POST /api/auth/google} — Login Google para aplicaciones mobile o server-to-server.
\end{itemize}

\textbf{Gestión de usuarios:}
\begin{itemize}
  \item \texttt{GET /api/profile} — Obtener perfil del usuario autenticado.
  \item \texttt{PUT /api/profile/password} — Cambiar contraseña (requiere contraseña actual).
  \item \texttt{PUT /api/profile/nombre} — Cambiar nombre del usuario.
\end{itemize}

\textbf{Admin usuarios:}
\begin{itemize}
  \item \texttt{GET /api/admin/users} — Listar todos los usuarios.
  \item \texttt{POST /api/admin/users} — Crear usuario con rol específico.
  \item \texttt{PUT /api/admin/users/\{id\}/block} — Bloquear usuario.
  \item \texttt{PUT /api/admin/users/\{id\}/unblock} — Desbloquear usuario.
  \item \texttt{DELETE /api/admin/users/\{id\}} — Eliminar usuario (protegido: no se puede eliminar el admin por defecto ni usuarios con pedidos).
\end{itemize}

\textbf{DataInitializer:}
\begin{itemize}
  \item Se implementó un \texttt{ApplicationRunner} que crea un administrador por defecto (\texttt{admin@ecommerce.com} / \texttt{Admin123!}) la primera vez que se inicia la aplicación con la base de datos vacía.
\end{itemize}

\textbf{Seguridad:}
\begin{itemize}
  \item Filtro JWT (\texttt{JwtAuthenticationFilter}) que intercepta todas las peticiones, extrae el token del header \texttt{Authorization: Bearer <token>}, lo valida y establece el contexto de seguridad.
  \item Configuración de roles: \texttt{ROLE\_CLIENT}, \texttt{ROLE\_ADMIN}, \texttt{ROLE\_STAFF}.
  \item Rutas públicas: GET de productos, categorías, reseñas y cupones.
  \item Rutas protegidas por rol: carrito, pedidos, wishlist, admin.
\end{itemize}

El diagrama de secuencia del flujo de login se muestra en el archivo \texttt{diagrams/secuencia-login.puml}.

\item \textbf{Supervisor responsable}

Ingeniero Eloy Edison De la Sota Carazas.

\end{enumerate}
