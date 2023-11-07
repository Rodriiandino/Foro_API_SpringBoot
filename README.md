# Foro_API_SpringBoot 🍀
API RESTful - Realizada con Spring Boot 

En el desarrollo de esta api, se trato de usar las mejores practicas, y se enfocó en seguir los principios de arquitectura REST.

Se aplicaron patrones de diseño como el patrón **MVC (Modelo-Vista-Controlador)** para separar la lógica de negocio, la presentación y el control de la aplicación. 

## ✨ Que se aprendio con este proyecto:
- **Desarrollo en Spring Framework**
- **Spring Boot**
- **Spring Security**
- **Seguridad:** Autenticación basada en tokens (JWT) y autorización, para proteger tu API.
- **Persistencia de Datos con JPA**
- **Documentación de API**
- **Gestión de Usuarios y Roles**
- **Gestión de Solicitudes y Respuestas HTTP**
- **Mensajes de Error y Excepciones, y Validación de Datos de una api**

![Foro Api](https://github.com/Rodriiandino/Foro_API_SpringBoot/assets/106351323/b61a1d81-5053-4f1b-80e4-af24a51743ff)

## ✨ Tecnologías Utilizadas:

- Java y el Framework Spring para la lógica de negocio y la API REST.
- Spring Security para la autenticación y autorización.
- Spring Data JPA para la persistencia de datos.
- SpringDoc para la generación de documentación de la API.
- Base de datos relacional (MySQL) para el almacenamiento de datos.
- Control de Versiones con Flyway

![image](https://github.com/Rodriiandino/Foro_API_SpringBoot/assets/106351323/86d4f5d4-bfa1-498f-8ed0-0265edeb2018)

![image](https://github.com/Rodriiandino/Foro_API_SpringBoot/assets/106351323/679048c0-19dc-48d7-8e1e-95866b91e734)

## ✨ Uso Básico

**Para comenzar a utilizar esta API, sigue estos pasos:**

1. **Clonar el Repositorio**: Clona este repositorio en tu entorno local utilizando el comando `git clone`.

2. **Configuración de la Base de Datos**: Asegúrate de que la configuración de la base de datos se ajuste a tus necesidades. Por defecto, se utiliza MySQL. Debes configurar las propiedades de conexión en el archivo `application.properties`.

3. **Compilación y Ejecución**: Utiliza Maven o tu IDE favorito para compilar y ejecutar la aplicación. La API estará disponible en `http://localhost:8080` de forma predeterminada.

4. **Documentación de la API**: Accede a la documentación de la API en `/swagger-ui.html` para obtener detalles sobre los puntos de conexión disponibles y cómo utilizarlos.
   
6. **Registrar usuario**: Utiliza las rutas `/api/users/create` para registrar a un usuario.

7. **Autenticación**: Utiliza las rutas `/api/login` para obtener un token JWT y autorizar tus solicitudes. Asegúrate de incluir el token en la cabecera `Authorization` de tus solicitudes posteriores.

8. **Gestión de Recursos**: Comienza a crear categorías, temas y publicaciones utilizando las rutas adecuadas. Asegúrate de tener los permisos necesarios para las operaciones de administración.
