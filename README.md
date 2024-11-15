# App Historial Medico
**Proposito:**
Sistema que pretende emular ciertas operaciones de una plataforma de gestión de historial médico, del cual se esperan un conjunto de funcionalidades como lo son: Agregar Paciente, Agregar Registro Médico, Consultar Historial Médico, Obtener Registros por Criterio, dichas funcionalidades serán ofrecidas a través de servicios REST. El sistema utiliza una base de datos MongoDB.

## Tecnologías utilizadas
*Backend:*
- Spring Boot
- Java 21
  
*Base de Datos:*
- MongoDB

## Endpoints disponibles
- Agregar Paciente
    - Descripción: Agrega un paciente que no existe en el sistema, se pasa como parámetro un objeto de tipo paciente. En caso de que el paciente ya exista en el sistema (la CI pertenece a un paciente que está en la base) se retorna el error 401 con el mensaje: “El           paciente ya existe”
    - URL: `http://localhost:8080/api/pacientes/nuevo-paciente`
      
      ```json
      Cuerpo de la peticion:
      {
        "ci": "",
        "nombre": "",
        "apellido": "",
        "fechaNacimiento": "",
        "sexo": ""
      }
      ```
- Agregar Registro Médico
  - Descripción: Agrega un registro médico asociado a un paciente. Se pasa como parámetro la cédula del paciente y un objeto de registro médico. Se genera automáticamente un id único asociado al registro. Si el paciente (cédula) no existe en el sistema, se retorna el
    error 402 con el mensaje: “No existe un paciente con la cédula aportada como parámetro”
    - URL: `http://localhost:8080/api/registro-medico/agregar-registro-medico`
      
      ```json
      Cuerpo de la peticion:
      {
          "fecha": "",
          "tipo": "" //CONSULTA, EXAMEN, INTERNACION,
          "diagnostico": "",
          "medico": {
              "nombre": "",
              "apellido": ""
          },
          "institucion": "",
          "descripcion": "", //Opcional
          "paciente": {
              "ci": ""
          },
          "medicaciones": [""] //Opcional
      }
      ```
- Consultar Historial Médico
    - Descripción: Obtiene todos los registros médicos asociados a un paciente. Se pasa como parámetro la cédula del paciente. Los registros se listan de forma tal que los últimos ingresados se muestran primero. Este endpoint debe ofrecer la posibilidad de obtener los 
      resultados de forma paginada. Si el paciente (cédula) no existe en el sistema, se retorna el error 402 con el mensaje: “No existe un paciente con la cédula aportada como parámetro”
    - URL: `http://localhost:8080/api/registro-medico/consultar-historial-medico`
      
      ```sh
        Parametros de la request
        ?cedula=
        &itemsPorPagina=10
        &pagina=1 (Este valor se cambia cuando se quiere acceder a la siguiente pagina)
      ```
- Obtener Registros por Criterio
    - Descripción: Obtiene todos los registros médicos asociados a un criterio de búsqueda, que puede ser por: Tipo (Consulta, Examen, Internación), Diagnóstico, Médico, Institución. Los criterios se pueden combinar. El criterio se pasa como parámetro.
    - URL: `http://localhost:8080/api/registro-medico/buscar-historial-por-criterio`
      
      ```sh
        Parametros de la request (Se debe agregar al menos uno, no es necesario todos)
        ?tipo=
        &diagnostico=
        &medico=
        &institucion=
      ```

## Configuración para ejecutar en local

Requisitos:
- IDE: en nuestro caso utilizamos IntelliJ IDEA, pero puede ser cualquier otro disponible
- Jdk 21
- Mavan 3.9.9
- MongoDB Compass

#### Pasos para ejecutar:
  1. Clonamos el proyecto
  2. Dentro de nuestro IDE hacemos un build de la solucion para descargar todas las dependencias necesarias.
  3. Vamos a `src/main/resources` y creamos el archivo `application.properties`
     
       ```bash
          spring.application.name=nosql
          spring.data.mongodb.uri=mongodb://<connection_string_local>/historias_medicas
       ```
  4. Entramos a MongoDB Compass y en la instancia local, creamos una nueva base de datos con el nombre "historias_medicas"
  5. Con todo lo anterios configurado, estamos en condiciones de hacer un run de la aplicación


## Configuración de la base de datos
Nuestra base de datos se compone de dos colecciones principales: Datos_Paciente y Registro_Medico

![image](https://github.com/user-attachments/assets/f072857c-de9f-49b3-99e0-a208f9c07a17)

La elección de este modelo se basó en los requerimientos del sistema, con la correspondiente estructura de las colecciones.

## Pruebas realizadas con POSTMAN

#### Agregar Paciente Exitoso:
![image](https://github.com/user-attachments/assets/78d46442-d79a-44f8-97f1-4ff8321a3ff0)

#### Agregar Paciente Error:
![image](https://github.com/user-attachments/assets/ee4d94b9-7c01-418f-8c3f-82387d7a5fc6)

#### Agregar Registro Médico Exitoso:
![image](https://github.com/user-attachments/assets/42a4951f-44d0-4039-b6ef-3c441df7b55b)

#### Agregar Registro Médico Error:
![image](https://github.com/user-attachments/assets/3510b246-8b8a-46e6-a066-0af0e506f5a4)

#### Consultar Historial Médico Exito:
![image](https://github.com/user-attachments/assets/0bf66404-4732-4639-9151-4eb1ff654012)

#### Consultar Historial Médico Error:
![image](https://github.com/user-attachments/assets/aab1ac05-d5c2-4272-8871-fb5593fc22e9)

#### Obtener Registros por Criterio Exito:
![image](https://github.com/user-attachments/assets/f100e497-5202-4a2f-b2a6-27e4fa068c99)

## Requerimientos adicionales
Adicionalmente se realizaron los siguientes aspectos opcionales:

1. Dockerizar la solución: junto con todo el código fuente de la solución se dispone de la posibilidad de correrlo en un docker, para esto se configuró el correspondiente `Dockerfile` con todo lo necesario para que funcione correctamente.
     Ejemplo de la solución dockerizada: `https://medical-hanna-pgcodedeveloper-0c997a9f.koyeb.app`
3. Pruebas de rendimiento con JMeter: En esta oportunidad se realizaron una serie de pruebas de redimiento de todo el servicio REST publicado en la nube. Las pruebas se basaron en una muestra de 600 usuarios (10 usuarios por segundo en un periodo de 60 segundos) dando como resultado primario:
     ![image](https://github.com/user-attachments/assets/64413a15-84fd-4c97-9072-219a6833c764)

     En cuanto al rendimiento tanto del servicio REST como de la Base de datos, se vió un comportamiento correcto donde el pico máximo de uso de la CPU en el caso del backend fue del 30%, y en cuanto a las metricas de la base de datos donde el pico estuvo en 0.36s en comandos INSERT y 0.34s en QUERY

