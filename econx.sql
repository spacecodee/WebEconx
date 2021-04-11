-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.22 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.1.0.6116
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para econx
CREATE DATABASE IF NOT EXISTS `econx` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `econx`;

-- Volcando estructura para tabla econx.boleta
CREATE TABLE IF NOT EXISTS `boleta` (
  `IDBoleta` int NOT NULL AUTO_INCREMENT,
  `IDTrae` int NOT NULL,
  `IDComprador` int NOT NULL,
  `Codigo` char(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `Precio` decimal(10,2) NOT NULL,
  `Cantidad` int NOT NULL,
  `Empresa` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'ECONX',
  PRIMARY KEY (`IDBoleta`),
  KEY `IDTrae` (`IDTrae`),
  KEY `IDCompradorFK` (`IDComprador`),
  CONSTRAINT `IDCompradorFK` FOREIGN KEY (`IDComprador`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `IDTrae` FOREIGN KEY (`IDTrae`) REFERENCES `traeproducto` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla econx.boleta: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `boleta` DISABLE KEYS */;
INSERT INTO `boleta` (`IDBoleta`, `IDTrae`, `IDComprador`, `Codigo`, `Precio`, `Cantidad`, `Empresa`) VALUES
	(2, 2, 14, '0001', 4.50, 54, 'ECONX'),
	(3, 2, 15, '0002', 22.00, 15, 'ECONX'),
	(5, 4, 13, '0004', 7.50, 22, 'ECONX'),
	(6, 5, 16, '0003', 6.00, 12, 'ECONX'),
	(7, 2, 12, '0005', 6.00, 12, 'ECONX'),
	(8, 6, 15, '0006', 68.00, 42, 'ECONX'),
	(9, 2, 13, '0007', 12.00, 52, 'ECONX'),
	(10, 4, 16, '0008', 12.60, 100, 'ECONX'),
	(11, 8, 31, '0009', 44.00, 50, 'ECONX'),
	(12, 9, 31, '0010', 45.00, 12, 'ECONX');
/*!40000 ALTER TABLE `boleta` ENABLE KEYS */;

-- Volcando estructura para tabla econx.categoriaproductos
CREATE TABLE IF NOT EXISTS `categoriaproductos` (
  `IDCategoria` int NOT NULL AUTO_INCREMENT,
  `Codigo` char(4) COLLATE utf8_bin NOT NULL DEFAULT '',
  `Categoria` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`IDCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla econx.categoriaproductos: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `categoriaproductos` DISABLE KEYS */;
INSERT INTO `categoriaproductos` (`IDCategoria`, `Codigo`, `Categoria`) VALUES
	(2, '02', 'Caramelos'),
	(3, '03', 'Gaseosas'),
	(4, '04', 'Cervezas'),
	(5, '05', 'Alchohol'),
	(6, '01', 'Chicles'),
	(7, '06', 'Vinos'),
	(8, '07', 'Atunes');
/*!40000 ALTER TABLE `categoriaproductos` ENABLE KEYS */;

-- Volcando estructura para procedimiento econx.listrarIdBoleta
DELIMITER //
CREATE PROCEDURE `listrarIdBoleta`(
	IN `i` INT
)
BEGIN
	SELECT b.IDBoleta, t.Codigo, p.Nombre, pr.Nombre, u.DNI, u.Nombre, b.Codigo,
	b.Precio, b.Cantidad, b.Empresa
	FROM boleta b
	INNER JOIN traeproducto t
		ON b.IDTrae = t.ID
	INNER JOIN proveedor p
		ON t.IDProveedor = p.ID
	INNER JOIN productos pr
		ON t.IDProducto = pr.ID
	INNER JOIN usuarios u
		ON b.IDComprador = u.id
	WHERE IDBoleta = i;
END//
DELIMITER ;

-- Volcando estructura para tabla econx.productos
CREATE TABLE IF NOT EXISTS `productos` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Codigo` char(4) COLLATE utf8_bin NOT NULL,
  `Nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Precio` decimal(10,2) NOT NULL,
  `Stock` int DEFAULT '0',
  `IDCategoria` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `IDCategoriaFK` (`IDCategoria`),
  CONSTRAINT `IDCategoriaFK` FOREIGN KEY (`IDCategoria`) REFERENCES `categoriaproductos` (`IDCategoria`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla econx.productos: ~11 rows (aproximadamente)
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` (`ID`, `Codigo`, `Nombre`, `Precio`, `Stock`, `IDCategoria`) VALUES
	(3, '01', 'Cerveza Cristal', 6.00, 56, 4),
	(5, '02', 'Ron Cartavio', 8.50, 100, 5),
	(6, '03', 'Vino Tinto', 22.00, 60, 7),
	(7, '04', 'Coca Cola 1L.', 4.00, 42, 3),
	(8, '05', 'Pepsi 2L.', 5.50, 12, 3),
	(9, '06', 'Inka Kola 1/2 L.', 1.50, 24, 3),
	(10, '07', 'Bubalo', 0.20, 150, 6),
	(11, '08', 'Inka Kola 3 L.', 5.50, 42, 3),
	(12, '11', 'Pepsi', 2.00, 50, 3),
	(14, '09', 'Vino', 18.50, 20, 7),
	(15, '10', 'Inka Kola 2L', 4.50, 120, 3);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;

-- Volcando estructura para tabla econx.proveedor
CREATE TABLE IF NOT EXISTS `proveedor` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `DNI` char(8) COLLATE utf8_bin NOT NULL DEFAULT '',
  `Nombre` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '',
  `Apellidos` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '',
  `Correo` varchar(100) COLLATE utf8_bin DEFAULT '',
  `Telefono` char(9) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla econx.proveedor: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` (`ID`, `DNI`, `Nombre`, `Apellidos`, `Correo`, `Telefono`) VALUES
	(1, '75690421', 'Juan', 'Del Carmen', 'proveedor@mail.com', '987654124'),
	(4, '75690422', 'Marta', 'Reyes', 'marta@mail.com', '988774452'),
	(5, '75690423', 'Ana', 'Del Carmen', 'ana@mail.com', '966332215'),
	(6, '75690424', 'Juan', 'Pérez', 'juana@mail.com', '965478112'),
	(7, '75690425', 'Anthony', 'Del Pilar', 'anthony@mail.com', '987451233'),
	(8, '75690426', 'Anastasia', 'Pérez', 'anastasia@mail.com', '963332211'),
	(9, '75690427', 'Ana María', 'Del Carmen', 'anama@mail.com', '954122004'),
	(10, '75690428', 'Karen Vannesa', 'Fernadez', 'karen@mail.com', '954128452'),
	(12, '75690430', 'Maria Alejandra', 'Fernandez', 'alejandra@mail.com', '957412305');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;

-- Volcando estructura para procedimiento econx.spActualizarBoleta
DELIMITER //
CREATE PROCEDURE `spActualizarBoleta`(
	IN `idT` INT,
	IN `idC` INT,
	IN `cod` CHAR(10),
	IN `pre` DECIMAL(10,2),
	IN `can` INT,
	IN `emp` VARCHAR(50),
	IN `id` INT
)
BEGIN
	UPDATE boleta
	SET IDTrae = idT, IDComprador = idC, Codigo = cod, Precio = pre, Cantidad = can, Empresa = emp
	WHERE IDBoleta = id;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spActualizarCategorias
DELIMITER //
CREATE PROCEDURE `spActualizarCategorias`(
	IN `cod` CHAR(4),
	IN `cate` VARCHAR(100),
	IN `id` INT
)
BEGIN
	UPDATE categoriaproductos
	SET Codigo = cod, Categoria = cate
	WHERE IDCategoria = id;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spActualizarInfoAvanzada
DELIMITER //
CREATE PROCEDURE `spActualizarInfoAvanzada`(
	IN `fecha` DATE,
	IN `idU` INT
)
BEGIN
	UPDATE usuarios 
	SET FechaNacimiento = fecha
	WHERE id = idU;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spActualizarInfoBasica
DELIMITER //
CREATE PROCEDURE `spActualizarInfoBasica`(
	IN `nom` VARCHAR(150),
	IN `ape` VARCHAR(150),
	IN `tele` VARCHAR(150),
	IN `mail` VARCHAR(150),
	IN `idU` INT
)
BEGIN
	UPDATE usuarios 
	SET Nombre = nom, Apellidos = ape, Telefono = tele, 
	CorreoElectronico = mail
	WHERE id = idU;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spActualizarProducto
DELIMITER //
CREATE PROCEDURE `spActualizarProducto`(
	IN `cod` CHAR(4),
	IN `nom` VARCHAR(100),
	IN `pre` DOUBLE,
	IN `stc` INT,
	IN `idC` INT,
	IN `idP` INT
)
BEGIN
	UPDATE productos
	SET Codigo = cod, Nombre = nom, Precio = pre, Stock = stc, IDCategoria = idC
	WHERE ID = idP;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spActualizarProveedor
DELIMITER //
CREATE PROCEDURE `spActualizarProveedor`(
	IN `dn` CHAR(8),
	IN `nom` VARCHAR(100),
	IN `ape` VARCHAR(100),
	IN `mail` VARCHAR(100),
	IN `tele` VARCHAR(100),
	IN `idP` INT
)
BEGIN
	UPDATE proveedor 
	SET DNI = dn, Nombre = nom, Apellidos = ape, Correo = mail, Telefono = tele 
	WHERE ID = idP;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spActualizarRoles
DELIMITER //
CREATE PROCEDURE `spActualizarRoles`(
	IN `cod` CHAR(4),
	IN `roll` VARCHAR(50),
	IN `idR` INT
)
BEGIN
	UPDATE tipousuarios
	SET Codigo = cod, Tipo = roll
	WHERE ID = idR;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spActualizarTablaIntermedia
DELIMITER //
CREATE PROCEDURE `spActualizarTablaIntermedia`(
	IN `cod` CHAR(10),
	IN `idP` INT,
	IN `idPro` INT,
	IN `fech` DATE,
	IN `idTrae` INT
)
BEGIN
	UPDATE traeproducto
	SET Codigo = cod, IDProveedor = idP, IDProducto = idPro, Fecha = fech
	WHERE ID = idTrae;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spActualizarUsuarios
DELIMITER //
CREATE PROCEDURE `spActualizarUsuarios`(
	IN `dn` CHAR(8),
	IN `nom` VARCHAR(150),
	IN `ape` VARCHAR(150),
	IN `telef` CHAR(9),
	IN `roll` INT,
	IN `idU` INT
)
BEGIN
	UPDATE usuarios
	SET DNI = dn, Nombre = nom, Apellidos = ape, Telefono = telef, IDTipo = roll
	WHERE id = idU;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spBuscarBoleta
DELIMITER //
CREATE PROCEDURE `spBuscarBoleta`(
	IN `cod` CHAR(10)
)
BEGIN
	SELECT b.IDBoleta, t.Codigo, p.Nombre, pr.Nombre, u.DNI, u.Nombre, b.Codigo, b.Precio, 
	b.Cantidad, b.Empresa
	FROM boleta b
	INNER JOIN traeproducto t
		ON b.IDTrae = t.ID
	INNER JOIN proveedor p
		ON t.IDProveedor = p.ID
	INNER JOIN productos pr
		ON t.IDProducto = pr.ID
	INNER JOIN usuarios u
		ON b.IDComprador = u.id
	WHERE b.Codigo 
	LIKE CONCAT('%', cod, '%');
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spBuscarCategoriaProductos
DELIMITER //
CREATE PROCEDURE `spBuscarCategoriaProductos`(
	IN `cod` CHAR(4)
)
BEGIN
	SELECT *
	FROM categoriaproductos
	WHERE Codigo LIKE CONCAT('%', cod, '%'); 
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spBuscarParaBoleta
DELIMITER //
CREATE PROCEDURE `spBuscarParaBoleta`(
	IN `dn` CHAR(9),
	IN `fe` DATE
)
BEGIN
	SELECT b.Codigo AS CodigoFactura, p.Nombre AS Proveedor, pr.Nombre AS Producto, 
	u.Nombre AS Comprador, b.Precio AS PrecioCompra,
	b.Cantidad AS Cantidad, 
	b.Empresa AS Empresa, t.Fecha AS FechaCompra
	FROM boleta b
	INNER JOIN traeproducto t
		ON b.IDTrae = t.ID
	INNER JOIN proveedor p
		ON t.IDProveedor = p.ID
	INNER JOIN productos pr
		ON t.IDProducto = pr.ID
	INNER JOIN usuarios u
		ON b.IDComprador = u.id
	WHERE u.DNI = dn
	AND t.Fecha = fe;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spBuscarProductos
DELIMITER //
CREATE PROCEDURE `spBuscarProductos`(
	IN `cod` CHAR(4)
)
BEGIN
	SELECT p.ID, p.Codigo, p.Nombre, p.Precio, p.Stock, c.Categoria
	FROM productos p
	INNER JOIN categoriaproductos c
		ON p.IDCategoria = c.IDCategoria
	WHERE p.Codigo LIKE CONCAT('%', cod, '%'); 
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spBuscarProveedores
DELIMITER //
CREATE PROCEDURE `spBuscarProveedores`(
	IN `dn` CHAR(8)
)
BEGIN
	SELECT ID, DNI, Nombre, Apellidos, Correo, Telefono
	FROM proveedor
	WHERE DNI LIKE CONCAT('%', dn, '%'); 
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spBuscarRoll
DELIMITER //
CREATE PROCEDURE `spBuscarRoll`(
	IN `cod` CHAR(4)
)
BEGIN
	SELECT *
	FROM tipousuarios
	WHERE Codigo LIKE CONCAT('%', cod, '%'); 
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spBuscarTablaCategoria
DELIMITER //
CREATE PROCEDURE `spBuscarTablaCategoria`(
	IN `cod` CHAR(4)
)
BEGIN
	SELECT *
	FROM categoriaproductos
	WHERE Codigo LIKE CONCAT('%', cod, '%'); 
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spBuscarTablaIntermedia
DELIMITER //
CREATE PROCEDURE `spBuscarTablaIntermedia`(
	IN `cod` CHAR(10)
)
BEGIN
	SELECT t.ID, t.Codigo, p.Nombre, pr.Nombre, t.Fecha
	FROM traeproducto t
	INNER JOIN proveedor p
		ON t.IDProveedor = p.ID
	INNER JOIN productos pr
		ON t.IDProducto = pr.ID
	WHERE t.Codigo LIKE CONCAT('%', cod, '%'); 
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spBuscarUsuario
DELIMITER //
CREATE PROCEDURE `spBuscarUsuario`(
	IN `dn` CHAR(8)
)
BEGIN
	SELECT u.id, u.DNI, u.Nombre, u.Apellidos, u.Telefono, t.Tipo
	FROM usuarios u
	INNER JOIN tipousuarios t ON u.IDTipo = t.ID
	WHERE DNI LIKE CONCAT('%', dn, '%');
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spCambiarPass
DELIMITER //
CREATE PROCEDURE `spCambiarPass`(
	IN `pass` VARCHAR(150),
	IN `idU` INT
)
BEGIN
	UPDATE usuarios 
	SET password = pass
	WHERE id = idU;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spCargarTablaUsuarios
DELIMITER //
CREATE PROCEDURE `spCargarTablaUsuarios`()
BEGIN
	SELECT u.id, u.DNI, u.Nombre, u.Apellidos, u.Telefono, t.Tipo
	FROM usuarios u
	INNER JOIN tipousuarios t
		ON u.IDTipo = t.ID;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spComboComprador
DELIMITER //
CREATE PROCEDURE `spComboComprador`()
BEGIN
	SELECT id, DNI
	FROM usuarios;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spComboProductos
DELIMITER //
CREATE PROCEDURE `spComboProductos`()
BEGIN
	SELECT ID, Nombre
	FROM productos;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spComboProveedor
DELIMITER //
CREATE PROCEDURE `spComboProveedor`()
BEGIN
	SELECT ID, Nombre
	FROM proveedor;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spComboTraeProd
DELIMITER //
CREATE PROCEDURE `spComboTraeProd`()
BEGIN
	SELECT ID, Codigo
	FROM traeproducto;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spComprobarPass
DELIMITER //
CREATE PROCEDURE `spComprobarPass`(
	IN `idU` INT
)
BEGIN
	SELECT Password
	FROM usuarios
	WHERE id = idU;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spEliminarBoleta
DELIMITER //
CREATE PROCEDURE `spEliminarBoleta`(
	IN `id` INT
)
BEGIN
	DELETE FROM boleta
	WHERE IDBoleta = id;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spEliminarCategoriaProd
DELIMITER //
CREATE PROCEDURE `spEliminarCategoriaProd`(
	IN `id` INT
)
BEGIN
	DELETE FROM categoriaproductos
	WHERE IDCategoria = id;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spEliminarProducto
DELIMITER //
CREATE PROCEDURE `spEliminarProducto`(
	IN `idP` INT
)
BEGIN
	DELETE FROM productos
	WHERE ID = idP;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spEliminarProveedor
DELIMITER //
CREATE PROCEDURE `spEliminarProveedor`(
	IN `idP` INT
)
BEGIN
	DELETE FROM proveedor 
	WHERE ID = idP;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spEliminarRoll
DELIMITER //
CREATE PROCEDURE `spEliminarRoll`(
	IN `idT` INT
)
BEGIN
	DELETE FROM tipousuarios
	WHERE ID = idT;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spEliminarTablaIntermedia
DELIMITER //
CREATE PROCEDURE `spEliminarTablaIntermedia`(
	IN `idTaba` INT
)
BEGIN
	DELETE FROM traeproducto
	WHERE ID = idTaba;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spEliminarUsuarios
DELIMITER //
CREATE PROCEDURE `spEliminarUsuarios`(
	IN `idUs` INT
)
BEGIN
	DELETE FROM usuarios
	WHERE id = idUs;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spFacturaBoleta
DELIMITER //
CREATE PROCEDURE `spFacturaBoleta`()
BEGIN
	SELECT b.Codigo AS CodigoFactura, p.Nombre AS Proveedor, pr.Nombre AS Producto, 
	u.Nombre AS Comprador, b.Precio AS PrecioCompra, b.Cantidad AS Cantidad, 
	b.Empresa AS Empresa, t.Fecha AS FechaCompra
	FROM boleta b
	INNER JOIN traeproducto t
		ON b.IDTrae = t.ID
	INNER JOIN proveedor p
		ON t.IDProveedor = p.ID
	INNER JOIN productos pr
		ON t.IDProducto = pr.ID
	INNER JOIN usuarios u
		ON b.IDComprador = u.id;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spFacturaBoletaBusqueda
DELIMITER //
CREATE PROCEDURE `spFacturaBoletaBusqueda`(
	IN `dn` CHAR(8),
	IN `da` DATE,
	IN `dat` DATE
)
BEGIN
	SELECT b.Codigo AS CodigoFactura, p.Nombre AS Proveedor, pr.Nombre AS Producto, 
	u.Nombre AS Comprador, b.Precio AS PrecioCompra, b.Cantidad AS Cantidad, 
	b.Empresa AS Empresa, t.Fecha AS FechaCompra
	FROM boleta b
	INNER JOIN traeproducto t
		ON b.IDTrae = t.ID
	INNER JOIN proveedor p
		ON t.IDProveedor = p.ID
	INNER JOIN productos pr
		ON t.IDProducto = pr.ID
	INNER JOIN usuarios u
		ON b.IDComprador = u.id
	WHERE u.DNI = dn
	AND t.Fecha BETWEEN da AND dat;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spInsertarBoleta
DELIMITER //
CREATE PROCEDURE `spInsertarBoleta`(
	IN `idT` INT,
	IN `idC` INT,
	IN `cod` CHAR(10),
	IN `prec` DECIMAL(10,2),
	IN `can` INT,
	IN `emp` VARCHAR(50)
)
BEGIN
	INSERT INTO boleta
	(IDTrae, IDComprador, Codigo, Precio, Cantidad, Empresa)
	VALUES (idT, idC, cod, prec, can, emp);
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spInsertarCategoriaP
DELIMITER //
CREATE PROCEDURE `spInsertarCategoriaP`(
	IN `cod` CHAR(4),
	IN `cate` VARCHAR(100)
)
BEGIN
	INSERT INTO categoriaproductos (Codigo, Categoria)
	VALUES (cod, cate);
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spInsertarProductos
DELIMITER //
CREATE PROCEDURE `spInsertarProductos`(
	IN `cod` CHAR(4),
	IN `nom` VARCHAR(100),
	IN `prec` DOUBLE,
	IN `stc` INT,
	IN `idC` INT
)
BEGIN
	INSERT INTO productos (Codigo, Nombre, Precio, Stock, IDCategoria)
	VALUES (cod, nom, prec, stc, idC);
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spInsertarProveedores
DELIMITER //
CREATE PROCEDURE `spInsertarProveedores`(
	IN `dn` CHAR(8),
	IN `nom` VARCHAR(100),
	IN `ape` VARCHAR(100),
	IN `mail` VARCHAR(100),
	IN `tele` VARCHAR(100)
)
BEGIN
	INSERT INTO proveedor (DNI, Nombre, Apellidos, Correo, Telefono)
	VALUES (dn, nom, ape, mail, tele);
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spInsertarRoles
DELIMITER //
CREATE PROCEDURE `spInsertarRoles`(
	IN `cod` CHAR(4),
	IN `roll` VARCHAR(50)
)
BEGIN
	INSERT INTO tipousuarios (Codigo, Tipo)
	VALUES (cod, roll);
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spInsertarTablaInermedia
DELIMITER //
CREATE PROCEDURE `spInsertarTablaInermedia`(
	IN `cod` CHAR(10),
	IN `idP` INT,
	IN `idPro` INT,
	IN `fech` DATE
)
BEGIN
	INSERT INTO traeproducto
	(Codigo, IDProveedor, IDProducto, Fecha)
	VALUES (cod, idP, idPro, fech);
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spInsertarUsuarios
DELIMITER //
CREATE PROCEDURE `spInsertarUsuarios`(
	IN `dn` CHAR(8),
	IN `pass` VARCHAR(150),
	IN `nom` VARCHAR(150),
	IN `ape` VARCHAR(150),
	IN `telefo` VARCHAR(150),
	IN `correo` VARCHAR(150),
	IN `fechaN` DATE,
	IN `roll` INT
)
BEGIN
	INSERT INTO usuarios 
	(DNI, Password, Nombre, Apellidos, Telefono, CorreoElectronico, FechaNacimiento, IDTipo)
	VALUES 
	(dn, pass, nom, ape, telefo, correo, fechaN, roll);
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spListarIdCategoriaP
DELIMITER //
CREATE PROCEDURE `spListarIdCategoriaP`(
	IN `i` INT
)
BEGIN
	SELECT *
	FROM categoriaproductos
	WHERE IDCategoria = i;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spListarIdIntermedia
DELIMITER //
CREATE PROCEDURE `spListarIdIntermedia`(
	IN `i` INT
)
BEGIN
	SELECT *
	FROM traeproducto
	WHERE ID = i;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spListarIdProducto
DELIMITER //
CREATE PROCEDURE `spListarIdProducto`(
	IN `i` INT
)
BEGIN
	SELECT *
	FROM productos
	WHERE ID = i;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spListarIdProveedor
DELIMITER //
CREATE PROCEDURE `spListarIdProveedor`(
	IN `i` INT
)
BEGIN
	SELECT *
	FROM proveedor
	WHERE ID = i;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spListarIDRoll
DELIMITER //
CREATE PROCEDURE `spListarIDRoll`(
	IN `i` INT
)
BEGIN
	SELECT * FROM tipousuarios
	WHERE ID = i;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spListarIDUsuario
DELIMITER //
CREATE PROCEDURE `spListarIDUsuario`(
	IN `i` INT
)
BEGIN
	SELECT * FROM usuarios
	WHERE id = i;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spLogin
DELIMITER //
CREATE PROCEDURE `spLogin`(
	IN `dn` CHAR(10)
)
BEGIN
	SELECT u.id, u.DNI, u.Password, u.Nombre, u.Apellidos, u.Telefono, u.CorreoElectronico,
	u.FechaNacimiento, t.Tipo
	FROM usuarios u
	INNER JOIN tipousuarios t
		ON u.IDTipo = t.ID
	WHERE u.DNI = dn;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spMostrarBoleta
DELIMITER //
CREATE PROCEDURE `spMostrarBoleta`()
BEGIN
	SELECT b.IDBoleta, t.Codigo, p.Nombre, pr.Nombre, u.DNI, u.Nombre, b.Codigo,
	b.Precio, b.Cantidad, b.Empresa
	FROM boleta b
	INNER JOIN traeproducto t
		ON b.IDTrae = t.ID
	INNER JOIN proveedor p
		ON t.IDProveedor = p.ID
	INNER JOIN productos pr
		ON t.IDProducto = pr.ID
	INNER JOIN usuarios u
		ON b.IDComprador = u.id;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spMostrarCategoriaP
DELIMITER //
CREATE PROCEDURE `spMostrarCategoriaP`()
BEGIN
	SELECT IDCategoria, Categoria
	FROM categoriaproductos;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spMostrarProductos
DELIMITER //
CREATE PROCEDURE `spMostrarProductos`()
BEGIN
	SELECT p.ID, p.Codigo, p.Nombre, p.Precio, p.Stock, c.Categoria
	FROM productos p
	INNER JOIN categoriaproductos c
		ON p.IDCategoria = c.IDCategoria;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spMostrarProveedores
DELIMITER //
CREATE PROCEDURE `spMostrarProveedores`()
BEGIN
	SELECT *
	FROM proveedor;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spMostrarRoles
DELIMITER //
CREATE PROCEDURE `spMostrarRoles`()
BEGIN
	SELECT *
	FROM tipousuarios;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spMostrarTablaCategoria
DELIMITER //
CREATE PROCEDURE `spMostrarTablaCategoria`()
BEGIN
	SELECT *
	FROM categoriaproductos;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spMostrarTablaCategorias
DELIMITER //
CREATE PROCEDURE `spMostrarTablaCategorias`()
BEGIN
	SELECT Codigo, Categoria
	FROM categoriaproductos;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spMostrarTablaIntermedia
DELIMITER //
CREATE PROCEDURE `spMostrarTablaIntermedia`()
BEGIN
	SELECT t.ID, t.Codigo, p.Nombre, pr.Nombre, t.Fecha
	FROM traeproducto t
	INNER JOIN proveedor p
		ON t.IDProveedor = p.ID
	INNER JOIN productos pr
		ON t.IDProducto = pr.ID;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spSeleccionarRoll
DELIMITER //
CREATE PROCEDURE `spSeleccionarRoll`()
BEGIN
	SELECT ID, Tipo 
	FROM tipousuarios;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spSeleccionarTotalEmpleados
DELIMITER //
CREATE PROCEDURE `spSeleccionarTotalEmpleados`()
BEGIN
	SELECT COUNT(*) AS EMPLEADOS 
	FROM usuarios;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spSeleccionarTotalProductos
DELIMITER //
CREATE PROCEDURE `spSeleccionarTotalProductos`()
BEGIN
	SELECT COUNT(*)
	FROM productos;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spSeleccionarTotalProveedores
DELIMITER //
CREATE PROCEDURE `spSeleccionarTotalProveedores`()
BEGIN
	SELECT COUNT(*) AS Proveedores 
	FROM proveedor;
END//
DELIMITER ;

-- Volcando estructura para procedimiento econx.spUsuarioExistente
DELIMITER //
CREATE PROCEDURE `spUsuarioExistente`(
	IN `dn` CHAR(8),
	IN `phone` CHAR(9),
	IN `correo` VARCHAR(150)
)
BEGIN
	SELECT COUNT(DNI) FROM usuarios 
	WHERE DNI = dn OR
	Telefono = phone OR
	CorreoElectronico = correo;
END//
DELIMITER ;

-- Volcando estructura para tabla econx.tipousuarios
CREATE TABLE IF NOT EXISTS `tipousuarios` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Codigo` char(4) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Tipo` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla econx.tipousuarios: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `tipousuarios` DISABLE KEYS */;
INSERT INTO `tipousuarios` (`ID`, `Codigo`, `Tipo`) VALUES
	(2, '02', 'Trabajador'),
	(6, '01', 'Administrador'),
	(7, '03', 'Persona'),
	(13, '04', 'Usuario');
/*!40000 ALTER TABLE `tipousuarios` ENABLE KEYS */;

-- Volcando estructura para tabla econx.traeproducto
CREATE TABLE IF NOT EXISTS `traeproducto` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Codigo` char(10) COLLATE utf8_bin DEFAULT NULL,
  `IDProveedor` int NOT NULL DEFAULT '0',
  `IDProducto` int NOT NULL DEFAULT '0',
  `Fecha` date NOT NULL DEFAULT '2020-01-12',
  PRIMARY KEY (`ID`),
  KEY `idProductoFK` (`IDProducto`),
  KEY `IDProveedorFK` (`IDProveedor`) USING BTREE,
  CONSTRAINT `idProductoFK` FOREIGN KEY (`IDProducto`) REFERENCES `productos` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `IDProveedorFK1` FOREIGN KEY (`IDProveedor`) REFERENCES `proveedor` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla econx.traeproducto: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `traeproducto` DISABLE KEYS */;
INSERT INTO `traeproducto` (`ID`, `Codigo`, `IDProveedor`, `IDProducto`, `Fecha`) VALUES
	(1, '01', 1, 5, '2020-12-01'),
	(2, '02', 5, 3, '2020-12-01'),
	(3, '03', 4, 8, '2020-12-02'),
	(4, '04', 5, 10, '2020-12-02'),
	(5, '05', 8, 10, '2020-12-03'),
	(6, '06', 9, 11, '2020-12-03'),
	(8, '07', 12, 11, '2020-12-09'),
	(9, '08', 10, 14, '2020-12-09');
/*!40000 ALTER TABLE `traeproducto` ENABLE KEYS */;

-- Volcando estructura para tabla econx.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `DNI` char(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `Password` varchar(150) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Nombre` varchar(150) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Apellidos` varchar(150) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Telefono` char(9) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `CorreoElectronico` varchar(150) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `FechaNacimiento` date DEFAULT NULL,
  `IDTipo` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `IDTipoFK` (`IDTipo`),
  CONSTRAINT `IDTipoFK` FOREIGN KEY (`IDTipo`) REFERENCES `tipousuarios` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla econx.usuarios: ~9 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`id`, `DNI`, `Password`, `Nombre`, `Apellidos`, `Telefono`, `CorreoElectronico`, `FechaNacimiento`, `IDTipo`) VALUES
	(12, '75690432', 'jOUGWYMjkKyJ7HxufVjO5w==', 'usuario1', 'usuario1', '963210458', 'usuario1@mail.com', '2002-12-25', 2),
	(13, '75690429', 'RanTScJ1UyjhRcUwU4WSwg==', 'Ronny Harol', 'Clavijo Céspedes', '957396150', 'ronnyharol05@mail.com', '2002-05-07', 6),
	(14, '75690430', 'EEzsdjjiIDw=', 'admin2', 'admin2', '987456322', 'admin2@mail.com', '2002-11-06', 6),
	(15, '75690431', 'mIC6qrfg7E6J7HxufVjO5w==', 'usuario2', 'usuario2', '965413388', 'usuario2@mail.com', '2002-12-22', 2),
	(16, '75690433', 'nLwbV4CwkcmJ7HxufVjO5w==', 'usuario3', 'usuario3', '987441123', 'usuario3@mail.com', '2002-11-06', 2),
	(29, '00251509', 'lVUio8QRwqo=', 'Ana Maria', 'Del Pilar', '965412379', 'ana@mail.com', '2002-01-02', 7),
	(31, '75690441', '66M8uEbQVxNmTYhVMDB32Q==', 'Angel', 'Carhuatanta', '956547462', 'angel@mail.com', '2000-12-04', 6),
	(32, '75690434', '123', 'admin', 'admin', '965412365', 'admin@mail.com', '2000-01-01', 6),
	(33, '123', '123', '123', '123', '123', NULL, '2000-01-01', 6),
	(34, '75692187', '1234', 'juana', 'marquez', '965412300', 'mail@mail.com', '2002-12-10', 2),
	(35, '75690436', '12345', 'Antonella', 'Pérez', '964125300', 'antonella@mail.com', '2002-07-10', 6),
	(36, '75690437', '1236', 'Ana Maria', 'Pérez', '965412300', 'anam@mail.com', '2002-06-12', 2),
	(37, '75690410', '1235', 'Mateo', 'Fernandez', '961235407', 'mateo@mail.com', '2020-12-11', 2),
	(38, '75690401', '1234', 'María', 'Fernández', '965412030', 'maria@mail.com', '2002-03-01', 2);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
