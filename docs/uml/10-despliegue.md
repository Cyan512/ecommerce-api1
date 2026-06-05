\subsubsection{Despliegue y Configuración}

\begin{enumerate}[label=\alph*)]

\item \textbf{Objetivo}

Configurar el entorno de desarrollo y despliegue de la aplicación, incluyendo la base de datos PostgreSQL, las variables de entorno necesarias y la integración con servicios externos como Cloudinary y Google OAuth2.

\item \textbf{Limitaciones}

La aplicación se desplegó únicamente en entorno local (localhost) para fines de desarrollo y pruebas. No se implementó un despliegue en producción.

\item \textbf{Planificación}

Esta actividad se realizó al inicio del proyecto (configuración inicial) y al final (configuración de servicios externos), con una duración total de 2 días hábiles.

\item \textbf{Justificación}

Una correcta configuración del entorno es fundamental para el desarrollo eficiente. Centralizar las variables de configuración en un archivo \texttt{.env} facilita la reproducibilidad del entorno y evita la exposición de credenciales en el código fuente.

\item \textbf{Descripción}

Se realizaron las siguientes configuraciones:

\textbf{Entorno de desarrollo:}
\begin{itemize}
  \item \textbf{Sistema operativo:} Ubuntu 24.04 LTS
  \item \textbf{Java:} OpenJDK 21
  \item \textbf{Maven:} 3.9.16 para gestión de dependencias y construcción
  \item \textbf{PostgreSQL:} 18.4, base de datos \texttt{ecommerce}
  \item \textbf{Puerto:} 8080 (configurable via \texttt{SERVER\_PORT})
\end{itemize}

\textbf{Archivo .env:}
Se creó un archivo \texttt{.env} en la raíz del proyecto que centraliza todas las variables de configuración:
\begin{itemize}
  \item \textbf{Base de datos:} \texttt{DB\_HOST}, \texttt{DB\_PORT}, \texttt{DB\_NAME}, \texttt{DB\_USER}, \texttt{DB\_PASSWORD}
  \item \textbf{JWT:} \texttt{JWT\_SECRET} (clave Base64 de 256 bits), \texttt{JWT\_EXPIRATION} (24 horas)
  \item \textbf{OAuth2 Google:} \texttt{OAUTH2\_CLIENT\_ID}, \texttt{OAUTH2\_CLIENT\_SECRET}
  \item \textbf{Cloudinary:} \texttt{CLOUDINARY\_CLOUD\_NAME}, \texttt{CLOUDINARY\_API\_KEY}, \texttt{CLOUDINARY\_API\_SECRET}
  \item \textbf{Admin por defecto:} \texttt{DEFAULT\_ADMIN\_EMAIL}, \texttt{DEFAULT\_ADMIN\_PASSWORD}, \texttt{DEFAULT\_ADMIN\_NAME}
\end{itemize}

\textbf{Carga del .env:}
Se implementó la clase \texttt{EnvConfig} que utiliza \texttt{PropertySourcesPlaceholderConfigurer} para cargar el archivo \texttt{.env} al iniciar la aplicación, permitiendo ejecutar el JAR sin necesidad de pasar variables de entorno en el comando.

\textbf{Ejecución:}
\begin{verbatim}
# Construir
./mvnw clean package -DskipTests

# Ejecutar (carga .env automáticamente)
java -jar target/ecommerce-api-0.0.1-SNAPSHOT.jar

# O con variables de entorno explícitas
DB_PASSWORD=123456789 java -jar target/ecommerce-api-0.0.1-SNAPSHOT.jar
\end{verbatim}

\textbf{Servicios externos:}
\begin{itemize}
  \item \textbf{Google Cloud Console:} Se registró la URI de redirección \texttt{http://localhost:8080/login/oauth2/code/google}.
  \item \textbf{Cloudinary:} Se configuraron las credenciales de la cuenta para la subida de imágenes.
\end{itemize}

\textbf{Nota:} Para cambiar la contraseña del admin por defecto, solo es necesario modificar las variables \texttt{DEFAULT\_ADMIN\_*} en el archivo \texttt{.env} y reiniciar la aplicación (la cuenta se crea solo si la base de datos está vacía).

\item \textbf{Supervisor responsable}

Ingeniero Eloy Edison De la Sota Carazas.

\end{enumerate}
