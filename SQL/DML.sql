-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: planit
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Dumping data for table `Projects`
--

LOCK TABLES `Projects` WRITE;
/*!40000 ALTER TABLE `Projects` DISABLE KEYS */;
INSERT INTO `Projects` VALUES (2,1,'It project','2021-11-29','2021-12-27',69,29000,30000),(3,2,'Mobile application','2021-11-01','2021-11-28',108,48000,50000);
/*!40000 ALTER TABLE `Projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Subtasks`
--

LOCK TABLES `Subtasks` WRITE;
/*!40000 ALTER TABLE `Subtasks` DISABLE KEYS */;
INSERT INTO `Subtasks` VALUES (2,2,'Domain model',4,1000),(3,2,'Use case diagram',2,500),(4,2,'Paper prototype',2,500),(5,2,'Risk management',2,500),(6,3,'Sequence diagram',4,1500),(7,3,'Database design',4,1500),(8,3,'Workshop',4,2000),(9,3,'Framework',7,2500),(10,4,'MVP',30,15000),(11,4,'Testing',6,2500),(12,4,'Design class diagram',4,1500),(13,5,'Use case diagram',2,1000),(14,5,'Domain model',4,2000),(15,5,'Paper prototype',4,2000),(16,5,'Risk management',4,2000),(17,5,'Design class diagram',4,2000),(18,6,'Design',5,2500),(19,6,'Sequence diagram',4,1500),(20,6,'Testing',4,1500),(21,6,'Framework ',6,2500),(25,7,'Minimal viable product',24,10000),(26,7,'Testing',3,1500),(27,7,'Bug fixes',8,3000),(29,8,'Review',8,2000),(30,8,'Finishing touches',4,1000),(31,8,'Beta testing ',10,2500),(33,8,'Social media comercials',6,6000),(34,8,'Launch the mobile app',4,2500),(35,8,'Launch celebration',4,2500);
/*!40000 ALTER TABLE `Subtasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Tasks`
--

LOCK TABLES `Tasks` WRITE;
/*!40000 ALTER TABLE `Tasks` DISABLE KEYS */;
INSERT INTO `Tasks` VALUES (2,2,'iteration 1','2021-11-29','2021-12-03',10,2500),(3,2,'iteration 2','2021-12-06','2021-12-10',19,7500),(4,2,'iteration 3','2021-12-13','2021-12-17',40,19000),(5,3,'Inception phase','2021-11-01','2021-11-07',18,9000),(6,3,'Elaboration phase','2021-11-08','2021-11-12',19,8000),(7,3,'Construction phase','2021-11-15','2021-11-19',35,14500),(8,3,'Transition phase','2021-11-22','2021-11-26',36,16500);
/*!40000 ALTER TABLE `Tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'Tine','tima@kea.dk','1234'),(2,'Marianne','mn@kea.dk','4321');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-15 19:46:14
