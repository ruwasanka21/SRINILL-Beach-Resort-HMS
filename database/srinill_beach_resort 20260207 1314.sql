-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.6.26-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema srinill_beach_resort
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ srinill_beach_resort;
USE srinill_beach_resort;

--
-- Table structure for table `srinill_beach_resort`.`booking`
--

DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking` (
  `booking_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `customer_tel_no` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `customer_address` varchar(450) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `customer_nic` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `booking_date` date NOT NULL,
  `checkin_date` date NOT NULL,
  `checkout_date` date NOT NULL,
  `no_of_pax` int(11) NOT NULL,
  `child` int(11) DEFAULT '0',
  `booking_from` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `room_no` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `room_price` decimal(10,2) NOT NULL,
  `total_price` decimal(10,2) NOT NULL,
  `remark` varchar(800) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `actual_checkin` datetime DEFAULT NULL,
  `actual_checkout` datetime DEFAULT NULL,
  `checkout_subtotal` decimal(10,2) DEFAULT '0.00',
  `additional_charges` decimal(10,2) DEFAULT '0.00',
  `additional_note` varchar(800) CHARACTER SET utf8mb4 DEFAULT NULL,
  `discount_amount` decimal(10,2) DEFAULT '0.00',
  `grand_total` decimal(10,2) DEFAULT '0.00',
  `payment_method` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cash_payment` decimal(10,2) DEFAULT '0.00',
  `card_payment` decimal(10,2) DEFAULT '0.00',
  `balance` decimal(10,2) DEFAULT '0.00',
  `booking_status` tinyint(1) DEFAULT '1',
  `booking_last_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`booking_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `srinill_beach_resort`.`booking`
--

/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;


--
-- Table structure for table `srinill_beach_resort`.`login_detail`
--

DROP TABLE IF EXISTS `login_detail`;
CREATE TABLE `login_detail` (
  `login_detail_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login_detail_username` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `login_detail_date_time` datetime NOT NULL,
  `login_detail_logout_date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`login_detail_id`)
) ENGINE=MyISAM AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `srinill_beach_resort`.`login_detail`
--

/*!40000 ALTER TABLE `login_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `login_detail` ENABLE KEYS */;


--
-- Table structure for table `srinill_beach_resort`.`room`
--

DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `room_no` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `room_type` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `room_status` tinyint(1) DEFAULT '1',
  `room_price` decimal(10,2) NOT NULL,
  `room_last_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`room_no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `srinill_beach_resort`.`room`
--

/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` (`room_no`,`room_type`,`room_status`,`room_price`,`room_last_update_time`) VALUES 
 ('101','Double',1,'13000.00','2026-01-15 09:30:21'),
 ('102','Single',1,'11000.00','2026-01-18 14:27:36'),
 ('103','Double',1,'15000.00','2026-01-17 16:25:50'),
 ('104','Double',1,'13000.00','2026-01-10 04:32:15'),
 ('105','Single',1,'8000.00','2026-01-10 06:30:12'),
 ('106','Single',1,'13000.00','2026-01-10 04:32:15'),
 ('107','Double',1,'13000.00','2026-01-10 04:32:15'),
 ('108','Double',1,'13000.00','2026-01-10 04:32:15'),
 ('109','Double',1,'13000.00','2026-01-10 04:32:15'),
 ('110','Bunker',1,'13000.00','2026-01-10 04:32:15');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;


--
-- Table structure for table `srinill_beach_resort`.`user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_status` tinyint(1) DEFAULT '1',
  `user_last_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_privilege` int(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `srinill_beach_resort`.`user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`,`username`,`password`,`user_status`,`user_last_update_time`,`user_privilege`) VALUES 
 (1,'nishan','2026',1,'2026-01-15 03:55:43',0),
 (2,'dilan','dilan2026',1,'2026-01-15 03:56:57',1),
 (3,'admin','123',1,'2026-01-15 03:57:55',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
