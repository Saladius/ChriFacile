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
-- Table structure for table `annonce`
--

DROP TABLE IF EXISTS `annonce`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `annonce` (
  `superficie_max` double DEFAULT NULL,
  `superficie_min` double DEFAULT NULL,
  `unites` tinyint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `id_annonce` bigint NOT NULL AUTO_INCREMENT,
  `id_appart` bigint DEFAULT NULL,
  `id_terrain_agricole` bigint DEFAULT NULL,
  `id_terrain_urbain` bigint DEFAULT NULL,
  `id_ville` bigint DEFAULT NULL,
  `prix_max` bigint DEFAULT NULL,
  `prix_min` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `type` enum('TYPE_APPARTEMENT','TYPE_TERRAIN_AGRICOLE','TYPE_TERRAIN_URBAIN','TYPE_VILLA') DEFAULT NULL,
  `nom_acheteur` varchar(255) DEFAULT NULL,
  `num_telephone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_annonce`),
  UNIQUE KEY `UK_gdoqer2rd7b7uus02ig8ic77b` (`id_appart`),
  UNIQUE KEY `UK_q2006dp8qqm9wb8v7wpro6nue` (`id_terrain_agricole`),
  UNIQUE KEY `UK_kiemepeleo9884lv7qj5o5ii` (`id_terrain_urbain`),
  KEY `FKtmgqyojxovt81rj8i8g7j227k` (`id_ville`),
  CONSTRAINT `FK1oudm56hnaoqpv50qw4ow93cy` FOREIGN KEY (`id_appart`) REFERENCES `appart` (`id_appart`),
  CONSTRAINT `FKi7fbs5nbalp6ygbsxdv1n92mb` FOREIGN KEY (`id_terrain_urbain`) REFERENCES `terrain_urbain` (`id_terrain_urbain`),
  CONSTRAINT `FKnsy2xvxt97r9ypxej2wfbb2sc` FOREIGN KEY (`id_terrain_agricole`) REFERENCES `terrain_agricole` (`id_terrain_agricole`),
  CONSTRAINT `FKtmgqyojxovt81rj8i8g7j227k` FOREIGN KEY (`id_ville`) REFERENCES `ville` (`id_ville`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `annonce`
--

LOCK TABLES `annonce` WRITE;
/*!40000 ALTER TABLE `annonce` DISABLE KEYS */;
INSERT INTO `annonce` VALUES (200,60,0,'2024-08-05 21:55:57.007826',1,1,NULL,NULL,1,3000000,0,NULL,'TYPE_APPARTEMENT',NULL,NULL),(200,60,0,'2024-08-05 21:56:20.371823',2,NULL,NULL,1,1,3000000,0,NULL,'TYPE_TERRAIN_URBAIN',NULL,NULL),(120,60,0,'2024-08-05 21:56:28.803832',3,2,NULL,NULL,1,NULL,NULL,NULL,'TYPE_APPARTEMENT',NULL,NULL);
/*!40000 ALTER TABLE `annonce` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-17 22:37:21
