\subsubsection{Implementación: Subida de Imágenes con Cloudinary}

\begin{enumerate}[label=\alph*)]

\item \textbf{Objetivo}

Implementar la funcionalidad de subida de imágenes para los productos, utilizando Cloudinary como servicio de almacenamiento en la nube.

\item \textbf{Limitaciones}

Las imágenes debían estar limitadas a un tamaño máximo de 5 MB. No se permitió el almacenamiento local en el servidor.

\item \textbf{Planificación}

Esta actividad se realizó después de la implementación del catálogo de productos, con una duración de 1 día hábil.

\item \textbf{Justificación}

Las imágenes de producto mejoran significativamente la experiencia de compra. Cloudinary ofrece un servicio confiable de almacenamiento en la nube con entrega optimizada mediante CDN, sin necesidad de gestionar almacenamiento local.

\item \textbf{Descripción}

Se integró Cloudinary como servicio de almacenamiento de imágenes siguiendo los siguientes pasos:

\begin{enumerate}
  \item \textbf{Dependencia:} Se agregó la librería \texttt{cloudinary-http44} versión 1.39.0 al archivo \texttt{pom.xml}.
  \item \textbf{Configuración:} Se creó la clase \texttt{CloudinaryConfig} que expone un bean de Cloudinary configurado con las credenciales (\texttt{cloud\_name}, \texttt{api\_key}, \texttt{api\_secret}) obtenidas desde variables de entorno.
  \item \textbf{Servicio:} Se implementó \texttt{CloudinaryService} con los métodos:
  \begin{itemize}
    \item \texttt{upload(MultipartFile file)} — Sube un archivo a Cloudinary y retorna la URL pública de la imagen.
    \item \texttt{delete(String publicId)} — Elimina una imagen de Cloudinary por su ID público.
  \end{itemize}
  \item \textbf{Endpoint:} Se agregó el endpoint \texttt{POST /api/admin/products/\{id\}/image} que acepta un archivo multipart, lo sube a Cloudinary y guarda la URL resultante en el campo \texttt{imagenUrl} del producto.
  \item \textbf{Configuración multipart:} Se estableció un límite de 5 MB para subida de archivos en \texttt{application.yaml}.
\end{enumerate}

El flujo de subida funciona de la siguiente manera:
\begin{enumerate}
  \item El administrador envía una petición \texttt{POST} con el archivo como \texttt{multipart/form-data}.
  \item El controlador recibe el archivo y lo pasa al servicio \texttt{CloudinaryService}.
  \item \texttt{CloudinaryService} sube el archivo a Cloudinary usando su API HTTP.
  \item Cloudinary devuelve la URL pública de la imagen.
  \item La URL se guarda en el producto mediante \texttt{ProductoUseCase.updateImage()}.
\end{enumerate}

\item \textbf{Supervisor responsable}

Ingeniero Eloy Edison De la Sota Carazas.

\end{enumerate}
