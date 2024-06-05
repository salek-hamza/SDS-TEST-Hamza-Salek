package com.example;
import java.util.List;

public class App {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Incorrect Usage: java App <id_cliente>");
            System.exit(1);
        }

        int idCliente = Integer.parseInt(args[0]); // Convierte el argumento a un entero
        DatabaseConnection dbConn = new DatabaseConnection();

        
        // Obtén los datos para el id_cliente especificado
        List<String[]> data = dbConn.queryProveedores(idCliente);


        // Verifica si los datos obtenidos están vacíos
        if (data.isEmpty()) {
            System.out.println("No data found for ID Cliente: " + idCliente);
            System.exit(1);  // Aborta el procesamiento adicional
        }

        dbConn.saveDataToCSV(data); // Guarda los datos obtenidos en un archivo CSV
        dbConn.saveDataToJSON(data); 
        dbConn.saveDataToHTML(data); 

    }
}