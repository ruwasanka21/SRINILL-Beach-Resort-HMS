-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 08, 2025 at 02:46 PM
-- Server version: 8.3.0
-- PHP Version: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `panorama_hotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_accounts`
--

DROP TABLE IF EXISTS `admin_accounts`;
CREATE TABLE IF NOT EXISTS `admin_accounts` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `admin_accounts`
--

INSERT INTO `admin_accounts` (`username`, `password`) VALUES
('tharindu', '123');

-- --------------------------------------------------------

--
-- Table structure for table `checkin_features`
--

DROP TABLE IF EXISTS `checkin_features`;
CREATE TABLE IF NOT EXISTS `checkin_features` (
  `id` int NOT NULL AUTO_INCREMENT,
  `checkin_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `feature_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `feature_id` (`feature_id`)
) ENGINE=MyISAM AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `checkin_features`
--

INSERT INTO `checkin_features` (`id`, `checkin_id`, `customer_id`, `feature_id`) VALUES
(85, 40, 48, 1),
(81, 39, 50, 4),
(80, 39, 50, 2),
(79, 39, 50, 1),
(78, 38, 45, 3),
(77, 38, 45, 2),
(76, 38, 45, 1),
(72, 36, 45, 4),
(71, 36, 45, 2),
(70, 36, 45, 1),
(86, 40, 48, 2),
(87, 40, 48, 4),
(88, 40, 48, 5),
(89, 46, 56, 2),
(90, 47, 57, 6);

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `gender` varchar(50) NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `NIC` varchar(12) NOT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `address` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `email_2` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customer_id`, `name`, `gender`, `email`, `NIC`, `phone`, `address`, `created_at`) VALUES
(56, 'Chirani', 'Female', 'chirani.silva@gmail.com', '200308111834', '0715758121', 'Colombo', '2025-05-04 20:18:19'),
(55, 'Meshan', 'Male', 'prashansamm200327@gmail.com', '20030811835', '0764315233', 'Puttalam', '2025-05-04 17:01:50'),
(54, 'Tharindu', 'Male', 'ruwasanka21@gmail.com', '200308111834', '0715758121', 'Moratuwa', '2025-05-04 16:44:45'),
(57, 'Meshan', 'Male', 'prashansamm200327@@gmail.com', '1234567890', '1234567890', 'sdfghj', '2025-05-06 03:12:37'),
(58, 'Sandun', 'Male', 'j@gmail.com', '89', '1234567890', 'k', '2025-05-06 03:15:46');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
CREATE TABLE IF NOT EXISTS `employees` (
  `employee_id` int NOT NULL,
  `NIC` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Age` int NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Phone` varchar(10) NOT NULL,
  `Email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Job_Role` varchar(20) NOT NULL,
  `Salary` varchar(20) NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employee_id`, `NIC`, `Name`, `Age`, `Address`, `Gender`, `Phone`, `Email`, `Job_Role`, `Salary`) VALUES
(3, '951234567V', 'Sanjeewa Gunaratne', 29, 'No. 22, Lake View Avenue, Gampaha', 'Male', '0759876543', 'sanjeewa.g@example.com', 'Chef', '120000.0'),
(2, '882451234V', 'Kamalani Herath', 36, 'No. 10, Kandy Road, Kurunegala', 'Male', '0712345678', 'kamalah@example.com', 'Chef', '95000.0'),
(1, '972341234V', 'Nimal Perera', 32, 'No. 45, Galle Road, Colombo 03', 'Female', '0771234567', 'nimalp@example.com', 'Chef', '85000.0'),
(4, '992358765V', 'Tharushi Silva', 26, 'No. 8, Beach Road, Negombo', 'Female', '0781239876', 'tharushis@example.com', 'Cashier', '78000.0'),
(5, '902456321V', 'Asela Jayasinghe', 41, 'No. 33, Temple Road, Matara', 'Male', '0767891234', 'asela.j@example.com', 'Chef', '110000.0'),
(6, '991234567V', 'Dinithi Rajapaksha', 28, 'No. 14, Flower Road, Colombo 07', 'Female', '0772345678', 'dinithi.r@example.com', 'Cleaner', '55000.0'),
(7, '861245678V', 'Ajith Fernando', 44, 'No. 52, Main Street, Nuwara Eliya', 'Male', '0723456789', 'ajithf@example.com', 'Cleaner', '48000.0'),
(8, '200045601234', 'Sachini Weerasinghe', 25, 'No. 18, High Level Road, Maharagama', 'Male', '0751234567', 'sachini.w@example.com', 'Chef', '70000.0'),
(9, '921245678V', 'Roshan Abeykoon', 38, 'No. 7, Hill Street, Kandy', 'Male', '0784567890', 'roshan.a@example.com', 'Cashier', '85000.0'),
(10, '199004512345', 'Isuru Tennakoon', 31, 'No. 5, Circular Road, Anuradhapura', 'Male', '0749876543', 'isurut@example.com', 'Room Helper', '82000.0');

