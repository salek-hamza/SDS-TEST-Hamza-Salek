CREATE DATABASE IF NOT EXISTS PROYECTO_PROVEEDORES;

USE DATABASE PROYECTO_PROVEEDORES;

CREATE TABLE proveedores (
  id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  fecha_de_alta DATE NOT NULL,
  id_cliente INT NOT NULL
);

INSERT INTO proveedores (nombre, fecha_de_alta, id_cliente) VALUES
(1, 'Coca-cola', '01/01/2024', 5),
(2, 'Pepsi', '02/01/2024', 5),
(3, 'Redbull', '03/01/2024', 6);