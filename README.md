_Proyecto Proveedores

Este es un pequeño programa en Java que se conecta a una base de datos MySQL para obtener información de proveedores y generar un archivo plano con los datos recolectados.

_Configuración

Para ejecutar el programa, necesitas colocar el archivo de configuración (config.properties) junto al archivo JAR generado. Puedes encontrar el archivo JAR en la carpeta target después de compilar el proyecto.

_Compilación

Para compilar el proyecto, puedes usar Maven. Ejecuta el siguiente comando en la raíz del proyecto:

mvn clean compile assembly:single

Esto generará un archivo JAR ejecutable en la carpeta target.

_Dependencias

El proyecto utiliza las siguientes bibliotecas externas:

-JUnit: Utilizado para realizar pruebas unitarias. (Versión 3.8.1)
-MySQL Connector/J: Proporciona la conectividad JDBC para MySQL. (Versión 8.0.33)
-Gson: Biblioteca de Google para trabajar con JSON en Java. (Versión 2.11.0)
