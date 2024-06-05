package com.example;

import java.sql.*;
import java.io.*;
import java.util.Properties;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConnection {

    private static final Properties config = loadProperties();

    private static Properties loadProperties() {
        Properties props = new Properties();
        // Usa una ruta de archivo para abrir un FileInputStream
        try (InputStream input = new FileInputStream("config.properties")) { // Busca config.properties en el directorio de trabajo actual
            props.load(input);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find config.properties. Please ensure it is in the same directory as the JAR.");
            return null;
        } catch (IOException e) {
            System.out.println("Unable to load config.properties. Check if the file is accessible and not corrupted.");
            e.printStackTrace();
            return null;
        }
        return props;
    }

    public List<String[]> queryProveedores(int idCliente) {
        List<String[]> data = new ArrayList<>();
        String tableName = config.getProperty("table.name", "proveedores");  // Usa "proveedores" por defecto si no está especificado
        String sql = "SELECT id_proveedor, nombre, fecha_de_alta, id_cliente FROM " + tableName + " WHERE id_cliente = ?";
    
        try (Connection conn = DriverManager.getConnection(config.getProperty("db.url"), config.getProperty("db.user"),
                config.getProperty("db.password"));
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setInt(1, idCliente); // Establece el parámetro id_cliente
    
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    data.add(new String[] {
                            String.valueOf(rs.getInt("id_proveedor")),
                            rs.getString("nombre"),
                            rs.getDate("fecha_de_alta").toString(),
                            String.valueOf(rs.getInt("id_cliente"))
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void saveDataToCSV(List<String[]> data) {
        String csvFilePath = config.getProperty("csv.output.path", "proveedores.csv");
        try {
            File file = new File(csvFilePath);
            file.getParentFile().mkdirs(); // Se asegura de que el directorio exista, lo crea si no es así

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write("ID,Nombre,Fecha de Alta,ID Cliente");
                bw.newLine();

                for (String[] row : data) {
                    bw.write(String.join(",", row));
                    bw.newLine();
                }
                System.out.println("Data successfully written to " + csvFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDataToHTML(List<String[]> data) {
        String htmlFilePath = config.getProperty("html.output.path", "proveedores.html");
        try {
            File file = new File(htmlFilePath);
            file.getParentFile().mkdirs(); // Se asegura de que el directorio exista, lo crea si no es así

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write("<!DOCTYPE html>\n");
                bw.write("<html>\n<head>\n<title>Proveedores Data</title>\n</head>\n<body>\n");
                bw.write("<h1>Proveedores</h1>\n");
                bw.write("<table border=\"1\">\n");
                bw.write("<tr><th>ID</th><th>Nombre</th><th>Fecha de Alta</th><th>ID Cliente</th></tr>\n");

                for (String[] row : data) {
                    bw.write("<tr>");
                    bw.write("<td>" + row[0] + "</td>");
                    bw.write("<td>" + row[1] + "</td>");
                    bw.write("<td>" + row[2] + "</td>");
                    bw.write("<td>" + row[3] + "</td>");
                    bw.write("</tr>\n");
                }

                bw.write("</table>\n</body>\n</html>");
                System.out.println("HTML data successfully written to " + htmlFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDataToJSON(List<String[]> data) {
        String jsonFilePath = config.getProperty("json.output.path", "proveedores.json");
        Gson gson = new Gson();
        List<Map<String, Object>> mapList = new ArrayList<>();

        for (String[] array : data) {
            Map<String, Object> map = new HashMap<>();
            map.put("ID", Integer.parseInt(array[0]));
            map.put("Nombre", array[1]);
            map.put("Fecha de Alta", array[2]);
            map.put("ID Cliente", Integer.parseInt(array[3]));
            mapList.add(map);
        }

        String json = gson.toJson(mapList);  // Convierte la lista de map a una cadena JSON

        try {
            File file = new File(jsonFilePath);
            file.getParentFile().mkdirs(); // Se asegura de que el directorio exista, lo crea si no es así

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(json);  // Escribe la cadena JSON en un archivo
                System.out.println("JSON data successfully written to " + jsonFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