-- --------------------------------------------------------

--
-- Table structure for table `employeetasks`
--

DROP TABLE IF EXISTS `employeetasks`;
CREATE TABLE IF NOT EXISTS `employeetasks` (
  `task_id` int NOT NULL AUTO_INCREMENT,
  `employee_id` varchar(10) DEFAULT NULL,
  `task_description` text,
  `assigned_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `dutyDate` varchar(50) NOT NULL,
  `dutyTime` varchar(50) NOT NULL,
  PRIMARY KEY (`task_id`),
  KEY `employee_id` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `employeetasks`
--

INSERT INTO `employeetasks` (`task_id`, `employee_id`, `task_description`, `assigned_date`, `dutyDate`, `dutyTime`) VALUES
(3, '1', 'Kitchen Head', '2025-05-08 06:59:35', '2025-05-08', '17:59:22'),
(4, '2', 'Breakfast ', '2025-05-08 08:04:00', '2025-05-08', '19:03:32');

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
CREATE TABLE IF NOT EXISTS `events` (
  `event_id` int NOT NULL AUTO_INCREMENT,
  `hallName` varchar(50) NOT NULL,
  `customer_id` int NOT NULL,
  `customer_name` varchar(30) NOT NULL,
  `event_name` varchar(100) DEFAULT NULL,
  `catergory` varchar(50) NOT NULL,
  `NoOfPeople` int NOT NULL,
  `event_date` date DEFAULT NULL,
  `description` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`event_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`event_id`, `hallName`, `customer_id`, `customer_name`, `event_name`, `catergory`, `NoOfPeople`, `event_date`, `description`, `created_at`) VALUES
(1, 'Araliya Garden Hall', 37, 'Kavindi', 'Birthday Function', 'comboBoxChanged', 400, '2025-03-28', 'Shen bday', '2025-03-27 21:07:47'),
(2, 'Araliya Garden Hall', 38, 'Tharindu', 'Wedding', 'comboBoxChanged', 250, '2025-03-31', 'Meshan & Kavindi', '2025-04-22 21:15:27'),
(3, 'Sudu Nelum Hall', 39, 'Thenuli', 'Wedding', 'comboBoxChanged', 400, '2025-03-28', 'lovely', '2025-03-27 21:23:38'),
(4, 'Siththara Hall', 40, 'Maleesha', 'Home Comming Function', 'comboBoxChanged', 350, '2025-03-22', 'kamal & amali wedding', '2025-03-27 21:26:17'),
(6, 'Ranweli Hall', 42, 'Pameesha', 'Thara', 'comboBoxChanged', 200, '2025-03-29', 'Thara', '2025-03-28 00:19:54'),
(7, 'Green Hall', 54, 'Tharindu', 'My Birthday', 'comboBoxChanged', 20, '2025-05-06', 'My Birthday', '2025-05-04 20:06:18'),
(8, 'Ranweli Hall', 60, 'Nimal', 'Wedding Reception', '', 200, '2025-06-15', 'Evening event with dinner', '2025-05-06 02:39:28'),
(9, 'Siththara Hall', 68, 'Suranga', 'Corporate Seminar', '', 75, '2025-07-10', 'Full-day seminar with lunch', '2025-05-06 02:39:28'),
(10, 'White Ballroom', 70, 'Lalith', 'Anniversary Party', '', 120, '2025-08-05', 'Evening celebration', '2025-05-06 02:39:28'),
(11, 'Ranweli Hall', 74, 'Nimal', 'Birthday Celebration', '', 60, '2025-06-25', 'Surprise birthday party', '2025-05-06 02:39:28'),
(12, 'Siththara Hall', 81, 'Suranga', 'Birthday Event', '', 90, '2025-09-12', 'Evening networking session', '2025-05-06 02:39:28');

-- --------------------------------------------------------

--
-- Table structure for table `expense`
--

