-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (x86_64)
--
-- Host: 172.19.140.94    Database: manage_property
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zipcode` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Fairfield','Arizona','1000 N 4 ST',52557),(2,'Fairfield','Alabama','1000 N 4 ST',52557),(3,'Fairfield','Alabama','1000 N 4 ST',52557),(4,'Fairfield','Alabama','1000 N 4 ST',52557),(5,'Fairfield','Arizona','1000 N 4 ST',52557),(6,'Fairfield','Arizona','1000 N 4 ST',52557),(7,'Fairfield','Alabama','1000 N 4 ST',52557),(8,'Fairfield','Alabama','1000 N 4 ST',52557),(9,'Fairfield','Alabama','1000 N 4 ST',52557),(10,'Fairfield','Alabama','1000 N 4 ST',52557),(11,'Fairfield','Arizona','1000 N 4 ST',52557),(12,'Fairfield','Alabama','1000 N 4 ST',52557),(13,'Fairfield','Alabama','1000 N 4 ST',52557),(14,'Fairfield','Alabama','1000 N 4 ST',52557),(15,'Stephens City','VA','117 Savannah Way',22655),(16,'Stephens City','VA','117 Savannah Way',22655),(18,'Fairfield','Alabama','1000 N 4 ST',52557),(19,'Fairfield','Arizona','1000 N 4 ST',52557),(20,'Fairfield','Iowa','1000 N 4 ST',52557),(21,'Fairfield','Iowa','1000 N 4 ST',52557),(22,'Fairfield','Iowa','1000 N 4 ST',52557),(23,'Fairfield','Alabama','1000 N 4 ST',52557);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assets`
--

DROP TABLE IF EXISTS `assets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assets` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `source` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assets`
--

LOCK TABLES `assets` WRITE;
/*!40000 ALTER TABLE `assets` DISABLE KEYS */;
/*!40000 ALTER TABLE `assets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `property_id` binary(16) DEFAULT NULL,
  `users_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrs1g9jcmat9q583e16dgh2c2f` (`property_id`),
  KEY `FKfd0ajq04evbjv655sp4b7oalo` (`users_id`),
  CONSTRAINT `FKfd0ajq04evbjv655sp4b7oalo` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKrs1g9jcmat9q583e16dgh2c2f` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite`
--

