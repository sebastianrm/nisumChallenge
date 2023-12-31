# Nisum Cchallenge
## Evaluación: JAVA


    Desarrolle una aplicación que exponga una API RESTful de creación de usuarios.
    Todos los endpoints deben aceptar y retornar solamente JSON, inclusive al para los mensajes de error.


    Todos los mensajes deben seguir el formato:


	`{"mensaje": "mensaje de error"}`
	
	


#### Tecnologias:

- java 17
- Spring boot 3.2.0
- h2
- Jpa Hibernate
- jwt: io.jsonwebtoken
- Log4j 2
- Validator
- OpenAI 3.0, swagger

#### Instrucciones de ejecucion:

1.- `git clone git@github.com:sebastianrm/nisumChallenge.git`

2.- `cd NisumChallenge`

3.- `git checkout release/0.1.1`

4.- `mvn spring-boot:run` 

or:

4.1- `mvn install -DskipTests`

4.2- `java -jar target/nisumChallenge-0.1.1-SNAPSHOT.jar`


#### endPoint

Method Post


http://localhost:8080/api/v1/users/add

Ejemplo body:


`
{
  "name": "string",
  "email": "aaaaaaa@dominio.cl",
  "password": "aaaaa@dominio.cl",
  "phones": [
    {
      "number": "string",
      "citycode": "string",
      "countrycode": "string"
    }
  ]
}
`
#### Base de datos

Esta se crea atraves del diagrama de clasess
```mermaid
erDiagram
    USER ||--o{ USER_PHONES : have
    PHONE ||--o{ USER_PHONES : have

    USER_LOG ||--o{ USER_LOG_PHONES : have
    PHONE_LOG ||--o{ USER_LOG_PHONES : have
```

### Flujo de la aplicacion
```mermaid
flowchart TD
    A[End Point] 
    A --> B{Valida entradas}
    B -->|OnError| C[Errormessage]
    B -->|OnSuccess| D{Usuario Existe}
    D -->|OnError Duplicated| C
    D -->|Usuario No existe| E{Genera Token} --> |OnError| C
    E --> F{Registra Nuevo Usuario} --> |OnError| C
    F --> G{Registra Telefonos} --> |OnError| C
    G --> H{Registra Usuario Log} --> |OnError| C
    H -->|Onsucces| I[OnsuccessMessageResponce]
```

    El EndPoint expuesto con metodo Post recibe en su payload un usuraio. Si este usuario cumple con las validaciones del Payload, encripta el password
    Y Este RequestPayload pasa a clases de servicios


    Se verifica si el mail existe, si ya existe se lansa excepcion, en caso contrario, Registra el nuevo usuario.
 
  
    Inserta en una tabla de log el insert del nuevo usuarios.

#### Patrones de diseño

Patron Factory:

    como clase padre para los objentos DTO json utilizados por la API y dto entidades de la base de datos. Entoces por ejemplo:

    se recive en el payloads un objeto json, este objeto json es persistido en la base de datos y en caso de exito se responce un objeto json son datos del objeto insertados.

    para este trabajo se tienen las clases ParentUser, UserRequest y UserEntity

    Para el log del ususario y la respuesta al cliente:
    ParentUserLog, OnSuccessUserResgister, LogUserEntity

##### Diagrama de clases:

```mermaid
classDiagram
    ParentUser <|-- UserRequest
    ParentUser <|-- UserEntity
    ParentUser : +UUID id
    ParentUser : +String email
    ParentUser : +String name
    ParentUser : +String password
    ParentUser : +Boolean isActive
    ParentUser: +factoryUserRequest()
    ParentUser : +factoryUserEntity()
    UserRequest : +factoryUserRequest()
    UserRequest : +factoryUserEntity()
    UserEntity : +factoryUserRequest()
    UserEntity : +factoryUserEntity()

    ParentUserLog <|-- OnSuccessUserResgister
    ParentUserLog <|-- LogUserEntity
    ParentUserLog : +UUID logId
    ParentUserLog : +UUID userId
    ParentUserLog : +Timestamp created
    ParentUserLog : +Timestamp modified
    ParentUserLog : +Timestamp lastLogin
    ParentUserLog : +String token
    ParentUserLog : +String isActive 
    ParentUserLog: +factoryGetLogUserEntity()
    ParentUserLog : +factorygetOnSuccessUserResgister()
    OnSuccessUserResgister : +factoryGetLogUserEntity()
    OnSuccessUserResgister : +factorygetOnSuccessUserResgister()
    LogUserEntity : +factoryGetLogUserEntity()
    LogUserEntity : +factorygetOnSuccessUserResgister()
```

#### Validaciones y mensajes.

    Las validaciones y mensajes de los DTO json y entidades de base dedatos fueron extrenalizadas a los archivos:

- validator-messages.properties
- patterns.properties
- error-messages.properties


#### GitFlow

    Se utilizo Gitfloy y se crearon las ramas:

- master
- develop
- release
- feature:
		- /configTecnologies
		- /registerUser

		
#### SWAGGER

  URL:
  http://localhost:8080/swagger-ui/index.html#/
