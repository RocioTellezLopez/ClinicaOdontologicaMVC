DROP TABLE IF EXISTS DOMICILIOS;
CREATE TABLE DOMICILIOS (
ID INT AUTO_INCREMENT PRIMARY KEY,
CALLE VARCHAR(50) NOT NULL,
NUMERO INT NOT NULL,
LOCALIDAD VARCHAR(50) NOT NULL,
PROVINCIA VARCHAR(50) NOT NULL);

DROP TABLE IF EXISTS PACIENTES;
CREATE TABLE PACIENTES(
ID INT AUTO_INCREMENT PRIMARY KEY,
APELLIDO VARCHAR(50) NOT NULL,
NOMBRE VARCHAR(50) NOT NULL,
DNI VARCHAR(50) NOT NULL,
FECHA_INGRESO DATE NOT NULL,
ID_DOMICILIO INT);

DROP TABLE IF EXISTS ODONTOLOGOS;
CREATE TABLE ODONTOLOGOS(
ID INT AUTO_INCREMENT PRIMARY KEY,
NRO_MATRICULA INT NOT NULL,
NOMBRE VARCHAR(50) NOT NULL,
APELLIDO VARCHAR(50) NOT NULL);

INSERT INTO DOMICILIOS VALUES (DEFAULT, 'SIEMPRE VIVA', 123, 'SAN PEDRO','JUJUY');
INSERT INTO PACIENTES VALUES (DEFAULT, 'COSME','FULANITO', '1545646', '2024-02-15', 1);

INSERT INTO DOMICILIOS VALUES (DEFAULT, 'SIEMPRE VIVA', 111, 'TILCARA','JUJUY');
INSERT INTO PACIENTES VALUES (DEFAULT, 'COSME','MENGANITO', '464646', '2024-02-16', 2);

INSERT INTO DOMICILIOS VALUES (DEFAULT, 'LAS FLORES', 111, 'MUY LEJANO','CALIFORNIA');
INSERT INTO PACIENTES VALUES (DEFAULT, 'ROFRIGUEZ','PEDRO', '123456', '2024-06-19', 3);

INSERT INTO ODONTOLOGOS VALUES (DEFAULT, 123456, 'MANUELA', 'JIMENEZ');
INSERT INTO ODONTOLOGOS VALUES (DEFAULT, 456789, 'COSMO', 'SOSA');