LOCK TABLES `favorite` WRITE;
/*!40000 ALTER TABLE `favorite` DISABLE KEYS */;
INSERT INTO `favorite` VALUES (5,_binary 'Ù¿¥\ﬁBïæi,&º˝',2),(10,_binary 'èqÚÅ\Ê\◊Mlá®d/ˆ}',2),(12,_binary '¨\Ô\Ë t\√M€æ	•7w\œ\‘.',2),(13,_binary 'ı\‡±|rF£ç!b_\ÃX’∑',2);
/*!40000 ALTER TABLE `favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property` (
  `id` binary(16) NOT NULL,
  `built_year` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `property_status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  `assets_id` bigint DEFAULT NULL,
  `owner_id` bigint DEFAULT NULL,
  `property_detail_id` bigint DEFAULT NULL,
  `property_option_id` bigint DEFAULT NULL,
  `property_type_id` bigint DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgcduyfiunk1ewg7920pw4l3o9` (`address_id`),
  KEY `FK8jjua2jr72dbnd4x56l4o58e5` (`assets_id`),
  KEY `FKqje3em0djsxgnxwy7klv6yju9` (`owner_id`),
  KEY `FK7fnh5hvhe3h95pynrx7xxwrgd` (`property_detail_id`),
  KEY `FK4u2udlecxegeb2jb67r52o9rb` (`property_option_id`),
  KEY `FKeqi0saw6kypvbw7tf91w64kc6` (`property_type_id`),
  CONSTRAINT `FK4u2udlecxegeb2jb67r52o9rb` FOREIGN KEY (`property_option_id`) REFERENCES `property_option` (`id`),
  CONSTRAINT `FK7fnh5hvhe3h95pynrx7xxwrgd` FOREIGN KEY (`property_detail_id`) REFERENCES `property_detail` (`id`),
  CONSTRAINT `FK8jjua2jr72dbnd4x56l4o58e5` FOREIGN KEY (`assets_id`) REFERENCES `assets` (`id`),
  CONSTRAINT `FKeqi0saw6kypvbw7tf91w64kc6` FOREIGN KEY (`property_type_id`) REFERENCES `property_type` (`id`),
  CONSTRAINT `FKgcduyfiunk1ewg7920pw4l3o9` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKqje3em0djsxgnxwy7klv6yju9` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property`
--

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
INSERT INTO `property` VALUES (_binary 'ı\‡±|rF£ç!b_\ÃX’∑',2023,150,'AVAILABLE','H3 ',23,NULL,1,23,1,2,_binary ''),(_binary ',)Ldz¯NsÇÄ\n°ß%\Ê∏',2023,150,'AVAILABLE','R1',19,NULL,1,19,2,2,_binary ''),(_binary '/ElÙnD0¨Lq;\∆yA!',2020,250000,'CONTINGENT','R4',22,NULL,1,22,1,3,_binary ''),(_binary 'x\Ôt\ {Z@\Á∫\Ôå_ª”£!',1992,NULL,'AVAILABLE','Something on title',16,NULL,1,16,1,1,NULL),(_binary 'Ç wv\ \‡O\Ëº3§(¨CâL',2023,7,'CONTINGENT','H1',11,NULL,1,11,1,1,_binary ''),(_binary 'èqÚÅ\Ê\◊Mlá®d/ˆ}',2023,150,'CONTINGENT','H4 ',12,NULL,1,12,2,2,_binary ''),(_binary '¨\Ô\Ë t\√M€æ	•7w\œ\‘.',2023,150,'CONTINGENT','H5',5,NULL,1,5,2,2,_binary ''),(_binary '\ \r¿)éîI	´;\€¯J\\\ﬁ',1992,NULL,'AVAILABLE','Something on title',15,NULL,1,15,1,1,_binary ''),(_binary '\Ã5∑\ÃyûHY£;›®°b«ê',2020,1500000,'AVAILABLE','R2',20,NULL,1,20,1,3,NULL),(_binary '\ﬂ\‰Ñ=E,Ç\’\œD\√\Î@\ﬁ',2018,15000000,'AVAILABLE','R3',21,NULL,1,21,2,2,_binary ''),(_binary 'Ù¿¥\ﬁBïæi,&º˝',2023,150,'AVAILABLE','H2',13,NULL,1,13,1,2,_binary ''),(_binary '˜ÃÇoUãKŸÑQÆéÆ…úT',2023,250000,'AVAILABLE','2BHK @ Farifield',18,NULL,1,18,2,2,NULL);
/*!40000 ALTER TABLE `property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_application`
--

DROP TABLE IF EXISTS `property_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property_application` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `offer_price` double DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `submission_date` date DEFAULT NULL,
  `property_id` binary(16) DEFAULT NULL,
  `users_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe3ll0o1g52bk8te8uxm7jotrs` (`property_id`),
  KEY `FKfiujx9fekpyw0fn3mw7f0xu2y` (`users_id`),
  CONSTRAINT `FKe3ll0o1g52bk8te8uxm7jotrs` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`),
  CONSTRAINT `FKfiujx9fekpyw0fn3mw7f0xu2y` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_application`
--

LOCK TABLES `property_application` WRITE;
/*!40000 ALTER TABLE `property_application` DISABLE KEYS */;
INSERT INTO `property_application` VALUES (1,5,'Interested','REJECTED',NULL,_binary 'Ç wv\ \‡O\Ëº3§(¨CâL',2),(2,120,'Interested','REJECTED',NULL,_binary '¨\Ô\Ë t\√M€æ	•7w\œ\‘.',2),(3,120,'Interested','REJECTED',NULL,_binary 'ı\‡±|rF£ç!b_\ÃX’∑',2),(4,5,'Interested','REJECTED',NULL,_binary 'Ç wv\ \‡O\Ëº3§(¨CâL',2),(5,7,'S','APPROVED',NULL,_binary 'Ç wv\ \‡O\Ëº3§(¨CâL',2),(6,130,'SAS','APPROVED',NULL,_binary 'èqÚÅ\Ê\◊Mlá®d/ˆ}',2),(7,100,'Interested','PENDING',NULL,_binary 'Ù¿¥\ﬁBïæi,&º˝',2),(8,240000,'Interested','CONTRACTED',NULL,_binary '/ElÙnD0¨Lq;\∆yA!',2),(9,140,'Interested','REJECTED',NULL,_binary '¨\Ô\Ë t\√M€æ	•7w\œ\‘.',2),(10,123000,'Give me your house','REJECTED',NULL,_binary '¨\Ô\Ë t\√M€æ	•7w\œ\‘.',3),(11,123000,'Give me your house','REJECTED',NULL,_binary '¨\Ô\Ë t\√M€æ	•7w\œ\‘.',3),(12,140,'RAS','CONTRACTED',NULL,_binary '¨\Ô\Ë t\√M€æ	•7w\œ\‘.',2);
/*!40000 ALTER TABLE `property_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_detail`
--

DROP TABLE IF EXISTS `property_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `area` float DEFAULT NULL,
  `bath` smallint DEFAULT NULL,
  `bed` smallint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `features` varchar(255) DEFAULT NULL,
  `has_basement` bit(1) DEFAULT NULL,
  `has_parking` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_detail`
--

LOCK TABLES `property_detail` WRITE;
/*!40000 ALTER TABLE `property_detail` DISABLE KEYS */;
INSERT INTO `property_detail` VALUES (1,7,3,4,'H1',NULL,_binary '',_binary ''),(2,250,2,2,'H2',NULL,_binary '\0',_binary '\0'),(3,150,3,5,'H3',NULL,_binary '',_binary ''),(4,150,3,3,'H4',NULL,_binary '\0',_binary '\0'),(5,150,5,5,'H5',NULL,_binary '\0',_binary '\0'),(6,7,3,4,'H1',NULL,_binary '',_binary ''),(7,250,2,2,'H2',NULL,_binary '\0',_binary '\0'),(8,150,3,3,'H4',NULL,_binary '\0',_binary '\0'),(9,150,3,5,'H3',NULL,_binary '',_binary ''),(10,150,3,5,'H3',NULL,_binary '',_binary ''),(11,7,3,4,'H1 - edited',NULL,_binary '',_binary ''),(12,150,3,3,'H4',NULL,_binary '\0',_binary '\0'),(13,250,2,2,'H2',NULL,_binary '\0',_binary '\0'),(14,150,3,5,'H3 Edited',NULL,_binary '',_binary ''),(15,1214,4,2,'Some description about the property.','Features are limitless',_binary '',NULL),(16,1214,4,2,'Some description about the property.','Features are limitless',_binary '',NULL),(18,15000,0,0,'A small house',NULL,_binary '\0',_binary '\0'),(19,150,5,4,'R1',NULL,_binary '\0',_binary '\0'),(20,1520,5,5,'R2',NULL,_binary '\0',_binary '\0'),(21,150,5,5,'R3',NULL,_binary '\0',_binary '\0'),(22,250,5,5,'R4',NULL,_binary '\0',_binary '\0'),(23,150,3,5,'H3 Edited',NULL,_binary '',_binary '');
/*!40000 ALTER TABLE `property_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_favorites`
--

DROP TABLE IF EXISTS `property_favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property_favorites` (
  `property_id` binary(16) NOT NULL,
  `favorites_id` bigint NOT NULL,
  UNIQUE KEY `UK_9s0hvplv3rmusjlpnw95pe7fn` (`favorites_id`),
  KEY `FKonv5hntb26bf57ew9rsrjbtlh` (`property_id`),
  CONSTRAINT `FKonv5hntb26bf57ew9rsrjbtlh` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`),
  CONSTRAINT `FKr65xnxa4m9iepk4urw0aiilxd` FOREIGN KEY (`favorites_id`) REFERENCES `favorite` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_favorites`
--

LOCK TABLES `property_favorites` WRITE;
/*!40000 ALTER TABLE `property_favorites` DISABLE KEYS */;
/*!40000 ALTER TABLE `property_favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_option`
--

DROP TABLE IF EXISTS `property_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property_option` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_option`
--

LOCK TABLES `property_option` WRITE;
/*!40000 ALTER TABLE `property_option` DISABLE KEYS */;
INSERT INTO `property_option` VALUES (1,'SALES'),(2,'RENTAL'),(3,'MORTGAGE');
/*!40000 ALTER TABLE `property_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_type`
--

DROP TABLE IF EXISTS `property_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_type`
--

LOCK TABLES `property_type` WRITE;
/*!40000 ALTER TABLE `property_type` DISABLE KEYS */;
INSERT INTO `property_type` VALUES (1,'Condo'),(2,'House'),(3,'Apartments'),(4,'Townhomes'),(5,'Multi-family'),(6,'Manufactured');
/*!40000 ALTER TABLE `property_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_view`
--

DROP TABLE IF EXISTS `property_view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property_view` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ip_address` varchar(255) DEFAULT NULL,
  `property_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfwsois02u6lwip94al24v8v7c` (`property_id`),
  CONSTRAINT `FKfwsois02u6lwip94al24v8v7c` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_view`
--

LOCK TABLES `property_view` WRITE;
/*!40000 ALTER TABLE `property_view` DISABLE KEYS */;
INSERT INTO `property_view` VALUES (1,'172.19.140.216',NULL),(2,'172.19.140.216',_binary '¨\Ô\Ë t\√M€æ	•7w\œ\‘.'),(3,'172.19.140.36',NULL),(4,'172.19.140.36',NULL),(5,'0:0:0:0:0:0:0:1',_binary 'Ç wv\ \‡O\Ëº3§(¨CâL'),(6,'0:0:0:0:0:0:0:1',_binary 'èqÚÅ\Ê\◊Mlá®d/ˆ}'),(7,'0:0:0:0:0:0:0:1',_binary 'ı\‡±|rF£ç!b_\ÃX’∑'),(8,'0:0:0:0:0:0:0:1',_binary '¨\Ô\Ë t\√M€æ	•7w\œ\‘.'),(9,'172.19.140.116',_binary '¨\Ô\Ë t\√M€æ	•7w\œ\‘.'),(10,'172.19.140.36',_binary 'ı\‡±|rF£ç!b_\ÃX’∑'),(11,'172.19.140.36',_binary 'ı\‡±|rF£ç!b_\ÃX’∑'),(12,'172.19.140.36',_binary 'Ç wv\ \‡O\Ëº3§(¨CâL'),(13,'172.19.140.36',_binary 'Ç wv\ \‡O\Ëº3§(¨CâL'),(14,'172.19.140.36',_binary 'èqÚÅ\Ê\◊Mlá®d/ˆ}'),(15,'172.19.140.36',_binary 'èqÚÅ\Ê\◊Mlá®d/ˆ}'),(16,'172.19.140.36',_binary 'Ù¿¥\ﬁBïæi,&º˝'),(17,'172.19.140.36',_binary 'Ù¿¥\ﬁBïæi,&º˝'),(18,'172.19.140.36',_binary '\ﬂ\‰Ñ=E,Ç\’\œD\√\Î@\ﬁ'),(19,'172.19.140.36',_binary '\ﬂ\‰Ñ=E,Ç\’\œD\√\Î@\ﬁ'),(20,'172.19.140.36',_binary '/ElÙnD0¨Lq;\∆yA!'),(21,'172.19.140.36',_binary '/ElÙnD0¨Lq;\∆yA!'),(22,'172.19.140.216',_binary 'ı\‡±|rF£ç!b_\ÃX’∑'),(23,'172.19.140.216',_binary '/ElÙnD0¨Lq;\∆yA!'),(24,'172.19.140.216',_binary ',)Ldz¯NsÇÄ\n°ß%\Ê∏'),(25,'172.19.140.216',_binary '\ \r¿)éîI	´;\€¯J\\\ﬁ'),(26,'172.19.140.36',_binary ',)Ldz¯NsÇÄ\n°ß%\Ê∏'),(27,'172.19.140.36',_binary '¨\Ô\Ë t\√M€æ	•7w\œ\‘.');
/*!40000 ALTER TABLE `property_view` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'CUSTOMER'),(3,'OWNER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `is_verified` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'navraj@lovely.com','Navraj',_binary '',_binary '\0','Khanal',NULL,'$2a$10$nYfs4H09KWJxVkDnhgXJ1OXe79MhLzfHmSRiAh.VbRr/GMMqi9m8C','navraj.khanal',_binary ''),(2,'rasikkunwar31@gmail.com','Rasik',_binary '',_binary '\0','Kunwar',NULL,'$2a$10$w/hImTsJy3qfNvzcwibEzu2/nwSlTaIGb8Tun..EqnZedPW/SHHAO','rasikkunwar31',_binary ''),(3,'admin@admin.com','Admin',_binary '',_binary '\0','Admin',NULL,'$2a$10$Vsdst85pbiZ6r3Ns8qcR7u1.0xHU8JKT7JF3/OEhWt1UmIS.0/taG','admin',_binary ''),(5,'amit@gmail.com','Amit',_binary '',_binary '\0','Yadav',NULL,'$2a$10$4fNCn1lLGI2ME9WUcqNeDOkeCKX3Qj4UC2kslS1gA8kwTVLSHzhDy','amit123',_binary ''),(6,'bibek@gmail.com','Bibek',_binary '',_binary '\0','Bibek',NULL,'$2a$10$6NiAE5.TOqcukpGsLvkAquJNssrm0niBLrlD4OtrPQ/XH9GfFsLV2','bibek',_binary ''),(7,'abhay@gmail.com','Abhay',_binary '',_binary '\0','Abhay',NULL,'$2a$10$KM2hvfHI9WZvzMbT4RSeCOGE9wPGMKbdATx/7DjK7aeF4WwYFtH0W','abhay',_binary '');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `users_id` bigint NOT NULL,
  `roles_id` bigint NOT NULL,
  KEY `FK15d410tj6juko0sq9k4km60xq` (`roles_id`),
  KEY `FKml90kef4w2jy7oxyqv742tsfc` (`users_id`),
  CONSTRAINT `FK15d410tj6juko0sq9k4km60xq` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKml90kef4w2jy7oxyqv742tsfc` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,3),(2,2),(3,1),(5,3),(6,3),(7,3);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-09 17:14:20
