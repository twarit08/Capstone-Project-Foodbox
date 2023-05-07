-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: foodbox
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `pid` bigint NOT NULL AUTO_INCREMENT,
  `cuisine_type` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `diet_type` varchar(255) DEFAULT NULL,
  `is_available` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `recommendation` varchar(255) DEFAULT NULL,
  `sub_type` varchar(255) DEFAULT NULL,
  `product_image_img_id` bigint DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `FKa2epoka3yrgstyhhn83cvrj18` (`product_image_img_id`),
  CONSTRAINT `FKa2epoka3yrgstyhhn83cvrj18` FOREIGN KEY (`product_image_img_id`) REFERENCES `product_image` (`img_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'SOUTH INDIAN','Masala dosa are crispy, soft, savory and healthy crepes made with a rice and lentil batter stuffed with a savory, wonderfully spiced potato and onion filling.','Vegetarian',_binary '','Masala Dosa',170,'BEST SELLER','Dosas',1),(3,'SOUTH INDIAN','Hearty, satisfying, comforting and a healthy meal of soft fluffy Rice Idlis (2 pieces) served with savory, spiced and lightly tangy sambar.','Vegetarian',_binary '','Idli Sambar',60,'BEST SELLER','Idlis',3),(4,'NORTH INDIAN','One of the most popular Punjabi dish consisting of chickpea curry and bhatura(2 piece) a soft and fluffy fried leavened bread.','Vegetarian',_binary '','Chole Bhature',190,'MUST TRY','Punjabi Cuisine',4),(5,'NORTH INDIAN','Aloo paratha (2 pieces) is a paratha stuffed with a special potato filling known as Alu Bharta. It is traditionally eaten for breakfast.','Vegetarian',_binary '','Aloo Parantha',102,'BEST SELLER','Punjabi Cuisine',5),(6,'GUJARATI','Khaman dhokla is a soft and fluffy steamed snack from the Gujarati repertoire.','Vegetarian',_binary '','Khaman Dhokla',98,'BEST SELLER','Dhoklas',6),(7,'ITALIAN','Delightful combination of onion, capsicum, tomato & grilled mushroom with new hand tossed crust. (Medium Size)','Vegetarian',_binary '','Farm House Pizza',469,'MUST TRY','Pizzas',7),(8,'CHINESE','Julian cut chilly with roasted garlic wok tossed with classic noodles. ','Vegetarian',_binary '','Chili Garlic Noodles',260,'BEST SELLER','Chowmein',8),(9,'MEXICAN','Mexican Rice, also known as Arroz Rojo or Spanish Rice, is a zesty and flavorful vegan dish.','Vegetarian',_binary '','Mexican Rice',350,'MUST TRY','Rices',9),(11,'ITALIAN','The awesome foursome! Golden corn, black olives, capsicum, red paprika. (Medium Size).','Vegetarian',_binary '','Veggie Paradise Pizza',415,'BEST SELLER','Pizzas',11);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-07 16:31:12
