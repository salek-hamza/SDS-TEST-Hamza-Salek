                                                           PROYECTO PROVEEDORES

Este es un pequeño programa en Java que se conecta a una base de datos MySQL para obtener información de proveedores y generar un archivo plano con los datos recolectados.

_**Ejecución inicial**

Para ejecutar el programa al inicio basta con modificar el archivo de configuración (config.properties) con los datos que desee y luego ejecutar el archivo jar como sigue:

*java -jar proyecto-proveedores-1.0-SNAPSHOT-jar-with-dependencies.jar <código_del_cliente>*

_**Configuración y Compilación**

Si se decide modificar el código, para ejecutar el programa necesitas colocar el archivo de configuración (config.properties) junto al archivo JAR generado. Puedes encontrar el archivo JAR en la carpeta target después de compilar el proyecto.

Para compilar el proyecto, puedes usar Maven. Ejecuta el siguiente comando en la raíz del proyecto:

*mvn clean compile assembly:single*

Esto generará un archivo JAR ejecutable en la carpeta target.

_**Dependencias**

El proyecto utiliza las siguientes bibliotecas externas:

-MySQL Connector/J: Proporciona la conectividad JDBC para MySQL. (Versión 8.0.33)

-Gson: Biblioteca de Google para trabajar con JSON en Java. (Versión 2.11.0)

Estas dependencias están especificadas en el archivo pom.xml del proyecto y se gestionan automáticamente mediante Maven.