DROP TABLE IF EXISTS `expense`;
CREATE TABLE IF NOT EXISTS `expense` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Des` varchar(100) NOT NULL,
  `LKR` decimal(10,2) NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=424 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `expense`
--

INSERT INTO `expense` (`ID`, `Name`, `Des`, `LKR`, `Date`) VALUES
(400, 'Room Service', 'Food and beverage in room', 1500.00, '2025-04-09 00:00:00'),
(401, 'Laundry', 'Clothing washing and pressing', 800.00, '2025-04-09 00:00:00'),
(402, 'Parking', 'Parking charges for vehicles', 300.00, '2025-04-29 00:00:00'),
(403, 'Housekeeping', 'Cleaning and upkeep of rooms', 1200.00, '2025-04-22 00:00:00'),
(404, 'Conference Room', 'Booking for meetings/events', 5000.00, '2025-04-09 00:00:00'),
(405, 'Spa', 'Spa services for guests', 2500.00, '2025-04-16 00:00:00'),
(406, 'Wi-Fi', 'Internet charges', 1000.00, '2025-03-25 00:00:00'),
(407, 'Minibar', 'Snacks and drinks from minibar', 600.00, '2025-03-27 00:00:00'),
(408, 'Transportation', 'Airport shuttle services', 1200.00, '2025-03-20 00:00:00'),
(409, 'Telephone', 'Charges for local and international calls', 450.00, '2025-05-03 00:00:00'),
(415, 'Security Torch', 'Buy inventory stock', 300.00, '2025-05-01 17:35:02'),
(414, 'Soup', 'Buy inventory stock', 5000.00, '2025-05-01 17:33:29'),
(418, 'AC Repair', 'Room No : 103', 5400.00, '2025-05-03 00:00:00'),
(419, 'Swimpool Cleaning', 'Cleaning', 7800.00, '2025-05-03 00:00:00'),
(420, 'New Reception table', 'Reception', 23400.00, '2025-05-03 00:00:00'),
(421, 'A4 Sheets Bundle', '', 1000.00, '2025-05-06 07:08:00'),
(422, 'A4 Sheets Bundle', '', 1000.00, '2025-05-06 19:26:42'),
(423, 'Soup', '', 1500.00, '2025-05-07 10:48:13');

-- --------------------------------------------------------

--
-- Table structure for table `features`
--

DROP TABLE IF EXISTS `features`;
CREATE TABLE IF NOT EXISTS `features` (
  `featureID` int NOT NULL,
  `featureName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `price` int NOT NULL,
  PRIMARY KEY (`featureID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `features`
--

INSERT INTO `features` (`featureID`, `featureName`, `description`, `price`) VALUES
(1, 'Free Wi-Fi', 'High-speed internet access in all rooms and public areas', 500),
(2, 'Breakfast Buffet', 'Complimentary breakfast buffet each morning', 2500),
(3, 'Swimming Pool', 'Access to outdoor or indoor pool', 250),
(4, 'Spa Services', 'Full-service spa with massage and treatments', 3500),
(5, 'Airport Shuttle', 'Transportation to and from the nearest airport', 12000),
(6, 'Gym Access', 'Gym Access', 500);

-- --------------------------------------------------------

--
-- Table structure for table `hallcheckin`
--

DROP TABLE IF EXISTS `hallcheckin`;
CREATE TABLE IF NOT EXISTS `hallcheckin` (
  `checkIn_id` int NOT NULL AUTO_INCREMENT,
  `reservation_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `customerName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `hall_Name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `reserved_date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Checked-IN',
  `checkInDate` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `checkOutDate` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`checkIn_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `hallcheckin`
--

INSERT INTO `hallcheckin` (`checkIn_id`, `reservation_id`, `customer_id`, `customerName`, `hall_Name`, `reserved_date`, `status`, `checkInDate`, `checkOutDate`) VALUES
(1, 17, 45, 'Tharindu', '2', '2025-04-15', '17', '2025-04-16', '2025-05-05'),
(2, 22, 54, 'Tharindu', 'White Ballroom', '2025-05-05', '22', '2025-05-05', '2025-05-05'),
(3, 17, 55, 'Meshan', 'Sudu Nelum Hall', '2025-04-15', '17', '2025-05-06', '2025-05-06');

-- --------------------------------------------------------

--
-- Table structure for table `hallreservation`
--

DROP TABLE IF EXISTS `hallreservation`;
CREATE TABLE IF NOT EXISTS `hallreservation` (
  `reservation_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `hallName` varchar(50) NOT NULL,
  `reserve_Date` varchar(100) NOT NULL,
  `checkInDate` varchar(100) NOT NULL,
  `price` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`reservation_id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hallreservation`
--

INSERT INTO `hallreservation` (`reservation_id`, `customer_id`, `hallName`, `reserve_Date`, `checkInDate`, `price`, `status`) VALUES
(17, 55, 'Sudu Nelum Hall', '2025-04-15', '2025-05-17', '25000', 'Booked'),
(12, 55, 'Green Hall', '2025-03-28', '2025-05-28', '25000', 'Booked'),
(18, 54, 'Ranweli Hall', '2025-05-05', '2025-05-06', '200000.00', 'Booked'),
(19, 54, 'Siththara Hall', '2025-05-05', '2025-05-07', '40000.00', 'Booked'),
(20, 54, 'Araliya Garden Hall', '2025-05-05', '2025-05-07', '120000.00', 'Booked'),
(21, 54, 'Sudu Nelum Hall', '2025-05-05', '2025-05-06', '50000.00', 'Booked'),
(22, 54, 'White Ballroom', '2025-05-05', '2025-05-06', '120000.00', 'Booked');

-- --------------------------------------------------------

--
-- Table structure for table `halls`
--

DROP TABLE IF EXISTS `halls`;
CREATE TABLE IF NOT EXISTS `halls` (
  `hall_id` int NOT NULL,
  `hall_name` varchar(100) DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `Availability` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`hall_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `halls`
--

INSERT INTO `halls` (`hall_id`, `hall_name`, `capacity`, `price`, `Availability`, `status`, `created_at`) VALUES
(5, 'Ranweli Hall', 2000, 200000.00, 'Reserved', 'Dirty', '2025-03-27 10:15:15'),
(4, 'Siththara Hall', 250, 40000.00, 'Available', 'Cleaned', '2025-03-27 09:07:19'),
(2, 'Araliya Garden Hall', 1000, 120000.00, 'Reserved', 'Dirty', '2025-03-26 11:23:56'),
(3, 'Sudu Nelum Hall', 500, 50000.00, 'Available', 'Cleaned', '2025-03-27 08:57:10'),
(6, 'White Ballroom', 500, 120000.00, 'Available', 'Cleaned', '2025-04-24 09:14:58'),
(7, 'Green Hall', 800, 65000.00, 'Available', 'Cleaned', '2025-04-24 09:15:50');

-- --------------------------------------------------------

--
-- Table structure for table `income`
--

DROP TABLE IF EXISTS `income`;
CREATE TABLE IF NOT EXISTS `income` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Des` varchar(100) NOT NULL,
  `LKR` decimal(10,2) NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=232 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `income`
--

INSERT INTO `income` (`ID`, `Name`, `Des`, `LKR`, `Date`) VALUES
(205, 'Hall Booking', 'Wedding reception', 150000.00, '2025-04-09 12:00:00'),
(206, 'Catering Services', 'Food and beverages for event', 85000.00, '2025-04-09 12:30:00'),
(207, 'Photography Package', 'Event photography', 45000.00, '2025-04-09 13:00:00'),
(224, 'Mini Bar', 'Room No : 101', 15000.00, '2025-05-03 00:00:00'),
(209, 'Sound & Lighting', 'Event sound and lighting setup', 35000.00, '2025-04-09 14:00:00'),
(210, 'DJ Services', 'DJ entertainment', 25000.00, '2025-03-29 14:30:00'),
(211, 'Projector Rental', 'Projector and screen rental', 10000.00, '2025-03-26 15:00:00'),
(212, 'Advance Payment', 'Partial booking fee', 50000.00, '2025-03-26 15:30:00'),
(213, 'Security Fee', 'Security service for event', 12000.00, '2025-04-09 16:00:00'),
(214, 'Cleaning Charges', 'Post-event cleanup', 8000.00, '2025-05-03 16:30:00'),
(225, 'Extra Glasses', 'Customer ID : 25', 8600.00, '2025-05-03 00:00:00'),
(222, 'Reservation ID : 108', 'Room Income', 87318.00, '2025-05-01 17:30:06'),
(223, 'Reservation ID : 106', 'Room Income', 129591.00, '2025-05-01 17:58:59'),
(226, '1L Black Label Bottle', 'Liquir', 17000.00, '2025-05-03 00:00:00'),
(227, 'Reservation ID : 14', 'Room Income', 36900.00, '2025-05-04 21:59:05'),
(228, 'Reservation ID : 22', 'Room Income', 108000.00, '2025-05-05 02:39:02'),
(229, 'Reservation ID : 22', 'Room Income', 108000.00, '2025-05-05 02:39:10'),
(230, 'Reservation ID : 25', 'Room Income', 20000.00, '2025-05-06 07:51:41'),
(231, 'Reservation ID : 26', 'Room Income', 10700.00, '2025-05-06 08:44:19');

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
CREATE TABLE IF NOT EXISTS `inventory` (
  `itemID` varchar(20) NOT NULL,
  `itemName` varchar(30) NOT NULL,
  `qty` int NOT NULL,
  `itemCategory` varchar(40) NOT NULL,
  `supplier` varchar(50) NOT NULL,
  `description` varchar(100) NOT NULL,
  `UpdatedDate` date NOT NULL,
  PRIMARY KEY (`itemID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`itemID`, `itemName`, `qty`, `itemCategory`, `supplier`, `description`, `UpdatedDate`) VALUES
('ITM011', 'Paint', 19, 'Maintenance and Facilities', 'Elite Maintenance Pvt Ltd', 'Maltilack Paints 4L', '2025-04-26'),
('ITM001', 'Soup', 45, 'Housekeeping', 'Uniliver Sri Lanka PLC', 'Baby Soup Pack', '2025-05-07'),
('ITM010', 'Toilet Paper Rolls', 110, 'Housekeeping', 'Crystal Clean Services', '2-ply toilet paper (12 rolls/pack)', '2025-03-29'),
('ITM009', 'First Aid Kit', 28, 'Housekeeping', 'Elite Maintenance Pvt Ltd', 'Standard workplace first aid supplies', '2025-04-02'),
('ITM007', 'Key Card Holders', 138, 'Front Desk / Reception', 'Elite Maintenance Pvt Ltd', 'Plastic RFID card holders', '2025-04-02'),
('ITM006', 'Bath Towels', 110, 'Housekeeping', 'Crystal Clean Services', '100% cotton bath towels (70x140cm)', '2025-04-02'),
('ITM005', 'Security Torch', 14, 'Security', 'Ceylon Security Solutions', 'Heavy-duty rechargeable flashlight', '2025-05-01'),
('ITM003', 'Visitor Log Book', 36, 'Front Desk / Reception', 'Premium Linens & Supplies', 'Hardbound visitor registration book', '2025-03-30'),
('ITM002', 'A4 Sheets Bundle', 65, 'Administrative', 'Premium Linens & Supplies', 'ProMate A4 sheet bundle', '2025-04-02'),
('ITM012', 'Anker Milk', 20, 'Food & Beverage', 'Keels PLC', 'Anker Milk 400g', '2025-04-26'),
('ITM014', 'Coca-Cola', 26, 'Front Desk / Reception', 'Cargils Food City PLC', '1L Bottles', '2025-04-26');

-- --------------------------------------------------------

--
-- Table structure for table `login_accounts`
--

DROP TABLE IF EXISTS `login_accounts`;
CREATE TABLE IF NOT EXISTS `login_accounts` (
  `ID` varchar(20) NOT NULL,
  `type` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `login_accounts`
--

INSERT INTO `login_accounts` (`ID`, `type`, `username`, `password`) VALUES
('A01', 'Admin', 'meshan', '123'),
('R01', 'Reception', 'tharindu', '123'),
('A02', 'Admin', 'kavindi', '123');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
CREATE TABLE IF NOT EXISTS `payments` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `invoiceNo` int NOT NULL,
  `reservation_id` int DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `payment_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_method` enum('Cash','Credit Card','Bank Transfer') DEFAULT NULL,
  `status` enum('Pending','Completed','Failed') DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `reservation_id` (`reservation_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`payment_id`, `invoiceNo`, `reservation_id`, `amount`, `payment_date`, `payment_method`, `status`) VALUES
(1, 1001, 101, 9450.00, '2025-04-24 00:05:00', 'Cash', 'Completed'),
(2, 1002, 101, 9450.00, '2025-04-24 00:15:37', 'Cash', 'Completed'),
(3, 1003, 101, 5250.00, '2025-04-24 00:25:32', 'Cash', 'Completed'),
(4, 1004, 102, 9180.00, '2025-04-24 03:03:39', 'Cash', 'Completed'),
(5, 1005, 103, 11250.00, '2025-04-24 03:21:37', 'Cash', 'Completed'),
(6, 1006, 104, 9810.00, '2025-04-24 03:25:20', 'Cash', 'Completed'),
(7, 1007, 107, 8680.00, '2025-04-24 03:55:48', 'Cash', 'Completed'),
(8, 1008, 102, 9180.00, '2025-04-24 10:06:42', 'Cash', 'Completed'),
(9, 1009, 108, 9280.00, '2025-04-24 15:40:31', 'Cash', 'Completed'),
(10, 1010, 102, 9690.00, '2025-05-01 11:42:43', 'Cash', 'Completed'),
(11, 1011, 104, 75537.00, '2025-05-01 11:57:14', 'Cash', 'Completed'),
(12, 1012, 108, 87318.00, '2025-05-01 12:00:06', 'Cash', 'Completed'),
(13, 1013, 106, 129591.00, '2025-05-01 12:28:59', 'Cash', 'Completed'),
(14, 1014, 22, 108000.00, '2025-05-04 21:09:02', 'Cash', 'Completed'),
(15, 1015, 26, 10700.00, '2025-05-06 03:14:19', 'Cash', 'Completed');

-- --------------------------------------------------------

--
-- Table structure for table `reception_accounts`
--

DROP TABLE IF EXISTS `reception_accounts`;
CREATE TABLE IF NOT EXISTS `reception_accounts` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `reception_accounts`
--

INSERT INTO `reception_accounts` (`username`, `password`) VALUES
('meshan', '123');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
CREATE TABLE IF NOT EXISTS `reservations` (
  `reservation_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int DEFAULT NULL,
  `reservation_type` enum('Room','Hall') NOT NULL,
  `room_id` int DEFAULT NULL,
  `hall_id` int DEFAULT NULL,
  `reservation_date` date DEFAULT NULL,
  `check_in_date` date DEFAULT NULL,
  `check_out_date` date DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `status` enum('Pending','Confirmed','Cancelled') DEFAULT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `customer_id` (`customer_id`),
  KEY `room_id` (`room_id`),
  KEY `hall_id` (`hall_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roomcheckin`
--

DROP TABLE IF EXISTS `roomcheckin`;
CREATE TABLE IF NOT EXISTS `roomcheckin` (
  `checkIn_id` int NOT NULL AUTO_INCREMENT,
  `reservation_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `customerName` varchar(20) NOT NULL,
  `room_id` int NOT NULL,
  `reserved_date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` varchar(20) NOT NULL,
  `checkInDate` varchar(50) DEFAULT NULL,
  `checkOutDate` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`checkIn_id`)
) ENGINE=MyISAM AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `roomcheckin`
--

INSERT INTO `roomcheckin` (`checkIn_id`, `reservation_id`, `customer_id`, `customerName`, `room_id`, `reserved_date`, `status`, `checkInDate`, `checkOutDate`) VALUES
(45, 23, 54, 'Tharindu', 101, '2025-05-04', 'Checked-OUT', '2025-05-04', '2025-05-04'),
(44, 24, 55, 'Meshan', 104, '2025-05-04', 'Checked-OUT', '2025-05-04', '2025-05-04'),
(46, 25, 56, 'Chirani', 101, '2025-05-05', 'Checked-OUT', '2025-05-06', '2025-05-06'),
(47, 26, 57, 'Meshan', 102, '2025-05-06', 'Checked-OUT', '2025-05-06', '2025-05-06');

-- --------------------------------------------------------

--
-- Table structure for table `roomreservation`
--

DROP TABLE IF EXISTS `roomreservation`;
CREATE TABLE IF NOT EXISTS `roomreservation` (
  `reservation_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `room_id` int NOT NULL,
  `reserve_Date` date NOT NULL,
  `checkInDate` date NOT NULL,
  `checkOutDate` date NOT NULL,
  `price` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`reservation_id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `roomreservation`
--

INSERT INTO `roomreservation` (`reservation_id`, `customer_id`, `room_id`, `reserve_Date`, `checkInDate`, `checkOutDate`, `price`, `status`) VALUES
(22, 50, 103, '2025-05-04', '2025-05-05', '2025-05-06', '120000.00', 'Booked'),
(27, 58, 103, '2025-05-06', '2025-05-28', '2025-05-29', '22500.00', 'Booked');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
CREATE TABLE IF NOT EXISTS `rooms` (
  `room_no` int NOT NULL,
  `category` varchar(50) DEFAULT NULL,
  `availability` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `bed_type` varchar(20) DEFAULT NULL,
  `floor` int DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `ac` tinyint(1) DEFAULT NULL,
  `fan` tinyint(1) DEFAULT NULL,
  `tv` tinyint(1) DEFAULT NULL,
  `mini_bar` tinyint(1) DEFAULT NULL,
  `balcony_view` tinyint(1) DEFAULT NULL,
  `wifi_access` tinyint(1) DEFAULT NULL,
  `attached_bathroom` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`room_no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`room_no`, `category`, `availability`, `status`, `bed_type`, `floor`, `price`, `ac`, `fan`, `tv`, `mini_bar`, `balcony_view`, `wifi_access`, `attached_bathroom`) VALUES
(110, 'Standard', 'Available', 'Clean', 'Twin', 1, 10950.00, 0, 1, 1, 0, 0, 1, 1),
(109, 'Suite', 'Available', 'Clean', 'King', 3, 29800.00, 1, 1, 1, 1, 1, 1, 1),
(108, 'Deluxe', 'Available', 'Clean', 'Queen', 2, 12600.00, 1, 1, 1, 1, 1, 1, 1),
(107, 'Standard', 'Available', 'Dirty', 'Twin', 1, 10850.00, 0, 1, 0, 0, 0, 0, 0),
(106, 'Suite', 'Available', 'Clean', 'King', 3, 18700.00, 1, 1, 1, 0, 1, 0, 1),
(105, 'Deluxe', 'Available', 'Clean', 'Queen', 2, 11400.00, 1, 1, 1, 1, 1, 1, 1),
(104, 'Standard', 'Available', 'Clean', 'Twin', 1, 10900.00, 0, 1, 1, 0, 0, 0, 1),
(103, 'Suite', 'Ocupied', 'Clean', 'King', 2, 22500.00, 1, 1, 1, 1, 1, 1, 1),
(101, 'Deluxe', 'Available', 'Clean', 'King', 1, 17500.00, 1, 1, 1, 1, 0, 1, 1),
(102, 'Standard', 'Available', 'Clean', 'Queen', 1, 10200.00, 0, 1, 1, 0, 0, 1, 1),
(111, 'Single', 'Available', 'Clean', 'King', 4, 9500.00, 0, 1, 1, 0, 0, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `suporders`
--

DROP TABLE IF EXISTS `suporders`;
CREATE TABLE IF NOT EXISTS `suporders` (
  `billNo` varchar(20) NOT NULL,
  `itemName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `itemID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Category` varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `supName` varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `qty` int NOT NULL,
  `itemPrice` decimal(10,2) NOT NULL,
  `description` varchar(100) NOT NULL,
  `Total` decimal(10,2) NOT NULL,
  `paidStatus` varchar(10) NOT NULL,
  `method` varchar(20) NOT NULL,
  `paid` decimal(10,2) NOT NULL,
  `balance` decimal(10,2) NOT NULL,
  `ExpireDate` date NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`billNo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `suporders`
--

INSERT INTO `suporders` (`billNo`, `itemName`, `itemID`, `Category`, `supName`, `qty`, `itemPrice`, `description`, `Total`, `paidStatus`, `method`, `paid`, `balance`, `ExpireDate`, `Date`) VALUES
('2385', 'Visitor Log Book', 'ITM003', 'Front Desk / Reception', 'Premium Linens & Supplies', 10, 400.00, '', 40000.00, 'Unpaid', 'null', 0.00, 0.00, '2025-05-24', '2025-04-25 18:02:35'),
('1031', 'Key Card Holders', 'ITM007', 'Front Desk / Reception', 'Elite Maintenance Pvt Ltd', 10, 10.00, '', 500.00, 'Paid', 'Cash', 500.00, 20.00, '2025-05-10', '2025-04-02 10:24:12'),
('1028', 'First Aid Kit', 'ITM009', 'Security', 'Elite Maintenance Pvt Ltd', 20, 600.00, '', 12000.00, 'Paid', 'Cash', 15000.00, 3000.00, '2025-04-24', '2025-04-02 10:14:23'),
('1026', 'Bath Towels', 'ITM006', 'Housekeeping', 'Crystal Clean Services', 20, 55.00, '', 1100.00, 'Paid', 'Cash', 150.00, 400.00, '2026-05-13', '2025-04-02 10:09:45'),
('1023', 'Security Torch', 'ITM005', 'Security', 'Ceylon Security Solutions', 8, 650.00, '', 5200.00, 'Paid', 'Cash', 6000.00, 800.00, '2025-05-31', '2025-04-02 09:58:14'),
('1022', 'Visitor Log Book', 'ITM003', 'Front Desk / Reception', 'Premium Linens & Supplies', 10, 165.00, '', 1650.00, 'Unpaid', 'null', 0.00, 0.00, '2025-08-13', '2025-04-02 09:57:41'),
('8465', 'Bath Towels', 'ITM006', 'Housekeeping', 'Crystal Clean Services', 10, 650.00, '', 6500.00, 'Unpaid', 'null', 0.00, 0.00, '2026-05-13', '2025-04-26 16:20:09'),
('5642', 'A4 Sheets Bundle', 'ITM002', 'Administrative', 'Premium Linens & Supplies', 12, 850.00, '', 3450.00, 'Paid', 'Cash', 3450.00, 0.00, '2026-05-13', '2025-04-25 18:01:38'),
('4253', 'Soup', 'ITM001', 'Housekeeping', 'Lanka Hospitality Solutions', 12, 65.00, '', 650.00, 'Paid', 'Cash', 1000.00, 300.00, '2026-05-13', '2025-04-25 18:00:58'),
('3020', 'Security Torch', 'ITM005', 'Security', 'Ceylon Security Solutions', 7, 200.00, '', 1000.00, 'Paid', 'Cash', 1000.00, 2.00, '2026-05-13', '2025-04-25 16:25:27'),
('456', 'A4 Sheets Bundle', 'ITM002', 'Administrative', 'Premium Linens & Supplies', 8, 9.00, '', 800.00, 'Paid', 'Cash', 800.00, 0.00, '2026-05-13', '2025-04-09 22:42:26'),
('1234', 'Anker Milk', 'ITM012', 'Food & Beverage', 'Keels PLC', 5, 900.00, '', 3000.00, 'Paid', 'Cash', 3000.00, 8.00, '2025-05-03', '2025-04-09 22:41:08'),
('56', 'Anker Milk', 'ITM012', 'Food & Beverage', 'Keels PLC', 5, 900.00, '', 3000.00, 'Paid', 'Cash', 3000.00, 0.00, '2025-05-01', '2025-04-09 22:40:52'),
('1032', 'Lifeboy Shampoo', 'ITM014', 'Housekeeping', 'Cargils Food City PLC', 8, 435.00, '', 3480.00, 'Paid', 'Cash', 3500.00, 20.00, '2026-05-13', '2025-04-02 11:01:33'),
('5489', 'Security Torch', 'ITM005', 'Security', 'Ceylon Security Solutions', 10, 1450.00, '', 14500.00, 'Paid', 'Cash', 15000.00, 500.00, '2026-05-13', '2025-04-25 18:03:16'),
('6489', 'Toilet Paper Rolls', 'ITM010', 'Housekeeping', 'Crystal Clean Services', 10, 50.00, '', 500.00, 'Unpaid', 'null', 0.00, 0.00, '2026-05-13', '2025-04-25 18:04:17'),
('9462', 'Coca-Cola', 'ITM014', 'Front Desk / Reception', 'Cargils Food City PLC', 10, 400.00, '', 4000.00, 'Paid', 'Cash', 4000.00, 0.00, '2026-05-13', '2025-04-28 11:38:40'),
('4832', 'Paint', 'ITM011', 'Maintenance and Facilities', 'Elite Maintenance Pvt Ltd', 10, 1000.00, '', 10000.00, 'Unpaid', 'null', 0.00, 0.00, '2026-05-13', '2025-05-01 17:31:36'),
('1568', 'Soup', 'ITM001', 'Housekeeping', 'Lanka Hospitality Solutions', 20, 500.00, '', 2000.00, 'Paid', 'Cash', 5000.00, 3000.00, '2026-05-13', '2025-05-01 17:33:29'),
('1645', 'Security Torch', 'ITM005', 'Security', 'Ceylon Security Solutions', 1, 300.00, '', 300.00, 'Paid', 'Cash', 500.00, 200.00, '2026-05-13', '2025-05-01 17:35:02'),
('7316', 'A4 Sheets Bundle', 'ITM002', 'Administrative', 'Premium Linens & Supplies', 10, 100.00, '', 1000.00, 'Unpaid', 'null', 0.00, 0.00, '2026-05-13', '2025-05-06 19:26:42'),
('4656', 'Soup', 'ITM001', 'Housekeeping', 'Uniliver Sri Lanka PLC', 20, 75.00, '', 1500.00, 'Paid', 'Cash', 2000.00, 500.00, '2025-05-31', '2025-05-07 10:48:13');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE IF NOT EXISTS `supplier` (
  `supID` varchar(10) NOT NULL,
  `supName` varchar(50) NOT NULL,
  `supCategory` varchar(50) NOT NULL,
  `supAddress` varchar(50) NOT NULL,
  `supPhone` varchar(10) NOT NULL,
  `supEmail` varchar(30) NOT NULL,
  `supBank` varchar(30) NOT NULL,
  `supBankAccNo` varchar(40) NOT NULL,
  `AddedDate` date NOT NULL,
  PRIMARY KEY (`supID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`supID`, `supName`, `supCategory`, `supAddress`, `supPhone`, `supEmail`, `supBank`, `supBankAccNo`, `AddedDate`) VALUES
('SUP08', 'Tropical Leisure Recreation', 'Guest Service and Recreation', 'Nuwara Eliya, Sri Lanka', '0118901230', 'prashansamm200327@gmail.com', 'Peoples Bank', '8901234567', '2025-03-21'),
('SUP09', 'Premium Linens & Supplies', 'Housekeeping', 'Kurunegala, Sri Lanka', '0118901235', 'prashansamm200327@gmail.com', 'HNB Bank', '9012345678', '2025-03-21'),
('SUP07', 'Hotel Management Experts', 'Administrative', 'Matara, Sri Lanka', '0138901230', 'prashansamm200327@gmail.com', 'Commercial Bank', '7890123456', '2025-03-21'),
('SUP06', 'Ceylon Security Solutions', 'Security', 'Jaffna, Sri Lanka', '0328901230', 'prashansamm200327@gmail.com', 'Sampath Bank', '6789012345', '2025-03-21'),
('SUP05', 'Uniliver Sri Lanka PLC', 'Guest Amenities', 'Colombo 07, Sri Lanka', '0348901230', 'prashansamm200327@gmail.com', 'Selan Bank', '5678901234', '2025-03-21'),
('SUP04', 'Elite Maintenance Pvt Ltd', 'Maintenance and Facilities', 'Negombo, Sri Lanka', '0318901230', 'prashansamm200327@gmail.com', 'BOC Bank', '4567890123', '2025-03-21'),
('SUP12', 'Cargils Food City PLC', 'Food & Beverage', 'Colombo 11, Sri Lanka', '0112345432', 'prashansamm200327@gmail.com', 'HNB Bank', '6767890', '2025-04-02'),
('SUP02', 'Crystal Clean Services', 'Housekeeping', 'Kandy, Sri Lanka', '0518901238', 'prashansamm200327@gmail.com', 'Peoples Bank', '2345678901', '2025-03-21'),
('SUP01', 'Lanka Hospitality Solutions', 'Front Desk / Reception', 'Colombo 03, Sri Lanka', '0158901235', 'prashansamm200327@gmail.com', 'Commercial Bank', '1234567890', '2025-03-21'),
('SUP10', 'Gourmet Hospitality Distributors', 'Food & Beverage', 'Batticaloa, Sri Lanka', '0178901230', 'prashansamm200327@gmail.com', 'BOC Bank', '0123456789', '2025-03-21'),
('SUP11', 'Keels PLC', 'Food & Beverage', 'Colombo 03, Sri Lanka', '0114356768', 'prashansamm200327@gmail.com', 'HNB Bank', '1234567890', '2025-04-02');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
