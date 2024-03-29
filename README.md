# APP RESERVAS CITAS MÉDICAS

El presente proyecto contiene una aplicación para la reserva de citas médicas del consultorio del Doctor Ramiro Fernandez para la administración de las historias médicas y el manejo de citas de sus pacientes.

A continuación se da conocer el modelamiento que se tuvo en cuanta para realizar dicho proyecto:

- Modelo Estratégico
  ![estrategico.png](img%2Festrategico.png)

- Modelo Táctico
  ![tactico.png](img%2Ftactico.png)

## Entrada de datos
- Crear Agenda
  ![crearAgenda.png](img%2FcrearAgenda.png)
- Definir Disponibilidad horaria
- ![definirDisponibilidad.png](img%2FdefinirDisponibilidad.png)
- Crear Paciente
  ![crearPaciente.png](img%2FcrearPaciente.png)
- Agendar Cita
  ![AgendarCita.png](img%2FAgendarCita.png)
- Crear Revisión
  ![crearRevision.png](img%2FcrearRevision.png)
  

## Salida de datos
- Agenda creada
  ![agendaCreada.png](img%2FagendaCreada.png)
- Disponibilidad horaria definida
  ![disponibilidadDefinida.png](img%2FdisponibilidadDefinida.png)
- Paciente creado
  ![pacienteCreado.png](img%2FpacienteCreado.png)
- Cita Agendada
  ![citaAgendada.png](img%2FcitaAgendada.png)
- Revisión creada
  ![revisionCreada.png](img%2FrevisionCreada.png)
- Historial de revisiones  
  ![historial1.png](img%2Fhistorial1.png)
  ![historial2.png](img%2Fhistorial2.png)


# Proyecto Base Implementando Clean Architecture

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por último el inicio y configuración de la aplicación.

Lee el artículo [Clean Architecture — Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patrón de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## Application

Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**


Tecnologías utilizadas:

-  [Java 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
   <image src="https://miro.medium.com/max/754/1*SYPAgsWPGGM9y3zN2B7LGQ.png">

-  IDE: [IntelliJ IDEA](https://www.jetbrains.com/idea/)
   <image src="https://pbs.twimg.com/profile_images/1206618215767584769/zl48EuhC_400x400.jpg" alt="IntelliJ IDEA" height="200">

### Autor
[@LilianaGallego](https://github.com/LilianaGallego) - Liliana Gallego