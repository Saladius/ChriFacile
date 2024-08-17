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
-- Table structure for table `appart`
--

DROP TABLE IF EXISTS `appart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appart` (
  `etage_max` int DEFAULT NULL,
  `nbr_chambre_max` int DEFAULT NULL,
  `nbr_chambre_min` int DEFAULT NULL,
  `nbr_salle_de_bain_max` int DEFAULT NULL,
  `nbr_salle_de_bain_min` int DEFAULT NULL,
  `annonce_id_annonce` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `id_appart` bigint NOT NULL,
  `id_quartier` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id_appart`),
  UNIQUE KEY `UK_pfd4elv95khuuov3i61wcd9sr` (`annonce_id_annonce`),
  KEY `FK26yogyqdu8idchwchvkn8bcd1` (`id_quartier`),
  CONSTRAINT `FK26yogyqdu8idchwchvkn8bcd1` FOREIGN KEY (`id_quartier`) REFERENCES `quartier` (`id_quartier`),
  CONSTRAINT `FKchkes116kpa5rilciay8wsvm6` FOREIGN KEY (`annonce_id_annonce`) REFERENCES `annonce` (`id_annonce`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appart`
--

LOCK TABLES `appart` WRITE;
/*!40000 ALTER TABLE `appart` DISABLE KEYS */;
INSERT INTO `appart` VALUES (3,7,2,7,2,NULL,'2024-08-05 21:55:57.019825',1,1,NULL),(5,3,1,2,1,NULL,'2024-08-05 21:56:28.807824',2,2,NULL);
/*!40000 ALTER TABLE `appart` ENABLE KEYS */;
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
