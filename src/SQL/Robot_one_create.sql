-- source c:\temp\Robot_one_create.sql
-- source c:/temp/Robot_one_create.sql
-- Prihlasit se jako root, prazdne heslo
CREATE USER  'student'@'localhost'   IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO   'student'@'%'   IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO   'student'@'localhost'   IDENTIFIED BY 'password';

DROP DATABASE IF EXISTS Robot_one;

CREATE DATABASE Robot_one CHARACTER SET utf8mb4 COLLATE utf8mb4_czech_ci;


USE Robot_one;

CREATE TABLE Uzivatel
(
	ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Prijmeni    VARCHAR(50),
	Jmeno       VARCHAR(50),
    Role        int(11),
	Heslo_hash  VARCHAR(250)
);

CREATE TABLE Ovladani
(
	ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Klic    VARCHAR(50),
	Hodnota  VARCHAR(250)
	
);

CREATE TABLE Parametr
(
	ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Klic    VARCHAR(50),
	Hodnota  VARCHAR(250),
    Popis    VARCHAR(250)	
);


CREATE TABLE Makro
(
	ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Nazev    VARCHAR(50),
	Hodnota  VARCHAR(250),
    Popis    VARCHAR(250)	
);

 
    
    
INSERT INTO Robot_one.Uzivatel (Prijmeni, Jmeno, Role, Heslo_hash  ) VALUES ('Petr','Medved',0,'6e017b5464f820a6c1bb5e9f6d711a667a80d8ea'),('Jana','Kropackova',1,'5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
INSERT INTO Robot_one.Ovladani (Klic, Hodnota  ) VALUES ('MotorMAX','100'),('MotorMIN','-100'),('SeriallPort','COM1');
INSERT INTO Robot_one.Parametr (Klic, Hodnota, Popis  ) VALUES ('BatMIM','30.0','Min napeti akumulatoru'),('TempMAX','90.0','MAX teplota driveru');
INSERT INTO Robot_one.Makro (Nazev, Hodnota, Popis  ) VALUES ('Vzad','M 180','Otceni vzad'),('Vpred','L50,R50','Jizda dopredu'),('VZad','L-50,R-50','Jizda dozadu');