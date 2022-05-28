# Jakarta Messaging Examples

Jakarta Messaging (JMS) es una especificación de Jakarta EE que permite la 
comunicación asincrónica entre diferentes sistemas mediantes colas y tópicos. 
En esta aplicación utilizaremos colas, siguiendo la especificación 
https://github.com/jakartaee/messaging

1. A message sender
2. A message receiver

## Construido con:
* OpenJDK 1.8.0_292
* Jakarta EE 9.1.0
* Maven 3.8.5
* Apache NetBeans IDE 13
* Eclipse Glassfish 6.2.5

# Connection Factories

Se debe crear una "connection factory" por cada cola destino que utilice nuestra 
aplicación. Debemos crear la siguiente conexión:

* jms/DemoConnectionFactory

## Crear connection Factory

Para crear una connection factory, debemos realizar los siguientes pasos:

* Clic en el botón "New..." y luego especificar los siguientes valores:
    * JNDI Name: jms/DemoConnectionFactory
    * Resource Type: jakarta.jms.ConnectionFactory
* Clic en el botón "Ok"

Repetir el procedimiento para las restantes conexiones.

# Destination Resources

En esta seccion debemos crear las colas de destino que recibiran los mensajes. 
Para crear una debemos realizar los siguientes pasos:

* Clic en el botón "New...", y espeficar los siguientes valores:
    * JNDI Name: jms/Demo
    * Physical Destination Name: PhysicalDemo
    * Resource Type: jakarta.jms.Queue
* Clic en el botón "Ok"

Repetir este procedimiento para cada cola de destino que hayamos definido en 
connection factory.

> **NOTA**: A los efectos de seguir cierta nomenclatura,los nombres de los 
destinos físicos deben comenzar con el prefijo Physical, seguidos del nombre 
JNDI especificado para la cola (sin incluir su prefijo **jms**) y deben emplear 
la notación UpperCamelCase.

