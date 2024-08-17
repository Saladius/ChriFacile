-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: chrifacile
-- ------------------------------------------------------
-- Server version	8.2.0

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
-- Table structure for table `terrain_urbain`
--

DROP TABLE IF EXISTS `terrain_urbain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `terrain_urbain` (
  `created_at` datetime(6) DEFAULT NULL,
  `id_quartier` bigint DEFAULT NULL,
  `id_terrain_urbain` bigint NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `name_autorisation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_terrain_urbain`),
  KEY `FKino61254uay2wb66yuj8axiia` (`name_autorisation`),
  KEY `FKkylknw137e46jkpi48fg1qklh` (`id_quartier`),
  CONSTRAINT `FKino61254uay2wb66yuj8axiia` FOREIGN KEY (`name_autorisation`) REFERENCES `authorization` (`name_autorisation`),
  CONSTRAINT `FKkylknw137e46jkpi48fg1qklh` FOREIGN KEY (`id_quartier`) REFERENCES `quartier` (`id_quartier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terrain_urbain`
--

LOCK TABLES `terrain_urbain` WRITE;
/*!40000 ALTER TABLE `terrain_urbain` DISABLE KEYS */;
INSERT INTO `terrain_urbain` VALUES ('2024-08-05 21:56:20.375823',1,1,NULL,'T5');
/*!40000 ALTER TABLE `terrain_urbain` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-17 22:37:20
