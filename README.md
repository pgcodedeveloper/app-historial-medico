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
