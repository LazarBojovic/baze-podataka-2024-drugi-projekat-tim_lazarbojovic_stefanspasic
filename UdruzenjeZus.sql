-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: udruzenjezus
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) DEFAULT NULL,
  `prezime` varchar(45) DEFAULT NULL,
  `korisnicko_ime` varchar(45) DEFAULT NULL,
  `lozinka` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `korisnicko_ime_UNIQUE` (`korisnicko_ime`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (1,'Lazar','Bojovic','BOJo','1234'),(2,'Vid','Plavsic','Vidoje','1234'),(3,'Jaja','Ova','jajara','1234'),(4,'Marko','Petrovic','Markic','1234'),(5,'Nebojsa','Bojovic','Shoyo','1234'),(6,'Milica','jajaj','Ckomi','1234'),(7,'Andrea','Jovanovic','Andreoti','1234'),(8,'Bojan','Rujevic','Konkvest','1234'),(9,'Petar','Petrovic','Pera','1234');
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kupovina`
--

DROP TABLE IF EXISTS `kupovina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kupovina` (
  `id` int NOT NULL AUTO_INCREMENT,
  `korisnik_id` int DEFAULT NULL,
  `objekat_id` int DEFAULT NULL,
  `polazak` date DEFAULT NULL,
  `dolazak` date DEFAULT NULL,
  `brVozila` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `korisnik_kupovina__fk_idx` (`korisnik_id`),
  KEY `objekat_kupovina__fk_idx` (`objekat_id`),
  CONSTRAINT `korisnik_kupovina__fk` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnik` (`id`),
  CONSTRAINT `objekat_kupovina__fk` FOREIGN KEY (`objekat_id`) REFERENCES `objekat` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kupovina`
--

LOCK TABLES `kupovina` WRITE;
/*!40000 ALTER TABLE `kupovina` DISABLE KEYS */;
INSERT INTO `kupovina` VALUES (10,9,3,'2024-06-07','2024-06-19','YO2529U'),(11,9,5,'2024-06-11','2024-06-25','qw25176');
/*!40000 ALTER TABLE `kupovina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `misija`
--

DROP TABLE IF EXISTS `misija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `misija` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tip_Misije` varchar(45) NOT NULL,
  `telo_id` int DEFAULT NULL,
  `pocetak` datetime DEFAULT NULL,
  `kraj` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idMisija_UNIQUE` (`id`),
  KEY `planeta__fk_idx` (`telo_id`),
  CONSTRAINT `planeta_misija__fk` FOREIGN KEY (`telo_id`) REFERENCES `svemirskotelo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='																						';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `misija`
--

LOCK TABLES `misija` WRITE;
/*!40000 ALTER TABLE `misija` DISABLE KEYS */;
INSERT INTO `misija` VALUES (1,'brza',1,'2100-03-01 04:30:00','2101-11-12 11:30:28'),(2,'detaljna',1,'2100-03-01 04:30:00','2101-11-12 11:30:28'),(3,'brza',2,'2100-03-01 04:30:00','2101-11-12 11:30:28'),(4,'detaljna',2,'2100-03-01 04:30:00','2101-11-12 11:30:28'),(5,'brza',3,'2100-03-01 04:30:00','2101-11-12 11:30:28'),(6,'detaljna',3,'2100-03-01 04:30:00','2101-11-12 11:30:28'),(7,'brza',9,'2100-03-01 04:30:00','2101-11-12 11:30:28'),(8,'detaljna',9,'2100-03-01 04:30:00','2101-11-12 11:30:28'),(9,'brza',10,'2100-03-01 04:30:00','2101-11-12 11:30:28'),(10,'detaljna',10,'2100-03-01 04:30:00','2101-11-12 11:30:28'),(11,'detaljna',11,'2100-03-01 04:30:00','2101-11-12 11:30:28'),(12,'detaljna',12,'2100-03-01 04:30:00','2101-11-12 11:30:28'),(13,'detaljna',12,'2100-03-01 04:30:00','2101-11-12 11:30:28');
/*!40000 ALTER TABLE `misija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objekat`
--

DROP TABLE IF EXISTS `objekat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `objekat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) DEFAULT NULL,
  `tip` varchar(45) DEFAULT NULL,
  `cena` double DEFAULT NULL,
  `povrsina` double DEFAULT NULL,
  `telo_id` int DEFAULT NULL,
  `kupljen` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `planeta_stan__fk_idx` (`telo_id`),
  CONSTRAINT `planeta_stan__fk` FOREIGN KEY (`telo_id`) REFERENCES `svemirskotelo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objekat`
--

LOCK TABLES `objekat` WRITE;
/*!40000 ALTER TABLE `objekat` DISABLE KEYS */;
INSERT INTO `objekat` VALUES (1,'Domos','Kuca',500000,1000,3,0),(2,'Pectus','Stan',10000,250,9,0),(3,'Lectum','Kuca',100000,550,1,1),(4,'Cubiculum','Garsonjera',15000,180,2,0),(5,'Quisquiliae','Stan',9000,220,1,1),(6,'Grande','Kuca',240000,580,10,0),(7,'Premium Villa','Kuca',220000,400,15,0),(8,'Centar','Stan',10000,200,14,0),(9,'Lep Pogled','Stan',5600,250,13,0),(10,'Entire Estate','Kuca',350000,700,12,0),(11,'maisonette','Stan',7200,110,11,0),(12,'Heras','Kuca',1000000,1000,2,0);
/*!40000 ALTER TABLE `objekat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `svemirskotelo`
--

DROP TABLE IF EXISTS `svemirskotelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `svemirskotelo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) DEFAULT NULL,
  `tip` varchar(45) DEFAULT NULL,
  `istrazen` tinyint NOT NULL,
  `nastanjiva` tinyint NOT NULL,
  `udaljenost` varchar(45) DEFAULT NULL,
  `niza_temp` double DEFAULT NULL,
  `visa_temp` double DEFAULT NULL,
  `procenat_kiseonika` double DEFAULT NULL,
  `procenat_gasa` double DEFAULT NULL,
  `gravitacija` double DEFAULT NULL,
  `brzina_orbitiranja` double DEFAULT NULL,
  `br_umrlih` int DEFAULT NULL,
  `godina_istrazivanja` int DEFAULT NULL,
  `telo_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `planeta__fk_idx` (`telo_id`),
  CONSTRAINT `svemirsko_telo__fk` FOREIGN KEY (`telo_id`) REFERENCES `svemirskotelo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `svemirskotelo`
--

LOCK TABLES `svemirskotelo` WRITE;
/*!40000 ALTER TABLE `svemirskotelo` DISABLE KEYS */;
INSERT INTO `svemirskotelo` VALUES (1,'Mars','planeta',1,1,'1',1,1,1,1,1,1,1,1,NULL),(2,'Phobos','satelit',1,1,'1',1,1,1,1,1,1,1,1,1),(3,'Uranus','planeta',1,1,'1',1,1,1,1,1,1,1,1,NULL),(9,'Pluto','planeta',1,1,'1',1,1,1,1,1,1,1,1,NULL),(10,'Jupiter','planeta',1,1,'1',1,1,1,1,1,1,1,1,NULL),(11,'Io','satelit',1,1,'1',1,1,1,1,1,1,1,1,10),(12,'Ariel','satelit',1,1,'1',1,1,1,1,1,1,1,1,3),(13,'Francisco','satelit',1,1,'1',1,1,1,1,1,1,1,1,3),(14,'Mercury','planeta',1,0,'1',1,1,1,1,1,1,1,1,NULL),(15,'Venus','planeta',0,0,'1',1,1,1,1,1,1,1,1,NULL);
/*!40000 ALTER TABLE `svemirskotelo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-08 19:36:59
