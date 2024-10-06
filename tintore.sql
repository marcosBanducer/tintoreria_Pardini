CREATE DATABASE TINTORERIA;
use TINTORERIA;
-- Creación de la tabla Cliente
CREATE TABLE Cliente (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    correo VARCHAR(100)
);
-- Creación de la tabla EstadoPedido
CREATE TABLE EstadoPedido (
    idEstadoPedido INT AUTO_INCREMENT PRIMARY KEY,
    nombreEstado VARCHAR(50) NOT NULL
);

-- Creación de la tabla Pedido
CREATE TABLE Pedido (
    idPedido INT AUTO_INCREMENT PRIMARY KEY,
    fechaPedido DATE NOT NULL,
    idCliente INT,
    idEstadoPedido INT,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente),
    FOREIGN KEY (idEstadoPedido) REFERENCES EstadoPedido(idEstadoPedido)
);

-- Creación de la tabla Prenda
CREATE TABLE Prenda (
    idPrenda INT AUTO_INCREMENT PRIMARY KEY,
    tipoPrenda VARCHAR(50) NOT NULL,
    color VARCHAR(50)
);

-- Creación de la tabla Servicio
CREATE TABLE Servicio (
    idServicio INT AUTO_INCREMENT PRIMARY KEY,
    nombreServicio VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2)
);

-- Creación de la tabla DetallePedido
CREATE TABLE DetallePedido (
    idDetallePedido INT AUTO_INCREMENT PRIMARY KEY,
    idPedido INT,
    idPrenda INT,
    idServicio INT,
    cantidad INT NOT NULL,
    subtotal DECIMAL(10, 2),
    FOREIGN KEY (idPedido) REFERENCES Pedido(idPedido),
    FOREIGN KEY (idPrenda) REFERENCES Prenda(idPrenda),
    FOREIGN KEY (idServicio) REFERENCES Servicio(idServicio)
);

-- Insertando datos en la tabla Cliente
INSERT INTO Cliente (nombre, direccion, telefono, correo) 
VALUES 
('Juan Perez', 'Av. Siempre Viva 123', '123456789', 'juan.perez@gmail.com'),
('Maria Gomez', 'Calle Falsa 456', '987654321', 'maria.gomez@yahoo.com');

-- Insertando datos en la tabla EstadoPedido
INSERT INTO EstadoPedido (nombreEstado) 
VALUES 
('Pendiente'),
('En proceso'),
('Listo'),
('Entregado');

-- Insertando datos en la tabla Pedido
INSERT INTO Pedido (fechaPedido, idCliente, idEstadoPedido) 
VALUES 
('2024-10-05', 1, 1),
('2024-10-05', 2, 2);

-- Insertando datos en la tabla Prenda
INSERT INTO Prenda (tipoPrenda, color) 
VALUES 
('Camisa', 'Blanca'),
('Pantalón', 'Negro');

-- Insertando datos en la tabla Servicio
INSERT INTO Servicio (nombreServicio, precio) 
VALUES 
('Lavado', 150.00),
('Planchado', 80.00);

-- Insertando datos en la tabla DetallePedido
INSERT INTO DetallePedido (idPedido, idPrenda, idServicio, cantidad, subtotal) 
VALUES 
(1, 1, 1, 2, 300.00),
(1, 2, 2, 1, 80.00),
(2, 1, 1, 1, 150.00);

SELECT Cliente.nombre, Pedido.idPedido, Pedido.fechaPedido, EstadoPedido.nombreEstado 
FROM Cliente 
JOIN Pedido ON Cliente.idCliente = Pedido.idCliente 
JOIN EstadoPedido ON Pedido.idEstadoPedido = EstadoPedido.idEstadoPedido;

SELECT Pedido.idPedido, Prenda.tipoPrenda, Servicio.nombreServicio, DetallePedido.cantidad, DetallePedido.subtotal 
FROM Pedido 
JOIN DetallePedido ON Pedido.idPedido = DetallePedido.idPedido 
JOIN Prenda ON DetallePedido.idPrenda = Prenda.idPrenda 
JOIN Servicio ON DetallePedido.idServicio = Servicio.idServicio
WHERE Pedido.idPedido = 1;

SELECT Cliente.nombre, Servicio.nombreServicio, SUM(DetallePedido.cantidad) AS total_servicios 
FROM Cliente 
JOIN Pedido ON Cliente.idCliente = Pedido.idCliente 
JOIN DetallePedido ON Pedido.idPedido = DetallePedido.idPedido 
JOIN Servicio ON DetallePedido.idServicio = Servicio.idServicio
WHERE Cliente.idCliente = 1
GROUP BY Servicio.nombreServicio;

SELECT Pedido.idPedido, SUM(DetallePedido.subtotal) AS total_a_pagar 
FROM Pedido 
JOIN DetallePedido ON Pedido.idPedido = DetallePedido.idPedido 
WHERE Pedido.idPedido = 1
GROUP BY Pedido.idPedido;

DELETE FROM DetallePedido 
WHERE idDetallePedido = 1;

DELETE FROM Pedido 
WHERE idPedido = 1;

DELETE FROM Cliente 
WHERE idCliente = 1;


